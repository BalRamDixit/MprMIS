package com.infotech.sgsy.stateSetupTarget;

import java.text.SimpleDateFormat;
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
import com.infotech.sgsy.master.appraisalAgencyMaster.AppraisalAgencyVO;
import com.infotech.sgsy.master.ctsaMaster.CtsaMasterVO;
import com.infotech.sgsy.master.state.StateVO;
import com.infotech.sgsy.master.tsaMaster.TsaMasterVO;
import com.infotech.sgsy.userAccessControlManagement.RoleMaster;
import com.infotech.sgsy.userAccessControlManagement.RoleMasterDaoImpl;

public class StateTargetFormAction extends DispatchAction{
	
	public ActionForward showStateTarget(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws HibernateException, Exception
	     {
	    	LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");
	    	//StateTargetForm stateTargetForm=(StateTargetForm) form;
            StateTargetDAO stateTargetDao = new StateTargetDAO();	
	    		
	    	int size=0;
	    	RoleMaster role=new RoleMasterDaoImpl().getRecordFromId(loginVO.getRoleId());
	        if(role.getId().equalsIgnoreCase("45")||role.getId().equalsIgnoreCase("49")){
            	StateTargetVO list=stateTargetDao.getDetails(loginVO.getEntityCode());
   			 if(list!=null){
   				 request.setAttribute("statetargetdetails",list);
   				 request.setAttribute("id",list.getId() );
   			 }
   			 else{
   				 request.setAttribute("statetargetdetails",new StateTargetVO());
   				 request.setAttribute("id",0);
   				 }
   			 //..this is done to get the statename by using stateid . i have state id in kk
   			 
   			 StateVO stateVO= (StateVO) stateTargetDao.getById(StateVO.class,loginVO.getEntityCode());
   			 request.setAttribute("stateName",stateVO.getStateName());
   			 request.setAttribute("appraisalAgencyList", stateTargetDao.getDropDownList( AppraisalAgencyVO.class,new String [] {"id","appraisalName"}));
   			 request.setAttribute("ctsaList", stateTargetDao.getDropDownListTsa( CtsaMasterVO.class,new String [] {"id","ctsaName"}));
   			 request.setAttribute("tsaList", stateTargetDao.getDropDownListTsa( TsaMasterVO.class,new String [] {"id","tsaName"}));
            	
	    	}else{
	    		
			
	    		List<StateTargetVO> stateList=(List<StateTargetVO>) AbsFinder.getList(StateTargetVO.class);
	    		SimpleDateFormat format=new SimpleDateFormat("dd-MM-yyyy");
	    		for(StateTargetVO vo:stateList){
	    			if(vo.getAnnualPlan()!=null){
	    				String date=format.format(vo.getAnnualPlan());
	    				
	    				vo.setDateformat(date);
	    				
	    			}
	    			
	    		}
	    		request.setAttribute("stateList",stateList);
	    		size=1;
	    	}
	    	request.setAttribute("size", size); 	 
			return mapping.findForward("target");
	     }

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{  	
		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");
		StateTargetForm stateTargetForm=(StateTargetForm) form;
		StateTargetVO stateTargetVo = new StateTargetVO();
		StateTargetDAO stateTargetDao = new StateTargetDAO();
				
		try {
			   // BeanUtils.copyProperties(stateTargetVo, stateTargetForm);
			   
			StateVO state = new StateVO();
			state.setStateId(loginVO.getEntityCode());
			stateTargetVo.setState(state);
			stateTargetVo.setUserId(loginVO.getUserid());
			stateTargetVo.setAnnualPlan(stateTargetForm.getAnnualPlan());
			stateTargetVo.setUserId(loginVO.getUserid());
			stateTargetVo.setScheme(stateTargetForm.getScheme());
			stateTargetVo.setAnnualPlan(stateTargetForm.getAnnualPlan());
			
			if(!"0".equalsIgnoreCase(stateTargetForm.getCtsa())){
	     		CtsaMasterVO ctsa = new CtsaMasterVO();
	     		  ctsa.setId(stateTargetForm.getCtsa());
	     		 stateTargetVo.setCtsa(ctsa);
	     	}else{
	     		 stateTargetVo.setCtsa(null);
	     	}
		  
		   if(!"0".equalsIgnoreCase(stateTargetForm.getAppraisalAgency())){
			   AppraisalAgencyVO agencyVO=new AppraisalAgencyVO();
			   agencyVO.setId(stateTargetForm.getAppraisalAgency());
			   stateTargetVo.setAppraisalAgency(agencyVO);
	     	}else{
	     		 stateTargetVo.setAppraisalAgency(null);
	     	}
			if(!"0".equalsIgnoreCase(stateTargetForm.getTsaName())){
				TsaMasterVO tsaMasterVO=new TsaMasterVO();
				tsaMasterVO.setId(stateTargetForm.getTsaName());
				 stateTargetVo.setTsaName(tsaMasterVO);
	     	}else{
	     		stateTargetVo.setTsaName(null);
	     	}
			stateTargetVo.setStateMis(stateTargetForm.getStateMis());
			stateTargetVo.setApplicationName(stateTargetForm.getApplicationName());
			stateTargetVo.setSopCompliant(stateTargetForm.getSopCompliant());
			stateTargetVo.setCentralMis(stateTargetForm.getCentralMis());
			stateTargetVo.setCreatedBy(loginVO.getUserid()); // --------------------------------------------------------------------------update
																// after user
																// module
			stateTargetVo.setCreatedOn(new Date());
    	     	 
    	     	stateTargetDao.save(stateTargetVo);
    	      	       
    	  
  		}	
			  
       catch (Exception e)
       {
    	  e.printStackTrace();
       }
		int size=0;
		RoleMaster role=new RoleMasterDaoImpl().getRecordFromId(loginVO.getRoleId());
	     if(role.getId().equalsIgnoreCase("45")||role.getId().equalsIgnoreCase("49")){
			StateTargetVO list=stateTargetDao.getDetails(loginVO.getEntityCode());
			 if(list!=null){
				 request.setAttribute("statetargetdetails",list);
				 request.setAttribute("id",list.getId() );
			 }
			 else{
				 request.setAttribute("statetargetdetails",new StateTargetVO());
				 request.setAttribute("id",0);
				 }
			 //..this is done to get the statename by using stateid . i have state id in kk
			 
			 StateVO stateVO= (StateVO) stateTargetDao.getById(StateVO.class,loginVO.getEntityCode());
			 request.setAttribute("stateName",stateVO.getStateName());
			 request.setAttribute("appraisalAgencyList", stateTargetDao.getDropDownList( AppraisalAgencyVO.class,new String [] {"id","appraisalName"}));
			 request.setAttribute("ctsaList", stateTargetDao.getDropDownListTsa( CtsaMasterVO.class,new String [] {"id","ctsaName"}));

			 request.setAttribute("tsaList", stateTargetDao.getDropDownListTsa( TsaMasterVO.class,new String [] {"id","tsaName"}));
    	}else{
    		List<StateTargetVO> stateList=(List<StateTargetVO>) AbsFinder.getList(StateTargetVO.class);
    		SimpleDateFormat format=new SimpleDateFormat("dd-MM-yyyy");
    		for(StateTargetVO vo:stateList){
    			if(vo.getAnnualPlan()!=null){
    				vo.setAnnualPlan(format.format(vo.getAnnualPlan()));
    			}
    			
    		}
    		
    		request.setAttribute("stateList",stateList);
    		size=1;
		
    	}
    	request.setAttribute("size", size); 
    return mapping.findForward("target"); 
}
	
	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{ 
		
		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");
		StateTargetForm stateTargetForm=(StateTargetForm) form;
		
		StateTargetDAO stateTargetDao = new StateTargetDAO();
		
		String id=request.getParameter("id");
		System.out.println("id..4."+id);
		
		try {
			StateTargetVO stateTargetVo =(StateTargetVO) AbsFinder.getById(StateTargetVO.class, stateTargetForm.getId());
		
			   StateVO state=new StateVO();
			   state.setStateId(loginVO.getEntityCode());
			   stateTargetVo.setState(state);
		       stateTargetVo.setUserId(loginVO.getUserid()); 
			   stateTargetVo.setAnnualPlan(stateTargetForm.getAnnualPlan());
			   stateTargetVo.setUserId(loginVO.getUserid());
			   stateTargetVo.setScheme(stateTargetForm.getScheme());
 	           stateTargetVo.setAnnualPlan(stateTargetForm.getAnnualPlan());
		     
		     	if(!"0".equalsIgnoreCase(stateTargetForm.getCtsa())){
		     		CtsaMasterVO ctsa = new CtsaMasterVO();
		     		  ctsa.setId(stateTargetForm.getCtsa());
		     		 stateTargetVo.setCtsa(ctsa);
		     	}else{
		     		 stateTargetVo.setCtsa(null);
		     	}
			  
			   if(!"0".equalsIgnoreCase(stateTargetForm.getAppraisalAgency())){
				   AppraisalAgencyVO agencyVO=new AppraisalAgencyVO();
				   agencyVO.setId(stateTargetForm.getAppraisalAgency());
				   stateTargetVo.setAppraisalAgency(agencyVO);
		     	}else{
		     		 stateTargetVo.setAppraisalAgency(null);
		     	}
				if(!"0".equalsIgnoreCase(stateTargetForm.getTsaName())){
					TsaMasterVO tsaMasterVO=new TsaMasterVO();
					tsaMasterVO.setId(stateTargetForm.getTsaName());
					 stateTargetVo.setTsaName(tsaMasterVO);
		     	}else{
		     		stateTargetVo.setTsaName(null);
		     	}
				
				
			  
 	           stateTargetVo.setStateMis(stateTargetForm.getStateMis());
 	           stateTargetVo.setApplicationName(stateTargetForm.getApplicationName());
 	           stateTargetVo.setSopCompliant(stateTargetForm.getSopCompliant());
 	           stateTargetVo.setCentralMis(stateTargetForm.getCentralMis());
 	        
 	           stateTargetVo.setUpdatedBy(loginVO.getUserid());   //--------------------------------------------------------------------------update after user module
 	           stateTargetVo.setUpdatedOn(new Date());
    	     	stateTargetDao.update(stateTargetVo);
    	        	       
    	      
  		
		}	
			  
       catch (Exception e)
       {
    	  e.printStackTrace();
       }
		int size=0;
		RoleMaster role=new RoleMasterDaoImpl().getRecordFromId(loginVO.getRoleId());
	     if(role.getId().equalsIgnoreCase("45")||role.getId().equalsIgnoreCase("49")){
			StateTargetVO list=stateTargetDao.getDetails(loginVO.getEntityCode());
			 if(list!=null){
				 request.setAttribute("statetargetdetails",list);
				 request.setAttribute("id",list.getId() );
			 }
			 else{
				 request.setAttribute("statetargetdetails",new StateTargetVO());
				 request.setAttribute("id",0);
				 }
			 //..this is done to get the statename by using stateid . i have state id in kk
			 
			 StateVO stateVO= (StateVO) stateTargetDao.getById(StateVO.class,loginVO.getEntityCode());
			 request.setAttribute("stateName",stateVO.getStateName());
			 request.setAttribute("appraisalAgencyList", stateTargetDao.getDropDownList( AppraisalAgencyVO.class,new String [] {"id","appraisalName"}));
			 request.setAttribute("ctsaList", stateTargetDao.getDropDownListTsa( CtsaMasterVO.class,new String [] {"id","ctsaName"}));

			 request.setAttribute("tsaList", stateTargetDao.getDropDownListTsa( TsaMasterVO.class,new String [] {"id","tsaName"}));
    	}else{
    		
		 /**/
		 
    		List<StateTargetVO> stateList=(List<StateTargetVO>) AbsFinder.getList(StateTargetVO.class);
    		SimpleDateFormat format=new SimpleDateFormat("dd-MM-yyyy");
    		for(StateTargetVO vo:stateList){
    			if(vo.getAnnualPlan()!=null){
    				vo.setAnnualPlan(format.format(vo.getAnnualPlan()));
    			}
    			//System.out.println("date -->   "+ vo.getAnnualPlan());
    		}
    		request.setAttribute("stateList",stateList);
    		size=1;
		 
		 
    	}
    	request.setAttribute("size", size); 
    return mapping.findForward("target"); 
}

	 
}
