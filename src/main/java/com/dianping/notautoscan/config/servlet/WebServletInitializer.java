package com.dianping.notautoscan.config.servlet;

/**
 * @author: lanxinyu@meituan.com  2018-10-31 下午12:53
 * @Description:
 */

import org.springframework.web.WebApplicationInitializer;

import javax.servlet.FilterRegistration;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * 添加其他的Servlet和Filter
 * 基于java的初始化（initializer）的一个好处就在于我们可以定义任意数量的初始化器类。因此，如果我们想往Web容器中注册其他组件的话，只需要创建一个新的初始化器类就可以了。
 * 最简单的方式时实现 {@link WebApplicationInitializer}
 */
public class WebServletInitializer implements WebApplicationInitializer {
    /**
     * 这是一个相当基础的Servlet注册初始化器类，它注册了一个Servlet并将其映射到一个路径上。我们也可以通过这种方式
     * 手动注册一个{@link org.springframework.web.servlet.DispatcherServlet}。
     * （这完全没必要，因为{@link com.dianping.notautoscan.config.WebAppInitializer}没用太多代码就将这项任务完成的很漂亮。）
     * @param servletContext
     * @throws ServletException
     */
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        //注册servlet
        ServletRegistration.Dynamic lansingServlet = servletContext
                .addServlet("lansingServlet", LansingServlet.class);
        //映射Servlet
        lansingServlet.addMapping("/lansing/**");
        lansingServlet.setMultipartConfig(new MultipartConfigElement("/tmp/lansing/uploads"));

        //注册filter
        FilterRegistration.Dynamic lansingFilter = servletContext.addFilter("lansingFilter", LansingFilter.class);
        //映射filter
        //lansingFilter.addMappingForUrlPatterns(null, false, "/lansing/**");
    }
}
