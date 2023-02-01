package com.niuma.ucenter.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.niuma.ucenter.mapper.UserMapper;
import com.niuma.ucenter.model.domain.User;
import com.niuma.ucenter.model.dto.AuthParamsDto;
import com.niuma.ucenter.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 账号密码认证,service 注入bean必须按照规定命名，保证在 UserDetailsServiceImpl 中可以通过 ioc拿到
 * 如果需要实现其他认证方法，可以仿照该方法
 *
 * @author niuma
 * @create 2023-01-30 11:06
 */
@Slf4j
@Service("password_authservice")
public class PasswordAuthServiceImpl implements AuthService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

//    @Autowired
//    CheckCodeClient checkCodeClient; 验证码服务客户端

    //实现账号和密码认证
    @Override
    public User execute(AuthParamsDto authParamsDto) {

//        //得到验证码
//        String checkcode = authParamsDto.getCheckcode();
//        String checkcodekey = authParamsDto.getCheckcodekey();
//        if(StringUtils.isBlank(checkcodekey) || StringUtils.isBlank(checkcode)){
//            throw new RuntimeException("验证码为空");
//
//        }
//
//        //校验验证码,请求验证码服务进行校验
//        Boolean result = checkCodeClient.verify(checkcodekey, checkcode);
//        if(result==null || !result){
//            throw new RuntimeException("验证码错误");
//        }

        //账号
        String username = authParamsDto.getUsername();
        //从数据库查询用户信息
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getName, username));
        if (user == null) {
            //账号不存在
            throw new RuntimeException("账号不存在");
        }
        //比对密码
        String passwordDB = user.getPassword();//正确的密码(加密后)
        String passwordInput = authParamsDto.getPassword();//输入的密码
        boolean matches = passwordEncoder.matches(passwordInput, passwordDB);
        if (!matches) {
            throw new RuntimeException("账号或密码错误");
        }

        return user;
    }
}
