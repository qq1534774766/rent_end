package com.aguo.dao;

import com.aguo.entity.Renting;
import com.aguo.entity.vol.RentingVol;
import com.aguo.vo.RentingWeek;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 租房关系表(Renting)表数据库访问层
 *
 * @author makejava
 * @since 2021-11-20 16:27:43
 */
@Mapper
@Repository
public interface RentingDao extends BaseMapper<Renting> {
    /**
     * 所有的房屋状态
     *
     * @return
     */
    List<RentingVol> queryAllRentingVol();

    /**
     * 通过id查房屋状态
     *
     * @return
     */
    @Deprecated
    RentingVol queryRentingVol(@Param("roomId") Integer roomId);

    int deleteByPrimaryKey(Long id);

    int insertSelective(Renting record);

    Renting selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Renting record);

    int updateByPrimaryKey(Renting record);


    List<RentingWeek> rentingWeek();
}

