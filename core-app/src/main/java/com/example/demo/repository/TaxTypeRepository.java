package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.example.demo.ExTaxTypeSpi;
import com.example.demo.model.TaxType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class TaxTypeRepository {

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
		ObjectMapper objectMapper =new ObjectMapper();

		if (exTaxTypeSpiService instanceof List) {

			List<ExTaxTypeSpi> exTaxTypeSpis = (List<ExTaxTypeSpi>) exTaxTypeSpiService;
			for (final ExTaxTypeSpi exTaxTypeSpi : exTaxTypeSpis) {

				System.out.println("********* TaxTypeRepository - Connection : "+jdbcTemplate.getDataSource().getConnection());
				extendedObject = exTaxTypeSpi.findAll(null, jdbcTemplate.getDataSource().getConnection());
				

			}

		}
		
		System.out.println("TaxTypeRepository :: findAll :: extendedObject : "+extendedObject);
		
		if(extendedObject!=null)
		{
			
			extendedObjectNode = objectMapper.convertValue(extendedObject, JsonNode.class);
			System.out.println("TaxTypeRepository :: findAll :: extendedObjectNode : "+extendedObjectNode);
			
		}

		final String finalQuery = "SELECT COD_TAX_TYPE,TXT_TAX_DESC,FLG_POSTINGCYCLE FROM OLM_TAX_TYPE";
		final List<TaxType> taxTypes = jdbcTemplate.query(finalQuery, new TaxTypeRowMapper());
		
		final List<TaxType> newTaxTypes = new ArrayList<>();
		for (TaxType taxType : taxTypes) {
			
			for(JsonNode node : extendedObjectNode)
			{
				final String txTypCode = node.get("txTypCode").textValue();
				if(txTypCode.equals(taxType.getTxTypCode()))
				{
						final Object object= objectMapper.convertValue(node, Object.class);
						taxType.setExtObject(object);
						break;
				}
			}
			
			newTaxTypes.add(taxType);
			
		}
		
		
		
		return newTaxTypes;
	}

	public TaxType findById(final String txTypeCode) throws Exception {

		Object extendedObject = null;

		if (exTaxTypeSpiService instanceof List) {

			List<ExTaxTypeSpi> exTaxTypeSpis = (List<ExTaxTypeSpi>) exTaxTypeSpiService;
			for (final ExTaxTypeSpi exTaxTypeSpi : exTaxTypeSpis) {

				
				extendedObject = exTaxTypeSpi.findById(txTypeCode, jdbcTemplate.getDataSource().getConnection());
				

			}

		}

		System.out.println("TaxTypeRepository :: findById :: extendedObject : "+extendedObject);
		final String finalQuery = "SELECT COD_TAX_TYPE,TXT_TAX_DESC,FLG_POSTINGCYCLE FROM OLM_TAX_TYPE WHERE COD_TAX_TYPE = :txTypeCode";

		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		mapSqlParameterSource.addValue("txTypeCode", txTypeCode);

		TaxType taxType = namedParameterJdbcTemplate.queryForObject(finalQuery, mapSqlParameterSource,
				new TaxTypeRowMapper());
		
		taxType.setExtObject(extendedObject);
		
		System.out.println("TaxTypeRepository :: findById :: taxType : "+taxType);
		return taxType;

	}

	
	public String save(TaxType taxType) throws Exception {
		
		TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
		TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);
		
		
		
		int val = 0;
		
		System.out.println("ExTaxTypeSpiImpl :: save : connObj.getAutoCommit() : "+jdbcTemplate.getDataSource().getConnection().toString());
		System.out.println("ExTaxTypeSpiImpl :: save : connObj.getAutoCommit() : "+jdbcTemplate.getDataSource().getConnection().getAutoCommit());
		
		try {
			
			System.out.println("============== extended Object Save Start =========================");
			
			if(taxType.getExtObject()!=null)
			{
				
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
			
			System.out.println("============== Parent Object Save Start =========================");
			
			 val = jdbcTemplate.update("INSERT INTO OLM_TAX_TYPE (COD_TAX_TYPE,TXT_TAX_DESC,FLG_POSTINGCYCLE) VALUES ('"
						+ taxType.getTxTypCode() + "','" + taxType.getTxTypDesc() + "','" + taxType.getFlgPostingCycle()
						+ "')");

				System.out.println("val : " + val);
				
				
			
			transactionManager.commit(transactionStatus);
			System.out.println("============== Parent Object Save end =========================");
		}catch (Exception ex) {
			transactionManager.rollback(transactionStatus);
		}
		
	

		if (val == 1) {
			return "SUCCESS";
		}

		return "FAILED";
	}

}
