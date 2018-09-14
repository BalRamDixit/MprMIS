package com.infotech.sgsy.masterdata.tradeMaster;

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
import com.infotech.sgsy.master.assessmentBodyMaster.AssessmentBodyVO;
import com.infotech.sgsy.masterdata.sectorMaster.SectorMasterVO;
import com.infotech.sgsy.util.DateUtil;

public class TradeMasterAction extends DispatchAction {

	
	public ActionForward show(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
			throws Exception {		 
 		

		String[] parameter=new String[]{"sectorId","sectorName"};

		List<SectorMasterVO> sectorList =(List<SectorMasterVO>) new TradeMasterDAO().getDropDownList(SectorMasterVO.class,parameter); 	
		List<AssessmentBodyVO> assBodyList=(List<AssessmentBodyVO>) new TradeMasterDAO().getDropDownList(AssessmentBodyVO.class,new String[]{"id","assBodyName"});  
		List<TradeMasterVO> ViewList=(List<TradeMasterVO>) new TradeMasterDAO().getAllRecords();
		request.setAttribute("sectorList", sectorList);
	    request.setAttribute("assBodyList", assBodyList);
		request.setAttribute("ViewList",ViewList);
 		return mapping.findForward("showTrade");

					}
	

 	public ActionForward edit(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
 		
		log.info("TradeMasterAction : Inside Edit method Starts======>");
		TradeMasterBean  Bean=(TradeMasterBean)form;
 		TradeMasterDAO helperDao = new TradeMasterDAO();
 		//request.setAttribute("TradeList", new TradeMasterDAO().getAllRecords());
 		String[] parameter=new String[]{"sectorId","sectorName"};
 		TradeMasterVO helperVO=new TradeMasterDAO().getRecordFromId(Bean.getTradeId());
		List<SectorMasterVO> sectorList =(List<SectorMasterVO>) helperDao.getDropDownList(SectorMasterVO.class,parameter); 	
		List<AssessmentBodyVO> assBodyList=(List<AssessmentBodyVO>) helperDao.getDropDownList(AssessmentBodyVO.class,new String[]{"id","assBodyName"});  
		List<TradeMasterVO> ViewList=(List<TradeMasterVO>) helperDao.getAllRecords();
		 request.setAttribute("sectorList", sectorList);
	        request.setAttribute("assBodyList", assBodyList);
 		
		if(helperVO!=null){
			BeanUtils.copyProperties(Bean,helperVO);
		}
  		Bean.setEditId(Bean.getTradeId());
		request.setAttribute("Bean", Bean);
		request.setAttribute("ViewList",ViewList);

		return mapping.findForward("editTrade");
	}
	 public ActionForward delete(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
	 		TradeMasterDAO helperDao = new TradeMasterDAO();

 		log.info("TradeMasterAction : Inside delet method Starts======>");
 		
		TradeMasterBean  Bean = (TradeMasterBean) form; 
		TradeMasterVO helperVo=new TradeMasterVO();
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
		String userID=  loginVO.getUserid();
 		//BeanUtils.copyProperties(helperVO,Bean);
	 	helperVo.setTradeId(Bean.getTradeId());
 	 	helperVo.setTradeCode(Bean.getTradeCode());
 	 	helperVo.setTradeName(Bean.getTradeName());
 	 	helperVo.setCourseDuration(Bean.getCourseDuration());
     	helperVo.setCreatedBy(userID);   
     	helperVo.setCreatedOn(new Date());
     	//helperVo.setSectorCode(Bean.getSectorCode());
     	//helperVo.setSectorCode(sectorMasterVO);
     	//helperVo.setAssessmentBodyCode(assessmentBodyVO); 		
 		boolean status=new TradeMasterDAO().deleteRecordFromId(helperVo);
		System.out.println("status Is --> "+status);
		String[] parameter=new String[]{"sectorId","sectorName"};
 		List<SectorMasterVO> sectorList =(List<SectorMasterVO>) helperDao.getDropDownList(SectorMasterVO.class,parameter); 	
		List<AssessmentBodyVO> assBodyList=(List<AssessmentBodyVO>) helperDao.getDropDownList(AssessmentBodyVO.class,new String[]{"id","assBodyName"});  
		List<TradeMasterVO> ViewList=(List<TradeMasterVO>) helperDao.getAllRecords();
		 request.setAttribute("sectorList", sectorList);
	        request.setAttribute("assBodyList", assBodyList);
		request.setAttribute("ViewList",ViewList);
			return mapping.findForward("showTrade");
	} 
	 
	
	 
	 public ActionForward update(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
			log.info("TradeMasterAction : Inside update method Starts======>");
			TradeMasterBean  Bean=(TradeMasterBean)form;
	 		TradeMasterDAO helperDao = new TradeMasterDAO();
 			System.out.println(Bean);
			if(Bean.getEditId()!=null){
				LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
			TradeMasterVO helperVO=new TradeMasterDAO().getRecordFromId(Bean.getEditId());
  				//helperVO.setSectorCode(Bean.getSectorCode()); 
				helperVO.setTradeCode(Bean.getTradeCode()); 
				helperVO.setTradeName(Bean.getTradeName()); 				
				helperVO.setCourseDuration(Bean.getCourseDuration());
				//helperVO.setAssessmentBodyCode(Bean.getAssessmentBodyCode());
				helperVO.setUpdatedBy(loginVO.getUserid());
				helperVO.setUpdatedOn(DateUtil.getPresentDate());
				System.out.println("Object To Save Is --> "+helperVO);
				new TradeMasterDAO().saveOrUpdate(helperVO);
				String[] parameter=new String[]{"sectorId","sectorName"};
		 		List<SectorMasterVO> sectorList =(List<SectorMasterVO>) helperDao.getDropDownList(SectorMasterVO.class,parameter); 	
				List<AssessmentBodyVO> assBodyList=(List<AssessmentBodyVO>) helperDao.getDropDownList(AssessmentBodyVO.class,new String[]{"id","assBodyName"});  
				
				 request.setAttribute("sectorList", sectorList);
			        request.setAttribute("assBodyList", assBodyList);
				List<TradeMasterVO> ViewList=(List<TradeMasterVO>) helperDao.getAllRecords();
				request.setAttribute("Bean",new TradeMasterBean());
				request.setAttribute("ViewList",ViewList);
			}
			;
			return mapping.findForward("updateTrade");
		} 
	 
	
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
//		TradeMasterDAO helperDao = new TradeMasterDAO();
		TradeMasterBean  tradeMasterBean = (TradeMasterBean) form; 
//		SectorMasterVO sectorMasterVO=(SectorMasterVO) new TradeMasterDAO().getById(SectorMasterVO.class, tradeMasterBean.getSectorId());
		
		SectorMasterVO sectorMasterVO=new SectorMasterVO();
		sectorMasterVO.setSectorId(tradeMasterBean.getSectorId());
//		AssessmentBodyVO assessmentBodyVO=(AssessmentBodyVO) new TradeMasterDAO().getById(AssessmentBodyVO.class, tradeMasterBean.getAssessmentBodyId());
		
		AssessmentBodyVO assessmentBodyVO=new AssessmentBodyVO();
		assessmentBodyVO.setId(tradeMasterBean.getAssessmentBodyId());
		
		
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
        String userID=  loginVO.getUserid();
        /*List<SectorMasterVO> sectorList =(List<SectorMasterVO>) helperDao.getDropDownList(SectorMasterVO.class,parameter); 	
		List<AssessmentBodyVO> assBodyList=(List<AssessmentBodyVO>) helperDao.getDropDownList(AssessmentBodyVO.class,new String[]{"id","assBodyName"});  
		*/
       
      
 	 	try 
	 	{
	 		 	 
 	 	TradeMasterVO helperVo = new TradeMasterVO();
     	//BeanUtils.copyProperties(helperVo, helperForm);
 	 	helperVo.setTradeId(tradeMasterBean.getTradeId());
 	 	helperVo.setTradeCode(tradeMasterBean.getTradeCode());
 	 	helperVo.setTradeName(tradeMasterBean.getTradeName());
 	 	helperVo.setCourseDuration(tradeMasterBean.getCourseDuration());
     	helperVo.setCreatedBy(userID);   
     	helperVo.setCreatedOn(new Date());
     	helperVo.setSectorId(sectorMasterVO);
     	helperVo.setAssessmentBodyId(assessmentBodyVO);      	
     	
     	new TradeMasterDAO().save(helperVo);	
      	tradeMasterBean.reset(mapping, request);
      	
        String[] parameter=new String[]{"sectorId","sectorName"};
 		List<SectorMasterVO> sectorList =(List<SectorMasterVO>) new TradeMasterDAO().getDropDownList(SectorMasterVO.class,parameter); 	
		List<AssessmentBodyVO> assBodyList=(List<AssessmentBodyVO>) new TradeMasterDAO().getDropDownList(AssessmentBodyVO.class,new String[]{"id","assBodyName"});  
		List<TradeMasterVO> ViewList=(List<TradeMasterVO>) new TradeMasterDAO().getAllRecords();
		 request.setAttribute("sectorList", sectorList);
	        request.setAttribute("assBodyList", assBodyList);
	        request.setAttribute("ViewList",ViewList);
	    
	      }   catch (Exception e) {
    	  e.printStackTrace();
     }
		
	   return mapping.findForward("showTrade");
}
	
	
}
