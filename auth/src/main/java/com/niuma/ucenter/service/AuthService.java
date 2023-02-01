package com.niuma.ucenter.service;

import com.niuma.ucenter.model.domain.User;
import com.niuma.ucenter.model.dto.AuthParamsDto;

/**
 * 认证接口，各种认证类型都需要实现这个接口
 * @author niuma
 * @create 2023-01-30 11:06
 */
public interface AuthService {

  /**
   * 认证方法
   * @param authParamsDto 认证参数
   * @return 返回认证成功的用户信息
   */
  User execute(AuthParamsDto authParamsDto);

}
