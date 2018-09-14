package com.infotech.sgsy.stateSanctionDetail;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.hibernate.HibernateException;

import com.infotech.sgsy.login.LoginVO;
import com.infotech.sgsy.master.AbsFinder;
import com.infotech.sgsy.master.state.StateVO;
import com.infotech.sgsy.stateFinance.StateFinancialVO;
import com.infotech.sgsy.stateSetupTarget.StateTargetVO;
import com.infotech.sgsy.userAccessControlManagement.RoleMaster;
import com.infotech.sgsy.userAccessControlManagement.RoleMasterDaoImpl;

public class StateSanctionAction  extends DispatchAction {
	
					StateSanctionDAO helperDao = new StateSanctionDAO();
 				
		public ActionForward show(ActionMapping mapping, ActionForm form, HttpServletRequest request,
							HttpServletResponse response) throws HibernateException, Exception {
						LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");
						
						
						int size=0;
						RoleMaster role=new RoleMasterDaoImpl().getRecordFromId(loginVO.getRoleId());
					     if(!(role.getId().equalsIgnoreCase("45")||role.getId().equalsIgnoreCase("49"))){
			            	List<StateSanctionVO> statesanctionList=(List<StateSanctionVO>) AbsFinder.getList(StateSanctionVO.class);
				    		request.setAttribute("statesanctionList",statesanctionList);
				    		size=1;
				    	}else{
						 List<StateSanctionVO> list = helperDao.getDetails(loginVO.getEntityCode());
						 Date date=new Date();
						 Calendar cal = Calendar.getInstance();
							cal.setTime(date);
							int year = cal.get(Calendar.YEAR);
							int minyear=year-5;
							List<Integer> yearint=new ArrayList<Integer>();
							for(int i=minyear;i<year;i++){
								yearint.add(i);
							}
						 request.setAttribute("year",yearint);
						if (list.size() > 0) {
							request.setAttribute("sanctiondetails", list.get(0));
							request.setAttribute("id", list.get(0).getId());
						} else {
							request.setAttribute("sanctiondetails", new StateSanctionVO());
							request.setAttribute("id", 0);
						}
				    	}
			            request.setAttribute("size", size); 	
					           
		return mapping.findForward("target_sanction");
		}

public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {  	
		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");
        StateSanctionForm helperForm = (StateSanctionForm) form;
		StateSanctionVO sanctionVo = new StateSanctionVO();
			
		try {
			sanctionVo.setTargetStartedYear(helperForm.getTargetStartedYear());
			sanctionVo.setTargetEndYear(helperForm.getTargetEndYear());
			sanctionVo.setSanctionedTrainingTarget(helperForm.getSanctionedTrainingTarget());
			sanctionVo.setFundSanctioned(helperForm.getFundSanctioned());
			sanctionVo.setSanctionDate(helperForm.getSanctionDate());
						//...new added....
			sanctionVo.setSkillGapAssessment(helperForm.getSkillGapAssessment());
			sanctionVo.setiEC(helperForm.getiEC());
			sanctionVo.setAlumniSupport(helperForm.getAlumniSupport());
			sanctionVo.setCapacityBuilding(helperForm.getCapacityBuilding());
			sanctionVo.setMonitoringEvaluation(helperForm.getMonitoringEvaluation());
			sanctionVo.setStaffBlockLevelBelow(helperForm.getStaffBlockLevelBelow());
			sanctionVo.setJobMela(helperForm.getJobMela());
			sanctionVo.setMigrationSupportCentre(helperForm.getMigrationSupportCentre());
			sanctionVo.setSupportCost(helperForm.getSupportCost());
					
			sanctionVo.setIfscCode(helperForm.getIfscCode());
			sanctionVo.setBankName(helperForm.getBankName());
			sanctionVo.setAccountNo(helperForm.getAccountNo());
			sanctionVo.setAgencyCode(helperForm.getAgencyCode());
			sanctionVo.setPfms(helperForm.getPfms());
		//	sanctionVo.setProjectName(helperForm.getProjectName());
			
			           //...ends added....
			sanctionVo.setCreatedBy(loginVO.getUserid());  
			sanctionVo.setCreatedOn(new Date());
			StateVO state = new StateVO();
			state.setStateId(loginVO.getEntityCode());
			sanctionVo.setState(state);
			
			System.out.println("data to save is --> "+sanctionVo);
			helperDao.save(sanctionVo);

			
			int size=0;
			RoleMaster role=new RoleMasterDaoImpl().getRecordFromId(loginVO.getRoleId());
		     if(!(role.getId().equalsIgnoreCase("45")||role.getId().equalsIgnoreCase("49"))){
            	List<StateSanctionVO> statesanctionList=(List<StateSanctionVO>) AbsFinder.getList(StateSanctionVO.class);
	    		request.setAttribute("statesanctionList",statesanctionList);
	    		size=1;
	    	}else{
			 List<StateSanctionVO> list = helperDao.getDetails(loginVO.getEntityCode());
			 Date date=new Date();
			 Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				int year = cal.get(Calendar.YEAR);
				int minyear=year-5;
				List<Integer> yearint=new ArrayList<Integer>();
				for(int i=minyear;i<year;i++){
					yearint.add(i);
				}
			 request.setAttribute("year",yearint);
			if (list.size() > 0) {
				request.setAttribute("sanctiondetails", list.get(0));
				request.setAttribute("id", list.get(0).getId());
			} else {
				request.setAttribute("sanctiondetails", new StateSanctionVO());
				request.setAttribute("id", 0);
			}
	    	}
            request.setAttribute("size", size); 
			request.setAttribute("targetStartedYear", helperForm.getTargetStartedYear());
			request.setAttribute("targetEndYear", helperForm.getTargetEndYear());

			helperForm.reset(mapping, request);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return mapping.findForward("target_sanction");
					}


	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{  	
		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");
		StateSanctionForm helperForm = (StateSanctionForm) form;
		//= new StateSanctionVO();
	try {
		   
		StateVO state = new StateVO();
		StateSanctionVO sanctionVo = (StateSanctionVO) AbsFinder.getById(StateSanctionVO.class, helperForm.getId());
		
		state.setStateId(loginVO.getEntityCode());
		sanctionVo.setState(state);
		
		       sanctionVo.setTargetStartedYear(helperForm.getTargetStartedYear());
		       sanctionVo.setTargetEndYear(helperForm.getTargetEndYear());
		       sanctionVo.setSanctionedTrainingTarget(helperForm.getSanctionedTrainingTarget());
		       sanctionVo.setFundSanctioned(helperForm.getFundSanctioned());
		       sanctionVo.setSanctionDate(helperForm.getSanctionDate()); 
		     //...new added....
				sanctionVo.setSkillGapAssessment(helperForm.getSkillGapAssessment());
				sanctionVo.setiEC(helperForm.getiEC());
				sanctionVo.setAlumniSupport(helperForm.getAlumniSupport());
				sanctionVo.setCapacityBuilding(helperForm.getCapacityBuilding());
				sanctionVo.setMonitoringEvaluation(helperForm.getMonitoringEvaluation());
				sanctionVo.setStaffBlockLevelBelow(helperForm.getStaffBlockLevelBelow());
				sanctionVo.setJobMela(helperForm.getJobMela());
				sanctionVo.setMigrationSupportCentre(helperForm.getMigrationSupportCentre());
				sanctionVo.setSupportCost(helperForm.getSupportCost());
				
				sanctionVo.setIfscCode(helperForm.getIfscCode());
				sanctionVo.setBankName(helperForm.getBankName());
				sanctionVo.setAccountNo(helperForm.getAccountNo());
				sanctionVo.setAgencyCode(helperForm.getAgencyCode());
				sanctionVo.setPfms(helperForm.getPfms());
			//	sanctionVo.setProjectName(helperForm.getProjectName());
				
				           //...ends added....
		       sanctionVo.setUpdatedBy(loginVO.getUserid());   //--------------------------------------------------------------------------update after user module
		       sanctionVo.setUpdatedOn(new Date());
		     //  sanctionVo.setId(helperForm.getId());

		      helperDao.update(sanctionVo);
		
		      int size=0;
		      RoleMaster role=new RoleMasterDaoImpl().getRecordFromId(loginVO.getRoleId());
			     if(!(role.getId().equalsIgnoreCase("45")||role.getId().equalsIgnoreCase("49"))){
	            	List<StateSanctionVO> statesanctionList=(List<StateSanctionVO>) AbsFinder.getList(StateSanctionVO.class);
		    		request.setAttribute("statesanctionList",statesanctionList);
		    		size=1;
		    	}else{
				 List<StateSanctionVO> list = helperDao.getDetails(loginVO.getEntityCode());
				 Date date=new Date();
				 Calendar cal = Calendar.getInstance();
					cal.setTime(date);
					int year = cal.get(Calendar.YEAR);
					int minyear=year-5;
					List<Integer> yearint=new ArrayList<Integer>();
					for(int i=minyear;i<year;i++){
						yearint.add(i);
					}
				 request.setAttribute("year",yearint);
				if (list.size() > 0) {
					request.setAttribute("sanctiondetails", list.get(0));
					request.setAttribute("id", list.get(0).getId());
				} else {
					request.setAttribute("sanctiondetails", new StateSanctionVO());
					request.setAttribute("id", 0);
				}
		    	}
	            request.setAttribute("size", size); 
			    request.setAttribute("targetStartedYear",helperForm.getTargetStartedYear());
			    request.setAttribute("targetEndYear",helperForm.getTargetEndYear());
 				
		       helperForm.reset(mapping, request);
	     }	
		  
          catch (Exception e)
       {
	        e.printStackTrace();
       }
         return mapping.findForward("target_sanction"); 
  }



	 
}

