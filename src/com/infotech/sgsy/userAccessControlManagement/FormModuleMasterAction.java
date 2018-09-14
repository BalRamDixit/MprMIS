package com.infotech.sgsy.userAccessControlManagement;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;

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

import com.infotech.sgsy.util.DateUtil;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

/**
 * THIS CLASS IMPLEMENTS AccessMaster Action
 * @author 37595
 *  Date : 24.04.2009
 */
public class FormModuleMasterAction extends DispatchAction {

	protected final Log log = LogFactory.getLog(getClass());
	 ActionMessages message = new ActionMessages();

	/*
	 * This method is used to display the Access Add Role page 
	 * 
	 */
	public ActionForward addModule(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("formModulesList", new FormModuleMasterDaoImpl().getAllRecords());
		FormModuleMasterBean formModuleMasterBean=new FormModuleMasterBean();
		request.setAttribute("masterModulesList",new FormModuleMasterDaoImpl().getAllMasterModuleList());
		request.setAttribute("formModuleMasterBean", formModuleMasterBean);
		return mapping.findForward("addModule");
	}
	
	public ActionForward checkRecord(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		log.info("checkRecord method Starts======>");
		
		String formName=request.getParameter("formName");
		response.setContentType("text/html");
		response.setHeader( "Pragma", "no-cache" );
		response.addHeader( "Cache-Control", "must-revalidate" );
		response.addHeader( "Cache-Control", "no-cache" );
		response.addHeader( "Cache-Control", "no-store" );
		response.setDateHeader("Expires", 0);
		String s=null;
		FormModuleMasterDaoImpl formModuleMasterDaoImpl=new FormModuleMasterDaoImpl();
		try{
			boolean flag=formModuleMasterDaoImpl.checkRecord(formName);
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
	public ActionForward saveModule(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("AccessMasterAction : Inside save method Starts======>");
		FormModuleMasterBean formModuleMasterBean=(FormModuleMasterBean)form;
		UserMaster loginVO = (UserMaster)request.getSession().getAttribute("userVO");
		FormModuleMaster formModuleMaster=new FormModuleMaster();
		BeanUtils.copyProperties(formModuleMaster,formModuleMasterBean);
		formModuleMaster.setCreatedBy(loginVO.getId());
		formModuleMaster.setCreatedDate(DateUtil.getPresentDate());
		System.out.println("Object To Save Is --> "+formModuleMaster);
		new FormModuleMasterDaoImpl().save(formModuleMaster);
		log.info("AccessMasterAction : Inside save method Ends======>");
		return mapping.findForward("saveModule");
	}
	public ActionForward editModule(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("AccessMasterAction : Inside Edit method Starts======>");
		FormModuleMasterBean formModuleMasterBean=(FormModuleMasterBean)form;
		request.setAttribute("formModulesList", new FormModuleMasterDaoImpl().getAllRecords());
		System.out.println("role Master Bean Id--> "+formModuleMasterBean.getId());
		FormModuleMaster formModuleMaster=new FormModuleMasterDaoImpl().getRecordFromId(formModuleMasterBean.getId());
		if(formModuleMaster!=null){
			BeanUtils.copyProperties(formModuleMasterBean,formModuleMaster);
		}
		System.out.println(formModuleMaster);
		System.out.println(formModuleMasterBean);
		if(formModuleMasterBean.getModuleName()==null||formModuleMasterBean.getModuleName().equalsIgnoreCase("")||formModuleMasterBean.getModuleName().equalsIgnoreCase("0")){
			formModuleMasterBean.setCheckForMasterModule(true);
		}
		formModuleMasterBean.setEditId(formModuleMasterBean.getId());
		request.setAttribute("formModuleMasterBean", formModuleMasterBean);
		request.setAttribute("masterModulesList",new FormModuleMasterDaoImpl().getAllMasterModuleList());
		return mapping.findForward("editModule");
	}
	public ActionForward deleteModule(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("AccessMasterAction : Inside deleteRole method Starts======>");
		FormModuleMasterBean formModuleMasterBean=(FormModuleMasterBean)form;
		FormModuleMaster formModuleMaster=new FormModuleMaster();
		System.out.println("role Master Bean Id--> "+formModuleMasterBean.getId());
		BeanUtils.copyProperties(formModuleMaster,formModuleMasterBean);
		System.out.println(formModuleMaster);
		System.out.println(formModuleMasterBean);
		boolean status=new FormModuleMasterDaoImpl().deleteRecordFromId(formModuleMaster);
		System.out.println("status Is --> "+status);
		request.setAttribute("formModulesList", new FormModuleMasterDaoImpl().getAllRecords());
		return mapping.findForward("addModule");
	}
	
	public ActionForward updateModule(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("AccessMasterAction : Inside updateRole method Starts======>");
		FormModuleMasterBean formModuleMasterBean=(FormModuleMasterBean)form;
		UserMaster loginVO = (UserMaster)request.getSession().getAttribute("userVO");
		
		System.out.println("role Master Bean edit Id--> "+formModuleMasterBean.getEditId());
		System.out.println(formModuleMasterBean);
		if(formModuleMasterBean.getEditId()!=null){
			FormModuleMaster formModuleMaster=new FormModuleMasterDaoImpl().getRecordFromId(formModuleMasterBean.getEditId());
			formModuleMaster.setFormName(formModuleMasterBean.getFormName());
			formModuleMaster.setFormOrderNo(formModuleMasterBean.getFormOrderNo());
			formModuleMaster.setUrl(formModuleMasterBean.getUrl());
			formModuleMaster.setUpdatedBy(loginVO.getId());
			formModuleMaster.setUpdatedDate(DateUtil.getPresentDate());
			formModuleMaster.setModuleName(formModuleMasterBean.getModuleName());
			System.out.println("Object To Save Is --> "+formModuleMaster);
			new FormModuleMasterDaoImpl().saveOrUpdate(formModuleMaster);
		}
		request.setAttribute("formModuleMasterBean",new FormModuleMasterBean());
		request.setAttribute("formModulesList", new FormModuleMasterDaoImpl().getAllRecords());
		return mapping.findForward("updateModule");
	}
	public ActionForward setMenuItem(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		log.info("checkRecord method Starts======>");
		
		String formId=request.getParameter("formId");
		response.setContentType("text/html");
		response.setHeader( "Pragma", "no-cache" );
		response.addHeader( "Cache-Control", "must-revalidate" );
		response.addHeader( "Cache-Control", "no-cache" );
		response.addHeader( "Cache-Control", "no-store" );
		response.setDateHeader("Expires", 0);
		String s=null;
		FormModuleMasterDaoImpl formModuleMasterDaoImpl=new FormModuleMasterDaoImpl();
		try{
			System.out.println("Form Id is --> "+formId);
			AccessModuleListForUserAndMenuBean bean=(AccessModuleListForUserAndMenuBean)request.getSession().getAttribute("userBeanData");
			if(bean==null || bean.getRoleId()==null || bean.getUserId()==null || formId==null){
				s="logout";
			}
			else{
				boolean flag=formModuleMasterDaoImpl.checkPermissionForForm(formId,bean);
				if(flag==false){
					response.getWriter().write("error");
					s="You Don't Have Permission To Access This Module";
					response.getWriter().write(s);
				}else{   
					request.getSession().setAttribute("currentFormId", formId);
					request.getSession().setAttribute("masterFormId",new FormModuleMasterDaoImpl().getMasterModuleFromFormId( formId));
					s="true";
					response.getWriter().write(s);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	public ActionForward getMenuItem(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		log.info("checkRecord method Starts======>");
		response.setContentType("text/html");
		response.setHeader( "Pragma", "no-cache" );
		response.addHeader( "Cache-Control", "must-revalidate" );
		response.addHeader( "Cache-Control", "no-cache" );
		response.addHeader( "Cache-Control", "no-store" );
		response.setDateHeader("Expires", 0);
		String s=null;
		
		try{
			if(request.getSession().getAttribute("currentFormId")!=null){
				s=new FormModuleMasterDaoImpl().getMasterModuleFromFormId(request.getSession().getAttribute("currentFormId")+"");
			}
			System.out.println("Form Module To Open is --> "+s);
			response.getWriter().write(s);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	public ActionForward checkPermissionForFormForInsert(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		log.info("checkRecord method Starts======>");
		
		response.setContentType("text/html");
		response.setHeader( "Pragma", "no-cache" );
		response.addHeader( "Cache-Control", "must-revalidate" );
		response.addHeader( "Cache-Control", "no-cache" );
		response.addHeader( "Cache-Control", "no-store" );
		response.setDateHeader("Expires", 0);
		String formId=request.getSession().getAttribute("currentFormId")+"";
		FormModuleMasterDaoImpl formModuleMasterDaoImpl=new FormModuleMasterDaoImpl();
		String s=null;
		try{
			System.out.println("Form Id is --> "+formId);
			AccessModuleListForUserAndMenuBean bean=(AccessModuleListForUserAndMenuBean)request.getSession().getAttribute("userBeanData");
			if(bean==null || bean.getRoleId()==null || bean.getUserId()==null || formId==null){
				s="logout";
			}
			else{
				boolean flag=formModuleMasterDaoImpl.checkPermissionForFormForInsert(formId,bean);
				if(flag==false){
					s="You Don't Have Permission To Perform This Action For This Module";
					response.getWriter().write(s);
				}else{   
					s="true";
					response.getWriter().write(s);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	public ActionForward downloadPdf(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		try {
			System.out.println("PDF CReation Started");
			
			String data=request.getParameter("data");
			data.replaceAll("<br>", "<br><//br>");
			String k = "<html><body> "+data+" </body></html>\0";
		    String filePath=getServlet().getServletContext().getRealPath("/")+"downloads";
		    System.out.println("Path To save File==> "+filePath);
		    Random random=new Random();
		    String fileFullPath=File.separator+"Test"+random.nextLong()+".pdf";
		    OutputStream file = new FileOutputStream(new File(filePath+fileFullPath));
		    Document document = new Document(PageSize.A4.rotate(),0,0,0,0);
		    PdfWriter writer = PdfWriter.getInstance(document, file);
		    document.open();
		    InputStream is = new ByteArrayInputStream(k.getBytes());
		    XMLWorkerHelper.getInstance().parseXHtml(writer, document, is);
		    document.close();
		    file.close();
		    response.setContentType("text/html");
			response.setHeader( "Pragma", "no-cache" );
			response.addHeader( "Cache-Control", "must-revalidate" );
			response.addHeader( "Cache-Control", "no-cache" );
			response.addHeader( "Cache-Control", "no-store" );
			response.setDateHeader("Expires", 0);
			response.getWriter().write(".."+File.separator+"downloads"+fileFullPath);
		} catch (Exception e) {
		    e.printStackTrace();
		}
		
		return null;
	}
}
