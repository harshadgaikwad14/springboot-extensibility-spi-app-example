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

import com.intellectdesign.igtb.lms.cz.swp.structure.CzSwpStructureBusinessValidation;
import com.intellectdesign.igtb.lms.cz.swp.structure.CzSwpStructureRequestValidation;
import com.intellectdesign.igtb.lms.cz.swp.structure.CzSwpStructureService;
import com.intellectdesign.igtb.lms.cz.test.CzTestBusinessValidation;
import com.intellectdesign.igtb.lms.cz.test.CzTestRequestValidation;
import com.intellectdesign.igtb.lms.exception.ApiSubError;

@Configuration
public class CzSwpStructureConfig {

	private static final Logger LOGGER = LoggerFactory.getLogger(CzSwpStructureConfig.class);

	@Bean
	@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
	public CzSwpStructureService<Object> czSwpStructureService() {
		LOGGER.info("Crating proxy for CzSwpStructureService implementations");

		final List<CzSwpStructureService> czSwpStructureServices = getCzSwpStructureServices();
		LOGGER.info("Found CzSwpStructureService implementations : {} ", czSwpStructureServices.size());

		czSwpStructureServices.stream().forEach(v -> LOGGER.info("Load Implementation Class : {}", v.getClass().getName()));

		return czSwpStructureServices.stream().findFirst().get();

	}

	@SuppressWarnings("unchecked")
	private List<CzSwpStructureService> getCzSwpStructureServices() {
		List<CzSwpStructureService> senders = new ArrayList<>();
		try {
			final ServiceListFactoryBean serviceListFactoryBean = new ServiceListFactoryBean();
			serviceListFactoryBean.setServiceType(CzSwpStructureService.class);
			serviceListFactoryBean.afterPropertiesSet();

			senders = (List<CzSwpStructureService>) serviceListFactoryBean.getObject();
		} catch (Exception ex) {
			LOGGER.info("Unable to retrieve CzSwpStructureService implementations : {} ", ex.getMessage());
			ex.printStackTrace();
		}

		return senders;
	}

	@Bean
	@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
	public CzSwpStructureBusinessValidation<Object, ApiSubError> czSwpStructureBusinessValidation() {
		LOGGER.info("Crating proxy for ExTestBusinessValidation implementations");

		final List<CzSwpStructureBusinessValidation> czSwpStructureBusinessValidations = getCzSwpStructureBusinessValidations();
		LOGGER.info("Found ExTestBusinessValidation implementations : {} ", czSwpStructureBusinessValidations.size());
		czSwpStructureBusinessValidations.stream().forEach(v -> LOGGER.info("Load Implementation Class : {}", v.getClass().getName()));

		return czSwpStructureBusinessValidations.stream().findFirst().get();

	}

	@SuppressWarnings("unchecked")
	private List<CzSwpStructureBusinessValidation> getCzSwpStructureBusinessValidations() {
		List<CzSwpStructureBusinessValidation> senders = new ArrayList<>();
		try {
			final ServiceListFactoryBean serviceListFactoryBean = new ServiceListFactoryBean();
			serviceListFactoryBean.setServiceType(CzSwpStructureBusinessValidation.class);
			serviceListFactoryBean.afterPropertiesSet();

			senders = (List<CzSwpStructureBusinessValidation>) serviceListFactoryBean.getObject();
		} catch (Exception ex) {
			LOGGER.info("Unable to retrieve CzSwpStructureBusinessValidation implementations : {} ", ex.getMessage());
			ex.printStackTrace();
		}

		return senders;
	}

	@Bean
	@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
	public CzSwpStructureRequestValidation czSwpStructureRequestValidation() {
		LOGGER.info("Crating proxy for CzSwpStructureRequestValidation implementations");

		final List<CzSwpStructureRequestValidation> czSwpStructureRequestValidations = getCzSwpStructureRequestValidations();
		LOGGER.info("Found CzSwpStructureRequestValidation implementations : {} ",
				czSwpStructureRequestValidations.size());
		czSwpStructureRequestValidations.stream().forEach(v -> LOGGER.info("Load Implementation Class : {}", v.getClass().getName()));

		return czSwpStructureRequestValidations.stream().findFirst().get();
		// createProxiedNotificationSendersAsSpringWillNotCreateProxyForThese(senders);
	}

	@SuppressWarnings("unchecked")
	private List<CzSwpStructureRequestValidation> getCzSwpStructureRequestValidations() {
		List<CzSwpStructureRequestValidation> senders = new ArrayList<>();
		try {
			final ServiceListFactoryBean serviceListFactoryBean = new ServiceListFactoryBean();
			serviceListFactoryBean.setServiceType(CzSwpStructureRequestValidation.class);
			serviceListFactoryBean.afterPropertiesSet();

			senders = (List<CzSwpStructureRequestValidation>) serviceListFactoryBean.getObject();
		} catch (Exception ex) {
			LOGGER.info("Unable to retrieve CzSwpStructureRequestValidation implementations : {} ", ex.getMessage());
			ex.printStackTrace();
		}

		return senders;
	}

}
