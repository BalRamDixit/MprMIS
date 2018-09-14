package com.infotech.skills.skillsSanctionOrders;


import java.util.ArrayList;
import java.util.List;

import org.apache.struts.upload.FormFile;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.infotech.sgsy.configuration.Persister;
import com.infotech.sgsy.configuration.PersisterImpl;
import com.infotech.sgsy.master.state.StateVO;
import com.infotech.skills.hbm.piaprofile.PiaDetailVO;

public class SanctionOrdersDaoImpl implements SanctionOrdersDao{
	
	
	public List<PiaDetailVO> getPiaDetail() throws Exception {
		List<PiaDetailVO> piaDetail = new ArrayList<PiaDetailVO>();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();

		try {
			tr.begin();
			Criteria crit = session.createCriteria(PiaDetailVO.class);
			crit.add( Restrictions.eq("piaStatus","V"));
			crit.addOrder(Order.asc("piaName"));
			piaDetail = crit.list();
			tr.commit();			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session.isOpen())			
				session.close();
		}
		return piaDetail;
	}
	
	public String getPRNumber( String piaCode) throws Exception {
		String prnNumber = null;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		String sqlQuery= "select pia_registration_no from pia_detail where pia_code = :piaCode ";

		try {
			tr.begin();
			Query queryList = session.createSQLQuery(sqlQuery);
			queryList.setString("piaCode", piaCode);
			prnNumber = (String) queryList.uniqueResult();
			tr.commit();			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session.isOpen())			
				session.close();
		}
		return prnNumber;
	}

	public boolean insert(SanctionOrdersVO sanctionvo) throws Exception {
		boolean flag=false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.beginTransaction();	
		try{
			session.save(sanctionvo);
			tr.commit();
			flag=true;
		}
		catch (Exception e) {
			System.err.println(e);
		}
		finally{
		session.close();
		}
		return flag;
	}
	
	public String checkFileType(FormFile file) throws Exception {
		String result = null;
		String contenttype = file.getContentType();
		String fileName = file.getFileName();
		// Getting the file extension
		String extension;
		if (!fileName.equals("")) {
			int dotPos = fileName.lastIndexOf(".");
			extension = fileName.substring(dotPos);
			if ((contenttype.equalsIgnoreCase("application/pdf") && extension.equalsIgnoreCase(".pdf"))
					|| extension.equalsIgnoreCase(".doc")
					|| extension.equalsIgnoreCase(".docx")) {
				if ((file.getFileSize() / 1024) > 7000) {
					result = "File Size greater than 7MB";
				} else {
					result = "true";
				}
			} else {
				result = "File format not supported";
			}
		}

		return result;
	}

	public int getSequenceCodeForSanctionFileUpload(){
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction transaction = session.getTransaction();
		int code = 0;
		String query_sql = "select nextval('sanction_upload_file_seq')";
		try {
			transaction.begin();
			Query query = session.createSQLQuery(query_sql);
			code = ((Number)query.uniqueResult()).intValue();
		}catch (Exception e) {
			// TODO: handle exception
			System.err.println(e);
		}
		return code;	
	}
	
	/*public List getSanctionOrderDetail() throws Exception {
		List sanctionDetail = new ArrayList();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		String sqlQuery= "  select s.sanction_order_no,s.date_of_sanction_order,s.pia_code,s.sanction_file_name,s.pia_reg_no,d.pia_name " 
                         +" from pia_sanction_orders_detail s , pia_detail d "
                         +" where s.pia_code = d.pia_code";

		try {
			tr.begin();
			Query queryList = session.createSQLQuery(sqlQuery);
			sanctionDetail = queryList.list();
			tr.commit();			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session.isOpen())			
				session.close();
		}
		return sanctionDetail;

	}*/
	
	public List getSanctionOrderDetail() throws Exception {
		List sanctionDetail = new ArrayList();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		String sqlQuery= "  select s.sanction_order_no,to_date(s.date_of_sanction_order,'dd-mm-yyyy') as date_of_sanction_order,s.pia_code,s.sanction_file_name,s.pia_reg_no,d.pia_name "
						+ " from pia_sanction_orders_detail s , pia_detail d "
						 +" where s.pia_code = d.pia_code "
						+ " order by to_date(s.date_of_sanction_order,'dd-mm-yyyy')  DESC ";

		try {
			tr.begin();
			Query queryList = session.createSQLQuery(sqlQuery);
			sanctionDetail = queryList.list();
			tr.commit();			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session.isOpen())			
				session.close();
		}
		return sanctionDetail;

	}
	
	public boolean getDuplicateSanctionOrderDetail(String sanctionOrderNo,String piaCode) throws Exception {
		List<SanctionOrdersVO> duplicateSanctionDetail = new ArrayList<SanctionOrdersVO>();
		boolean duplicateSanction = true;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();

		try {
			tr.begin();
			Criteria crit = session.createCriteria(SanctionOrdersVO.class);
			crit.add( Restrictions.eq("sanctionOrderNo",sanctionOrderNo));
			crit.add( Restrictions.eq("piaCode",piaCode));
			duplicateSanctionDetail = crit.list();
			if(duplicateSanctionDetail.size() > 0){
			duplicateSanction = false;
			}else{
				duplicateSanction = true;
			}
			tr.commit();			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session.isOpen())			
				session.close();
		}
		return duplicateSanction;
	}
	
	/*Modify Sanction Order
	Sandeep Tiwari
	23 March 2014*/
	
	@Override
	public List getSanctionNumber(String piaCode) {
		List sanctionNumber = null;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction(); 
		
		try {
			tr.begin();
			Criteria crit = session.createCriteria(SanctionOrdersVO.class);
			crit.add( Restrictions.eq("piaCode",piaCode));
			sanctionNumber = crit.list();
			tr.commit();			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session.isOpen())			
				session.close();
		}
		return sanctionNumber;
	}

	@Override
	public List getSanctionDetail(String sanctionOrderNo) {
		List modifySanctionDetail = null;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction(); 
		
		try {
			tr.begin();
			Criteria crit = session.createCriteria(SanctionOrdersVO.class);
			crit.add( Restrictions.eq("sanctionOrderNo",sanctionOrderNo));
			modifySanctionDetail = crit.list();
			tr.commit();			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session.isOpen())			
				session.close();
		}
		return modifySanctionDetail;
	}
	
	public boolean update(SanctionOrdersVO sanctionvo) {
		boolean flag=false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.beginTransaction();
		try{
			tr.begin();
			session.update(sanctionvo);
			tr.commit();
			flag=true;
		}
		catch (Exception e) {
			System.err.println(e);
		}
		finally{
		session.close();
		}
		return flag;
	}
	
	
	public List getSanctionOrderDetailFromExcel() throws Exception {
		List sanctionDetail = new ArrayList();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		String sqlQuery= "  ";

		try {
			tr.begin();
			Query queryList = session.createSQLQuery(sqlQuery);
			sanctionDetail = queryList.list();
			tr.commit();			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session.isOpen())			
				session.close();
		}
		return sanctionDetail;

	}
	
	
	public List getSanctionOrderDetails(String encd) {

		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();

		List entryStatusDetailList = new ArrayList();

		String code = encd;

		String SQL_Query = null;
		System.out.println("code"+code);
		
		if(code.length()==1){
		SQL_Query= "select * from rpt_ddugjy_pia_state order by state_name";
		}
		else if(code.length()==2){
			SQL_Query= "select * from rpt_ddugjy_pia_district where substring(district_code,1,2)=:code ";
			}
		else if(code.length()==4 && code!=null ){
			SQL_Query= "SELECT state, district, district_code, scheme_type, ctsa, pia_name," + 
					   " overall_target, district_target, pia_contact_person, " +
					   " contact_no  FROM ddu_gky_pia_projects_district where district_code=:code ";
			}

		try {
			tr.begin();
			Query query = session.createSQLQuery(SQL_Query);

			if (code.length() == 2 || code.length() == 4) {
				query.setParameter("code", code);
			}
			
			entryStatusDetailList = query.list();
			tr.commit();

		} catch (Exception e) {

		} finally {
			try {
				persister.closeSession();
			} catch (HibernateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return entryStatusDetailList;

	}

	
}