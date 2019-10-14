package com.baizhi.mapper;

import com.baizhi.entity.User;
import com.baizhi.entity.testmape;

import java.util.List;

public interface UserMapper {
    public List<User> selectAll();

    public List<testmape> select();

    public List<Integer> count();
}
