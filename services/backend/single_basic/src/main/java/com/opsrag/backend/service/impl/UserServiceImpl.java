package com.opsrag.backend.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.opsrag.backend.common.aop.CreateAop;
import com.opsrag.backend.common.aop.LogAop;
import com.opsrag.backend.common.aop.UpdateAop;
import com.opsrag.backend.common.constent.JwtConstant;
import com.opsrag.backend.common.exception.BusinessException;
import com.opsrag.backend.common.response.Result;
import com.opsrag.backend.common.utils.JwtUtils;
import com.opsrag.backend.common.utils.PasswordEncoding;
import com.opsrag.backend.pojo.Entity.User;
import com.opsrag.backend.mapper.UserMapper;
import com.opsrag.backend.pojo.dto.UserCreateDTO;
import com.opsrag.backend.pojo.dto.UserLoginDTO;
import com.opsrag.backend.pojo.vo.UserLoginVO;
import com.opsrag.backend.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author author
 * @since 2026-04-12
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private JwtUtils jwtUtils;

    @Override
    public Result login(UserLoginDTO userLoginDTO) {

        User user = lambdaQuery().eq(User::getAccount,userLoginDTO.getAccount()).one();
        if(user == null||user.getIsDeleted()==true){
            throw new BusinessException("账号不存在");
        }
        if(!PasswordEncoding.matches(userLoginDTO.getPassword(),user.getPassword())){
            throw  new BusinessException("密码错误");
        }

        Map<String,Object>claims = new HashMap<>();
        claims.put("userId",user.getUserId());
        String token = jwtUtils.generateToken(claims);
        return Result.success(token);
    }

    @Override
    public Result logout() {

        return Result.success();
    }

    @Override
    @CreateAop
    @LogAop(content = "创建用户", module = "用户管理")
    public Result createAccount(UserCreateDTO userCreateDTO) {
        User user = lambdaQuery()
                .eq(User::getAccount,userCreateDTO.getAccount()).one();
        if(user!=null){
            throw  new BusinessException("账号已存在");
        }
        if(userCreateDTO.getPassword()==null){
            throw new BusinessException("请输入密码");
        }
        user = BeanUtil.copyProperties(userCreateDTO,User.class);
        user.setPassword(PasswordEncoding.encode(user.getPassword()));
        userMapper.insert(user);
        return Result.success();
    }

    @Override
    public Result deleteAccount(Long userId) {
        lambdaUpdate()
                .eq(User::getUserId,userId)
                .eq(User::getIsDeleted,false)
                .set(User::getIsDeleted,true);
        return Result.success();
    }

    @Override
    @UpdateAop
    public Result updateAccount(User user) {
        userMapper.updateById(user);

        return Result.success();
    }

    @Override
    public Result getAccount(Long userId) {

        return null;
    }

    @Override
    public Result resetPassword(Long userId, String password) {
        User user=lambdaQuery().eq(User::getUserId,userId).one();
        if(user == null){
            throw new BusinessException("用户不存在");
        }
        lambdaUpdate().eq(User::getUserId,userId)
                .set(User::getPassword,PasswordEncoding.encode(password));
        return Result.success();
    }
}
