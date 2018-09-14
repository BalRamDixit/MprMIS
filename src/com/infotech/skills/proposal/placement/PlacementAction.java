package com.infotech.skills.proposal.placement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import com.infotech.skills.dao.proposal.PiaProposalDaoImpl;
import com.infotech.skills.hbm.piaproposal.PlacementTiaUpsVO;
import com.infotech.skills.hbm.piaproposal.PlacementVO;
import com.infotech.sgsy.util.DateUtil;


public class PlacementAction extends DispatchAction {

	public ActionForward showPage(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mapping.findForward("PlacementDetailsPage");	
		}
	
	public ActionForward save(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
			String requestPage="ERROR";
			String UserId = "HRMURTHY";
			String piaCode = "SKILLS";
		
			PlacementForm plaform=(PlacementForm)form;
			PlacementVO plavo=new PlacementVO();
			PlacementTiaUpsVO plavoTIA = new PlacementTiaUpsVO();
			BeanUtils.copyProperties(plavo, plaform);
			BeanUtils.copyProperties(plavoTIA, plaform);
			DateUtil date=new DateUtil();
			plavo.setPiaCode(piaCode);
			plavo.setCreatedBy(UserId);
			plavo.setCreatedOn(date.getCurrentDateAsString());
			plavoTIA.setPiaCode(piaCode);
			plavoTIA.setCreatedBy(UserId);
			plavoTIA.setCreatedOn(date.getCurrentDateAsString());

			PiaProposalDaoImpl helper = new PiaProposalDaoImpl();
				if(helper.save(plavo, plavoTIA)== true){
					log.error("Save SuccessFully");
					BeanUtils.copyProperties(plaform, plavo);
					request.setAttribute("placementDetail",plaform);
					BeanUtils.copyProperties(plaform, plavoTIA);
					request.setAttribute("placementTIA",plaform);
					request.setAttribute("NOTIFICATION","Record Save Successfully.");
		}
		else {
			log.error("fail save process");
		}
		return mapping.findForward(requestPage);
		}
	
}