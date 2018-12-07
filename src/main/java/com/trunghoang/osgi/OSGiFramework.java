package com.trunghoang.osgi;

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

import org.omg.CORBA.portable.ApplicationException;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.launch.Framework;
import org.osgi.framework.launch.FrameworkFactory;

/**
 * 
 */
public class OSGiFramework {

    private Framework framework;

    private BundleContext bundleContext;

    /**
     * Start the OSGi Framework
     * 
     * @throws BundleException
     *
     * @throws ApplicationException
     */
    public void start() throws BundleException {

	FrameworkFactory frameworkFactory = ServiceLoader.load( FrameworkFactory.class ).iterator().next();

	Map<String, String> osgiConfiguration = new HashMap<>();

	osgiConfiguration.put( "org.osgi.framework.storage.clean", "onFirstInit" );
	osgiConfiguration.put( "org.osgi.framework.storage", "/home/hmtrung/workspace/osgi-example/bundle.cache" );
	osgiConfiguration.put( "org.osgi.framework.bundle.parent", "app" );
	osgiConfiguration.put( "org.osgi.framework.system.packages.extra", "com.trunghoang.osgi.service.*" );

	framework = frameworkFactory.newFramework( osgiConfiguration );
	framework.start();
	bundleContext = framework.getBundleContext();
    }

    public void installBundle( String fullPath ) throws BundleException {
	bundleContext.installBundle( fullPath );
    }

    /**
     * Stop the OSGi Framework
     * 
     * @throws BundleException
     * @throws InterruptedException
     */
    public void stop() throws BundleException, InterruptedException {
	framework.stop();
	framework.waitForStop( 0 );
    }

    public Bundle getBundleByName( String name ) {
	for ( Bundle bundle : bundleContext.getBundles() ) {
	    if ( bundle.getSymbolicName().equals( name ) ) {
		return bundle;
	    }
	}
	return null;
    }

    public static void main( String[] args ) throws BundleException, InterruptedException {
	String serviceName = "com.trunghoang.osgi.service.HelloService";
	OSGiFramework fw = new OSGiFramework();

	fw.start();
	fw.installBundle( "file:target/osgi-example-0.0.1-SNAPSHOT.jar" );

	Bundle hello = fw.getBundleByName( serviceName );
	hello.start();
	Thread.sleep( 1000 );
	fw.stop();
    }
}
