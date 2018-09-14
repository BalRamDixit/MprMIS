package com.infotech.sgsy.stateFinance;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.infotech.sgsy.login.LoginVO;
import com.infotech.sgsy.master.AbsFinder;
import com.infotech.sgsy.stateSanctionDetail.StateSanctionVO;
import com.infotech.sgsy.userAccessControlManagement.RoleMaster;
import com.infotech.sgsy.userAccessControlManagement.RoleMasterDaoImpl;

public class StateFinanceAction extends DispatchAction {
	
	StateFinancialDAO helperDao = new StateFinancialDAO();

	public ActionForward show(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String forward=null;
		
		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");
		RoleMaster role=new RoleMasterDaoImpl().getRecordFromId(loginVO.getRoleId());
		 if(role.getId().equalsIgnoreCase("45")||role.getId().equalsIgnoreCase("49")){
			 StateFinancialVO stateFinancialVO =new StateFinancialDAO().getStateFinancialDetail(loginVO.getEntityCode());
				request.setAttribute("financialVO",stateFinancialVO);
				forward="showPage";
		 }else{
		
		 List<StateFinancialVO> stateFinanceList=(List<StateFinancialVO>) AbsFinder.getList(StateFinancialVO.class);
		 request.setAttribute("statefinanceList",stateFinanceList);
		forward="showPageforAdmin"; 
		 }
		
		return mapping.findForward(forward);
		
	}
	public ActionForward edit(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String forward=null;
		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");
		 
		String id=request.getParameter("id");
		System.out.println("id-------->   "+id);
		if(id!=null && id!=""){
			StateFinancialVO stateFinancialVO =(StateFinancialVO) AbsFinder.getById(StateFinancialVO.class, id);
			request.setAttribute("financialVO",stateFinancialVO);
		}
		
		forward="showPage";
		
		return mapping.findForward(forward);
		
	}
	
	 public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
			String forwards = "Error";
        	long count=0;
        	
        	StateFinanceActionForm helperForm = (StateFinanceActionForm) form;
        	
        	long central_totalprojectcost=helperForm.getCentralProgramCost()+helperForm.getCentralSuportCost()+helperForm.getCentralCtsa();
        	long central_otherprojectcost=helperForm.getCentralAdminCost()+helperForm.getCentralCapacityCost();
        	long central_total_cost=central_totalprojectcost+central_otherprojectcost;
        	
        	
         	helperForm.setCentral_totalprojectcost(central_totalprojectcost);
        	helperForm.setCentral_otherprojectcost(central_otherprojectcost);
        	helperForm.setCentral_totalcost(central_total_cost);
        	
        	long state_totalprojectcost=helperForm.getStateProgramCost()+helperForm.getStateSuportCost()+helperForm.getStateCtsa();
        	long state_otherprojectcost=helperForm.getStateAdminCost()+helperForm.getStateCapacityCost();
        	long state_totalcost=state_totalprojectcost+state_otherprojectcost;
        	
 
        	
        	helperForm.setState_totalprojectcost(state_totalprojectcost);
        	helperForm.setState_otherprojectcost(state_otherprojectcost);
        	helperForm.setState_totalcost(state_totalcost);
        	
        	long centralreleased_totalprojectcost=helperForm.getCentralReleasedProgramCost()+helperForm.getCentralReleasedSuportCost()+helperForm.getCentralReleasedCtsa();
        	long centralreleased_otherprojectcost=helperForm.getCentralReleasedAdminCost()+helperForm.getCentralReleasedCapacityCost();
        	long centralreleased_totalcost=centralreleased_totalprojectcost+centralreleased_otherprojectcost;
        	
 
        	helperForm.setCentralreleased_totalprojectcost(centralreleased_totalprojectcost);
        	helperForm.setCentralreleased_otherprojectcost(centralreleased_otherprojectcost);
        	helperForm.setCentralreleased_totalcost(centralreleased_totalcost);
        	
        	long centralonly_totalprojectcost=helperForm.getCentralOnlyProgramCost()+helperForm.getCentralOnlySuportCost()+helperForm.getCentralOnlyCtsa();
        	long centralonly_otherprojectcost=helperForm.getCentralOnlyAdminCost()+helperForm.getCentralOnlyCapacityCost();
        	long centralonly_totalcost=centralonly_totalprojectcost+centralonly_otherprojectcost;
        	
        	
         	
        	helperForm.setCentralonly_totalprojectcost(centralonly_totalprojectcost);
        	helperForm.setCentralonly_otherprojectcost(centralonly_otherprojectcost);
        	helperForm.setCentralonly_totalcost(centralonly_totalcost);
        	
