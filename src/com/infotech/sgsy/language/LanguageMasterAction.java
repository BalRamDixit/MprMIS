package com.infotech.sgsy.language;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import com.infotech.sgsy.common.LocationVO;
import com.infotech.sgsy.common.MasterAction;
import com.infotech.sgsy.login.LoginVO;
import com.infotech.sgsy.util.Constants;
import com.infotech.sgsy.util.PopUpMessages;
import com.infotech.sgsy.util.SGSYConstants;

public class LanguageMasterAction extends MasterAction{
	
	LanguageService service = new LanguageServiceImpl();
	LanguageVO langVO = new LanguageVO();
	public ActionForward changeLanguage(ActionMapping mapping, ActionForm form,	HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		
		LanguageActionForm actionForm=(LanguageActionForm)form;
		System.out.println(" hello in lang");
		String lang = request.getParameter("lang");
		actionForm.setLanguage(lang);
		request.getSession().setAttribute("lang", actionForm.getLanguage());
		System.out.println("language="+lang);
	    request.getSession().setAttribute("org.apache.struts.action.LOCALE", new Locale(lang));
	//    ConfigurationMessageResourceFactory factory=new ConfigurationMessageResourceFactory();
	//    factory.createResources("ApplicationResources");
        System.out.println("the encoding "+request.getCharacterEncoding());
		
		return mapping.findForward("continue");
	}
	
	public ActionForward showAddLanguagePack(ActionMapping mapping,ActionForm form,HttpServletRequest request, HttpServletResponse response)
	throws Exception{ 
			 
		 
		LanguageService service = new LanguageServiceImpl();
		LanguageVO langVO = new LanguageVO();
		
		String getLangList =service.getLanguage(langVO);
		request.setAttribute("languageList",getLangList);
	    return mapping.findForward("showAddLanguage");
	}
	public ActionForward showAllLanguage(ActionMapping mapping,ActionForm form,HttpServletRequest request, HttpServletResponse response)
	throws Exception{ 
			 
		ActionMessages messages=new ActionMessages();
		LanguageService service = new LanguageServiceImpl();
		HttpSession session=request.getSession();
		LocationVO  location=(LocationVO)session.getAttribute(SGSYConstants.SGSY_LOCATIONVO);
		List getLangList =service.getLanguagesById(location.getStateShortName());
		request.setAttribute("LanguageList",getLangList);
		if(getLangList.size()==0){
			ActionMessage message=new ActionMessage("search.fail");
			messages.add(SGSYConstants.MSG,message);
		}
		saveMessages(request, messages);
	    return mapping.findForward("showAllLanguage");
	    
	}
	public ActionForward getResources(ActionMapping mapping,ActionForm form,HttpServletRequest request, HttpServletResponse response)
	throws Exception{ 
			 
		LanguageActionForm actionForm=(LanguageActionForm)form;
		LanguageService service = new LanguageServiceImpl();
		HttpSession session=request.getSession();
		LanguageVO langVO = new LanguageVO();
		BeanUtils.copyProperties(langVO,actionForm);
		langVO.setLanguageshortname(actionForm.getLanguageShortName());
		LocationVO  location=(LocationVO)session.getAttribute(SGSYConstants.SGSY_LOCATIONVO);
		int i=0;	
		String[] resourceid=request.getParameterValues("resourceid1");
		String[] resourcename=request.getParameterValues("resourceName1");
		LoginVO loginVO=(LoginVO)request.getSession().getAttribute("loginVO");
	//	Init aa=new Init();
		if(resourceid!=null)
		{		
			langVO.setResourceidArr1(resourceid);
			langVO.setResourceNameArr1(resourcename);
			i=service.saveResources(langVO);
			if(i==1){
			 	request.setAttribute("resourcesSavedSuccessfully", "resourcesSavedSuccessfully");
			 	/*Init aa=new Init();
			 	aa.createFile(request.getSession().getServletContext());*/
			}	
		}
			
		ArrayList getResourceList =service.getAllResources(actionForm.getLanguageShortName());
		request.setAttribute("allResourceList",getResourceList);
		List getLangList =service.getLanguagesById(location.getStateShortName());
		request.setAttribute("LanguageList",getLangList);
		
		
		return mapping.findForward("showAllLanguage");
	    
	}
	
	public ActionForward addLanguage(ActionMapping mapping,ActionForm form,HttpServletRequest request, HttpServletResponse response)
	throws Exception{
			
		log.info("LanguageMasterAction : addLanguage method Starts======>");
		LanguageActionForm actionForm=(LanguageActionForm)form;		
		BeanUtils.copyProperties(langVO,actionForm);
		langVO.setLanguageshortname(actionForm.getLanguageShortName());
		int i=0;		
		i=service.saveLanguage(langVO);
		if(i==1){
		 	request.setAttribute("languageSavedSuccessfully", "languageSavedSuccessfully");
		}	
		actionForm.reset();
		actionForm.setInformationDialog(true); 
		actionForm.setInformationDialogHeader(PopUpMessages.SAVE_HEADER);
		actionForm.setInformationDialogText(PopUpMessages.SAVE_TEXT);
		
		log.info("LanguageMasterAction : addLanguage method Ends======>");
	 
		return mapping.findForward("success");
	}

	@Override
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ActionForward modify(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
			ActionForward forward=new ActionForward();
			ActionMessages messages=new ActionMessages();
			LanguageActionForm actionForm=(LanguageActionForm)form;
			HttpSession session=request.getSession();
			LocationVO  location=(LocationVO)session.getAttribute(SGSYConstants.SGSY_LOCATIONVO);
			langVO=new LanguageVO();
			langVO.setLanguage(actionForm.getLanguage());
			langVO.setResourceidArr1(request.getParameterValues("writeAccess"));
			String languagePack=(String)session.getServletContext().getAttribute("label.languagePack");
			try{
				service.assignLanguagePack(langVO);
				ActionMessage message=new ActionMessage("update.success.message",languagePack);
				messages.add(SGSYConstants.MSG,message);
			}
			catch(Exception e){
				ActionMessage message=new ActionMessage("error.update",languagePack);
				messages.add(SGSYConstants.MSG,message);
			}
			request.setAttribute("allLanguageList", service.getAllLanguage(langVO));
			if(actionForm.getLanguage()!=null && !actionForm.getLanguage().trim().equals("")){
				request.setAttribute("SETSTATELIST", service.getAssignLanguage(actionForm.getLanguage()));
				request.setAttribute(Constants.STATE_LIST, service.getState(location));
			}
			forward=mapping.findForward("showAssign");
			saveMessages(request, messages);
			return forward;
	}

	@Override
	public ActionForward showAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		
			ActionForward forward=new ActionForward();
			LanguageActionForm actionForm=(LanguageActionForm)form;
			HttpSession session=request.getSession();
			LocationVO  location=(LocationVO)session.getAttribute(SGSYConstants.SGSY_LOCATIONVO);
			langVO=new LanguageVO();
			request.setAttribute("allLanguageList", service.getAllLanguage(langVO));
			if(actionForm.getLanguage()!=null && !actionForm.getLanguage().trim().equals("")){
				request.setAttribute("SETSTATELIST", service.getAssignLanguage(actionForm.getLanguage()));
				request.setAttribute(Constants.STATE_LIST, service.getState(location));
			}
			forward=mapping.findForward("showAssign");
			return forward;
	}

	@Override
	public ActionForward showDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ActionForward showModify(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ActionForward showView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
