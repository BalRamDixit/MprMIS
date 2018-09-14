package com.infotech.skills.dao.pia;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.infotech.sgsy.configuration.Persister;
import com.infotech.sgsy.configuration.PersisterImpl;
import com.infotech.skills.hbm.piaprofile.PiaDetailVO;
import com.infotech.skills.skillsReport.piaLog.PiaLogForm;

public class PiaLogDaoImpl {

	protected final Log log = LogFactory.getLog(getClass());

	public List<PiaDetailVO> getPiaStatusList(PiaLogForm piaLogForm) {
		List<PiaDetailVO> piaList = new ArrayList<PiaDetailVO>();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();

		try {
			tr.begin();
			
			Criteria crit = session.createCriteria(PiaDetailVO.class);
			if(piaLogForm.getPiaReportStatus() != null && !piaLogForm.getPiaReportStatus().isEmpty()){
				crit.add(Restrictions.eq("piaStatus", piaLogForm.getPiaReportStatus()));	
			}
			
			piaList = (List<PiaDetailVO>) crit.list();
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session.isOpen())
				session.close();
		}
		return piaList;

	}

	public PiaDetailVO getPiaCurrentStatus(String piaCode) {
		PiaDetailVO piaDetail = new PiaDetailVO();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();

		try {
			tr.begin();
			Criteria crit = session.createCriteria(PiaDetailVO.class).add(Restrictions.eq("piaCode", piaCode));
			piaDetail = (PiaDetailVO) crit.list().get(0);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session.isOpen())
				session.close();
		}
		return piaDetail;

	}

	@SuppressWarnings("unchecked")
	/*public List<PiaDetailVO> getPiaAllLogList(String piaCode) {
		List piaLogList = new ArrayList();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();

		try {
			tr.begin();
			Query query = session.createSQLQuery("SELECT stamp, pia_status, remark, pia_code, pia_registration_no, pia_name"
						+" FROM pia_detail_audit where  pia_code = :piaCode order by stamp");
			query.setParameter("piaCode", piaCode);
			piaLogList = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session.isOpen())
				session.close();
		}
		return piaLogList;

	}*/
	
	public List<PiaDetailVO> getPiaAllLogList(String piaCode) {
		List piaLogList = new ArrayList();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();

		try {
			tr.begin();
			Query query = session.createSQLQuery(" select pia_code,pia_name,"  
                                               + " cast ((case when pia_status='P' and updated_pia_status='P' and modify_by is null then 'Applicant Organization First Page Registration Done and Confirmed by Applicant Organization but Second Page Entry is Pending.' else  "
                                               + " case when pia_status='P' and updated_pia_status='P' and modify_by is not null then 'Applicant Organization First Page Registration Done and Confirmed by Applicant Organization.Second Page Entry is Done but not Confirmed by Applicant Organization.' else " 
                                               + " case when pia_status='P' and updated_pia_status='S' and modify_by is not null  and remark is null then 'Applicant Organization First Page Registration Done and Confirmed by Applicant Organization.Second Page Entry is Done and Confirmed by Applicant Organization.' else " 
                                               + " case when pia_status='S' and updated_pia_status='P' and modify_by is not null  and (remark is not null or updated_remark is not null) then 'Applicant Organization Registration Submitted but found incomplete at Document Checking.' else " 
                                               + " case when pia_status='P' and updated_pia_status='S' and modify_by is not null  and (remark is not null or updated_remark is not null) then 'Applicant Organization Registration Submitted but found incomplete at Document Checking and Corrected by Applicant Organization.' else "
                                               + " case when pia_status='S' and updated_pia_status='C' and modify_by is not null  then 'Applicant Organization Registration Submitted and found proper after Document Checking.' else  "
                                               + " case when pia_status='C' and updated_pia_status='S' and modify_by is not null  then 'Applicant Organization Registration Submitted and found proper after Document Checking but  Reverted back for Document Checking from  final-Verification Level.' else  "
                                               + " case when pia_status='C' and updated_pia_status='R' and modify_by is not null  and (remark is not null or updated_remark is not null) then 'Applicant Organization Registration Submitted and found proper after Document Checking but  found Improper/Rejected at Verification Level.' else " 
                                               + " case when pia_status='R' and updated_pia_status='P' and modify_by is not null  and (remark is not null or updated_remark is not null) then 'Applicant Organization Registration is in progress after found Improper/Rejected at Verification Level.' else  "
                                               + " case when pia_status='P' and updated_pia_status='S' and modify_by is not null  and (remark is not null or updated_remark is not null) then 'Applicant Organization Registration Done and Document Checking is in progress after found Improper/Rejected at Verification Level.' else  " 
                                               + " case when pia_status='C' and updated_pia_status='V' and modify_by is not null  then 'Applicant Organization Registration Done , Document Checking and final Verification is completed.' "
                                               + " else  case when pia_status='V' and updated_pia_status='PW' and modify_by is not null  then 'Applicant Organization was Verified but Withdrawn at L3 ' "
                                               + " else  case when pia_status='V' and updated_pia_status='PD' and modify_by is not null  then 'Applicant Organization was Verified but Debarred at L3 '  "
                                               + " else  case when pia_status='V' and updated_pia_status='PB' and modify_by is not null  then 'Applicant Organization was Verified but Blacklisted at L3 '  else '' "
                                               + " end end end end end end end end end end end end end end ) as character varying) status, "
                                               + "updated_pia_status, " 
                                               + "(case when created_by=''||pia_code||'' then ''||pia_name||'' else 'Mr.'||(select user_name from mst_user where login_id=''||p.created_by||'')||'' end) as creator,created_on,  "
                                               + "(case when updated_by=''||pia_code||'' then ''||pia_name||'' else 'Mr.'||(select user_name from mst_user where login_id=''||p.updated_by||'')||'' end ) as updator,  to_char(cast (updated_on as date) ,'DD-MM-YYYY') as updated from pia_detail_updates as p  where pia_code = :piaCode order by updated_on desc  ");                                                 
			query.setParameter("piaCode", piaCode);
			piaLogList = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session.isOpen())
				session.close();
		}
		return piaLogList;

	}
}
