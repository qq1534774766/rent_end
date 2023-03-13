package com.aguo;

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

    @Test
    void addUser() {
//        System.out.println(rentingService.houseState(1, 1));
//        System.out.println(rentingService.houseOneState( 2));
//        List<RoomItemVol> list = archiRRoomService.listRoomItemVol();
//        System.out.println(list);
        System.out.println(rentingService.renterRentState(2));

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
