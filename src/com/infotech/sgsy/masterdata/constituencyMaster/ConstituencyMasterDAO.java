package com.infotech.sgsy.masterdata.constituencyMaster;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.infotech.sgsy.configuration.Persister;
import com.infotech.sgsy.configuration.PersisterImpl;
import com.infotech.sgsy.master.districtMasterNew.DistrictMasterVO;
import com.infotech.sgsy.master.parliamentaryConstituencyMaster.ParliamentaryConstituencyMasterVO;
import com.infotech.sgsy.master.state.StateVO;

public class ConstituencyMasterDAO {

	public boolean save(ConstituencyMasterVO helperVo) throws Exception {
		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(helperVo);
			tx.commit();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}
	
 
	
 public ConstituencyMasterVO getRecordFromId(String id)throws Exception {
		//log.info("====checkRecord method Starts======>");		
 		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
            Criteria criteria = session.createCriteria(ConstituencyMasterVO.class)
                    .add(Restrictions.eq("id", id));

            Object result = criteria.uniqueResult();
            if (result != null) {
            	return (ConstituencyMasterVO)result;
            }
        }
		catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}finally {
            session.close();
        }
		return null;
	}
	
	
	public boolean deleteRecordFromId(ConstituencyMasterVO helperVO) {
		boolean status=false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			session.delete(helperVO);
			tx.commit();
			status = true;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			session.close();
		}
		return status;
	}
	
	 
	public List<ConstituencyMasterVO> getAllRecords(){
		List<ConstituencyMasterVO> list=new ArrayList<ConstituencyMasterVO>();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			Criteria query = session.createCriteria(ConstituencyMasterVO.class);
			
			for(Object o : query.list()) {
			    list.add((ConstituencyMasterVO)o);
			}
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			if (session.isOpen())
				try {
					persister.closeSession();
				} catch (HibernateException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
		System.out.println("List to Print is --> "+list);
		return list;
	}
	
 	public boolean saveOrUpdate(ConstituencyMasterVO helperVO) throws Exception {

		boolean flag = false;
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tx = session.beginTransaction();
		try {
			session.update(helperVO);
			tx.commit();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}



	public List<StateVO> getAllState() {
		List<StateVO> list=new ArrayList<StateVO>();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			Criteria query = session.createCriteria(StateVO.class);
			
			for(Object o : query.list()) {
			    list.add((StateVO)o);
			}
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			if (session.isOpen())
				try {
					persister.closeSession();
				} catch (HibernateException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
		System.out.println("List to Print is --> "+list);
		return list;
	}



	public List<DistrictMasterVO> getDistrict(String stateId) {
		List<DistrictMasterVO> list=new ArrayList<DistrictMasterVO>();
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			Criteria query = session.createCriteria(DistrictMasterVO.class).add(Restrictions.eq("state.stateId", stateId));
			
			for(Object o : query.list()) {
			    list.add((DistrictMasterVO)o);
			}
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			if (session.isOpen())
				try {
					persister.closeSession();
				} catch (HibernateException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
		System.out.println("List to Print is --> "+list);
		return list;
	}



	public String getDistrictById(String districtid) {
		Persister persister = PersisterImpl.getPersister();
		Session session = persister.getSession();
		Transaction tr = session.getTransaction();
		String stateId="";
		try {
			tr.begin();
			Criteria query1 = session.createCriteria(DistrictMasterVO.class).add(Restrictions.eq("districtId", districtid));
			stateId=((DistrictMasterVO)query1.uniqueResult()).getState().getStateId();
			System.out.println("State Id is --> "+stateId);
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			if (session.isOpen())
				try {
					persister.closeSession();
				} catch (HibernateException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
		return stateId;
	}



	public List<ParliamentaryConstituencyMasterVO> getAllParliamentaryConstituencyList() {
			List<ParliamentaryConstituencyMasterVO> list=new ArrayList<ParliamentaryConstituencyMasterVO>();
			Persister persister = PersisterImpl.getPersister();
			Session session = persister.getSession();
			Transaction tr = session.getTransaction();
			try {
				tr.begin();
				Criteria query = session.createCriteria(ParliamentaryConstituencyMasterVO.class);
				
				for(Object o : query.list()) {
				    list.add((ParliamentaryConstituencyMasterVO)o);
				}
				tr.commit();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				session.close();
				if (session.isOpen())
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
			System.out.println("List to Print is --> "+list);
			return list;
	}
	
	
	
}
