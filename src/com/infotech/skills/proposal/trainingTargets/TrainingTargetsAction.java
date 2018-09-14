package com.infotech.skills.proposal.trainingTargets;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import com.infotech.skills.dao.proposal.PiaProposalDaoImpl;
import com.infotech.skills.hbm.piaproposal.TrainingTargetsVO;
import com.infotech.skills.master.MasterDao;
import com.infotech.skills.master.MasterDaoImpl;
import com.infotech.skills.util.Constants;
import com.infotech.sgsy.common.LocationVO;
import com.infotech.sgsy.login.LoginVO;
import com.infotech.sgsy.util.DateUtil;
import com.infotech.sgsy.util.SGSYConstants;

public class TrainingTargetsAction extends DispatchAction {
	
	public ActionForward showPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		log.error("::::::: showReportPage method call ::::::");
		String requestPage="Error";
		
		try {
			LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");				
			LocationVO locationVO = (LocationVO) request.getSession().getAttribute(SGSYConstants.SGSY_LOCATIONVO);				
			String entityCode = loginVO.getEntityCode();
								
			PiaProposalDaoImpl helper=new PiaProposalDaoImpl();
			
			TrainingTargetsForm trainingForm=new TrainingTargetsForm();
						
		    List monthlist=helper.getMonthList();	
		    request.setAttribute("monthList", monthlist);
		
			requestPage="trainingTarget";
		}
		catch(Exception e) {
			log.error("Exception while TrainingTarget " + e.getMessage());
		}		
		return mapping.findForward(requestPage);
	}

	public ActionForward save(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
	throws Exception {			
		String requestPage="ERROR";
	 LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");				
	
	    TrainingTargetsForm trainingForm = (TrainingTargetsForm)form;
		TrainingTargetsVO trainingDetail=new TrainingTargetsVO();
		BeanUtils.copyProperties(trainingDetail, trainingForm);
	    DateUtil date=new DateUtil();
	    trainingDetail.setCreatedBy(loginVO.getUserid());
	    trainingDetail.setCreatedOn(date.getCurrentDateAsString());
	    PiaProposalDaoImpl helper=new PiaProposalDaoImpl();
		
		
			if(helper.insertTrainingTargetDetail(trainingDetail)== true){
				log.error("Save SuccessFully");
				BeanUtils.copyProperties(trainingForm, trainingDetail);
				request.setAttribute("trainingDetail",trainingForm);
				request.setAttribute("NOTIFICATION","Record Save Successfully.");
	}
	else{
		log.error("fail save process");
	}
	
		
	return mapping.findForward(requestPage);
	}


	
}