package com.example.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ExtendedDictionary implements Dictionary {

	final ExtendedModel extendedModel = new ExtendedModel();

	@Override
	public String getName() {

		System.out.println("ExtendedDictionary :: getName :: called :: Version 3");
		return getClass().getSimpleName() + " Version 2";
	}

	@Override
	public Object getObject() {
		System.out.println("getObject : extendedModel : " + extendedModel);
		return extendedModel;
	}

	@Override
	public Object postObject(Object object) {

		ExtendedModel e = null;
		System.out.println("postObject : object :: " + object);
		ObjectMapper om = new ObjectMapper();
		try {
			String s = om.writeValueAsString(object);
			System.out.println("postObject : s :: " + s);	
			e = om.readValue(s, ExtendedModel.class);
			System.out.println("postObject : e :: " + e);
		} catch (JsonProcessingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		extendedModel.setCode(e.getCode());
		extendedModel.setDescription(e.getDescription());

		System.out.println("postObject : extendedModel : " + extendedModel);

		return "success";
	}
}
