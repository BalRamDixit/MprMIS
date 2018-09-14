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
import com.infotech.skills.skillsReport.piaStatus.PiaStatusForm;

public class PiaStatusDaoImpl {

	protected final Log log = LogFactory.getLog(getClass());

	public List getPiaList(PiaStatusForm piaStatusForm) {
		List piaList = new ArrayList();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		String piaStatusQuery = "SELECT pia_code, (case when p.pia_status = 'V' then 'Verified'               when  p.pia_status = 'S'"
				+ " then 'Submitted'               when  p.pia_status = 'C' then 'Checked'               when  p.pia_status = 'R'"
				+ " then 'Rejected'               when  p.pia_status = 'P' then 'Incomplete'              End) as pia_status,"
				+ "  (case when char_length(pia_confirmation_date)<8 or pia_confirmation_date is null  then '-' else pia_confirmation_date "
				+ "End) as pia_confirmation_date ,  (case when char_length(created_on)<8 or created_on is null  then '-' else created_on"
				+ " End) as created_on,  pia_name,  coalesce(pia_registration_no,'-') as pia_registration_no,  (select category_name from "
				+ "pia_category  where  category_id =pia_category_code) as category_name,  cast((SELECT coalesce(array_agg(at.activity_name)"
				+ ", null) FROM pia_activity_mapping am,  pia_activity at WHERE am.activity_id = at.activity_id AND"
				+ " am.pia_code = p.pia_code)as text) as pia_activity,  address,  (select state_name from mst_state as st"
				+ " where st.state_code = p.state_code) as state_name,   coalesce( (select (case when char_length(district_name)<1 or"
				+ " district_name is null  then '-' else district_name End) as district_name from mst_district as dt where "
				+ "dt.district_code = p.district_code),'-' ) as  district_name ,   coalesce((select coalesce((case when "
				+ "(char_length(block_name)<1 or block_name is null ) then '-' else block_name End),'-') as block_name from mst_block"
				+ " as bk where bk.block_code =p.block_code) ,'-')as  block_name ,  pin,  office_phone,  office_fax, email,  website, "
				+ " (select state_name from mst_state as st where st.state_code = p.registration_state_code) as state_registered, "
				+ " registration_number,  date_of_registration, pan_no, tan_no,  coalesce(tin_no,'-') as tin_no,   "
				+ "coalesce(reg_no_sec_12a,'-') as  reg_no_sec_12a,   (case when char_length(reg_date_sec_12a)<1 or reg_date_sec_12a is"
				+ " null  then '-' else reg_date_sec_12a End) as reg_date_sec_12a ,  (case when char_length(reg_no_sec_80g)<1 or"
				+ " reg_no_sec_80g is null  then '-' else reg_no_sec_80g End) as reg_no_sec_80g ,"
				+ "  (case when char_length(reg_date_sec_80g)<1 or reg_date_sec_80g is null  then '-' else reg_date_sec_80g End) "
				+ "as reg_date_sec_80g ,   (case when char_length(reg_no_fcra)<1 or reg_no_fcra is null  then '-' else reg_no_fcra End)"
				+ " as reg_no_fcra ,   (case when char_length(reg_date_fcra)<1 or reg_date_fcra is null  then '-' else reg_date_fcra End)"
				+ " as  reg_date_fcra ,  coalesce(address_land_building,'-') as address_land_building  ,  coalesce(freehold_mortgaged,'-')"
				+ " as  freehold_mortgaged,  coalesce(remark,'-') as remark  FROM pia_detail as p   ";
         if(piaStatusForm != null)
 			if(piaStatusForm.getPiaReportStatus() != null && !piaStatusForm.getPiaReportStatus().equals("") && !piaStatusForm.getPiaReportStatus().equals("A")){
 				piaStatusQuery = piaStatusQuery + " where pia_status = :piaStatus ";
 			}
         piaStatusQuery= piaStatusQuery+ " order by pia_status, pia_name";

		try {
			tr.begin();			
			Query query = session.createSQLQuery(piaStatusQuery);
			if(piaStatusForm != null)
			if(piaStatusForm.getPiaReportStatus() != null && !piaStatusForm.getPiaReportStatus().equals("") && !piaStatusForm.getPiaReportStatus().equals("A")){
				query.setParameter("piaStatus", piaStatusForm.getPiaReportStatus());	
			}
			piaList =  query.list();	
			
			
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session.isOpen())
				session.close();
		}
		return piaList;

	}

}
