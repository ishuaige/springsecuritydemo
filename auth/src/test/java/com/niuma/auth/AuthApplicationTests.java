package com.niuma.auth;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.niuma.ucenter.mapper.UserMapper;
import com.niuma.ucenter.model.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class AuthApplicationTests {
    @Resource
    UserMapper userMapper;

    @Test
    void contextLoads() {
    }

    @Test
    public void testsql(){
        List<User> users = userMapper.selectList(new QueryWrapper<>());
        System.out.println(users);
    }


}
