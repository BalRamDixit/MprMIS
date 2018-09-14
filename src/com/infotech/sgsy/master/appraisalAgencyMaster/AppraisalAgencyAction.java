package com.infotech.sgsy.master.appraisalAgencyMaster;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.action.ActionMessages;
import com.infotech.sgsy.login.LoginVO;
import com.infotech.sgsy.util.DateUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;





public class AppraisalAgencyAction extends DispatchAction{

	protected final Log log = LogFactory.getLog(getClass());
    ActionMessages message = new ActionMessages();

	public ActionForward addAppraisalAgency(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		
	 AppraisalAgencyBean appraisalAgencyBean=new AppraisalAgencyBean();
		 request.setAttribute("appAgencyList", new AppraisalAgencyDaoImpl().getAllRecords());
		 request.setAttribute("appraisalAgencyBean", appraisalAgencyBean);
	 return mapping.findForward("addAppraisalAgency");
	}
	
	public ActionForward checkRecord(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		log.info("checkRecord method Starts======>");
		
		String appraisalName=request.getParameter("appraisalName");
		System.out.println("appraisalName......"+appraisalName);
		response.setContentType("text/xml");
		response.setHeader( "Pragma", "no-cache" );
		response.addHeader( "Cache-Control", "must-revalidate" );
		response.addHeader( "Cache-Control", "no-cache" );
		response.addHeader( "Cache-Control", "no-store" );
		response.setDateHeader("Expires", 0);
		String s=null;
		AppraisalAgencyDaoImpl appraisalAgencyDaoImpl=new AppraisalAgencyDaoImpl();
		
		try{
			 boolean flag=appraisalAgencyDaoImpl.checkRecord(appraisalName);
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
	
	public ActionForward saveAppraisalAgency(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		log.info("AppraisalAgencyAction : Inside save method Starts======>");
		
		AppraisalAgencyBean appraisalAgencyBean=(AppraisalAgencyBean)form;
		AppraisalAgencyVO appraisalAgencyVO=new AppraisalAgencyVO();
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
				
		BeanUtils.copyProperties(appraisalAgencyVO,appraisalAgencyBean);
		
		appraisalAgencyVO.setCreatedBy(loginVO.getUserid());
		appraisalAgencyVO.setCreatedOnDate(DateUtil.getPresentDate());
		System.out.println("Object To Save Is for tsa--> "+appraisalAgencyVO);
		
		new AppraisalAgencyDaoImpl().save(appraisalAgencyVO);
		log.info("AppraisalAgency Inside save method Ends======>");
		return mapping.findForward("saveAppraisalAgency");
	}
	
	public ActionForward editAppraisalAgency(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		log.info("AppraisalAgencyAction : Inside Edit method Starts======>");
		
		AppraisalAgencyBean appraisalAgencyBean=(AppraisalAgencyBean)form;
		request.setAttribute("appAgencyList", new AppraisalAgencyDaoImpl().getAllRecords());
		System.out.println("appraisalAgencyBean Master Bean Id--> "+appraisalAgencyBean.getId());
		
		AppraisalAgencyVO appraisalAgencyVO=new AppraisalAgencyDaoImpl().getRecordFromId(appraisalAgencyBean.getId());
		if(appraisalAgencyVO!=null){
			BeanUtils.copyProperties(appraisalAgencyBean,appraisalAgencyVO);
		}
		System.out.println(appraisalAgencyVO);
		System.out.println(appraisalAgencyBean);
		
		appraisalAgencyBean.setEditId(appraisalAgencyBean.getId());
		request.setAttribute("appraisalAgencyBean", appraisalAgencyBean);
		return mapping.findForward("editAppraisalAgency");
	}
	
	
	public ActionForward deleteAppraisalAgency(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		log.info("AppraisalAgencyAction : Inside deleteRole method Starts====  for ctsa==>");
		
		AppraisalAgencyBean appraisalAgencyBean=(AppraisalAgencyBean)form;
		AppraisalAgencyVO appraisalAgencyVO=new AppraisalAgencyVO();
		
		System.out.println("TSA Bean Id--> "+appraisalAgencyBean.getId());
		BeanUtils.copyProperties(appraisalAgencyVO,appraisalAgencyBean);
		System.out.println(appraisalAgencyVO);
		System.out.println(appraisalAgencyBean);
		boolean status=new AppraisalAgencyDaoImpl().deleteRecordFromId(appraisalAgencyVO);
		System.out.println("status Is --> "+status);
		request.setAttribute("appAgencyList", new AppraisalAgencyDaoImpl().getAllRecords());
		return mapping.findForward("addAppraisalAgency");
	}
	
public ActionForward updateAppraisalAgency(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		log.info("AppraisalAgencyAction : Inside updateCtsa method Starts======>");
		
		AppraisalAgencyBean appraisalAgencyBean=(AppraisalAgencyBean)form;
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
		
		System.out.println(",,,,,,,lllllllllllllllll,,,,,"+appraisalAgencyBean.getEditId());
		if(appraisalAgencyBean.getEditId()!=null){
			AppraisalAgencyVO appraisalAgencyVO=new AppraisalAgencyDaoImpl().getRecordFromId(appraisalAgencyBean.getEditId());
			System.out.println(appraisalAgencyVO);
			
			appraisalAgencyVO.setAppraisalName(appraisalAgencyBean.getAppraisalName());
			appraisalAgencyVO.setAppraisalCode(appraisalAgencyBean.getAppraisalCode());
			appraisalAgencyVO.setUpdatedBy(loginVO.getUserid());
			appraisalAgencyVO.setUpdatedOnDate(DateUtil.getPresentDate());
			
		    new AppraisalAgencyDaoImpl().saveOrUpdate(appraisalAgencyVO);
		}
		
		return mapping.findForward("updateAppraisalAgency");
	}
}
