package com.infotech.sgsy.master.certifyingAgency;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.infotech.sgsy.login.LoginVO;
import com.infotech.sgsy.userAccessControlManagement.RoleMaster;
import com.infotech.sgsy.userAccessControlManagement.RoleMasterBean;
import com.infotech.sgsy.userAccessControlManagement.RoleMasterDaoImpl;
import com.infotech.sgsy.util.DateUtil;

public class CertifyingAgencyAction extends DispatchAction{

	public ActionForward addCertifyingAgency(ActionMapping mapping, ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {
		request.setAttribute("roleList", new CertifyingAgencyDao().getAllRecords());
	
		/*RoleMasterBean roleMasterBean=new RoleMasterBean();
		request.setAttribute("roleMasterBean", roleMasterBean);*/
		/*return mapping.findForward("addRole")*/
	
	
		return mapping.findForward("showPage");
       }
   
public ActionForward saveRole(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("AccessMasterAction : Inside save method Starts======>");
		CertifyingAgencyForm certifyingAgencyForm=(CertifyingAgencyForm)form;
		
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
		CertifyingAgencyVO certifyingAgencyVO =new CertifyingAgencyVO();
		/*RoleMaster roleMaster=new RoleMaster();*/
		BeanUtils.copyProperties(certifyingAgencyVO,certifyingAgencyForm);
		certifyingAgencyVO.setCreatedBy(loginVO.getUserid());
		certifyingAgencyVO.setCreatedDate(DateUtil.getPresentDate());
		System.out.println("Object To Save Is --> "+certifyingAgencyVO);
		new CertifyingAgencyDao().save(certifyingAgencyVO);
		/*new RoleMasterDaoImpl().save(roleMaster);*/
		log.info("AccessMasterAction : Inside save method Ends======>");
		return mapping.findForward("saveRole");
	}
public ActionForward editRole(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
	log.info("AccessMasterAction : Inside Edit method Starts======>");
	
	CertifyingAgencyForm certifyingAgencyForm=(CertifyingAgencyForm)form;
	request.setAttribute("roleList", new CertifyingAgencyDao().getAllRecords());
	System.out.println("role Master Bean Id--> "+certifyingAgencyForm.getCertifyingAgencyId());
	CertifyingAgencyVO certifyingAgencyVO=new CertifyingAgencyDao().getRecordFromId(certifyingAgencyForm.getCertifyingAgencyId());
	if(certifyingAgencyVO!=null){
		BeanUtils.copyProperties(certifyingAgencyForm,certifyingAgencyVO);
	}
	System.out.println(certifyingAgencyVO);
	System.out.println(certifyingAgencyForm);
	certifyingAgencyForm.setEditId(certifyingAgencyForm.getCertifyingAgencyId());
	request.setAttribute("certifyingAgencyForm", certifyingAgencyForm);
	return mapping.findForward("editRole");
}
public ActionForward deleteRole(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
	log.info("AccessMasterAction : Inside deleteRole method Starts======>");
	CertifyingAgencyForm certifyingAgencyForm=(CertifyingAgencyForm)form;
	CertifyingAgencyVO certifyingAgencyVO=new CertifyingAgencyVO();
	System.out.println("role Master Bean Id--> "+certifyingAgencyForm.getCertifyingAgencyId());
	BeanUtils.copyProperties(certifyingAgencyVO,certifyingAgencyForm);
	System.out.println(certifyingAgencyVO);
	System.out.println(certifyingAgencyForm);
	boolean status=new CertifyingAgencyDao().deleteRecordFromId(certifyingAgencyVO);
	System.out.println("status Is --> "+status);
	request.setAttribute("roleList", new CertifyingAgencyDao().getAllRecords());
	return mapping.findForward("showPage");
}

public ActionForward updateRole(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
	log.info("AccessMasterAction : Inside updateRole method Starts======>");
	CertifyingAgencyForm certifyingAgencyForm=(CertifyingAgencyForm)form;
	LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
	
	System.out.println("role Master Bean edit Id--> "+certifyingAgencyForm.getEditId());
	System.out.println(certifyingAgencyForm);
	if(certifyingAgencyForm.getEditId()!=null){
		CertifyingAgencyVO certifyingAgencyVO=new CertifyingAgencyDao().getRecordFromId(certifyingAgencyForm.getEditId());
		certifyingAgencyVO.setCertifyingAgencyName(certifyingAgencyForm.getCertifyingAgencyName());
		certifyingAgencyVO.setCertifyingAgencyCode(certifyingAgencyForm.getCertifyingAgencyCode());
		certifyingAgencyVO.setUpdatedBy(loginVO.getUserid());
		certifyingAgencyVO.setUpdatedDate(DateUtil.getPresentDate());
		System.out.println("Object To Save Is --> "+certifyingAgencyVO);
		new CertifyingAgencyDao().saveOrUpdate(certifyingAgencyVO);
	}
	request.setAttribute("certifyingAgencyForm",new CertifyingAgencyForm());
	request.setAttribute("roleList", new CertifyingAgencyDao().getAllRecords());
	return mapping.findForward("updateRole");
}
public ActionForward checkRecord(ActionMapping mapping, ActionForm form, 
		HttpServletRequest request, HttpServletResponse response) throws Exception{
	log.info("checkRecord method Starts======>");
	
	String certifyingAgencyName=request.getParameter("certifyingAgencyName");
	/*response.setContentType("text/xml");
	response.setHeader( "Pragma", "no-cache" );
	response.addHeader( "Cache-Control", "must-revalidate" );
	response.addHeader( "Cache-Control", "no-cache" );
	response.addHeader( "Cache-Control", "no-store" );
	response.setDateHeader("Expires", 0);*/
	String s=null;
	CertifyingAgencyDao certifyingAgencyDao=new CertifyingAgencyDao();
	try{
		boolean flag=certifyingAgencyDao.checkRecord(certifyingAgencyName);
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
}

