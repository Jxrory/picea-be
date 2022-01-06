package com.jxrory.picea.user.controller;

import cn.hutool.core.lang.UUID;
import cn.hutool.crypto.SecureUtil;
import com.jxrory.picea.user.model.entity.User;
import com.jxrory.picea.user.model.request.UserCuRequest;
import com.jxrory.picea.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Rory
 * @date 2022/1/3 下午7:03
 */
@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping
    public ResponseEntity<Boolean> add(@RequestBody @Validated UserCuRequest userCuRequest) {
        log.info("add userCuRequest={}", userCuRequest);
        User user = new User();
        BeanUtils.copyProperties(userCuRequest, user);

        // 生成密码的盐
        String salt = UUID.randomUUID().toString(true);
        user.setPwSalt(salt);

        // 密码加密 SecureUtil
        String password = SecureUtil.hmacMd5(salt).digestHex(userCuRequest.getPassword());

        user.setPassword(password);

        log.info("user={}", user);
        return new ResponseEntity<>(userService.save(user), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{uid}")
    public ResponseEntity<Boolean> delete(@PathVariable String uid) {
        log.info("delete uid={}", uid);
        return new ResponseEntity<>(userService.removeById(uid), HttpStatus.OK);
    }

    @PutMapping(value = "/{uid}")
    public ResponseEntity<Boolean> update(@PathVariable String uid, @RequestBody UserCuRequest userCuRequest) {
        User user = userService.getById(uid);
        BeanUtils.copyProperties(userCuRequest, user);
        log.debug("save user={}", user);
        return new ResponseEntity<>(userService.updateById(user), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<User>> list() {
        return new ResponseEntity<>(userService.list(), HttpStatus.OK);
    }

    @GetMapping(value = "/{uid}")
    public ResponseEntity<User> get(@PathVariable String uid) {
        return new ResponseEntity<>(userService.getById(uid), HttpStatus.OK);
    }
}
