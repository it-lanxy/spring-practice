package com.dianping.notautoscan.config.view;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

/**
 * @author: lanxinyu@meituan.com  2018-10-23 上午11:01
 * @Description:
 */
public class TilesViewConfig {
    /**
     * 使用Apache Tiles 视图定义布局
     * 假设我们想为应用的所有页面定义一个通用的头部和底部。最原始的方式时查找每个jsp模板。为每个页面添加
     * 头部和底部的HTML。但是这种方法的扩展性不好，也难以维护。为每个页面添加这些元素会有一些初始成本，而后续每次变更都会耗费
     * 类似成本。
     * 更好的方式是使用布局引擎，如 Apache Tiles，定义适用于所有页面的通用页面布局。
     * SpringMVC 以视图解析器的形式为Apache Tiles提供了支持，这个视图解析器能够将逻辑视图名解析为Tiles的定义。
     * @return
     */
    @Bean public TilesConfigurer tilesConfigurer() {
        TilesConfigurer tilesConfigurer = new TilesConfigurer();
        tilesConfigurer.setDefinitions(
                new String[] {"/WEB-INF/layout/tiles.xml"}
        );
        //启动刷新功能
        tilesConfigurer.setCheckRefresh(true);
        return tilesConfigurer;
    }

    @Bean public ViewResolver tilesViewResolver() {
        return new TilesViewResolver();
    }
}
