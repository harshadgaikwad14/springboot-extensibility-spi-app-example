package com.example.demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.ExTaxType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ExTaxTypeSpiImpl implements ExTaxTypeSpi {

	public ExTaxTypeSpiImpl() {
		super();
	}

	@Override
	public Object findAll(final Object object, final Object jdbcTemplate) throws Exception {

		System.out.println("***************************************************************");
		System.out.println("********* ExTaxTypeSpiImpl 0.0.2-SNAPSHOT *********************");
		System.out.println("***************************************************************");

		final Connection connObj = (Connection) jdbcTemplate;

		System.out.println("********* ExTaxTypeSpiImpl - Connection : " + connObj);

		final Statement stmtObj = connObj.createStatement();
		final ResultSet resObj = stmtObj.executeQuery("SELECT COD_TAX_TYPE,FIELD1,FIELD2 FROM EX_OLM_TAX_TYPE");
		List<ExTaxType> exTaxTypeDaos = new ArrayList<>();
		while (resObj.next()) {
			ExTaxType exTaxType = new ExTaxType();

			exTaxType.setTxTypCode(resObj.getString("COD_TAX_TYPE"));
			exTaxType.setExtField1(resObj.getString("FIELD1"));
			exTaxType.setExtField2(resObj.getString("FIELD2"));
			exTaxTypeDaos.add(exTaxType);
		}

		return exTaxTypeDaos;
	}

	public Object save(final Object object, final JdbcTemplate jdbcTemplate) throws Exception {

		ExTaxType exTaxType = null;
		final ObjectMapper objectMapper = new ObjectMapper();

		try {
			final String objectAsString = objectMapper.writeValueAsString(object);
			System.out.println("ExTaxTypeSpiImpl :: save : objectAsString :: " + objectAsString);
			exTaxType = objectMapper.readValue(objectAsString, ExTaxType.class);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int val = 0;

		val = jdbcTemplate.update(
				"INSERT INTO EX_OLM_TAX_TYPE (COD_TAX_TYPE, FIELD1, FIELD2) VALUES ('" + exTaxType.getTxTypCode()
						+ "','" + exTaxType.getExtField1() + "','" + exTaxType.getExtField2() + "')");

		System.out.println(val + " records inserted");

		System.out.println("val : " + val);

		return val;

	}
	
	

	@Override
	public Object save(final Object object, final Connection connObj) throws Exception {

		ExTaxType exTaxType = null;
		final ObjectMapper objectMapper = new ObjectMapper();

		System.out.println("ExTaxTypeSpiImpl :: save : connObj.getAutoCommit() : " + connObj.toString());
		System.out.println("ExTaxTypeSpiImpl :: save : connObj.getAutoCommit() : " + connObj.getAutoCommit());

		try {
			final String objectAsString = objectMapper.writeValueAsString(object);
			System.out.println("ExTaxTypeSpiImpl :: save : objectAsString :: " + objectAsString);
			exTaxType = objectMapper.readValue(objectAsString, ExTaxType.class);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int val = 0;

		if (exTaxType.getExtField1().equalsIgnoreCase("Y")) {

			final PreparedStatement stmt = connObj
					.prepareStatement("INSERT INTO EX_OLM_TAX_TYPE (COD_TAX_TYPE, FIELD1, FIELD2) VALUES (?,?,?)");
			stmt.setString(1, exTaxType.getTxTypCode());
			stmt.setString(2, exTaxType.getExtField1());
			stmt.setString(3, exTaxType.getExtField2());

			val = stmt.executeUpdate();
		}

		System.out.println(val + " records inserted");

		System.out.println("val : " + val);

		return val;

	}

	@Override
	public Object findById(final Object object, final Object jdbcTemplate) throws Exception {

		System.out.println("ExTaxTypeSpiImpl :: findById : object :: " + object);
		final ObjectMapper objectMapper = new ObjectMapper();
		String txTypCode = null;
		try {
			final String objectAsString = objectMapper.writeValueAsString(object);
			System.out.println("ExTaxTypeSpiImpl :: findById : objectAsString :: " + objectAsString);
			txTypCode = objectMapper.readValue(objectAsString, String.class);

		} catch (JsonProcessingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		final Connection connObj = (Connection) jdbcTemplate;

		// create SQL query to fetch particular record
		String sqlSelectQuery = "SELECT COD_TAX_TYPE, FIELD1, FIELD2 FROM EX_OLM_TAX_TYPE WHERE COD_TAX_TYPE = ?";

		PreparedStatement preparedStatement = connObj.prepareStatement(sqlSelectQuery);
		System.out.println("txTypCode::::::::::::::::::::::: " + txTypCode);
		preparedStatement.setString(1, txTypCode);
		ResultSet resultSet = preparedStatement.executeQuery();

		ExTaxType exTaxType = null;

		// processing returned data and printing into console
		while (resultSet.next()) {
			exTaxType = new ExTaxType();
			exTaxType.setTxTypCode(resultSet.getString("COD_TAX_TYPE"));
			exTaxType.setExtField1(resultSet.getString("FIELD1"));
			exTaxType.setExtField2(resultSet.getString("FIELD2"));
			break;

		}

		System.out.println("ExTaxTypeSpiImpl :: findById : exTaxType :: " + exTaxType);
		return exTaxType;

	}

}
