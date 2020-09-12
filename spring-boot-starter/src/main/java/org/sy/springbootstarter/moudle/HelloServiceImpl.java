package org.sy.springbootstarter.moudle;

import org.springframework.stereotype.Component;

@Component
public class HelloServiceImpl implements HelloService {
    public String sayHello() {
        return "hello";
    }
}
