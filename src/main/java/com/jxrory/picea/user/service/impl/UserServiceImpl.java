package com.jxrory.picea.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxrory.picea.user.model.entity.User;
import com.jxrory.picea.user.service.UserService;
import com.jxrory.picea.user.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




