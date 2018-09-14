package com.infotech.sgsy.selfHelpGroup;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import com.infotech.sgsy.common.LocationVO;
import com.infotech.sgsy.common.MasterBusinessFactory;
import com.infotech.sgsy.login.LoginVO;
import com.infotech.sgsy.util.Constants;
import com.infotech.sgsy.util.SGSYConstants;

public class SHGAction extends DispatchAction{
	
SHGBusiness business = null;	

 
//Function Used to Show the SHG Registration Page

public List<String[]> socialCategory(){
	List<String[]> socialCategory = new ArrayList<String[]>();
	socialCategory.add(new String[]{"SC","SC"});
	socialCategory.add(new String[]{"ST","ST"});
	socialCategory.add(new String[]{"OBC","OBC"});
	socialCategory.add(new String[]{"Other","OTHER"});
	return socialCategory;
}
 
public ActionForward addPage(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
		throws Exception {
	log.info("Function show add shg calls");
	
	String forward= "ADD_SHG";
	try{
	business= (SHGBusiness) MasterBusinessFactory.getInstance(Constants.GET_SHG_BUSI) ;
	HttpSession session=request.getSession();
	LocationVO  location=(LocationVO)session.getAttribute(SGSYConstants.SGSY_LOCATIONVO);
	
	request.setAttribute("socialCategory", this.socialCategory());
	request.setAttribute("BankList", business.getBankList(location.getBlockCode()));
	request.setAttribute("VillageList",business.getVillageList(location.getBlockCode()));
	
	if(forward.equals("showPage")){
		SHGActionForm shgForm=(SHGActionForm)form;
		/*if(shgForm.getBankCode()!=null && shgForm.getBankCode().trim()!=""){				
			request.setAttribute("BankBranchList",business.getBankBranchList(location.getBlockCode(),shgForm.getBankCode()));
		}
		else{
			shgForm.reset();
		}*/
	}		
	}
	catch(Exception e){e.printStackTrace();}
	return mapping.findForward(forward);
}
	

/* 
 * Function Used to Show the SHG Registration Page
 */
public ActionForward modifyPage(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
		throws Exception {
	String forward="MODIFY_SHG";	
	HttpSession session=request.getSession();	
	business= (SHGBusiness) MasterBusinessFactory.getInstance(Constants.GET_SHG_BUSI) ;
	LocationVO  locationVO = (LocationVO)session.getAttribute(SGSYConstants.SGSY_LOCATIONVO);
	MasterShgVO shgRegDetail = null;
	List<MasterShgMemberDetails> shgMemberDetail = null;
	try{
		SHGActionForm shgForm = (SHGActionForm) form;
		if(shgForm.getEntityCode()!= null){
			request.setAttribute("SHGList", new SHGMasterDAO().getSHGListForDropdown(locationVO, shgForm.getEntityCode()));
		}
		
		if(shgForm.getShgCode()!= null && !"".equals(shgForm.getShgCode())){
			shgRegDetail = new SHGMasterDAO().getshgRegDetails(locationVO, shgForm.getShgCode());
			shgMemberDetail = new SHGMasterDAO().getSHGMemberDetails(locationVO, shgForm.getShgCode());
			if(shgRegDetail != null)
				request.setAttribute("shgDetails", shgRegDetail);
			if(shgMemberDetail != null && !shgMemberDetail.isEmpty()){
				request.setAttribute("shgMemberDetails", shgMemberDetail);
				request.setAttribute("shgMemberCount", shgMemberDetail.size());
			}
			request.setAttribute("socialCategory", this.socialCategory());
			request.setAttribute("BankList", business.getBankList(locationVO.getBlockCode()));
			
			if(shgRegDetail.getBankBranchCode()!=null && shgRegDetail.getBankBranchCode().trim()!=""){				
				request.setAttribute("BankBranchList", business.getBankBranchListNew(shgRegDetail.getBankCode(), locationVO.getBlockCode()));
			}
		}		
		request.setAttribute("VillageList",business.getVillageList(locationVO.getBlockCode()));		
	}
	catch(Exception e){
		e.printStackTrace();
		log.error("SHG VIEW :::" + e.getMessage());
	}
	return mapping.findForward(forward);
}


 
 // Function Used to Show the SHG Registration Page
 
public ActionForward viewPage(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
		throws Exception {
	String forward = "VIEW_SHG";	
	HttpSession session=request.getSession();	
	business= (SHGBusiness) MasterBusinessFactory.getInstance(Constants.GET_SHG_BUSI) ;
	LocationVO  locationVO = (LocationVO)session.getAttribute(SGSYConstants.SGSY_LOCATIONVO);
	MasterShgVO shgRegDetail = null;
	List<MasterShgMemberDetails> shgMemberDetail = null;
	try{
		SHGActionForm shgForm = (SHGActionForm) form;
		
		if(shgForm.getEntityCode()!= null){
			request.setAttribute("SHGList", new SHGMasterDAO().getSHGListForDropdown(locationVO, shgForm.getEntityCode()));
		}
		
		if(shgForm.getShgCode()!= null && !"".equals(shgForm.getShgCode())){
			shgRegDetail = new SHGMasterDAO().getshgRegDetails(locationVO, shgForm.getShgCode());
			shgMemberDetail = new SHGMasterDAO().getSHGMemberDetails(locationVO, shgForm.getShgCode());
			if(shgRegDetail != null)
				request.setAttribute("shgDetails", shgRegDetail);
			if(shgMemberDetail != null && !shgMemberDetail.isEmpty())
				request.setAttribute("shgMemberDetails", shgMemberDetail);
		}
		request.setAttribute("VillageList",business.getVillageList(locationVO.getBlockCode()));
	}
	catch(Exception e){
		e.printStackTrace();
		log.error("SHG VIEW :::" + e.getMessage());
	}
	return mapping.findForward(forward);
}



	/* Function Used to save the SHG and its member Details in the data base
	 * (non-Javadoc)
	 * @see com.infotech.sgsy.common.MasterAction#save(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {		
			log.info("SHGAction : ------------------ Inside Save Method ------------------------");
			String forwards="ADD_SHG";
		try{	
			LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");				
			LocationVO locationVO = (LocationVO) request.getSession().getAttribute(SGSYConstants.SGSY_LOCATIONVO);		// Used to get the loggers detail
			
			business= (SHGBusiness) MasterBusinessFactory.getInstance(Constants.GET_SHG_BUSI) ;				
			SHGActionForm shgForm=(SHGActionForm)form;
			if(new SHGMasterDAO().save(shgForm, locationVO.getStateShortName(), loginVO.getUserid())){
				request.setAttribute("NOTIFICATION","SHG "+ shgForm.getGroupName() +" created successfully.");
				form.reset(mapping, request);
			}		
			this.addPage(mapping, form, request, response);							
		}					
		catch(Exception e){
			e.printStackTrace();
			log.error(e);					
		}
			
		return mapping.findForward(forwards);
	}
	
	
	/* Function Used to save the SHG and its member Details in the data base
	 * (non-Javadoc)
	 * @see com.infotech.sgsy.common.MasterAction#save(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward modify(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {		
			log.info("SHGAction : ------ INSIDE MODIFY METHOD ---------");
			String forwards="MODIFY_SHG";
		try{	
			LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");				
			LocationVO locationVO = (LocationVO) request.getSession().getAttribute(SGSYConstants.SGSY_LOCATIONVO) ;		// Used to get the loggers detail			
			business= (SHGBusiness) MasterBusinessFactory.getInstance(Constants.GET_SHG_BUSI) ;					
			SHGActionForm shgForm=(SHGActionForm)form;
			if(new SHGMasterDAO().updateOrInsert(shgForm, locationVO.getStateShortName(), loginVO.getUserid())){
				request.setAttribute("NOTIFICATION","SHG "+ shgForm.getGroupName() +" modify successfully.");				
			}	
			this.modifyPage(mapping, shgForm, request, response);												
			}					
		catch(Exception e){
			e.printStackTrace();
			log.error(e);					
		}
			
		return mapping.findForward(forwards);
	}
	
	
	

}
