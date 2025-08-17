package com.example.demo.xml;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.example.demo.member.MemberService;
import com.example.demo.order.OrderService;
import com.example.demo.order.OrderServiceImpl;

class XmlAppContext {
    
    @Test
    void xmlAppContext() {
        ApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        OrderService orderService = ac.getBean("orderService", OrderService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);
        Assertions.assertThat(orderService).isInstanceOf(OrderServiceImpl.class);
    }
}
