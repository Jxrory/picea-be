package com.jxrory.picea.todo.entity.request;

import com.jxrory.picea.model.Period;
import lombok.Data;

import java.util.List;

/**
 * 今天创建的, 今天完成的, 今天更新的 Item 都展示出来.
 *
 * @author Rory
 * @date 2022/1/3 下午3:49
 */
@Data
public class TodoRequest {

    /**
     * 根据状态获取数据
     */
    private List<Integer> statuses;

    /**
     * 是否要包含今日已完成的任务
     */
    private Boolean includeCompleted = false;

    /**
     * 使用完成时间过滤
     */
    private Period completed;

    /**
     * 使用创建时间过滤
     */
    private Period created;
}
