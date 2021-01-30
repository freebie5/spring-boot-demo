package org.sy.springbootapollo;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableApolloConfig({"application","test01"})
@SpringBootApplication
public class SpringBootApolloApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootApolloApplication.class, args);
    }

}
