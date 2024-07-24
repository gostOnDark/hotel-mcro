package com.company.hotel.admin.utils;

import tk.mybatis.mapper.genid.GenId;

import java.util.UUID;

public class UUIdGenId implements GenId {
    @Override
    public Object genId(String s, String s1) {
        return UUID.randomUUID().toString();
    }
}
