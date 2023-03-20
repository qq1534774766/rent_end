package com.aguo;

import com.aguo.dao.ArchiRRoomDao;
import com.aguo.dao.RentingDao;
import com.aguo.dao.UUserVolDao;
import com.aguo.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RentEndApplicationTests {

//    @Autowired
//    UserMapper userMapper;
    @Autowired
    UUserService userService;
    @Autowired
    private ArchiBBuildingService archiBBuildingService;
    @Autowired
    private ArchiRRoomService archiRRoomService;
    @Autowired
    private RentingService rentingService;

    @Autowired
    private RenterService renterService;
    @Autowired
    private UUserVolDao uUserVolDao;

    @Autowired
    private RentingDao rentingDao;

    @Autowired
    private ArchiRRoomDao archiRRoomDao;


    @Test
    void addUser() {
//        List<RentingVol> rentingVols = rentingDao./*queryRentingVol("101");
//        System.out.println(rentingVols.size());*/

    }

    @Test
    void test2() {
//        System.out.println(rentingService.houseState(1, 1));
//        System.out.println(rentingService.houseOneState( 2));
//        List<RoomItemVol> list = archiRRoomService.listRoomItemVol();
//        renterService.listRenterItemVol();
//        System.out.println(list);
//        System.out.println(uUserVolDao.selectById(1));

    }




}
