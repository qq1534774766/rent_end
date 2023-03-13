package com.aguo.service;

import com.aguo.entity.UUser;
import com.aguo.entity.vol.RenterItemVol;
import com.aguo.entity.vol.UUserVol;
import com.aguo.vo.PageParams;

import java.util.List;

public interface RenterService {
    List<RenterItemVol> listRenterItemVol(PageParams pageParams);
    Boolean addRenter(UUser uUser);
    Boolean deleteRenterById(Integer userId);

    Integer getTotalRenter();

    List<UUserVol> listUUser();
}
