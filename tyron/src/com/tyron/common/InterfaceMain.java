package com.tyron.common;

public class InterfaceMain {
	public static void main(String[] args)
	{
		InterfaceI test = new InterfaceImpl("tyron");
		InterfaceProxy proxy = new InterfaceProxy();
		InterfaceI interfaceI = (InterfaceI) proxy.bind(test);
		System.out.println(interfaceI.getName());
	}
}
