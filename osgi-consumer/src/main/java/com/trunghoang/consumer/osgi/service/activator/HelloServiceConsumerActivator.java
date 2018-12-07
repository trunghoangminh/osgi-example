package com.trunghoang.consumer.osgi.service.activator;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import com.trunghoang.provider.osgi.service.HelloService;

/**
 * 
 */
public class HelloServiceConsumerActivator implements BundleActivator {

    ServiceReference<HelloService> helloServiceReference;

    @SuppressWarnings( "unchecked" )
    @Override
    public void start( BundleContext context ) throws Exception {
	System.out.println( "<-- Welcome Consumer -->" );
	helloServiceReference = (ServiceReference<HelloService>) context.getServiceReference( HelloService.class.getName() );
	System.out.println( context.getService( helloServiceReference ).sayHello() );
    }

    @Override
    public void stop( BundleContext context ) throws Exception {
	System.out.println( "<-- Goodbye Consumer -->" );
	if ( helloServiceReference != null ) {
	    context.ungetService( helloServiceReference );
	}
    }
}
