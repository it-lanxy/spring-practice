package com.dianping.notautoscan.config.view;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * @author: lanxinyu@meituan.com  2018-10-23 上午11:01
 * @Description:
 */
public class JspViewConfig {
    /**
     * 配置Jsp视图解析器
     * 问：为什么配置多个视图解析器不管用？
     * 因为：{@link org.springframework.web.servlet.DispatcherServlet.resolveViewName()}
     *   需要考虑解析器配置顺序，选到了一个配置了的view，就会return。
     */
    @Bean
    public ViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        resolver.setExposeContextBeansAsAttributes(true);
        resolver.setViewClass(JstlView.class);
        return resolver;
    }
}
