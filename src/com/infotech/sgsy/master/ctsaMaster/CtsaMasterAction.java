package com.infotech.sgsy.master.ctsaMaster;

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


/**
 *  Date : 29.04.2017
 */

public class CtsaMasterAction extends DispatchAction{

	
	protected final Log log = LogFactory.getLog(getClass());
    ActionMessages message = new ActionMessages();

	public ActionForward addCtsaMaster(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		
	    CtsaMasterBean ctsaMasterBean=new CtsaMasterBean();
		
		request.setAttribute("ctsaList", new CtsaMasterDaoImpl().getAllRecords());
		request.setAttribute("ctsaMasterBean", ctsaMasterBean);
		return mapping.findForward("addCtsaMaster");
	}
	
	//...code for CHECK RECORD......
	public ActionForward checkRecord(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		log.info("checkRecord method Starts======>");
		
		String ctsaName=request.getParameter("ctsaName");
		System.out.println("ctsa......"+ctsaName);
		response.setContentType("text/xml");
		response.setHeader( "Pragma", "no-cache" );
		response.addHeader( "Cache-Control", "must-revalidate" );
		response.addHeader( "Cache-Control", "no-cache" );
		response.addHeader( "Cache-Control", "no-store" );
		response.setDateHeader("Expires", 0);
		String s=null;
		CtsaMasterDaoImpl ctsaMasterDaoImpl=new CtsaMasterDaoImpl();
		
		try{
			 boolean flag=ctsaMasterDaoImpl.checkRecord(ctsaName);
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
	//...code for saving CTSA NAME......
	
	public ActionForward saveCtsa(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		log.info("CtsaMasterAction : Inside save method Starts======>");
		
		CtsaMasterBean ctsaMasterBean=(CtsaMasterBean)form;
		CtsaMasterVO ctsaMasterVO=new CtsaMasterVO();
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
				
		BeanUtils.copyProperties(ctsaMasterVO,ctsaMasterBean);
		
		ctsaMasterVO.setCreatedBy(loginVO.getUserid());
		ctsaMasterVO.setCreatedOnDate(DateUtil.getPresentDate());
		System.out.println("Object To Save Is for Ctsa--> "+ctsaMasterVO);
		
		new CtsaMasterDaoImpl().save(ctsaMasterVO);
		log.info("CTSA Inside save method Ends======>");
		return mapping.findForward("saveCtsa");
	}
	
	//...code for EDIT CTSA......
	
	public ActionForward editCtsa(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		log.info("CtsaMasterAction : Inside Edit method Starts======>");
		
		CtsaMasterBean ctsaMasterBean=(CtsaMasterBean)form;
		request.setAttribute("ctsaList", new CtsaMasterDaoImpl().getAllRecords());
		System.out.println("Ctsa Master Bean Id--> "+ctsaMasterBean.getId());
		
		CtsaMasterVO ctsaMasterVO=new CtsaMasterDaoImpl().getRecordFromId(ctsaMasterBean.getId());
		if(ctsaMasterVO!=null){
			BeanUtils.copyProperties(ctsaMasterBean,ctsaMasterVO);
		}
		System.out.println(ctsaMasterVO);
		System.out.println(ctsaMasterBean);
		
		ctsaMasterBean.setEditId(ctsaMasterBean.getId());
		request.setAttribute("ctsaMasterBean", ctsaMasterBean);
		return mapping.findForward("editCtsa");
	}
	
	//...code for delete the CTSA ......
	
	public ActionForward deleteCtsa(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		log.info("CtsaMasterAction : Inside deleteRole method Starts====  for ctsa==>");
		
		CtsaMasterBean ctsaMasterBean=(CtsaMasterBean)form;
		CtsaMasterVO ctsaMasterVO=new CtsaMasterVO();
		
		System.out.println("CTSA Bean Id--> "+ctsaMasterBean.getId());
		BeanUtils.copyProperties(ctsaMasterVO,ctsaMasterBean);
		System.out.println(ctsaMasterVO);
		System.out.println(ctsaMasterBean);
		boolean status=new CtsaMasterDaoImpl().deleteRecordFromId(ctsaMasterVO);
		System.out.println("status Is --> "+status);
		request.setAttribute("ctsaList", new CtsaMasterDaoImpl().getAllRecords());
		return mapping.findForward("addCtsaMaster");
	}
	
	
	//...code for UPDATION......
	public ActionForward updateCtsa(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		log.info("CtsaMasterAction : Inside updateCtsa method Starts======>");
		
		CtsaMasterBean ctsaMasterBean=(CtsaMasterBean)form;
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
		
		System.out.println("cytsa Master Bean edit Id--> "+ctsaMasterBean.getEditId());
		System.out.println(ctsaMasterBean);
		
		if(ctsaMasterBean.getEditId()!=null){
			CtsaMasterVO ctsaMasterVO=new CtsaMasterDaoImpl().getRecordFromId(ctsaMasterBean.getEditId());
			ctsaMasterVO.setCtsaName(ctsaMasterBean.getCtsaName());
			ctsaMasterVO.setCtsaCode(ctsaMasterBean.getCtsaCode());
			
			ctsaMasterVO.setUpdatedBy(loginVO.getUserid());
			ctsaMasterVO.setUpdatedOnDate(DateUtil.getPresentDate());
			
			 System.out.println("Object To Save Is --> "+ctsaMasterVO);
			 
			new CtsaMasterDaoImpl().saveOrUpdate(ctsaMasterVO);
		}
		request.setAttribute("ctsaMasterBean",new CtsaMasterBean());
		request.setAttribute("ctsaList", new CtsaMasterDaoImpl().getAllRecords());
		return mapping.findForward("updateCtsa");
	}
}
