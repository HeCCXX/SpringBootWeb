package com.hcx.springbootweb.config;

import com.hcx.springbootweb.component.filter.MyFilter;
import com.hcx.springbootweb.component.listener.MyListener;
import com.hcx.springbootweb.component.servlet.MyServlet;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Arrays;

/**
 * @ClassName MymvcConfig
 * @Description TODO
 * @Author 贺楚翔
 * @Date 2019-12-03 17:17
 * @Version 1.0
 **/
/**
* 使用webmvcConfigurerAdapter可以扩展SpringMVC的功能
 * 不能标注@EnableWebMvc，因为标注了之后webmvc的自动配置将失效
**/

@Configuration
public class MymvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/hgg").setViewName("login");

    }

    /**
    * 所有的WebMvcConfigurerAdapter都会一起起作用
    **/
    @Bean
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter(){
        WebMvcConfigurerAdapter adapter = new WebMvcConfigurerAdapter(){
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/index.html").setViewName("login");
                registry.addViewController("/main.html").setViewName("dashboard");
            }

            //添加自定义拦截器
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**").excludePathPatterns("/index.html","/","/user/login");
            }
        };
        return adapter;
    }

    /**
    * 国际化自定义，根据页面点击中文英文来切换国际化显示
    * @return org.springframework.web.servlet.LocaleResolver
    * @exception
    **/
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }


    //定制器加入容器中
    @Bean
    public EmbeddedServletContainerCustomizer embeddedServletContainerCustomizer(){
        return new EmbeddedServletContainerCustomizer() {
            //定制嵌入式的Servlet容器的相关规则
            @Override
            public void customize(ConfigurableEmbeddedServletContainer container) {
                container.setPort(8081);
            }
        };
    }

    /**
    * 将组件添加到容器中
    **/
    @Bean
    public ServletRegistrationBean myServlet(){
        return new ServletRegistrationBean(new MyServlet(),"/myservlet");
    }


    @Bean
    public FilterRegistrationBean myFilter(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new MyFilter());
        registrationBean.setUrlPatterns(Arrays.asList("/hcx","/myservlet"));
        return registrationBean;
    }

    @Bean
    public ServletListenerRegistrationBean myListener(){
        return new ServletListenerRegistrationBean(new MyListener());
    }
}
