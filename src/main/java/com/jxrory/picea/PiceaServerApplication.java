package com.jxrory.picea;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Rory
 * @date 2021/12/29 下午8:28
 */
@SpringBootApplication
@MapperScan({"com.jxrory.picea.**.mapper"})
public class PiceaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PiceaServerApplication.class, args);
    }

}
