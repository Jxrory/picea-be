package com.jxrory.picea.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.jxrory.picea.todo.entity.Todo;
import com.jxrory.picea.todo.entity.request.TodoRequest;
import com.jxrory.picea.user.entity.User;
import com.jxrory.picea.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Boolean> add(@RequestBody User user) {
        log.info("add user={}", user);
        return new ResponseEntity<>(userService.save(user), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{uid}")
    public ResponseEntity<Boolean> delete(@PathVariable String uid) {
        log.info("delete uid={}", uid);
        return new ResponseEntity<>(userService.removeById(uid), HttpStatus.OK);
    }

    @PutMapping(value = "/{uid}")
    public ResponseEntity<Boolean> update(@PathVariable String uid, @RequestBody User user) {
        user.setUid(uid);
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
