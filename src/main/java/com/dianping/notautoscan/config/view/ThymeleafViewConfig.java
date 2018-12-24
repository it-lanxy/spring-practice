package com.dianping.notautoscan.config.view;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.ViewResolver;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

/**
 * @author: lanxinyu@meituan.com  2018-10-23 上午11:03
 * @Description:
 */
public class ThymeleafViewConfig implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    /**
     * 将逻辑视图名称解析为Thymeleaf模板视图
     * @param templateEngine
     * @return
     */
    @Bean public ViewResolver thymeleafViewResolver(
            @Qualifier("springTemplateEngine") TemplateEngine templateEngine) {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine);
        viewResolver.setCharacterEncoding("UTF-8");
        viewResolver.setForceContentType(true);
        return viewResolver;
    }

    /**
     * 处理模板并渲染结果
     * @param templateResolver
     * @return
     */
    @Bean public TemplateEngine springTemplateEngine(
            @Qualifier("servletContextTemplateResolver") ITemplateResolver templateResolver) {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setEnableSpringELCompiler(Boolean.TRUE);
        templateEngine.setTemplateResolver(templateResolver);
        return templateEngine;
    }

    /**
     * 加载Thymeleaf模板
     * @return
     */
    @Bean public ITemplateResolver servletContextTemplateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext);
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCharacterEncoding("UTF-8");
        return templateResolver;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
