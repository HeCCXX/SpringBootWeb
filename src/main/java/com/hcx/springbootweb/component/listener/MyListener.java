package com.hcx.springbootweb.component.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @ClassName MyListener
 * @Description TODO
 * @Author 贺楚翔
 * @Date 2019-12-05 16:00
 * @Version 1.0
 **/
public class MyListener  implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("初始化listener.....");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("销毁listener......");
    }
}
