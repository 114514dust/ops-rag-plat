package com.opsrag.backend.service;

import com.opsrag.backend.common.response.Result;
import com.opsrag.backend.pojo.Entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.opsrag.backend.pojo.dto.UserCreateDTO;
import com.opsrag.backend.pojo.dto.UserLoginDTO;
import com.opsrag.backend.pojo.vo.UserLoginVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2026-04-12
 */
public interface IUserService extends IService<User> {

    Result login(UserLoginDTO userLoginDTO);

    Result logout();

    Result createAccount(UserCreateDTO userCreateDTO);

    Result deleteAccount(Long userId);

    Result updateAccount(User user);

    Result getAccount(Long userId);

    Result resetPassword(Long userId, String password);
}
