package com.dianping.notautoscan.config;

import com.dianping.notautoscan.config.view.ThymeleafViewConfig;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.nio.charset.Charset;

/**
 * @author: lanxinyu@meituan.com  2018-09-27 上午10:16
 * @Description:
 */

/**
 * 没有配置视图解析器，如果这样的话，Spring默认会使用 {@link org.springframework.web.servlet.view.BeanNameViewResolver},
 * 这个视图解析器会查找ID与视图名称匹配的bean，并且查找的bean要实现View接口，他以这样的方式来解析式图。
 *
 * 没有启动组件扫描。结果是，Spring只能找到显式声明在配置类中的控制器。
 *
 * 这样配置的话，DispatcherServlet会映射为应用的默认Servlet，所以它会处理所有的请求，包括对静态资源的请求，如图片和样式表（在大多数情况下，可能不是想要的效果）
 */
@Configuration
/**启动SpringMVC */
@EnableWebMvc
/**启动组件扫描 */
@ComponentScan("com.dianping.hui")
@Import({ThymeleafViewConfig.class})
public class WebConfig extends WebMvcConfigurerAdapter {

    @Bean
    public StringHttpMessageConverter stringHttpMessageConverter() {
        return new StringHttpMessageConverter(Charset.forName("UTF-8"));
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource resource = new ResourceBundleMessageSource();
        resource.setDefaultEncoding("UTF-8");
        return resource;
    }

    /**
     * multipart请求看起来很复杂，但是spring mvc中处理他们却很容易，在编写控制器方法「Controller」处理文件上传之前，
     * 我们必须要配置一个multipart解析器，通过它来搞死DispatcherServlet如何读取multipart请求。
     */
    @Bean public MultipartResolver multipartResolver() {
        //TODO 我们如何做到限制文件大小，临时写入目录呢？ answer：不在MultipartResolver中限制，在Servlet中限制。如WebServletInitializer、WebAppInitializer.customizeRegistration
        return new StandardServletMultipartResolver();
        //非Servlet3.0
        //return new CommonsMultipartResolver();
    }

    /**
     * 配置静态资源的处理
     * @param configurer
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}
