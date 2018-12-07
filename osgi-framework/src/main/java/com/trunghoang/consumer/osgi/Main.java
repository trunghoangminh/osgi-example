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

	    fw.installBundle( "file:/home/hmtrung/workspace/osgi-example/osgi-provider/target/osgi-provider-0.0.1-SNAPSHOT.jar" );
	    fw.installBundle( "file:/home/hmtrung/workspace/osgi-example/osgi-consumer/target/osgi-consumer-0.0.1-SNAPSHOT.jar" );

	    fw.getBundleByName( providerName ).start();
	    fw.getBundleByName( consumerName ).start();
	    Thread.sleep( 1000 );

	} finally {
	    fw.stop();
	}
    }
}
