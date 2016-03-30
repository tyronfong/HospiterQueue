package com.tyron.common;

public class InterfaceImpl implements InterfaceI {
	private String name;
	public InterfaceImpl(String name)
	{
		this.name = name;
	}
	@Override
	public String getName() {
		return this.name;
	}

}
