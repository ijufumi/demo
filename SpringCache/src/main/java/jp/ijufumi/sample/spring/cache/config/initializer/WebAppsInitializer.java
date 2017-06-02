package jp.ijufumi.sample.spring.cache.config.initializer;

import javax.servlet.Filter;
import javax.servlet.ServletContext;

import jp.ijufumi.sample.spring.cache.config.ApplicationConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Created by iju on 2015/10/07.
 */
public class WebAppsInitializer  extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * Specify the servlet mapping(s) for the {@code DispatcherServlet} &mdash;
     * for example {@code "/"}, {@code "/app"}, etc.
     *
     * @see #registerDispatcherServlet(ServletContext)
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    /**
     * Specify {@link Configuration @Configuration}
     * and/or {@link Component @Component} classes to be
     * provided to the {@linkplain #createRootApplicationContext() root application context}.
     *
     * @return the configuration classes for the root application context, or {@code null}
     * if creation and registration of a root context is not desired
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{ApplicationConfig.class};
    }

    /**
     * Specify {@link Configuration @Configuration}
     * and/or {@link Component @Component} classes to be
     * provided to the {@linkplain #createServletApplicationContext() dispatcher servlet
     * application context}.
     *
     * @return the configuration classes for the dispatcher servlet application context or
     * {@code null} if all configuration is specified through root config classes.
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{};
    }

    /**
     * Specify filters to add and map to the {@code DispatcherServlet}.
     *
     * @return an array of filters or {@code null}
     * @see #registerServletFilter(ServletContext, Filter)
     */
    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);

        return new Filter[]{
                characterEncodingFilter
        };
    }

    /**
     * Create a {@link DispatcherServlet} with the specified {@link WebApplicationContext}.
     */
    protected DispatcherServlet createDispatcherServlet(WebApplicationContext servletAppContext) {
        DispatcherServlet dispatcherServlet = super.createDispatcherServlet(servletAppContext);
        dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);

        return dispatcherServlet;
    }
}
