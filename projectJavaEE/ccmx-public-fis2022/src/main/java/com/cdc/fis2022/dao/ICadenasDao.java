package com.cdc.fis2022.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdc.fis2022.beans.DataA;


@Repository
public interface ICadenasDao {
	
	List<DataA> getData();
	
	
	

}
