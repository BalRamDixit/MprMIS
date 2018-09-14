package com.infotech.sgsy.uploadcircular;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.infotech.sgsy.common.MasterDAO;
import com.infotech.sgsy.common.MasterVO;
import com.infotech.sgsy.common.uploadCircularVO;
import com.infotech.sgsy.configuration.Persister;
import com.infotech.sgsy.configuration.PersisterImpl;
import com.infotech.sgsy.util.CommonUtils;
import com.infotech.sgsy.util.Constants;


public class UploadCircularDAO implements MasterDAO{
	
	protected final Log log = LogFactory.getLog(getClass());
	
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
		return 0;
	}
	
	public static List<uploadCircularVO> schemeUpload() throws Exception{
		Connection con = null;
		PreparedStatement stm = null ;
		ResultSet rs = null ;
		List<uploadCircularVO> listscheme=new ArrayList<uploadCircularVO>();
		String queryCircular="select * from mst_circular_scheme ";
		try {
			con =PersisterImpl.getConnection();
			con.setAutoCommit(false);
			stm = con.prepareStatement(queryCircular);			
			rs = stm.executeQuery();
			while (rs.next()) {		
				
				uploadCircularVO uploadVO = new uploadCircularVO();
				uploadVO.setCircularScheme_code((rs.getString("scheme_id")));
				uploadVO.setCircularScheme((rs.getString("scheme_name")));
				listscheme.add(uploadVO);
			}		
			con.commit();
		} catch(SQLException e){
			e.printStackTrace();
			//throw e;			
		}catch (Exception e) {
			e.printStackTrace();		
		} finally {
			CommonUtils.closeDatabaseUtil(stm, con, rs);
		}
		return listscheme;
	}
	 
	 
	public int maxid() throws Exception {
		int next=0;
		Connection con = null;
		PreparedStatement stm = null ;
		ResultSet rs = null ;
		
		String queryCircular="select max(ulid) as id from mst_uploadCircularVO ";
		con=PersisterImpl.getConnection();
		con.setAutoCommit(false);
		stm=con.prepareStatement(queryCircular);
		rs=stm.executeQuery();
		while(rs.next()){
			next=rs.getInt("id")+1;
		}
		
		return next;
		
	}
	public int maxidScheme() throws Exception {
		int next=0;
		Connection con = null;
		PreparedStatement stm = null ;
		ResultSet rs = null ;		
		String queryCircular="select count(scheme_id) as id from mst_circular_scheme";
		con=PersisterImpl.getConnection();
		con.setAutoCommit(false);
		stm=con.prepareStatement(queryCircular);
		rs=stm.executeQuery();
		while(rs.next())
		{
			next=rs.getInt("id")+1;
		}
		
		return next;
		
	}
	
	public static List<uploadCircularVO> CircularDetaill(String circularScheme) throws Exception{
		Connection con = null;
		PreparedStatement stm = null ;
		ResultSet rs = null ;
		List<uploadCircularVO> listscheme=new ArrayList<uploadCircularVO>();
		String queryCircular="select ulid,circular_scheme,circular_desc,circular_file_name, circular_file_name_hindi " +
				" from mst_uploadCircularVO where circular_scheme=? and show='Y'";
		try {
			con =PersisterImpl.getConnection();
			con.setAutoCommit(false);
			stm = con.prepareStatement(queryCircular);	
			stm.setString(1,circularScheme );
			rs = stm.executeQuery();
			while (rs.next()) {		
				uploadCircularVO uploadVO = new uploadCircularVO();
				uploadVO.setUploadCircularCode(Integer.toString(rs.getInt("ulid"))+"_"+rs.getString("circular_file_name"));
				uploadVO.setUploadCircularCodeHindi((Integer.parseInt(rs.getString("ulid"))) + "_Hindi_" + rs.getString("circular_file_name_hindi"));
				uploadVO.setCircularScheme((rs.getString("circular_scheme")));
				uploadVO.setUploadCircularName((rs.getString("circular_desc")));
				uploadVO.setUploadCircularFileName(rs.getString("circular_file_name"));
				uploadVO.setUploadCircularFileNameHindi(rs.getString("circular_file_name_hindi"));
				listscheme.add(uploadVO);
			}		
			con.commit();
		} catch(SQLException e){
			e.printStackTrace();
			//throw e;
			
		} catch (Exception e) {
			e.printStackTrace();
		
		} finally {
			CommonUtils.closeDatabaseUtil(stm, con, rs);

		}
		return listscheme;
	}
	
	public int save(uploadCircularVO udv) throws Exception {
		Connection con = null;
		PreparedStatement stm = null ;
		ResultSet rs = null ;
		//String queryCircular="INSERT INTO  mst_circular_scheme values(1,"+udv.getCircularScheme()+","+udv.getUploadCircularName()+","+udv.getUploadCircularDate()+","+udv.getServerDate()+","+udv.getUploadCircularFileName()+")";
	    int index=maxid();
		String sqlInsertUpload = "INSERT INTO  mst_uploadCircularVO values(?,?,?,?,?,now(),?,?)";
		log.info("inside update method");
		try{	
			uploadCircularVO userVO = (uploadCircularVO)udv;
				con =PersisterImpl.getConnection();		
				con.setAutoCommit(false);
				stm = con.prepareStatement(sqlInsertUpload);
				stm.setInt(1,index);
				stm.setString(2,userVO.getCircularScheme());
				stm.setString(3,userVO.getUploadCircularName());
				stm.setString(4,userVO.getUploadCircularDate());
				stm.setString(5,userVO.getUploadCircularFileName());
				stm.setString(6, userVO.getUploadCircularFileNameHindi());
				stm.setString(7, "Y");
				stm.executeUpdate();
				con.commit(); 	
		}
		 catch(SQLException e){
			e.printStackTrace();
			//throw e;
		} catch (Exception e) {
			e.printStackTrace();
		
		} finally {
			CommonUtils.closeDatabaseUtil(stm, con, rs);
		}
		return Constants.ADD_SUCCESS;
	}
	
	public int add(String schemeName) throws Exception {	
		Connection con = null;
		PreparedStatement stm = null ;
		ResultSet rs = null ;
		
	    int index=maxidScheme();
		String sqlInsertUpload = "INSERT INTO  mst_circular_scheme values(?,?)";
		
		log.info("inside update method");
		try {
			con = PersisterImpl.getConnection();
			con.setAutoCommit(false);
			stm = con.prepareStatement(sqlInsertUpload);
			stm.setInt(1, index);
			stm.setString(2, schemeName);
			stm.executeUpdate();
			con.commit();
		}
		 catch(SQLException e){
			e.printStackTrace();
			//throw e;
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			CommonUtils.closeDatabaseUtil(stm, con, rs);
		}
		return Constants.ADD_SUCCESS;
	}
		
	
	public static uploadCircularVO getCircularDetails(String schemeCode, String circularDescription) {
		uploadCircularVO uploadCircularVO = new uploadCircularVO();
		Connection con = null;
		PreparedStatement ps = null ;
		ResultSet rs = null ;
		try {
			con = PersisterImpl.getConnection();
			ps = con.prepareStatement("select * from mst_uploadcircularvo where circular_scheme = ? and circular_desc = ?");
			ps.setString(1, schemeCode);
			ps.setString(2, circularDescription);
			rs = ps.executeQuery();
			if(rs.next()) {
				uploadCircularVO.setUploadCircularDate(rs.getString("circular_date"));
				uploadCircularVO.setUploadCircularName(circularDescription);
				uploadCircularVO.setId(rs.getInt("ulid"));
				uploadCircularVO.setUploadCircularFileName(rs.getString("circular_file_name"));
				uploadCircularVO.setUploadCircularFileNameHindi(rs.getString("circular_file_name_hindi"));
				uploadCircularVO.setShow(rs.getString("show"));
			}
			con.commit();
		}
		catch(Exception e) {
			System.out.println(("Error getting circular details. " + e.getMessage()));
		}
		finally {
			try {
				CommonUtils.closeDatabaseUtil(ps, con, rs);
			} catch(SQLException e) {
				System.out.println("Error while closing connection. " + e.getMessage());
			}
		}
		return uploadCircularVO;
	}
	
	public boolean modify(uploadCircularVO uploadCircularVO) {
		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			session.update(uploadCircularVO);
			tr.commit();
			flag = true;
		}
		catch(Exception e) {
			log.error("Exception updating circular. " + e.getMessage());
		}
		finally{
			if(session.isOpen()){
				session.close();	
			}
		}
		return flag;
	}
	 
}