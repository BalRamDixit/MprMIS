package com.infotech.skills.proposal.projectDelivery;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.infotech.skills.dao.proposal.PiaProposalDaoImpl;
import com.infotech.skills.hbm.piaproposal.ProjectDeliveryVO;
import com.infotech.sgsy.util.DateUtil;

public class ProjectDeliveryAction extends DispatchAction {

	public ActionForward showPage(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mapping.findForward("ProjectDeliveryPage");	
		}
	
	public ActionForward save(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
			String requestPage="ERROR";
			String UserId = "HRMURTHY";
			String piaCode = "SKILLS";
		
			ProjectDeliveryForm deliveryfrom = (ProjectDeliveryForm) form ;
			ProjectDeliveryVO deliveryVo = new ProjectDeliveryVO() ;
			BeanUtils.copyProperties(deliveryVo, deliveryfrom);
			DateUtil date=new DateUtil();
			deliveryVo.setPiaCode(piaCode);
			deliveryVo.setCreatedBy(UserId);
			deliveryVo.setCreatedOn(date.getCurrentDateAsString());
			
			PiaProposalDaoImpl helper = new PiaProposalDaoImpl();
				if(helper.save(deliveryVo)== true){
					log.error("Save SuccessFully");
					BeanUtils.copyProperties(deliveryfrom, deliveryVo);
					request.setAttribute("placementDetail",deliveryfrom);
					request.setAttribute("NOTIFICATION","Record Save Successfully.");
		}
		else {
			log.error("fail save process");
		}
		return mapping.findForward(requestPage);
		}
}