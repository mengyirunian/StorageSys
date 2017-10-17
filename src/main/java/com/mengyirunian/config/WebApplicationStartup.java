package com.mengyirunian.config;

import javax.servlet.Filter;

import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebApplicationStartup extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     *  定义拦截器 ContextLoaderListener 应用上下文中的 beans
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { RootConfig.class };
    }

    /**
     *  定义 DispatcherServlet 应用上下文中的 beans
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { WebConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

    @Bean
    public CharacterEncodingFilter characterEncodingFilter() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return characterEncodingFilter;
    }

    @Override
    protected Filter[] getServletFilters() {
        return new Filter[] { characterEncodingFilter() };
    }

}
