package com.kh.spring.common.mybatis;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;

public class BkIdxsTypeHandler extends BaseTypeHandler{

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType)
			throws SQLException {
	
		ArrayDescriptor descriptor = 
				ArrayDescriptor.createDescriptor("BK_IDXS", ps.getConnection());
		
		List<String> bkIdxs = (List<String>)parameter;
		ARRAY bkIdxArr = new ARRAY(descriptor, ps.getConnection(), bkIdxs.toArray());
		ps.setArray(i, bkIdxArr);
	}

	 @Override
	  public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
	    return rs.getString(columnName);
	  }

	  @Override
	  public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
	    return rs.getString(columnIndex);
	  }

	  @Override
	  public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
	    return cs.getString(columnIndex);
	  }

}
