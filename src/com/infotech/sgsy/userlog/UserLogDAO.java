package com.infotech.sgsy.userlog; 

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import com.infotech.sgsy.util.*;
import com.infotech.sgsy.configuration.PersisterImpl;


public class UserLogDAO {

	public List search(String userCode,String fromdate,String todate) throws Exception {

		PreparedStatement stm = null;
		Connection con  = PersisterImpl.getConnection();	 
		int i=0;
		if( userCode == null ){
			userCode=""; 
		}	
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		String SEARCH_QUERY=" select * from mst_user_loginlog ";
		
		if( userCode !=null && userCode.trim().length() != 0 ) 
			SEARCH_QUERY = SEARCH_QUERY + "  where user_name  like ? " ;
		else 
			SEARCH_QUERY = SEARCH_QUERY + "  where user_name  like '%' " ;
		
		if( fromdate !=null && fromdate.trim().length() != 0 ) 
			SEARCH_QUERY = SEARCH_QUERY + " and  date_trunc('day',login_time) >= ? " ;			
		if( todate !=null && todate.trim().length() != 0 ) 
			SEARCH_QUERY = SEARCH_QUERY + " and date_trunc('day',login_time) <=?  " ;

		SEARCH_QUERY = SEARCH_QUERY + " order by  login_time" ;		
		ResultSet rs = null;
		List dataList = new ArrayList();

		try{
			stm = con.prepareStatement(SEARCH_QUERY);
			int index = 1;
			if( userCode !=null && userCode.trim().length() != 0 ){ 
				stm.setString(index, "%" + userCode.trim() + "%");
				index++;
			}
			if( fromdate != null && fromdate.trim().length() != 0 ){ 
				stm.setDate(index, new java.sql.Date(new com.infotech.sgsy.util.Util().getDate(fromdate).getTime()));
				index++;
			}
			if( todate != null && todate.trim().length() != 0 ){ 
				stm.setDate(index, new java.sql.Date(new com.infotech.sgsy.util.Util().getDate(todate).getTime()));
				index++;
			}

			rs = stm.executeQuery();
			UserLogDTO dataDTO = null;			
			String status = "";
			while(rs.next()){
				dataDTO =  new UserLogDTO();
				dataDTO.setUsername(rs.getString("user_name"));
				dataDTO.setTimestamp(sdf.format(rs.getTimestamp("login_time")));
				dataDTO.setIp(rs.getString("ip_address"));				
				if(rs.getString("login_status")!=null){				
					if(rs.getString("login_status").equals("Logged"))
						status="Login Successful";
					else if(rs.getString("login_status").equals("Failed"))
						status="Login Failed";
					else
						status="Logout";
				}				
				dataDTO.setLogin_status(status);			
				dataList.add(dataDTO);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			CommonUtils.closeDatabaseUtil(stm, con, rs);
		}
		return dataList;		
	}
}