package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sun.swing.internal.plaf.basic.resources.basic;

@RestController
public class TestController {

	@Autowired
	@Qualifier("dictionaryServices")
	private Object dictionaries;

	@RequestMapping("/first")
	public String getFirst() {

		StringBuilder sb = new StringBuilder();
		sb.append("First Api Called");
		if (dictionaries instanceof List) {

			List<Dictionary> dis = (List<Dictionary>) dictionaries;
			for (Dictionary dictionary : dis) {

				sb.append(" With Extensibility - " + dictionary.getName());
			}

		}
		return sb.toString();
	}

}
