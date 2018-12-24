package com.dianping.notautoscan.config;

import com.dianping.notautoscan.config.servlet.LansingFilter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;
import java.util.HashMap;

/**
 * @author: lanxinyu@meituan.com  2018-09-27 上午9:57
 * @Description:
 *
 * 初始化DispatcherServlet：
 *  1，配置过程
 *      在Servlet3.0环境中，容器会在类路中查找实现{@link javax.servlet.ServletContainerInitializer}接口的类，如果发现的话就会用他来配置Servlet容器。
 *      Spring通过{@link org.springframework.web.SpringServletContainerInitializer}实现了该接口，反过来又通过调用 {@link org.springframework.web.WebApplicationInitializer}
 *      的实现来配置DispatcherServlet。
 *
 *  2，特点 两个应用上下文。
 *      当 DispatcherServlet启动的时候，它会创建Spring应用上下文，并加载配置文件或配置类中所声明的Bean。
 *      我们要求DispatcherServlet加载应用上下文时{@method WebAppInitializer.getServletConfigClasses}，
 *      使用定义在{@link WebConfig}配置类的bean。
 *
 *      但是在SpringWeb应用中，通常还会有另外一个应用上下文。另外这个应用上下文是由 {@link org.springframework.web.context.ContextLoaderListener} 创建的
 *
 */
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {


    /**
     * ContextLoaderListener加载应用中的其他bean。这些bean通常驱动应用后端的中间层和数据层组件。
     * @return
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootConfig.class};
    }

    /**
     * 我们希望DispatcherServlet加载包含Web组件的bean，如控制器、视图解析器以及处理器映射
     * @return
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    /**
     * 将一个或多个路径映射到 DispatcherServlet 上，处理请求。
     * @return
     */
    @Override
    protected String[] getServletMappings() {
        //将 DispatcherServlet 映射到 /
        return new String[]{"/"};
    }

    @Override
    protected Filter[] getServletFilters() {
        /**
         * 如果只注册filter，并且filter只映射到DispatcherServlet上，那么放到这里就会很便捷
         */
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);

        LansingFilter lansingFilter = new LansingFilter();
        return new Filter[] { characterEncodingFilter, lansingFilter };
    }

    /**************  spring mvc 的高级技术，自定义DispatcherServlet配置   ***************/
    /**
     * 在 {@link AbstractAnnotationConfigDispatcherServletInitializer}
     * 将 DispatcherServlet 注册到 Servlet容器中之后，就会调用 customizeRegistration，将 servlet注册后得到的
     *  {@link ServletRegistration.Dynamic} 传递进来，重载后可对 {@link ServletRegistration.Dynamic} 进行额外的配置
     * @param registration
     */
    private static final int M = 1024 * 1024;
    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {

        //功能1：如何处理multipart请求和文件上传。如果计划使用Servlet3.0对multipart配置的支持，那么需要使用 registration 来启用 multipart请求。
        registration.setMultipartConfig(new MultipartConfigElement(
                //绝对目录「上传文件时会临时写入该目录」，限制文件大小不超过2M，整个请求不超过4M，而且所有文件都写在磁盘中
                "/tmp/lansing/upload", 2 * M, 4 * M, 0));
        //功能2：设置load-on-startup优先级
        registration.setLoadOnStartup(1);
        //功能3：设置初始化参数
        registration.setInitParameters(new HashMap<>());
        registration.setInitParameter("key","value");
        //功能4：支持异步
        registration.setAsyncSupported(true);
        //功能5：设置servlet安全
        //registration.setServletSecurity(null);
        //功能6：设置角色
        registration.setRunAsRole("lansing");
    }
}
