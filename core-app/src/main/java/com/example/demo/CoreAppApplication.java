package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.serviceloader.ServiceListFactoryBean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CoreAppApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CoreAppApplication.class, args);
	}

	/**
	 * Run Jar
	 * 
	 * java -cp core-app-0.0.1-SNAPSHOT.jar
	 * -Dloader.path=/C:/Users/harshad.gaikwad/Desktop/jar/
	 * org.springframework.boot.loader.PropertiesLauncher
	 * 
	 */

	/*
	 * @Autowired
	 * 
	 * @Qualifier("dictionaryServices") private Object dictionaries;
	 */

	@SuppressWarnings("unchecked")
	@Override
	public void run(String... args) throws Exception {

		/*
		 * if (dictionaries instanceof List) {
		 * 
		 * List<Dictionary> dis = (List<Dictionary>) dictionaries; for (Dictionary
		 * dictionary : dis) { System.out.println(dictionary.getName()); }
		 * 
		 * }
		 */

	}

	@Bean
	public List<Dictionary> dictionaryServices() {
		System.out.println("Crating proxy for Dictionary implementations");

		List<Dictionary> senders = getAvailableDictionaryServices();
		System.out.println("Found Dictionary implementations" + senders.size());

		return senders;// createProxiedNotificationSendersAsSpringWillNotCreateProxyForThese(senders);
	}

	private List<Dictionary> getAvailableDictionaryServices() {
		List<Dictionary> senders = new ArrayList<>();
		try {
			ServiceListFactoryBean serviceListFactoryBean = new ServiceListFactoryBean();
			serviceListFactoryBean.setServiceType(Dictionary.class);
			serviceListFactoryBean.afterPropertiesSet();

			senders = (List<Dictionary>) serviceListFactoryBean.getObject();
		} catch (Exception ex) {
			System.out.println("Unable to retrieve Dictionary sender implementations" + ex.getMessage());
			ex.printStackTrace();
		}

		return senders;
	}

}
