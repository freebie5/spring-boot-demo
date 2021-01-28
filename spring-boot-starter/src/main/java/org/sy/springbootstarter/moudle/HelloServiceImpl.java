package org.sy.springbootstarter.moudle;

import org.springframework.stereotype.Component;

/**
 * @Author john
 * @Date 2021/1/28 23:39
 * @Version 1.0
 */
@Component
public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello() {
        return "hello";
    }

}
