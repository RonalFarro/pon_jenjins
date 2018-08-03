package com.debug.dummys.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.FilterRegistration.Dynamic;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
public class AppInitializer implements WebApplicationInitializer{
	
	private static final String COM_DEBUG_CONFIG = "com.debug.dummys.config";
	private static final String DISPATCHERSERVLET = "DispatcherServlet";
	private static final String SPRING_DISPATCHERSERVLET_MAPPING = "/*";
	
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		registerDispatcherServlet(servletContext);
		registerFilter(servletContext);
	}
	
	private AnnotationConfigWebApplicationContext getContext() {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.setConfigLocation(COM_DEBUG_CONFIG);
		return context;
	}
	
	private void registerFilter(final ServletContext servletContext) {

		final Dynamic filterRegistrationLogging = servletContext.addFilter("request-filter", RequestFilter.class);
		filterRegistrationLogging.addMappingForUrlPatterns(null, false, "/*");
		
		final Dynamic filterRegistration = servletContext.addFilter("encoding-filter", new CharacterEncodingFilter());
		filterRegistration.setInitParameter("encoding", "UTF-8");
		filterRegistration.setInitParameter("forceEncoding", "true");
		filterRegistration.addMappingForUrlPatterns(null, true, "/*");

	}	
	
	protected void registerDispatcherServlet(final ServletContext servletContext) {
		WebApplicationContext context = getContext();
		servletContext.addListener(new ContextLoaderListener(context));
		
		DispatcherServlet dispatcherServlet = new DispatcherServlet(context);
		dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
		
		ServletRegistration.Dynamic dispatcher = servletContext.addServlet(DISPATCHERSERVLET, dispatcherServlet);
		
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping(SPRING_DISPATCHERSERVLET_MAPPING);
	}

}
