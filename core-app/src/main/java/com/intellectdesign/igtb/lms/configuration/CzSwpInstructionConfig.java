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

import com.intellectdesign.igtb.lms.cz.swp.instruction.CzSwpInstructionBusinessValidation;
import com.intellectdesign.igtb.lms.cz.swp.instruction.CzSwpInstructionRequestValidation;
import com.intellectdesign.igtb.lms.cz.swp.instruction.CzSwpInstructionService;
import com.intellectdesign.igtb.lms.exception.ApiSubError;

@Configuration
public class CzSwpInstructionConfig {

	private static final Logger LOGGER = LoggerFactory.getLogger(CzSwpInstructionConfig.class);

	@Bean
	@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
	public CzSwpInstructionService<Object> czSwpInstructionService() {
		LOGGER.info("Crating proxy for CzSwpInstructionService implementations");

		final List<CzSwpInstructionService> czSwpInstructionServices = getCzSwpInstructionServices();
		LOGGER.info("Found CzSwpInstructionService implementations : {} ", czSwpInstructionServices.size());

		czSwpInstructionServices.stream().forEach(v -> LOGGER.info("Load Implementation Class : {}", v.getClass().getName()));

		return czSwpInstructionServices.stream().findFirst().get();
		// createProxiedNotificationSendersAsSpringWillNotCreateProxyForThese(senders);
	}

	@SuppressWarnings("unchecked")
	private List<CzSwpInstructionService> getCzSwpInstructionServices() {
		List<CzSwpInstructionService> senders = new ArrayList<>();
		try {
			final ServiceListFactoryBean serviceListFactoryBean = new ServiceListFactoryBean();
			serviceListFactoryBean.setServiceType(CzSwpInstructionService.class);
			serviceListFactoryBean.afterPropertiesSet();

			senders = (List<CzSwpInstructionService>) serviceListFactoryBean.getObject();
		} catch (Exception ex) {
			LOGGER.info("Unable to retrieve CzSwpInstructionService implementations : {} ", ex.getMessage());
			ex.printStackTrace();
		}

		return senders;
	}

	@Bean
	@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
	public CzSwpInstructionBusinessValidation<Object, ApiSubError> exTestBusinessValidation() {
		LOGGER.info("Crating proxy for CzSwpInstructionBusinessValidation implementations");

		final List<CzSwpInstructionBusinessValidation> czSwpInstructionBusinessValidations = getCzSwpInstructionBusinessValidations();
		LOGGER.info("Found CzSwpInstructionBusinessValidation implementations : {} ",
				czSwpInstructionBusinessValidations.size());
		
		czSwpInstructionBusinessValidations.stream().forEach(v -> LOGGER.info("Load Implementation Class : {}", v.getClass().getName()));

		return czSwpInstructionBusinessValidations.stream().findFirst().get();
		// createProxiedNotificationSendersAsSpringWillNotCreateProxyForThese(senders);
	}

	@SuppressWarnings("unchecked")
	private List<CzSwpInstructionBusinessValidation> getCzSwpInstructionBusinessValidations() {
		List<CzSwpInstructionBusinessValidation> senders = new ArrayList<>();
		try {
			final ServiceListFactoryBean serviceListFactoryBean = new ServiceListFactoryBean();
			serviceListFactoryBean.setServiceType(CzSwpInstructionBusinessValidation.class);
			serviceListFactoryBean.afterPropertiesSet();

			senders = (List<CzSwpInstructionBusinessValidation>) serviceListFactoryBean.getObject();
		} catch (Exception ex) {
			LOGGER.info("Unable to retrieve CzSwpInstructionBusinessValidation implementations : {} ", ex.getMessage());
			ex.printStackTrace();
		}

		return senders;
	}

	@Bean
	@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
	public CzSwpInstructionRequestValidation czSwpInstructionRequestValidation() {
		LOGGER.info("Crating proxy for CzSwpInstructionRequestValidation implementations");

		final List<CzSwpInstructionRequestValidation> czSwpInstructionRequestValidation = getCzSwpInstructionRequestValidations();
		LOGGER.info("Found CzSwpInstructionRequestValidation implementations : {} ",
				czSwpInstructionRequestValidation.size());
		
		czSwpInstructionRequestValidation.stream().forEach(v -> LOGGER.info("Load Implementation Class : {}", v.getClass().getName()));

		return czSwpInstructionRequestValidation.stream().findFirst().get();
		// createProxiedNotificationSendersAsSpringWillNotCreateProxyForThese(senders);
	}

	@SuppressWarnings("unchecked")
	private List<CzSwpInstructionRequestValidation> getCzSwpInstructionRequestValidations() {
		List<CzSwpInstructionRequestValidation> senders = new ArrayList<>();
		try {
			final ServiceListFactoryBean serviceListFactoryBean = new ServiceListFactoryBean();
			serviceListFactoryBean.setServiceType(CzSwpInstructionRequestValidation.class);
			serviceListFactoryBean.afterPropertiesSet();

			senders = (List<CzSwpInstructionRequestValidation>) serviceListFactoryBean.getObject();
		} catch (Exception ex) {
			LOGGER.info("Unable to retrieve CzSwpInstructionRequestValidation implementations : {} ", ex.getMessage());
			ex.printStackTrace();
		}

		return senders;
	}

}
