package com.infotech.sgsy.master.month;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import com.infotech.sgsy.common.MasterDAO;
import com.infotech.sgsy.common.MasterVO;
import com.infotech.sgsy.configuration.PersisterImpl;
import com.infotech.sgsy.util.CommonUtils;
import com.infotech.sgsy.util.PropertyModel;

/**
 * @author NIC 
 * @since July, 2013
 */
public class MonthDAO implements MasterDAO{
	
	
	private static Logger logger = Logger.getRootLogger();

	@Override
	public int delete(MasterVO masterVO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(MasterVO masterVO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(MasterVO masterVO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * @author NIC 
	 * @since July, 2013
	 */
	public List getMonth(){
			List reults=new ArrayList();
			Connection connection=null;
			String query="";
			PreparedStatement stmt=null;
			ResultSet rs=null;
			PropertyModel propertyModel=new PropertyModel();
		
			try
			{
				connection=PersisterImpl.getConnection();
				connection.setAutoCommit(false);
				query="select month_code,month_name from mst_month order by month_code";
				stmt=connection.prepareStatement(query);
				rs=stmt.executeQuery();
				while(rs.next())
				{
					propertyModel=new PropertyModel();
					propertyModel.setPropertyCode(rs.getString("month_code"));
					propertyModel.setPropertyValue(rs.getString("month_name"));
					reults.add(propertyModel);
				}
				connection.commit();
			}catch (Exception e) {
				logger.error("ERROR While getting month inside MonthDAO  : " + e.getMessage() + " ::" +e.getCause());
				e.printStackTrace();			
			}finally{
				try {
					CommonUtils.closeDatabaseUtil(stmt, connection, rs);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return reults;
	}
}
