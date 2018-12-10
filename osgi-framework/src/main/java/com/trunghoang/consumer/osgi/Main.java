package com.trunghoang.consumer.osgi;

import org.osgi.framework.BundleException;
import org.osgi.framework.InvalidSyntaxException;

public class Main {
    public static void main( String[] args ) throws BundleException, InterruptedException, InvalidSyntaxException {
	OSGiFramework fw = new OSGiFramework();
	try {
	    String providerName = "HelloServiceProvider";
	    String consumerName = "HelloServiceConsumer";

	    fw.start();

	    fw.installBundle( Constants.BUNDLE_PROVIDER_PATH );
	    fw.installBundle( Constants.BUNDLE_CONSUMER_PATH );

	    fw.getBundleByName( providerName ).start();
	    fw.getBundleByName( consumerName ).start();
	    Thread.sleep( 1000 );

	} finally {
	    fw.stop();
	}
    }
}
