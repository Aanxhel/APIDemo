package com.cdc.fis2022.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cdc.fis2022.beans.DataA;



public class FisMapperD implements RowMapper{

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		

		DataA rsDataA = new DataA();
		
		if(rs != null && rowNum >=0) {
			try {
				
				if(rs.getString("X_DEVOPS_APPNAME") != null && !"".equals(rs.getString("X_DEVOPS_APPNAME"))) {
					rsDataA.setAppName(rs.getString("X_DEVOPS_APPNAME"));
				}
				if(rs.getString("X_DEVOPS_AUTH") != null && !"".equals(rs.getString("X_DEVOPS_AUTH"))) {
					rsDataA.setAppAut(rs.getString("X_DEVOPS_AUTH"));
				}
//				if(rs.getString("X_AUTHORIZATION") != null && !"".equals(rs.getString("X_AUTHORIZATION"))) {
//					rsDataA.setAppPas(rs.getString("X_AUTHORIZATION"));
//				}
				
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		
		
		return rsDataA;
	}

}
