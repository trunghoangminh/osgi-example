package com.trunghoang.consumer.osgi;

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

	// Set custom path cache. Default is "felix-cache"
	osgiConfiguration.put( "org.osgi.framework.storage", Constants.CACHE_PATH );
	osgiConfiguration.put( "org.osgi.framework.bundle.parent", "app" );
	osgiConfiguration.put( "org.osgi.framework.system.packages.extra", "com.trunghoang.*" );

	framework = frameworkFactory.newFramework( osgiConfiguration );
	framework.start();
	bundleContext = framework.getBundleContext();
    }

    public void installBundle( String fullPath ) throws BundleException {
	bundleContext.installBundle( fullPath );
    }

    public BundleContext getBundleContext() {
	return bundleContext;
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

}
