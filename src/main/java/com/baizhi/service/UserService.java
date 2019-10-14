package com.baizhi.service;

import com.baizhi.entity.testmape;

import java.util.List;

public interface UserService {
    public List<testmape> select();

    public List<Integer> count();
}
