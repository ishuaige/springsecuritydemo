package com.niuma.ucenter.service.impl;

import com.alibaba.fastjson.JSON;
import com.niuma.ucenter.mapper.UserMapper;
import com.niuma.ucenter.model.dto.AuthParamsDto;
import com.niuma.ucenter.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 这里实现 UserDetailsService接口 自己做用户的校验，可以从数据库中取数据
 *
 * @author niuma
 * @create 2023-01-30 11:06
 */
@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    UserMapper userMapper;

    @Resource
    ApplicationContext applicationContext;

    /**
     * @param info 请求中带的 username 参数，因为我们可能需要更多的信息（例如登录类型，微信登陆等等），
     *             所以这个参数我们可以接收一个json，再转换成dto，这样请求中的password就不用携带了，
     *             将参数全部放在 username 中
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String info) throws UsernameNotFoundException {
        AuthParamsDto authParamsDto = null;
        try {
            //将认证参数转为AuthParamsDto类型
            authParamsDto = JSON.parseObject(info, AuthParamsDto.class);
        } catch (Exception e) {
            log.info("认证请求不符合项目要求:{}", info);
            throw new RuntimeException("认证请求数据格式不对");
        }
        // 根据不同的登陆类型作不同的校验（通过ioc拿校验方法）
        //认证方式
        String authType = authParamsDto.getAuthType();
        //从spring容器中拿具体的认证bean实例
        AuthService authService = applicationContext.getBean(authType + "_authservice", AuthService.class);
        //开始认证,认证成功拿到用户信息
        com.niuma.ucenter.model.domain.User user = authService.execute(authParamsDto);
        // 将登陆返回的对象 封装为 UserDetails,返回 UserDetails
        return getUserPrincipal(user);
    }

    /**
     * 根据 User 对象构造一个UserDetails对象
     *
     * @param user
     * @return
     */
    public UserDetails getUserPrincipal(com.niuma.ucenter.model.domain.User user) {
        //权限列表，存放的用户权限
        List<String> permissionList = new ArrayList<>();

        //这里的权限比较简单，实际应该会有权限表，角色表，角色权限表，用户角色表等
        int role = user.getRole();
        if(role == 1){
            permissionList.add("admin");
        }else {
            permissionList.add("teacher");
        }
        //根据用户id查询数据库中他的权限
        if (permissionList.size() == 0) {
            //用户权限,如果不加报 Cannot pass a null GrantedAuthority collection
            permissionList.add("test");
        }

        String[] authorities = permissionList.toArray(new String[0]);
        //原来存的是账号，现在扩展为用户的全部信息(密码不要放)
        user.setPassword(null);
        String jsonString = JSON.toJSONString(user);
        UserDetails userDetails = User.withUsername(jsonString).password("").authorities(authorities).build();

        return userDetails;
    }

}
