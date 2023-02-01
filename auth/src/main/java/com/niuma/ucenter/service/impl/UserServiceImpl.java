package com.niuma.ucenter.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.niuma.ucenter.model.domain.User;
import com.niuma.ucenter.service.UserService;
import com.niuma.ucenter.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author niumazlb
* @description 针对表【user】的数据库操作Service实现
* @createDate 2023-01-30 12:25:38
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




