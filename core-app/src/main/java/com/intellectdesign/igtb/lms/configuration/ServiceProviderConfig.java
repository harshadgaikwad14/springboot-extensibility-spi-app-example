package com.intellectdesign.igtb.lms.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.serviceloader.ServiceListFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.intellectdesign.igtb.lms.ExCommonSpi;
import com.intellectdesign.igtb.lms.ExSweepStructureSpi;
import com.intellectdesign.igtb.lms.ExTaxTypeSpi;

@Configuration
public class ServiceProviderConfig {

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

	@Bean
	public ExCommonSpi exCommonSpiService() {
		System.out.println("Crating proxy for ExCommonSpi implementations");

		List<ExCommonSpi> exCommonSpis = getExCommonSpiServices();
		System.out.println("Found ExCommonSpi implementations : " + exCommonSpis.size());

		return exCommonSpis.stream().findFirst().get();// createProxiedNotificationSendersAsSpringWillNotCreateProxyForThese(senders);
	}

	private List<ExCommonSpi> getExCommonSpiServices() {
		List<ExCommonSpi> senders = new ArrayList<>();
		try {
			ServiceListFactoryBean serviceListFactoryBean = new ServiceListFactoryBean();
			serviceListFactoryBean.setServiceType(ExCommonSpi.class);
			serviceListFactoryBean.afterPropertiesSet();

			senders = (List<ExCommonSpi>) serviceListFactoryBean.getObject();
		} catch (Exception ex) {
			System.out.println("Unable to retrieve ExCommonSpi implementations" + ex.getMessage());
			ex.printStackTrace();
		}

		return senders;
	}
	
	
	@Bean
	public ExSweepStructureSpi exSweepStructureSpiService() {
		System.out.println("Crating proxy for ExCommonSpi implementations");

		List<ExSweepStructureSpi> exsweepStructureSpis = getExSweepStructureSpiServices();
		System.out.println("Found ExCommonSpi implementations : " + exsweepStructureSpis.size());

		return exsweepStructureSpis.stream().findFirst().get();// createProxiedNotificationSendersAsSpringWillNotCreateProxyForThese(senders);
	}

	private List<ExSweepStructureSpi> getExSweepStructureSpiServices() {
		List<ExSweepStructureSpi> senders = new ArrayList<>();
		try {
			ServiceListFactoryBean serviceListFactoryBean = new ServiceListFactoryBean();
			serviceListFactoryBean.setServiceType(ExSweepStructureSpi.class);
			serviceListFactoryBean.afterPropertiesSet();

			senders = (List<ExSweepStructureSpi>) serviceListFactoryBean.getObject();
		} catch (Exception ex) {
			System.out.println("Unable to retrieve ExSweepStructureSpi implementations" + ex.getMessage());
			ex.printStackTrace();
		}

		return senders;
	}

}
