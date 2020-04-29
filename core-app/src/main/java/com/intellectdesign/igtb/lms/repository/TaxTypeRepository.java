package com.intellectdesign.igtb.lms.repository;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ValidationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.intellectdesign.igtb.lms.ExTaxTypeSpi;
import com.intellectdesign.igtb.lms.entity.TaxType;
import com.intellectdesign.igtb.lms.exception.DataNotFoundException;
import com.intellectdesign.igtb.lms.rowmapper.TaxTypeRowMapper;

@Repository
public class TaxTypeRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(TaxTypeRepository.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	PlatformTransactionManager transactionManager;

	@Autowired
	@Qualifier("exTaxTypeSpiService")
	private Object exTaxTypeSpiService;

	public List<TaxType> findAll() throws Exception {

		Object extendedObject = null;
		JsonNode extendedObjectNode = null;
		ObjectMapper objectMapper = new ObjectMapper();

		if (exTaxTypeSpiService instanceof List) {

			List<ExTaxTypeSpi> exTaxTypeSpis = (List<ExTaxTypeSpi>) exTaxTypeSpiService;
			for (final ExTaxTypeSpi exTaxTypeSpi : exTaxTypeSpis) {

				System.out.println(
						"********* TaxTypeRepository - Connection : " + jdbcTemplate.getDataSource().getConnection());
				extendedObject = exTaxTypeSpi.findAll(null, jdbcTemplate.getDataSource().getConnection());

			}

		}

		System.out.println("TaxTypeRepository :: findAll :: extendedObject : " + extendedObject);

		if (extendedObject != null) {

			extendedObjectNode = objectMapper.convertValue(extendedObject, JsonNode.class);
			System.out.println("TaxTypeRepository :: findAll :: extendedObjectNode : " + extendedObjectNode);

		}

		final String finalQuery = "SELECT COD_TAX_TYPE,TXT_TAX_DESC,FLG_POSTINGCYCLE FROM OLM_TAX_TYPE";
		final List<TaxType> taxTypes = jdbcTemplate.query(finalQuery, new TaxTypeRowMapper());

		final List<TaxType> newTaxTypes = new ArrayList<>();
		for (TaxType taxType : taxTypes) {

			for (JsonNode node : extendedObjectNode) {
				final String txTypCode = node.get("txTypCode").textValue();
				if (txTypCode.equals(taxType.getTxTypCode())) {
					final Object object = objectMapper.convertValue(node, Object.class);
					taxType.setExtObject(object);
					break;
				}
			}

			newTaxTypes.add(taxType);

		}

		return newTaxTypes;
	}

	public TaxType findById(final String txTypeCode) throws Exception {

		final String finalQuery = "SELECT COD_TAX_TYPE,TXT_TAX_DESC,FLG_POSTINGCYCLE FROM OLM_TAX_TYPE WHERE COD_TAX_TYPE = :txTypeCode";

		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		mapSqlParameterSource.addValue("txTypeCode", txTypeCode);

		TaxType taxType = null;
		try {
			taxType= namedParameterJdbcTemplate.queryForObject(finalQuery, mapSqlParameterSource, new TaxTypeRowMapper());

		} catch (EmptyResultDataAccessException e) {
			throw new DataNotFoundException("TaxType " + txTypeCode + " not found");
		}

		Object extendedObject = null;

		if (exTaxTypeSpiService instanceof List) {

			List<ExTaxTypeSpi> exTaxTypeSpis = (List<ExTaxTypeSpi>) exTaxTypeSpiService;
			for (final ExTaxTypeSpi exTaxTypeSpi : exTaxTypeSpis) {

				extendedObject = exTaxTypeSpi.findById(txTypeCode, jdbcTemplate.getDataSource().getConnection());
				break;

			}

		}

		System.out.println("TaxTypeRepository :: findById :: extendedObject : " + extendedObject);

		if(extendedObject!=null)
		{
			
			taxType.setExtObject(extendedObject);
		}

		System.out.println("TaxTypeRepository :: findById :: taxType : " + taxType);
		return taxType;

	}

	public String save(TaxType taxType) throws Exception {

		LOGGER.info("taxType : {} ", taxType);

		TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
		TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);

		int val = 0;

		System.out.println("ExTaxTypeSpiImpl :: save : connObj.getAutoCommit() : "
				+ jdbcTemplate.getDataSource().getConnection().getAutoCommit());

		System.out.println("============== Parent Object Save Start =========================");

		try {

			val = jdbcTemplate.update("INSERT INTO OLM_TAX_TYPE (COD_TAX_TYPE,TXT_TAX_DESC,FLG_POSTINGCYCLE) VALUES ('"
					+ taxType.getTxTypCode() + "','" + taxType.getTxTypDesc() + "','" + taxType.getFlgPostingCycle()
					+ "')");

			System.out.println("val : " + val);

			System.out.println("============== extended Object Save Start =========================");

			if (taxType.getExtObject() != null) {

				int extendedObject = 0;

				if (exTaxTypeSpiService instanceof List) {

					List<ExTaxTypeSpi> exTaxTypeSpis = (List<ExTaxTypeSpi>) exTaxTypeSpiService;
					for (final ExTaxTypeSpi exTaxTypeSpi : exTaxTypeSpis) {

						/*
						 * extendedObject = (int) exTaxTypeSpi.save(taxType.getExtObject(),
						 * jdbcTemplate.getDataSource().getConnection());
						 */

						extendedObject = (int) exTaxTypeSpi.save(taxType.getExtObject(), jdbcTemplate);

					}

				}

				System.out.println("extendedObject insert Reponse : " + extendedObject);
			}

			System.out.println("============== extended Object Save End =========================");

			transactionManager.commit(transactionStatus);
			System.out.println("============== Parent Object Save end =========================");
		} catch (Exception ex) {
			val = 0;
			transactionManager.rollback(transactionStatus);
	
			
			throw new ValidationException("Persistance Failed "+ex.getMessage());
			
		}

		if (val == 1) {
			return "SUCCESS";
		}

		return "FAILED";
	}

}
