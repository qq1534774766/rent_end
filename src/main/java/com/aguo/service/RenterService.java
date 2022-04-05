package com.aguo.service;

import com.aguo.entity.UUser;
import com.aguo.entity.vol.RenterItemVol;

import java.util.List;

public interface RenterService {
    List<RenterItemVol> listRenterItemVol();
    Boolean addRenter(UUser uUser);
    Boolean deleteRenterById(Integer userId);
}
