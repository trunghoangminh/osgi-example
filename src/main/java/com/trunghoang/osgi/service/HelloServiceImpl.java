package com.trunghoang.osgi.service;

public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello() {
	return "I'm HellService OSGi";
    }
}
