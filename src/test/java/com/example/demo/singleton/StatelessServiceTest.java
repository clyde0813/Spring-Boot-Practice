package com.example.demo.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatelessServiceTest {
    
    @Test
    void statelessServiceTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        StatelessService statelessService1 = ac.getBean(StatelessService.class);
        StatelessService statelessService2 = ac.getBean(StatelessService.class);
        
        //ThreadA: A사용자 10000원 주문
        int userAprice = statelessService1.order("userA", 10000);
        //ThreadB: B사용자 20000원 주문
        int userBprice = statelessService2.order("userB", 20000);

        Assertions.assertThat(userAprice).isEqualTo(10000);
        Assertions.assertThat(userBprice).isEqualTo(20000);
    }

    static class TestConfig {

        @Bean
        public StatelessService statefulService() {
            return new StatelessService();
        }
    }
}
