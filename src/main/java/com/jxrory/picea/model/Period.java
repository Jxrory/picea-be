package com.jxrory.picea.model;

import lombok.Data;

import java.util.Date;

/**
 * 一般用于时间段查询, 开始时间 & 结束时间
 *
 * @author Rory
 * @date 2022/1/3 下午4:04
 */
@Data
public class Period {

    /**
     * 开始时间
     */
    private Date start;

    /**
     * 结束时间
     */
    private Date end;
}
