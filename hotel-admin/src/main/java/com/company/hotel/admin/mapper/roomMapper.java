package com.company.hotel.admin.mapper;

import com.company.hotel.admin.entity.room;

public interface roomMapper {
    int deleteByPrimaryKey(String id);

    int insert(room record);

    int insertSelective(room record);

    room selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(room record);

    int updateByPrimaryKey(room record);
}