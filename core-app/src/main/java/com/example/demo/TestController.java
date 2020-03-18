package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

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

	@GetMapping("/coreModels")
	public CoreModel getSecond() {

		Object extendeOb = null;
		CoreModel c = new CoreModel();
		c.setId(1);
		c.setName("Harshad");

		if (dictionaries instanceof List) {

			List<Dictionary> dis = (List<Dictionary>) dictionaries;
			for (Dictionary dictionary : dis) {

				extendeOb = dictionary.getObject();
				System.out.println("testController - getSecond - extendeOb : " + extendeOb);

			}

		}

		c.setExtensibleObject(extendeOb);

		System.out.println("testController - getSecond - coreModel : " + c);

		return c;
	}

	@PostMapping("/coreModels")
	public String postData(@RequestBody CoreModel coreModel) {
		ObjectMapper objectMapper = new ObjectMapper();

		Object extensibleObjectResp = null;

		System.out.println("postData : coreModel : " + coreModel);

		if (dictionaries instanceof List) {

			List<Dictionary> dis = (List<Dictionary>) dictionaries;
			for (Dictionary dictionary : dis) {

				
			    final Object ob=  coreModel.getExtensibleObject();
			    System.out.println("ob : "+ob);
				extensibleObjectResp = dictionary.postObject(ob);

			}

		}

		System.out.println("postData : extensibleObjectResp " + extensibleObjectResp);

		return "success";
	}

}
