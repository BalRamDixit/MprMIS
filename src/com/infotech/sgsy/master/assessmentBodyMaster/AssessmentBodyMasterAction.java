package com.infotech.sgsy.master.assessmentBodyMaster;

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

import com.infotech.sgsy.login.LoginVO;
import com.infotech.sgsy.util.DateUtil;

public class AssessmentBodyMasterAction extends DispatchAction{

	protected final Log log = LogFactory.getLog(getClass());
    ActionMessages message = new ActionMessages();


	public ActionForward addAssessmentBodyMaster(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		AssessmentBodyBean assessmentBodyBean=new AssessmentBodyBean();
		 request.setAttribute("assBodyList", new AssessmentBodyMasterDaoImpl().getAllRecords());
		 request.setAttribute("assessmentBodyBean", assessmentBodyBean);
	 return mapping.findForward("addAssessmentBodyMaster");
	}
	
	public ActionForward checkRecord(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		log.info("checkRecord method Starts======>");
		
		String assBodyName=request.getParameter("assBodyName");
		System.out.println("assBodyName......"+assBodyName);
		response.setContentType("text/xml");
		response.setHeader( "Pragma", "no-cache" );
		response.addHeader( "Cache-Control", "must-revalidate" );
		response.addHeader( "Cache-Control", "no-cache" );
		response.addHeader( "Cache-Control", "no-store" );
		response.setDateHeader("Expires", 0);
		String s=null;
		AssessmentBodyMasterDaoImpl assessmentBodyMasterDaoImpl=new AssessmentBodyMasterDaoImpl();
		
		try{
			 boolean flag=assessmentBodyMasterDaoImpl.checkRecord(assBodyName);
			 if(flag==false){
			 	response.getWriter().write("error");
			 }else{   
				s="true";
				response.getWriter().write(s);
			 }
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public ActionForward saveAssessmentBody(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		log.info("AssessmentBodyMasterAction : Inside save method Starts======>");
		
		AssessmentBodyBean assessmentBodyBean=(AssessmentBodyBean)form;
		AssessmentBodyVO assessmentBodyVO=new AssessmentBodyVO();
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
				
		BeanUtils.copyProperties(assessmentBodyVO,assessmentBodyBean);
		
		assessmentBodyVO.setCreatedBy(loginVO.getUserid());
		assessmentBodyVO.setCreatedOnDate(DateUtil.getPresentDate());
		System.out.println("Object To Save Is for app--> "+assessmentBodyVO);
		
		new AssessmentBodyMasterDaoImpl().save(assessmentBodyVO);
		log.info("assessmentBody Inside save method Ends======>");
		return mapping.findForward("saveAssessmentBody");
	}
	
	public ActionForward editAssessmentBody(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		log.info("AssessmentBodyMasterAction : Inside Edit method Starts======>");
		
		AssessmentBodyBean assessmentBodyBean=(AssessmentBodyBean)form;
		request.setAttribute("assBodyList", new AssessmentBodyMasterDaoImpl().getAllRecords());
		System.out.println("assessmentBodyBean Master Bean Id--> "+assessmentBodyBean.getId());
		
		AssessmentBodyVO assessmentBodyVO=new AssessmentBodyMasterDaoImpl().getRecordFromId(assessmentBodyBean.getId());
		if(assessmentBodyVO!=null){
			BeanUtils.copyProperties(assessmentBodyBean,assessmentBodyVO);
		}
		System.out.println(assessmentBodyVO);
		System.out.println(assessmentBodyBean);
		
		assessmentBodyBean.setEditId(assessmentBodyBean.getId());
		request.setAttribute("assessmentBodyBean", assessmentBodyBean);
		return mapping.findForward("editAssessmentBody");
	}
	
	public ActionForward deleteAssessmentBody(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		log.info("AssessmentBodyMasterAction : Inside deleteRole method Starts====  for ctsa==>");
		
		AssessmentBodyBean assessmentBodyBean=(AssessmentBodyBean)form;
		AssessmentBodyVO assessmentBodyVO=new AssessmentBodyVO();
		
		System.out.println("assessmentBody Bean Id--> "+assessmentBodyBean.getId());
		BeanUtils.copyProperties(assessmentBodyVO,assessmentBodyBean);
		System.out.println(assessmentBodyVO);
		System.out.println(assessmentBodyBean);
		boolean status=new AssessmentBodyMasterDaoImpl().deleteRecordFromId(assessmentBodyVO);
		System.out.println("status Is --> "+status);
		request.setAttribute("assBodyList", new AssessmentBodyMasterDaoImpl().getAllRecords());
		return mapping.findForward("addAssessmentBodyMaster");
	}
	
	
public ActionForward updateAssessmentBody(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		log.info("AssessmentBodyMasterAction : Inside updateCtsa method Starts======>");
		
		AssessmentBodyBean assessmentBodyBean=(AssessmentBodyBean)form;
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
		
		System.out.println(",,,,,,,lllllllllllllllll,,,,,"+assessmentBodyBean.getEditId());
		if(assessmentBodyBean.getEditId()!=null){
			AssessmentBodyVO assessmentBodyVO=new AssessmentBodyMasterDaoImpl().getRecordFromId(assessmentBodyBean.getEditId());
			System.out.println(assessmentBodyVO);
			
			assessmentBodyVO.setAssBodyName(assessmentBodyBean.getAssBodyName());
			assessmentBodyVO.setAssBodyCode(assessmentBodyBean.getAssBodyCode());
			assessmentBodyVO.setUpdatedBy(loginVO.getUserid());
			assessmentBodyVO.setUpdatedOnDate(DateUtil.getPresentDate());
			
		    new AssessmentBodyMasterDaoImpl().saveOrUpdate(assessmentBodyVO);
		}
		
		return mapping.findForward("updateAssessmentBody");
	}

}
