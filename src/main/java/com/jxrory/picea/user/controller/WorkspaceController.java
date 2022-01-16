package com.jxrory.picea.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.jxrory.picea.user.model.entity.Workspace;
import com.jxrory.picea.user.service.WorkspaceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 检验 userId 是否和用户匹配, 防止用户修改错误
 *
 * @author Rory
 * @date 2022/1/16 上午12:24
 */
@Slf4j
@RestController
@RequestMapping("/api/{userId}/workspaces")
public class WorkspaceController {

    @Resource
    private WorkspaceService workspaceService;

    @PostMapping
    public ResponseEntity<Boolean> add(@PathVariable String userId, @RequestBody Workspace workspace) {
        workspace.setUserId(userId);
        return new ResponseEntity<>(workspaceService.save(workspace), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{workspaceId}")
    public ResponseEntity<Boolean> delete(@PathVariable String userId, @PathVariable String workspaceId) {
        return new ResponseEntity<>(workspaceService.removeById(workspaceId), HttpStatus.OK);
    }

    @PutMapping(value = "/{workspaceId}")
    public ResponseEntity<Boolean> update(@PathVariable String userId, @PathVariable String workspaceId, @RequestBody Workspace workspace) {
        workspace.setUid(workspaceId);
        return new ResponseEntity<>(workspaceService.updateById(workspace), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Workspace>> list(@PathVariable String userId) {
        return new ResponseEntity<>(workspaceService.list(Wrappers.<Workspace>lambdaQuery().eq(Workspace::getUserId, userId)), HttpStatus.OK);
    }

    @GetMapping(value = "/{workspaceId}")
    public ResponseEntity<Workspace> get(@PathVariable String userId, @PathVariable String workspaceId) {
        LambdaQueryWrapper<Workspace> wrapper = Wrappers.<Workspace>lambdaQuery()
                .eq(Workspace::getUserId, userId)
                .eq(Workspace::getUid, workspaceId);
        return new ResponseEntity<>(workspaceService.getOne(wrapper), HttpStatus.OK);
    }
}
