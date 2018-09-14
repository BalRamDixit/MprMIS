package com.infotech.sgsy.master.parliamentaryConstituencyMaster;

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

public class ParliamentaryConstituencyMasterAction extends DispatchAction{

	
	protected final Log log = LogFactory.getLog(getClass());
    ActionMessages message = new ActionMessages();

	public ActionForward addParliamentaryConstituencyMaster(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		
	    ParliamentaryConstituencyMasterBean ctsaMasterBean=new ParliamentaryConstituencyMasterBean();
		
		request.setAttribute("ctsaList", new ParliamentaryConstituencyMasterDaoImpl().getAllRecords());
		request.setAttribute("ctsaMasterBean", ctsaMasterBean);
		return mapping.findForward("addParliamentaryConstituencyMaster");
	}
	
	//...code for CHECK RECORD......
	public ActionForward checkRecord(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		log.info("checkRecord method Starts======>");
		
		String ctsaName=request.getParameter("name");
		System.out.println("ctsa......"+ctsaName);
		response.setContentType("text/xml");
		response.setHeader( "Pragma", "no-cache" );
		response.addHeader( "Cache-Control", "must-revalidate" );
		response.addHeader( "Cache-Control", "no-cache" );
		response.addHeader( "Cache-Control", "no-store" );
		response.setDateHeader("Expires", 0);
		String s=null;
		ParliamentaryConstituencyMasterDaoImpl ctsaMasterDaoImpl=new ParliamentaryConstituencyMasterDaoImpl();
		
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
		
		ParliamentaryConstituencyMasterBean ctsaMasterBean=(ParliamentaryConstituencyMasterBean)form;
		ParliamentaryConstituencyMasterVO ctsaMasterVO=new ParliamentaryConstituencyMasterVO();
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
				
		BeanUtils.copyProperties(ctsaMasterVO,ctsaMasterBean);
		
		ctsaMasterVO.setCreatedBy(loginVO.getUserid());
		ctsaMasterVO.setCreatedOnDate(DateUtil.getPresentDate());
		System.out.println("Object To Save Is for Ctsa--> "+ctsaMasterVO);
		
		new ParliamentaryConstituencyMasterDaoImpl().save(ctsaMasterVO);
		log.info("CTSA Inside save method Ends======>");
		return mapping.findForward("saveParliamentaryConstituencyMaster");
	}
	
	//...code for EDIT CTSA......
	
	public ActionForward editCtsa(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		log.info("CtsaMasterAction : Inside Edit method Starts======>");
		
		ParliamentaryConstituencyMasterBean ctsaMasterBean=(ParliamentaryConstituencyMasterBean)form;
		request.setAttribute("ctsaList", new ParliamentaryConstituencyMasterDaoImpl().getAllRecords());
		System.out.println("Ctsa Master Bean Id--> "+ctsaMasterBean.getId());
		
		ParliamentaryConstituencyMasterVO ctsaMasterVO=new ParliamentaryConstituencyMasterDaoImpl().getRecordFromId(ctsaMasterBean.getId());
		if(ctsaMasterVO!=null){
			BeanUtils.copyProperties(ctsaMasterBean,ctsaMasterVO);
		}
		System.out.println(ctsaMasterVO);
		System.out.println(ctsaMasterBean);
		
		ctsaMasterBean.setEditId(ctsaMasterBean.getId());
		request.setAttribute("ctsaMasterBean", ctsaMasterBean);
		return mapping.findForward("editParliamentaryConstituencyMaster");
	}
	
	//...code for delete the CTSA ......
	
	public ActionForward deleteCtsa(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		log.info("CtsaMasterAction : Inside deleteRole method Starts====  for ctsa==>");
		
		ParliamentaryConstituencyMasterBean ctsaMasterBean=(ParliamentaryConstituencyMasterBean)form;
		ParliamentaryConstituencyMasterVO ctsaMasterVO=new ParliamentaryConstituencyMasterVO();
		
		System.out.println("CTSA Bean Id--> "+ctsaMasterBean.getId());
		BeanUtils.copyProperties(ctsaMasterVO,ctsaMasterBean);
		System.out.println(ctsaMasterVO);
		System.out.println(ctsaMasterBean);
		boolean status=new ParliamentaryConstituencyMasterDaoImpl().deleteRecordFromId(ctsaMasterVO);
		System.out.println("status Is --> "+status);
		request.setAttribute("ctsaList", new ParliamentaryConstituencyMasterDaoImpl().getAllRecords());
		return mapping.findForward("addParliamentaryConstituencyMaster");
	}
	
	
	//...code for UPDATION......
	public ActionForward updateCtsa(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		log.info("CtsaMasterAction : Inside updateCtsa method Starts======>");
		
		ParliamentaryConstituencyMasterBean ctsaMasterBean=(ParliamentaryConstituencyMasterBean)form;
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
		
		System.out.println("cytsa Master Bean edit Id--> "+ctsaMasterBean.getEditId());
		System.out.println(ctsaMasterBean);
		
		if(ctsaMasterBean.getEditId()!=null){
			ParliamentaryConstituencyMasterVO ctsaMasterVO=new ParliamentaryConstituencyMasterDaoImpl().getRecordFromId(ctsaMasterBean.getEditId());
			ctsaMasterVO.setName(ctsaMasterBean.getName());
			ctsaMasterVO.setCode(ctsaMasterBean.getCode());
			
			ctsaMasterVO.setUpdatedBy(loginVO.getUserid());
			ctsaMasterVO.setUpdatedOnDate(DateUtil.getPresentDate());
			
			 System.out.println("Object To Save Is --> "+ctsaMasterVO);
			 
			new ParliamentaryConstituencyMasterDaoImpl().saveOrUpdate(ctsaMasterVO);
		}
		request.setAttribute("ctsaMasterBean",new ParliamentaryConstituencyMasterBean());
		request.setAttribute("ctsaList", new ParliamentaryConstituencyMasterDaoImpl().getAllRecords());
		return mapping.findForward("updateParliamentaryConstituencyMaster");
	}
}
