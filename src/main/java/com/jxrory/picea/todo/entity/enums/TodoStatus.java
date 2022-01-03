package com.jxrory.picea.todo.entity.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * 0 NEEDS-ACTIO
 * 1 COMPLETED
 * 2 IN-PROCESS
 * 9 CANCELLED
 *
 * @author Rory
 * @date 2022/1/3 下午4:47
 */
public enum TodoStatus {
    NEEDS_ACTIO(0, "待激活"),
    COMPLETED(1, "完成"),
    IN_PROCESS(2, "处理中"),
    CANCELLED(9, "取消"),
    ;

    TodoStatus(int code, String descp) {
        this.code = code;
        this.descp = descp;
    }

    @EnumValue
    @JsonValue
    private final int code;

    private final String descp;

    public int getCode() {
        return code;
    }

    public String getDescp() {
        return descp;
    }
}
