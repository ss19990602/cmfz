package com.baizhi.serviceimpl;

import com.baizhi.entity.testmape;
import com.baizhi.mapper.UserMapper;
import com.baizhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<testmape> select() {
        List<testmape> select = userMapper.select();
        return select;
    }

    @Override
    public List<Integer> count() {
        List<Integer> count = userMapper.count();
        Collections.reverse(count);
        return count;
    }
}
