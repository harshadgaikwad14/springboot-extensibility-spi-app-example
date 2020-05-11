package com.intellectdesign.igtb.lms.configuration;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.serviceloader.ServiceListFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

import com.intellectdesign.igtb.lms.cz.test.CzTestBusinessValidation;
import com.intellectdesign.igtb.lms.cz.test.CzTestRequestValidation;
import com.intellectdesign.igtb.lms.cz.test.CzTestService;
import com.intellectdesign.igtb.lms.exception.ApiSubError;

@Configuration
public class CzTestConfig {

	private static final Logger LOGGER = LoggerFactory.getLogger(CzTestConfig.class);

	@Bean
	@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
	public CzTestService<Object> czTestService() {
		LOGGER.info("Crating proxy for CzTestService implementations");

		final List<CzTestService> czTestServices = getCzTestServices();
		LOGGER.info("Found CzTestService implementations : {} ", czTestServices.size());

		if (czTestServices != null) {

			czTestServices.stream().forEach(v -> LOGGER.info("Load Implementation Class : {}", v.getClass().getName()));
			return czTestServices.stream().findFirst().get();
		}

		return null;

		// createProxiedNotificationSendersAsSpringWillNotCreateProxyForThese(senders);
	}

	@SuppressWarnings("unchecked")
	private List<CzTestService> getCzTestServices() {
		List<CzTestService> senders = new ArrayList<>();
		try {
			final ServiceListFactoryBean serviceListFactoryBean = new ServiceListFactoryBean();
			serviceListFactoryBean.setServiceType(CzTestService.class);
			serviceListFactoryBean.afterPropertiesSet();

			senders = (List<CzTestService>) serviceListFactoryBean.getObject();
		} catch (Exception ex) {
			LOGGER.info("Unable to retrieve CzTestService implementations : {} ", ex.getMessage());
			ex.printStackTrace();
		}

		return senders;
	}

	@Bean
	@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
	public CzTestBusinessValidation<Object, ApiSubError> czTestBusinessValidation() {
		LOGGER.info("Crating proxy for CzTestBusinessValidation implementations");

		final List<CzTestBusinessValidation> czTestBusinessValidations = getCzTestBuisinessValidations();
		LOGGER.info("Found CzTestBusinessValidation implementations : {} ", czTestBusinessValidations.size());

		czTestBusinessValidations.stream()
				.forEach(v -> LOGGER.info("Load Implementation Class : {}", v.getClass().getName()));
		return czTestBusinessValidations.stream().findFirst().get();
		// createProxiedNotificationSendersAsSpringWillNotCreateProxyForThese(senders);
	}

	@SuppressWarnings("unchecked")
	private List<CzTestBusinessValidation> getCzTestBuisinessValidations() {
		List<CzTestBusinessValidation> senders = new ArrayList<>();
		try {
			final ServiceListFactoryBean serviceListFactoryBean = new ServiceListFactoryBean();
			serviceListFactoryBean.setServiceType(CzTestBusinessValidation.class);
			serviceListFactoryBean.afterPropertiesSet();

			senders = (List<CzTestBusinessValidation>) serviceListFactoryBean.getObject();
		} catch (Exception ex) {
			LOGGER.info("Unable to retrieve CzTestBusinessValidation implementations : {} ", ex.getMessage());
			ex.printStackTrace();
		}

		return senders;
	}

	@Bean
	@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
	public CzTestRequestValidation czTestRequestValidation() {
		LOGGER.info("Crating proxy for CzTestRequestValidation implementations");

		final List<CzTestRequestValidation> czTestRequestValidations = getCzTestRequestValidations();
		LOGGER.info("Found CzTestRequestValidation implementations : {} ", czTestRequestValidations.size());
		czTestRequestValidations.stream()
				.forEach(v -> LOGGER.info("Load Implementation Class : {}", v.getClass().getName()));

		return czTestRequestValidations.stream().findFirst().get();
		// createProxiedNotificationSendersAsSpringWillNotCreateProxyForThese(senders);
	}

	@SuppressWarnings("unchecked")
	private List<CzTestRequestValidation> getCzTestRequestValidations() {
		List<CzTestRequestValidation> senders = new ArrayList<>();
		try {
			final ServiceListFactoryBean serviceListFactoryBean = new ServiceListFactoryBean();
			serviceListFactoryBean.setServiceType(CzTestRequestValidation.class);
			serviceListFactoryBean.afterPropertiesSet();

			senders = (List<CzTestRequestValidation>) serviceListFactoryBean.getObject();
		} catch (Exception ex) {
			LOGGER.info("Unable to retrieve CzTestRequestValidation implementations : {} ", ex.getMessage());
			ex.printStackTrace();
		}

		return senders;
	}

}
