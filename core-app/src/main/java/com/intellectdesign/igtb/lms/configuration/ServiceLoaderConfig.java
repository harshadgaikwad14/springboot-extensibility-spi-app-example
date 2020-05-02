package com.intellectdesign.igtb.lms.configuration;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.serviceloader.ServiceListFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.intellectdesign.igtb.lms.ExCommonSpi;
import com.intellectdesign.igtb.lms.ExSweepStructureSpi;

@Configuration
public class ServiceLoaderConfig {

	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceLoaderConfig.class);

	@Bean
	public ExCommonSpi exCommonSpiService() {
		LOGGER.info("Crating proxy for ExCommonSpi implementations");

		List<ExCommonSpi> exCommonSpis = getExCommonSpiServices();
		LOGGER.info("Found ExCommonSpi implementations : {} ", exCommonSpis.size());

		return exCommonSpis.stream().findFirst().get();
		// createProxiedNotificationSendersAsSpringWillNotCreateProxyForThese(senders);
	}

	@SuppressWarnings("unchecked")
	private List<ExCommonSpi> getExCommonSpiServices() {
		List<ExCommonSpi> senders = new ArrayList<>();
		try {
			final ServiceListFactoryBean serviceListFactoryBean = new ServiceListFactoryBean();
			serviceListFactoryBean.setServiceType(ExCommonSpi.class);
			serviceListFactoryBean.afterPropertiesSet();

			senders = (List<ExCommonSpi>) serviceListFactoryBean.getObject();
		} catch (Exception ex) {
			LOGGER.info("Unable to retrieve ExCommonSpi implementations : {} ", ex.getMessage());
			ex.printStackTrace();
		}

		return senders;
	}

	@Bean
	public ExSweepStructureSpi exSweepStructureSpiService() {
		LOGGER.info("Crating proxy for ExSweepStructureSpi implementations");

		List<ExSweepStructureSpi> exsweepStructureSpis = getExSweepStructureSpiServices();
		LOGGER.info("Found ExSweepStructureSpi implementations : {} ", exsweepStructureSpis.size());

		return exsweepStructureSpis.stream().findFirst().get();// createProxiedNotificationSendersAsSpringWillNotCreateProxyForThese(senders);
	}

	@SuppressWarnings("unchecked")
	private List<ExSweepStructureSpi> getExSweepStructureSpiServices() {
		List<ExSweepStructureSpi> senders = new ArrayList<>();
		try {
			final ServiceListFactoryBean serviceListFactoryBean = new ServiceListFactoryBean();
			serviceListFactoryBean.setServiceType(ExSweepStructureSpi.class);
			serviceListFactoryBean.afterPropertiesSet();

			senders = (List<ExSweepStructureSpi>) serviceListFactoryBean.getObject();
		} catch (Exception ex) {
			LOGGER.info("Unable to retrieve ExSweepStructureSpi implementations : {} ", ex.getMessage());
			ex.printStackTrace();
		}

		return senders;
	}

}
