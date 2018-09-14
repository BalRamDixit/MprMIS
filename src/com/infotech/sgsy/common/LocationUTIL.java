package com.infotech.sgsy.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException; 

import com.infotech.sgsy.configuration.PersisterImpl;
import com.infotech.sgsy.util.CommonUtils;
import com.infotech.sgsy.util.SGSYConnectionUtil;

public class LocationUTIL {

	private  final String SQL_SELECT_STATE_BY_ID= "select state_name  from mst_state where state_code=?";
	private  final String SQL_SELECT_DISTRICT_BY_ID= "select district_name  from mst_district where district_code=?";
	private  final String SQL_SELECT_BLOCK_BY_ID= "select block_name  from mst_block where block_code=?";
	private  final String SQL_SELECT_VILLAGE_BY_ID= "select village_name  from mst_village where village_code=?";
	private  final String SQL_SELECT_STATESHORTNAME_BY_ID= "select state_short_name  from mst_state where state_code=?";



	public LocationVO getLocationData(String entityCode) throws Exception {

		LocationVO dataVO = new LocationVO();
		Connection connection = PersisterImpl.getConnection();		
		PreparedStatement statement = null;		 
		String villageName =null,blockName=null,districtName=null,stateName=null,stateShortName=null;
		String villageCode =null,blockCode=null,districtCode=null,stateCode=null;
		try{
			if(entityCode == null) 
				return null;
			else if(entityCode !=null && entityCode.length() == 13 ){
				villageName=new LocationUTIL().getVillageName(entityCode);
				blockName = new LocationUTIL().getBlockName(entityCode.substring(0,7));
				districtName = new LocationUTIL().getDistrictName(entityCode.substring(0,4));
				stateName = new LocationUTIL().getStateName(entityCode.substring(0,2));	
				villageCode = entityCode;
				blockCode =  entityCode.substring(0,7);
				districtCode=entityCode.substring(0,4);
				stateCode=entityCode.substring(0,2); 
				stateShortName =new LocationUTIL().getStateShortName(entityCode.substring(0,2));
			}else if(entityCode !=null && entityCode.length() == 7 ){
				blockName=new LocationUTIL().getBlockName(entityCode);
				districtName = new LocationUTIL().getDistrictName(entityCode.substring(0,4));
				stateName = new LocationUTIL().getStateName(entityCode.substring(0,2));	
				blockCode =  entityCode.substring(0,7);
				districtCode=entityCode.substring(0,4);
				stateCode=entityCode.substring(0,2); 
				stateShortName =new LocationUTIL().getStateShortName(entityCode.substring(0,2));
			}else if(entityCode !=null && entityCode.length() == 4 ){
				districtName=new LocationUTIL().getDistrictName(entityCode);
				stateName = new LocationUTIL().getStateName(entityCode.substring(0,2));	
				districtCode=entityCode.substring(0,4);
				stateCode=entityCode.substring(0,2); 
				stateShortName =new LocationUTIL().getStateShortName(entityCode.substring(0,2));
			}else if(entityCode !=null && entityCode.length() == 2 ){
				stateName = new LocationUTIL().getStateName(entityCode.substring(0,2));	
				stateCode=entityCode.substring(0,2); 
				stateShortName =new LocationUTIL().getStateShortName(entityCode.substring(0,2));
			}

			

		} catch(Exception e){
			e.printStackTrace();			 
		}  finally {			
			CommonUtils.closeDatabaseUtil(statement, connection, null);		 

		}
		dataVO.setVillageCode(villageCode);
		dataVO.setVillageName(villageName);
		dataVO.setBlockName(blockName);
		dataVO.setBlockCode(blockCode);
		dataVO.setDistrictCode(districtCode);
		dataVO.setDistrictName(districtName);
		dataVO.setStateName(stateName);
		dataVO.setStateCode(stateCode);
		dataVO.setStateShortName(stateShortName);



		return dataVO;
	}

	public  String getStateShortName(String code) 	throws Exception {

		Connection connection = PersisterImpl.getConnection();		
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String stateName= "" ;

		try {
			statement = connection.prepareStatement(SQL_SELECT_STATESHORTNAME_BY_ID);			
			statement.setString(1,code);
			resultSet = statement.executeQuery();
			if(resultSet.next()){			 
				stateName = resultSet.getString(1); 
			}
		} catch(SQLException e){
			e.printStackTrace();			 
		}  finally {
			CommonUtils.closeDatabaseUtil(statement, connection, null);		 

		}
		return  stateName; 

	}


	public  String getVillageName(String code) 	throws Exception {

		Connection connection = PersisterImpl.getConnection();		
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String stateName= "" ;

		try {
			statement = connection.prepareStatement(SQL_SELECT_VILLAGE_BY_ID);			
			statement.setString(1,code);
			resultSet = statement.executeQuery();
			if(resultSet.next()){			 
				stateName = resultSet.getString(1); 
			}
		} catch(SQLException e){
			e.printStackTrace();			 
		}  finally {
			CommonUtils.closeDatabaseUtil(statement, connection, null);		 

		}
		return  stateName; 

	}

	public  String getBlockName(String code)
	throws Exception {

		Connection connection = PersisterImpl.getConnection();		
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String stateName= "" ;

		try {
			statement = connection.prepareStatement(SQL_SELECT_BLOCK_BY_ID);			
			statement.setString(1,code);
			resultSet = statement.executeQuery();
			if(resultSet.next()){			 
				stateName = resultSet.getString(1); 
			}
		} catch(SQLException e){
			e.printStackTrace();			 
		}  finally {
			CommonUtils.closeDatabaseUtil(statement, connection, null);		 

		}
		return  stateName; 

	}
	public  String getDistrictName(String code)	throws Exception {

		Connection connection = PersisterImpl.getConnection();		
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String stateName= "" ;

		try {
			statement = connection.prepareStatement(SQL_SELECT_DISTRICT_BY_ID);			
			statement.setString(1,code);
			resultSet = statement.executeQuery();
			if(resultSet.next()){			 
				stateName = resultSet.getString(1); 
			}
		} catch(SQLException e){
			e.printStackTrace();			 
		}  finally {
			CommonUtils.closeDatabaseUtil(statement, connection, null);		 

		}
		return  stateName; 

	}

	public  String getStateName(String code)	throws Exception {

		Connection connection = PersisterImpl.getConnection();		
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String stateName= "" ;

		try {
			statement = connection.prepareStatement(SQL_SELECT_STATE_BY_ID);			
			statement.setString(1,code);
			resultSet = statement.executeQuery();
			if(resultSet.next()){			 
				stateName = resultSet.getString(1); 
			}
		} catch(SQLException e){
			e.printStackTrace();			 
		}  finally {
			CommonUtils.closeDatabaseUtil(statement, connection, null);		 

		}
		return  stateName; 

	}




}
