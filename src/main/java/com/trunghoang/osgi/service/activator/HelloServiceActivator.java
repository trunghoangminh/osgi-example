package com.trunghoang.osgi.service.activator;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import com.trunghoang.osgi.service.HelloService;
import com.trunghoang.osgi.service.HelloServiceImpl;

/**
 * 
 */
public class HelloServiceActivator implements BundleActivator {

    private ServiceRegistration<?> service;

    @Override
    public void start( BundleContext context ) throws Exception {
	System.out.println( "<-- Welcome -->" );
	HelloService helloService = new HelloServiceImpl();
	service = context.registerService( HelloService.class.getName(), helloService, null );
    }

    @Override
    public void stop( BundleContext context ) throws Exception {
	System.out.println( "<-- Goodbye -->" );
	if ( service != null ) {
	    service.unregister();
	}
    }
}
