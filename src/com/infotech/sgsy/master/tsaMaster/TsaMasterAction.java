package com.infotech.sgsy.master.tsaMaster;

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




public class TsaMasterAction extends DispatchAction{

	
	protected final Log log = LogFactory.getLog(getClass());
    ActionMessages message = new ActionMessages();

	public ActionForward addTsaMaster(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		
	    TsaMasterBean tsaMasterBean=new TsaMasterBean();
		
		request.setAttribute("tsaList", new TsaMasterDaoImpl().getAllRecords());
		request.setAttribute("tsaMasterBean", tsaMasterBean);
		return mapping.findForward("addTsaMaster");
	}
	
	//...code for CHECK RECORD......
	public ActionForward checkRecord(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		log.info("checkRecord method Starts======>");
		
		String tsaName=request.getParameter("tsaName");
		response.setContentType("text/xml");
		response.setHeader( "Pragma", "no-cache" );
		response.addHeader( "Cache-Control", "must-revalidate" );
		response.addHeader( "Cache-Control", "no-cache" );
		response.addHeader( "Cache-Control", "no-store" );
		response.setDateHeader("Expires", 0);
		String s=null;
		TsaMasterDaoImpl tsaMasterDaoImpl=new TsaMasterDaoImpl();
		
		try{
			 boolean flag=tsaMasterDaoImpl.checkRecord(tsaName);
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
	
	public ActionForward saveTsa(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		log.info("TsaMasterAction : Inside save method Starts======>");
		
		TsaMasterBean tsaMasterBean=(TsaMasterBean)form;
		TsaMasterVO tsaMasterVO=new TsaMasterVO();
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
				
		BeanUtils.copyProperties(tsaMasterVO,tsaMasterBean);
		
		tsaMasterVO.setCreatedBy(loginVO.getUserid());
		tsaMasterVO.setCreatedOnDate(DateUtil.getPresentDate());
				
		new TsaMasterDaoImpl().save(tsaMasterVO);
		log.info("CTSA Inside save method Ends======>");
		return mapping.findForward("saveTsa");
	}
	
	//...code for EDIT CTSA......
	
	public ActionForward editTsa(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		log.info("TsaMasterAction : Inside Edit method Starts======>");
		
		TsaMasterBean tsaMasterBean=(TsaMasterBean)form;
		request.setAttribute("tsaList", new TsaMasterDaoImpl().getAllRecords());
				
		TsaMasterVO tsaMasterVO=new TsaMasterDaoImpl().getRecordFromId(tsaMasterBean.getId());
		if(tsaMasterVO!=null){
			BeanUtils.copyProperties(tsaMasterBean,tsaMasterVO);
		}
		
		tsaMasterBean.setEditId(tsaMasterBean.getId());
		request.setAttribute("tsaMasterBean", tsaMasterBean);
		return mapping.findForward("editTsa");
	}
	
	//...code for delete the CTSA ......
	
	public ActionForward deleteTsa(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		log.info("TsaMasterAction : Inside deleteRole method Starts====  for ctsa==>");
		
		TsaMasterBean tsaMasterBean=(TsaMasterBean)form;
		TsaMasterVO tsaMasterVO=new TsaMasterVO();
		
		BeanUtils.copyProperties(tsaMasterVO,tsaMasterBean);
		boolean status=new TsaMasterDaoImpl().deleteRecordFromId(tsaMasterVO);
		request.setAttribute("tsaList", new TsaMasterDaoImpl().getAllRecords());
		
		return mapping.findForward("addTsaMaster");
	}
	
	
	//...code for UPDATION......
public ActionForward updateTsa(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		log.info("TsaMasterAction : Inside updateCtsa method Starts======>");
		
		TsaMasterBean tsaMasterBean=(TsaMasterBean)form;
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
		System.out.println(",,,,,,,lllllllllllllllll,,,,,"+tsaMasterBean.getEditId());
		if(tsaMasterBean.getEditId()!=null){
			TsaMasterVO tsaMasterVO=new TsaMasterDaoImpl().getRecordFromId(tsaMasterBean.getEditId());
			System.out.println(tsaMasterVO);
			tsaMasterVO.setTsaName(tsaMasterBean.getTsaName());
			tsaMasterVO.setTsaCode(tsaMasterBean.getTsaCode());
			
			tsaMasterVO.setUpdatedBy(loginVO.getUserid());
			tsaMasterVO.setUpdatedOnDate(DateUtil.getPresentDate());
			
		    new TsaMasterDaoImpl().saveOrUpdate(tsaMasterVO);
		}
		
		return mapping.findForward("updateTsa");
	}
	
}
