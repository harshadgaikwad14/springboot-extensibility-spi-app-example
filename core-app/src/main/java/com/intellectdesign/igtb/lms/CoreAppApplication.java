package com.intellectdesign.igtb.lms;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.serviceloader.ServiceListFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.intellectdesign.igtb.lms.ExTaxTypeSpi;

@SpringBootApplication
public class CoreAppApplication {

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

	@Bean
	public List<ExTaxTypeSpi> exTaxTypeSpiService() {
		System.out.println("Crating proxy for exTaxTypeSpiService implementations");

		List<ExTaxTypeSpi> exTaxTypeSpis = getExTaxTypeSpiServices();
		System.out.println("Found exTaxTypeSpiService implementations" + exTaxTypeSpis.size());

		return exTaxTypeSpis;// createProxiedNotificationSendersAsSpringWillNotCreateProxyForThese(senders);
	}

	private List<ExTaxTypeSpi> getExTaxTypeSpiServices() {
		List<ExTaxTypeSpi> senders = new ArrayList<>();
		try {
			ServiceListFactoryBean serviceListFactoryBean = new ServiceListFactoryBean();
			serviceListFactoryBean.setServiceType(ExTaxTypeSpi.class);
			serviceListFactoryBean.afterPropertiesSet();

			senders = (List<ExTaxTypeSpi>) serviceListFactoryBean.getObject();
		} catch (Exception ex) {
			System.out.println("Unable to retrieve ExTaxTypeSpi implementations" + ex.getMessage());
			ex.printStackTrace();
		}

		return senders;
	}

}
