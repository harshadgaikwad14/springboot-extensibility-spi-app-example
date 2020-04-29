package com.intellectdesign.igtb.lms.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.intellectdesign.igtb.lms.entity.TaxType;



public class TaxTypeRowMapper implements RowMapper<TaxType> {

	public TaxType mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		final TaxType taxType = new TaxType();
		taxType.setTxTypCode(rs.getString("COD_TAX_TYPE"));
		taxType.setTxTypDesc(rs.getString("TXT_TAX_DESC"));
		taxType.setFlgPostingCycle(rs.getString("FLG_POSTINGCYCLE"));

		return taxType;
	}

}