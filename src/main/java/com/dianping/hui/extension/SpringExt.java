package com.dianping.hui.extension;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: lanxinyu@meituan.com  2018-10-15 下午10:03
 * @Description:
 */
@Configuration
public class SpringExt {

    public static ApplicationContext applicationContext;

    @Bean
    public SpringExt.A getA() {
        return new SpringExt.A();
    }
    @Bean
    public SpringExt.B getB() {
        return new SpringExt.B();
    }

    public static void main(String[] args) {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(SpringExt.class);
    }

    class A implements ApplicationContextAware{

        @Override
        public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
            SpringExt.applicationContext = applicationContext;
        }
    }

    class B implements InitializingBean{
        SpringExt.A a;
        @Override
        public void afterPropertiesSet() throws Exception {
            a = SpringExt.applicationContext.getBean(SpringExt.A.class);
        }
    }
}
