package MonitoringAlertDAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.infotech.sgsy.configuration.Persister;
import com.infotech.sgsy.configuration.PersisterImpl;

public class CandidateDataDAO {

	
	public List getProjectCandidateList(String entityCode) throws HibernateException, Exception {
		String SQL = null;
		List ProjectCandidateList = new ArrayList();
		
		SQL = "select project_id ,  total_no_of_candidate ,  candidate_with_aadhar ,  candidate_with_bank_account , candidate_with_mobile_no  ,  candidate_aadhar_link_bank_account  ,  candidate_with_insurance , "
				+ " pro_life_trained_candidate  , candidate_foreign_placed  , candidate_placed_earning_more_then_15k  ,  candidate_retain_for_more_then_one_year , created_date,"
				+ "modified_date , user_code  from project_candidate_data  where srlm_code ='"+entityCode+"' order by project_id";
		
		System.out.println("SQL........"+SQL);
		
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			Query query = session.createSQLQuery(SQL);
			//query.setParameter("entityCode", entityCode);
			ProjectCandidateList = query.list();
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			if (session.isOpen())
				persister.closeSession();
		}
		return ProjectCandidateList;
	
	
}
}
