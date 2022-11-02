package org.pro.springorder;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

class A {
    private final B b;

    public A(B b) {
        this.b = b;
    }
}

class B {
    private final A a;

    public B(A a) {
        this.a = a;
    }
}

@Configuration
class CircularConfig {

    @Bean
    public A a(B b) {
        return new A(b);
    }

    @Bean
    public B b(A a) {
        return new B(a);
    }
}

public class CircularDepTester {

    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(CircularConfig.class);


    }

}
