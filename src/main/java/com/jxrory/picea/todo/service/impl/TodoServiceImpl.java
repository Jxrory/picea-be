package com.jxrory.picea.todo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxrory.picea.todo.entity.Todo;
import com.jxrory.picea.todo.service.TodoService;
import com.jxrory.picea.todo.mapper.TodoMapper;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class TodoServiceImpl extends ServiceImpl<TodoMapper, Todo>
    implements TodoService{

}




