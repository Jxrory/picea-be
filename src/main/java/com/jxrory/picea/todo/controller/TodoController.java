package com.jxrory.picea.todo.controller;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.jxrory.picea.todo.entity.Todo;
import com.jxrory.picea.todo.entity.enums.TodoStatus;
import com.jxrory.picea.todo.entity.request.TodoRequest;
import com.jxrory.picea.todo.service.TodoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author Rory
 * @date 2021/12/29 下午8:28
 */
@Slf4j
@RestController
@RequestMapping("/api/todos")
public class TodoController {

    @Resource
    private TodoService todoService;

    @PostMapping
    public ResponseEntity<Boolean> add(@RequestBody Todo todo) {
        log.info("add todo={}", todo);
        return new ResponseEntity<>(todoService.save(todo), HttpStatus.OK);
    }

    @PutMapping(value = "/{uid}")
    public ResponseEntity<Boolean> update(@PathVariable String uid, @RequestBody Todo todo) {
        todo.setUid(uid);
        return new ResponseEntity<>(todoService.updateById(todo), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{uid}")
    public ResponseEntity<Boolean> delete(@PathVariable String uid) {
        log.info("delete uid={}", uid);
        return new ResponseEntity<>(todoService.removeById(uid), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Todo>> list(TodoRequest todoRequest) {
        log.info("todoRequest={}", todoRequest);
        LambdaQueryWrapper<Todo> wrapper = Wrappers.<Todo>lambdaQuery();

        if (!CollectionUtils.isEmpty(todoRequest.getStatuses())) {
            wrapper.in(Todo::getStatus, todoRequest.getStatuses());
        }
        return new ResponseEntity<>(todoService.list(wrapper), HttpStatus.OK);
    }

    /**
     * 获取今日需要展示的任务, 目前的筛选条件如下:
     * 1. 今天已经完成的任务
     * 2. 所有待完成的任务
     * 2.1 需要排除计划开始时间 > 今日的结束时间
     *
     * @param includeCompleted 是否要包含 今天已经完成的任务
     * @return 任务列表
     */
    @GetMapping(value = "/today")
    public ResponseEntity<List<Todo>> listToday(@RequestParam(defaultValue = "true") Boolean includeCompleted) {
        LambdaQueryWrapper<Todo> wrapper = Wrappers.<Todo>lambdaQuery();

        wrapper.and(i -> i.in(Todo::getStatus, ListUtil.toList(TodoStatus.IN_PROCESS.getCode(), TodoStatus.NEEDS_ACTIO))
                .lt(Todo::getDtstart, DateUtil.beginOfDay(DateUtil.tomorrow())));

        // 今日已完成任务
        if (includeCompleted) {
            wrapper.or(i -> i.gt(Todo::getCompleted, DateUtil.beginOfDay(new Date())));
        }

        return new ResponseEntity<>(todoService.list(wrapper), HttpStatus.OK);
    }
}
