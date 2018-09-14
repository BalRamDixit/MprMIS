package com.infotech.skills.proposal.humanResource;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import com.infotech.skills.dao.proposal.PiaProposalDao;
import com.infotech.skills.dao.proposal.PiaProposalDaoImpl;
import com.infotech.skills.hbm.piaproposal.HumanResourceEmpVO;
import com.infotech.skills.hbm.piaproposal.HumanResourceProposedVO;
import com.infotech.skills.master.MasterDao;
import com.infotech.skills.master.MasterDaoImpl;
import com.infotech.skills.util.Constants;
import com.infotech.sgsy.accessControl.AccessVO;
import com.infotech.sgsy.common.LocationVO;
import com.infotech.sgsy.login.LoginVO;
import com.infotech.sgsy.util.DateUtil;
import com.infotech.sgsy.util.PopUpMessages;

public class HumanResourceAction extends DispatchAction {
	
	protected final Log log = LogFactory.getLog(getClass());
	 ActionMessages message = new ActionMessages();
	 
	 public ActionForward showPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	 	throws Exception{
		 log.info("inside show page at HumanResourceAction page");
		 return mapping.findForward("showPage");
		 
	 }
	public ActionForward save(ActionMapping mapping,ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception{
		
		log.info("HumanResourceAction:: Inside save method Starts. ");
		String userId = "laxman";
		HumanResourceForm humanResourceForm = (HumanResourceForm) form;
		humanResourceForm.reset();
		//LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");	
		String type = request.getParameter("type");
		if(type.equals("emp")){
			HumanResourceEmpVO humanResourceEmpVO = new HumanResourceEmpVO();
			BeanUtils.copyProperties(humanResourceEmpVO,humanResourceForm);
			humanResourceEmpVO.setCreatedBy(userId);
			humanResourceEmpVO.setCreatedOn(DateUtil.getPresentDate().toString());
			humanResourceEmpVO.setProposalCode("skills");
			PiaProposalDaoImpl helper = new PiaProposalDaoImpl();
			if(helper.saveEmp(humanResourceEmpVO) == true){
				log.info("Proposal saved successfully..!!");
				request.setAttribute("NOTIFICATION","Record Save Successfully.");
			}else{
				log.info("ERROR:::\tHuman Resource employed page(HumanResourceAction) not saved.");
			}
		}else if(type.equals("prop")){
			HumanResourceProposedVO humanResourceProposedVO = new HumanResourceProposedVO();
			BeanUtils.copyProperties(humanResourceProposedVO,humanResourceForm);
			humanResourceProposedVO.setCreatedBy(userId);
			humanResourceProposedVO.setCreatedOn(DateUtil.getPresentDate().toString());
			PiaProposalDaoImpl helper = new PiaProposalDaoImpl();
			if(helper.saveProp(humanResourceProposedVO) == true){
				log.info("Proposal saved successfully..!!");
				request.setAttribute("NOTIFICATION","Record Save Successfully.");
			}else{
				log.info("ERROR:::\tHuman Resource proposed page(HumanResourceAction) not saved.");
			}
		}
		return mapping.findForward("success");
	}


}