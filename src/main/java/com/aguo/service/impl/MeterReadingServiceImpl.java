package com.aguo.service.impl;

import com.aguo.dao.MeterReadingDao;
import com.aguo.entity.ArchiRRoom;
import com.aguo.entity.MeterReading;
import com.aguo.enums.ExcelHeaderEnum;
import com.aguo.service.ArchiRRoomService;
import com.aguo.service.MeterReadingService;
import com.aguo.vo.ApiResponse;
import com.aguo.vo.MeterReadingExcelVo;
import com.aguo.vo.MeterReadingVo;
import com.aguo.vo.params.MeterReadingParam;
import com.aguo.vo.params.PageParam;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.*;

import static com.aguo.enums.ExcelHeaderEnum.*;

/**
 * @author Administrator
 * @description 针对表【meter_reading】的数据库操作Service实现
 * @createDate 2023-03-27 10:18:09
 */
@Service
public class MeterReadingServiceImpl extends ServiceImpl<MeterReadingDao, MeterReading> implements MeterReadingService {

    @Autowired
    private MeterReadingDao meterReadingDao;

    @Autowired
    private ArchiRRoomService archiRRoomService;

    @Override
    public ApiResponse queryMeterReading(PageParam pageParam, MeterReadingParam meterReadingParam) {
        Integer year = null, month = null;
        if (ObjectUtils.isNotEmpty(meterReadingParam.getReadingDate())) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(meterReadingParam.getReadingDate());
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH) + 1;
        }
        List<MeterReadingVo> list = meterReadingDao.queryMeterReading(
                (pageParam.getPage() - 1) * pageParam.getPageSize(),
                pageParam.getPageSize(),
                meterReadingParam.getBuildingName(), meterReadingParam.getHouseNumber(),
                year, month
        );
        Long count = meterReadingDao.countQueryMeterReading(meterReadingParam.getBuildingName(), meterReadingParam.getHouseNumber(),
                year, month);

        HashMap<String, Object> map = new HashMap<>(100);
        map.put("list", list);
        map.put("count", count);
        return ApiResponse.success(map);
    }

    @Override
    public ApiResponse addMeterReading(MeterReading meterReading) {
        try {
            ArchiRRoom room = archiRRoomService.getById(meterReading.getRoomId());
            if (room == null) {
                throw new RuntimeException("房屋不存在");
            }
            if (ObjectUtils.isEmpty(meterReading.getReadingDate())) {
                meterReading.setReadingDate(new Date());
            }
            if (ObjectUtils.isEmpty(meterReading.getWaterReading()) || ObjectUtils.isEmpty(meterReading.getElectricityReading())) {
                return ApiResponse.error("房屋水电记录不能为空");

            }
        } catch (Exception e) {
            return ApiResponse.error("房屋不存在");
        }
        int insert = meterReadingDao.insert(meterReading);
        return ApiResponse.booleanResponse(insert > 0, "水电记录插入失败");
    }

    @Override
    public ApiResponse updateMeterReading(MeterReading meterReading) {
        int i = meterReadingDao.updateById(meterReading);
        return ApiResponse.booleanResponse(i > 0, "水电记录更新失败");
    }

    @Override
    public ApiResponse deleteMeterReading(Integer meterReadingId) {
        int i = meterReadingDao.deleteById(meterReadingId);
        return ApiResponse.booleanResponse(i > 0, "水电记录删除失败");
    }

    @Override
    public void downloadExcelTemplate(Integer buildingId, Date readingDate, HttpServletResponse response) throws IOException {
        if (ObjectUtils.isEmpty(readingDate) || ObjectUtils.isEmpty(readingDate)) {
            throw new IOException("楼盘名与读取日期不能为空");
        }
        // Define the headers
        ExcelHeaderEnum[] headers = {BUILDING_ID, BUILDING_NAME, ROOM_ID, HOUSE_NUMBER, WATER_READING, ELECTRICITY_READING};
        // Fetch the data from the database
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(readingDate);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        List<MeterReadingExcelVo> data = meterReadingDao.fetchMeterReadingExcelVo(buildingId, year, month, day);
        data.sort(Comparator.comparing(MeterReadingExcelVo::getHouseNumber));
        if (data.isEmpty()) {
            throw new IOException("该楼房不存在任何房屋");
        }

        // Create a new workbook
        Workbook workbook = new XSSFWorkbook();

        // Create a new sheet
        String workbookName = data.get(0).getBuildingName() + "(" + year + "-" + month + "-" + day + ")";
        Sheet sheet = workbook.createSheet(workbookName);

        // Create a new cell style
        CellStyle cellStyle = workbook.createCellStyle();

        // Resize all columns to fit the content size
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        for (int i = 0; i < data.size(); i++) {
            sheet.setColumnWidth(i, 10 * 256);
            sheet.autoSizeColumn(i);
        }

        // Create a header row
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i].getDescription());
            // Apply the cell style
            cell.setCellStyle(cellStyle);
        }

        // Create data rows
        int rowNum = 1;
        for (MeterReadingExcelVo item : data) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(item.getBuildingId());
            row.createCell(1).setCellValue(item.getBuildingName());
            row.createCell(2).setCellValue(item.getRoomId());
            row.createCell(3).setCellValue(item.getHouseNumber());
            if (item.getWaterReading() == null) {
                row.createCell(4).setCellValue("");
            } else {
                row.createCell(4).setCellValue(item.getWaterReading());
            }
            if (item.getElectricityReading() == null) {
                row.createCell(5).setCellValue("");
            } else {
                row.createCell(5).setCellValue(item.getElectricityReading());
            }
            for (int i = 0; i < headers.length; i++) {
                // Apply the cell style
                row.getCell(i).setCellStyle(cellStyle);
            }
        }


        // Set the content type and header
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename*=UTF-8''" + URLEncoder.encode(workbookName, "UTF-8") + ".xlsx");

        // Write the workbook to the response output stream
        OutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();

    }

}
