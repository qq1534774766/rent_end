package com.aguo.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.aguo.entity.URole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import javax.management.relation.Role;
import java.util.List;

/**
 * 角色表(URole)表数据库访问层
 *
 * @author makejava
 * @since 2021-11-11 13:37:00
 */
@Mapper
@Repository
public interface URoleDao extends BaseMapper<URole> {


}

