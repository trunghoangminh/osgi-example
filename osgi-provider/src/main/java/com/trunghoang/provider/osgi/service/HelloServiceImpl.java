package com.trunghoang.provider.osgi.service;

public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello() {
	return "I'm HellService OSGi";
    }
}
