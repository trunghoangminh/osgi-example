package com.trunghoang.provider.osgi.service.activator;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import com.trunghoang.provider.osgi.service.HelloService;
import com.trunghoang.provider.osgi.service.HelloServiceImpl;

/**
 * 
 */
public class HelloServiceProviderActivator implements BundleActivator {

    private ServiceRegistration<?> service;

    @Override
    public void start( BundleContext context ) throws Exception {
	System.out.println( "<-- Welcome Provider -->" );
	service = context.registerService( HelloService.class.getName(), new HelloServiceImpl(), null );
    }

    @Override
    public void stop( BundleContext context ) throws Exception {
	System.out.println( "<-- Goodbye Provider -->" );
	if ( service != null ) {
	    service.unregister();
	}
    }
}