        	long stateonly_totalprojectcost=helperForm.getStateOnlyProgramCost()+helperForm.getStateOnlySuportCost()+helperForm.getStateOnlyCtsa();
        	long stateonly_otherprojectcost=helperForm.getStateOnlyAdminCost()+helperForm.getStateOnlyCapacityCost();
        	long stateonly_totalcost=stateonly_totalprojectcost+stateonly_otherprojectcost;
        	
 
        	
        	helperForm.setStateonly_totalprojectcost(stateonly_totalprojectcost);
        	helperForm.setStateonly_otherprojectcost(stateonly_otherprojectcost);
        	helperForm.setStateonly_totalcost(stateonly_totalcost);
        	
        	
        	LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");
        	//String createdBy=loginVO.getUserid();
			String stateid = loginVO.getEntityCode();				
           // System.err.println("stateid-->"+stateid);
            StateFinancialDAO statefinancialdAO=new StateFinancialDAO();
            StateSanctionVO  sanctionVO=(StateSanctionVO) statefinancialdAO.getSanctionDetailsByStateId(stateid);
           
         try {
        	StateFinancialVO helperVo = new StateFinancialVO();
	     //	BeanUtils.copyProperties(helperVo, helperForm);
        	helperVo.setCentralProgramCost(helperForm.getCentralProgramCost());
        	helperVo.setCentralSuportCost(helperForm.getCentralSuportCost());
        	helperVo.setCentralCtsa(helperForm.getCentralCtsa());
        	helperVo.setCentralAdminCost(helperForm.getCentralAdminCost());
        	helperVo.setCentralCapacityCost(helperForm.getCentralCapacityCost());
        	helperVo.setStateProgramCost(helperForm.getStateProgramCost());
        	helperVo.setStateSuportCost(helperForm.getStateSuportCost());
        	helperVo.setStateCtsa(helperForm.getStateCtsa());
        	helperVo.setStateAdminCost(helperForm.getStateAdminCost());
        	helperVo.setStateCapacityCost(helperForm.getStateCapacityCost());
        	helperVo.setCentralReleasedProgramCost(helperForm.getCentralReleasedProgramCost());
        	helperVo.setCentralReleasedSuportCost(helperForm.getCentralReleasedSuportCost());
        	helperVo.setCentralReleasedCtsa(helperForm.getCentralReleasedCtsa());
        	helperVo.setCentralReleasedAdminCost(helperForm.getCentralReleasedAdminCost());
        	helperVo.setCentralReleasedCapacityCost(helperForm.getCentralReleasedCapacityCost());
        	helperVo.setCentralOnlyProgramCost(helperForm.getCentralOnlyProgramCost());
        	helperVo.setCentralOnlySuportCost(helperForm.getCentralOnlySuportCost());
        	helperVo.setCentralOnlyCtsa(helperForm.getCentralOnlyCtsa());
        	helperVo.setCentralOnlyAdminCost(helperForm.getCentralOnlyAdminCost());
        	helperVo.setCentralOnlyCapacityCost(helperForm.getCentralOnlyCapacityCost());
        	helperVo.setStateOnlyProgramCost(helperForm.getStateOnlyProgramCost());
        	helperVo.setStateOnlySuportCost(helperForm.getStateOnlySuportCost());
        	helperVo.setStateOnlyCtsa(helperForm.getStateOnlyCtsa());
        	helperVo.setStateOnlyAdminCost(helperForm.getStateOnlyAdminCost());
        	helperVo.setStateOnlyCapacityCost(helperForm.getStateOnlyCapacityCost());
        	helperVo.setCentral_totalprojectcost(helperForm.getCentral_totalprojectcost());
        	helperVo.setCentral_otherprojectcost(helperForm.getCentral_otherprojectcost());
        	helperVo.setCentral_totalcost(helperForm.getCentral_totalcost());
        	helperVo.setState_totalprojectcost(helperForm.getState_totalprojectcost());
        	helperVo.setState_otherprojectcost(helperForm.getState_otherprojectcost());
        	helperVo.setState_totalcost(helperForm.getState_totalcost());
        	helperVo.setCentralreleased_totalprojectcost(helperForm.getCentralreleased_totalprojectcost());
        	helperVo.setCentralreleased_otherprojectcost(helperForm.getCentralreleased_otherprojectcost());
        	helperVo.setCentralreleased_totalcost(helperForm.getCentralreleased_totalcost());
        	helperVo.setCentralonly_totalprojectcost(helperForm.getCentralonly_totalprojectcost());
        	helperVo.setCentralonly_otherprojectcost(helperForm.getCentralonly_otherprojectcost());
        	helperVo.setCentralonly_totalcost(helperForm.getCentralonly_totalcost());
        	helperVo.setStateonly_totalprojectcost(helperForm.getStateonly_totalprojectcost());
        	helperVo.setStateonly_otherprojectcost(helperForm.getStateonly_otherprojectcost());
        	helperVo.setStateonly_totalcost(helperForm.getStateonly_totalcost());
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
	     	
	     	helperVo.setCentralReleasedDateProgramCost(helperForm.getCentralReleasedDateProgramCost());
	     	helperVo.setCentralReleasedDatSuportCost(helperForm.getCentralReleasedDatSuportCost());
	     	helperVo.setCentralReleasedDatCtsa(helperForm.getCentralReleasedDatCtsa());
	     	helperVo.setCentralReleasedDatAdminCost(helperForm.getCentralReleasedDatAdminCost());
	     	helperVo.setCentralReleasedDatCapacityCost(helperForm.getCentralReleasedDatCapacityCost());
	     	helperVo.setStateReleasedDateProgramCost(helperForm.getStateReleasedDateProgramCost());
	     	helperVo.setStateReleasedDateSuportCost(helperForm.getStateReleasedDateSuportCost());
	     	helperVo.setStateReleasedDateCtsa(helperForm.getStateReleasedDateCtsa());
	     	helperVo.setStateReleasedDateAdminCost(helperForm.getStateReleasedDateAdminCost());
	     	helperVo.setStateReleasedDateCapacityCost(helperForm.getStateReleasedDateCapacityCost());
	     	helperVo.setCreatedBy(loginVO.getUserid());        //--------------------------------------------------------------------------update after user module
	     	helperVo.setCreatedOn(new Date());
	     	System.out.println(helperVo.toString());
	     	helperVo.setSanctionDetailId(sanctionVO);
	     	
	     	helperDao.save(helperVo);	     	 
	     	/*StateFinancialVO stateFinancialVO =new StateFinancialDAO().getStateFinancialDetail(loginVO.getEntityCode());
 			request.setAttribute("financialVO",stateFinancialVO);
	     	helperForm.reset(mapping, request);*/
	     	
       }   catch (Exception e) {
	    	  e.printStackTrace();
	     }
         String forward=null;
         RoleMaster role=new RoleMasterDaoImpl().getRecordFromId(loginVO.getRoleId());
         if(role.getId().equalsIgnoreCase("45")||role.getId().equalsIgnoreCase("49")){
			 StateFinancialVO stateFinancialVO =new StateFinancialDAO().getStateFinancialDetail(loginVO.getEntityCode());
				request.setAttribute("financialVO",stateFinancialVO);
				forward="showPage";
		 }else{
		
		 List<StateFinancialVO> stateFinanceList=(List<StateFinancialVO>) AbsFinder.getList(StateFinancialVO.class);
		 request.setAttribute("statefinanceList",stateFinanceList);
		forward="showPageforAdmin"; 
		 }
	    return mapping.findForward(forward); 
}
	 
	 public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
			String forwards = "Error";
     	int count=0;
    	LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");
     	String stateid = loginVO.getEntityCode();				
          StateFinancialDAO statefinancialdAO=new StateFinancialDAO();
     	StateFinanceActionForm helperForm = (StateFinanceActionForm) form;
     	
     	long central_totalprojectcost=helperForm.getCentralProgramCost()+helperForm.getCentralSuportCost()+helperForm.getCentralCtsa();
     	long central_otherprojectcost=helperForm.getCentralAdminCost()+helperForm.getCentralCapacityCost();
     	long central_total_cost=central_totalprojectcost+central_otherprojectcost;
     	
     	helperForm.setCentral_totalprojectcost(central_totalprojectcost);
     	helperForm.setCentral_otherprojectcost(central_otherprojectcost);
     	helperForm.setCentral_totalcost(central_total_cost);
     	
     	long state_totalprojectcost=helperForm.getStateProgramCost()+helperForm.getStateSuportCost()+helperForm.getStateCtsa();
     	long state_otherprojectcost=helperForm.getStateAdminCost()+helperForm.getStateCapacityCost();
     	long state_totalcost=state_totalprojectcost+state_otherprojectcost;
     	
     	helperForm.setState_totalprojectcost(state_totalprojectcost);
     	helperForm.setState_otherprojectcost(state_otherprojectcost);
     	helperForm.setState_totalcost(state_totalcost);
     	
     	long centralreleased_totalprojectcost=helperForm.getCentralReleasedProgramCost()+helperForm.getCentralReleasedSuportCost()+helperForm.getCentralReleasedCtsa();
     	long centralreleased_otherprojectcost=helperForm.getCentralReleasedAdminCost()+helperForm.getCentralReleasedCapacityCost();
     	long centralreleased_totalcost=centralreleased_totalprojectcost+centralreleased_otherprojectcost;
     	
     	helperForm.setCentralreleased_totalprojectcost(centralreleased_totalprojectcost);
     	helperForm.setCentralreleased_otherprojectcost(centralreleased_otherprojectcost);
     	helperForm.setCentralreleased_totalcost(centralreleased_totalcost);
     	
     	long centralonly_totalprojectcost=helperForm.getCentralOnlyProgramCost()+helperForm.getCentralOnlySuportCost()+helperForm.getCentralOnlyCtsa();
     	long centralonly_otherprojectcost=helperForm.getCentralOnlyAdminCost()+helperForm.getCentralOnlyCapacityCost();
     	long centralonly_totalcost=centralonly_totalprojectcost+centralonly_otherprojectcost;
     	
     	helperForm.setCentralonly_totalprojectcost(centralonly_totalprojectcost);
     	helperForm.setCentralonly_otherprojectcost(centralonly_otherprojectcost);
     	helperForm.setCentralonly_totalcost(centralonly_totalcost);
     	
     	long stateonly_totalprojectcost=helperForm.getStateOnlyProgramCost()+helperForm.getStateOnlySuportCost()+helperForm.getStateOnlyCtsa();
     	long stateonly_otherprojectcost=helperForm.getStateOnlyAdminCost()+helperForm.getStateOnlyCapacityCost();
     	long stateonly_totalcost=stateonly_totalprojectcost+stateonly_otherprojectcost;
     	
     	helperForm.setStateonly_totalprojectcost(stateonly_totalprojectcost);
     	helperForm.setStateonly_otherprojectcost(stateonly_otherprojectcost);
     	helperForm.setStateonly_totalcost(stateonly_totalcost);
     	
     	
     	//LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");
     	//String updatedBy=loginVO.getCreatedBy();
     	 StateSanctionVO  sanctionVO=(StateSanctionVO) statefinancialdAO.getSanctionDetailsByStateId(stateid);
     try {
     	/*helperForm.setEntityCode(loginVO.getEntityCode());
 		helperForm.setStateName(helperDao.getState(helperForm.getEntityCode())); */
     	StateFinancialVO helperVo =(StateFinancialVO) AbsFinder.getById(StateFinancialVO.class, helperForm.getId());
 		
     //	BeanUtils.copyProperties(helperVo, helperForm);
		
     	helperVo.setCentralProgramCost(helperForm.getCentralProgramCost());
    	helperVo.setCentralSuportCost(helperForm.getCentralSuportCost());
    	helperVo.setCentralCtsa(helperForm.getCentralCtsa());
    	helperVo.setCentralAdminCost(helperForm.getCentralAdminCost());
    	helperVo.setCentralCapacityCost(helperForm.getCentralCapacityCost());
    	helperVo.setStateProgramCost(helperForm.getStateProgramCost());
    	helperVo.setStateSuportCost(helperForm.getStateSuportCost());
    	helperVo.setStateCtsa(helperForm.getStateCtsa());
    	helperVo.setStateAdminCost(helperForm.getStateAdminCost());
    	helperVo.setStateCapacityCost(helperForm.getStateCapacityCost());
    	helperVo.setCentralReleasedProgramCost(helperForm.getCentralReleasedProgramCost());
    	helperVo.setCentralReleasedSuportCost(helperForm.getCentralReleasedSuportCost());
    	helperVo.setCentralReleasedCtsa(helperForm.getCentralReleasedCtsa());
    	helperVo.setCentralReleasedAdminCost(helperForm.getCentralReleasedAdminCost());
    	helperVo.setCentralReleasedCapacityCost(helperForm.getCentralReleasedCapacityCost());
    	helperVo.setCentralOnlyProgramCost(helperForm.getCentralOnlyProgramCost());
    	helperVo.setCentralOnlySuportCost(helperForm.getCentralOnlySuportCost());
    	helperVo.setCentralOnlyCtsa(helperForm.getCentralOnlyCtsa());
    	helperVo.setCentralOnlyAdminCost(helperForm.getCentralOnlyAdminCost());
    	helperVo.setCentralOnlyCapacityCost(helperForm.getCentralOnlyCapacityCost());
    	helperVo.setStateOnlyProgramCost(helperForm.getStateOnlyProgramCost());
    	helperVo.setStateOnlySuportCost(helperForm.getStateOnlySuportCost());
    	helperVo.setStateOnlyCtsa(helperForm.getStateOnlyCtsa());
    	helperVo.setStateOnlyAdminCost(helperForm.getStateOnlyAdminCost());
    	helperVo.setStateOnlyCapacityCost(helperForm.getStateOnlyCapacityCost());
    	helperVo.setCentral_totalprojectcost(helperForm.getCentral_totalprojectcost());
    	helperVo.setCentral_otherprojectcost(helperForm.getCentral_otherprojectcost());
    	helperVo.setCentral_totalcost(helperForm.getCentral_totalcost());
    	helperVo.setState_totalprojectcost(helperForm.getState_totalprojectcost());
    	helperVo.setState_otherprojectcost(helperForm.getState_otherprojectcost());
    	helperVo.setState_totalcost(helperForm.getState_totalcost());
    	helperVo.setCentralreleased_totalprojectcost(helperForm.getCentralreleased_totalprojectcost());
    	helperVo.setCentralreleased_otherprojectcost(helperForm.getCentralreleased_otherprojectcost());
    	helperVo.setCentralreleased_totalcost(helperForm.getCentralreleased_totalcost());
    	helperVo.setCentralonly_totalprojectcost(helperForm.getCentralonly_totalprojectcost());
    	helperVo.setCentralonly_otherprojectcost(helperForm.getCentralonly_otherprojectcost());
    	helperVo.setCentralonly_totalcost(helperForm.getCentralonly_totalcost());
    	helperVo.setStateonly_totalprojectcost(helperForm.getStateonly_totalprojectcost());
    	helperVo.setStateonly_otherprojectcost(helperForm.getStateonly_otherprojectcost());
    	helperVo.setStateonly_totalcost(helperForm.getStateonly_totalcost());
     	
     	
     	
     	
     	
     	
     	
     	helperVo.setCentralReleasedDateProgramCost(helperForm.getCentralReleasedDateProgramCost());
 		helperVo.setCentralReleasedDatSuportCost(helperForm.getCentralReleasedDatSuportCost());
     	helperVo.setCentralReleasedDatCtsa(helperForm.getCentralReleasedDatCtsa());
     	helperVo.setCentralReleasedDatAdminCost(helperForm.getCentralReleasedDatAdminCost());
     	helperVo.setCentralReleasedDatCapacityCost(helperForm.getCentralReleasedDatCapacityCost());
     	helperVo.setStateReleasedDateProgramCost(helperForm.getStateReleasedDateProgramCost());
     	helperVo.setStateReleasedDateSuportCost(helperForm.getStateReleasedDateSuportCost());
     	helperVo.setStateReleasedDateCtsa(helperForm.getStateReleasedDateCtsa());
     	helperVo.setStateReleasedDateAdminCost(helperForm.getStateReleasedDateAdminCost());
     	helperVo.setStateReleasedDateCapacityCost(helperForm.getStateReleasedDateCapacityCost());
    	helperVo.setSanctionDetailId(sanctionVO);
	     //	StateFinancialVO helperVo1=helperDao.getStateFinancialDetailByid(helperForm.getId());
	      // 	helperVo.setCreatedOn(helperVo1.getCreatedOn());
	     	//helperVo.setCreatedBy(helperVo1.getCreatedBy());
	     	helperVo.setUpdatedBy(loginVO.getUserid());   //--------------------------------------------------------------------------update after user module
	     	helperVo.setUpdatedOn(new Date());
	     	helperDao.update(helperVo);
	     	/*StateFinancialVO stateFinancialVO =new StateFinancialDAO().getStateFinancialDetail(loginVO.getEntityCode());	     	 
			 request.setAttribute("financialVO",stateFinancialVO);*/
	     	
	//     	request.setAttribute("detailList", helperDao.getfinancialList(helperVo.getId()));
		
	         	//sadas
	      	helperForm.reset(mapping, request);
    }   catch (Exception e) {
	    	  e.printStackTrace();
	     }
     
     String forward=null;
     RoleMaster role=new RoleMasterDaoImpl().getRecordFromId(loginVO.getRoleId());
     if(role.getId().equalsIgnoreCase("45")||role.getId().equalsIgnoreCase("49")){
		 StateFinancialVO stateFinancialVO =new StateFinancialDAO().getStateFinancialDetail(loginVO.getEntityCode());
			request.setAttribute("financialVO",stateFinancialVO);
			forward="showPage";
	 }else{
	
	 List<StateFinancialVO> stateFinanceList=(List<StateFinancialVO>) AbsFinder.getList(StateFinancialVO.class);
	 request.setAttribute("statefinanceList",stateFinanceList);
	forward="showPageforAdmin"; 
	 }
	    return mapping.findForward(forward); 
}
		 
}
