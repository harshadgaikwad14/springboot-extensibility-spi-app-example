package com.example.demo;

public class ExtendedDictionary implements Dictionary {
	@Override
	public String getName() {

		System.out.println("ExtendedDictionary :: getName :: called :: Version 2");
		return getClass().getSimpleName()+" Version 2";
	}
}
