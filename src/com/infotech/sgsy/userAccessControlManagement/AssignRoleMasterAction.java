package com.infotech.sgsy.userAccessControlManagement;

import java.util.List;
import java.util.Map;
import java.util.Set;

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
 * THIS CLASS IMPLEMENTS AccessMaster Action
 * @author 37595
 *  Date : 24.04.2009
 */
public class AssignRoleMasterAction extends DispatchAction {

	protected final Log log = LogFactory.getLog(getClass());
	 ActionMessages message = new ActionMessages();

	public ActionForward addAssignRoleMaster(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		AssignRoleMasterBean tempassignRoleMasterBean=(AssignRoleMasterBean)form;
		tempassignRoleMasterBean=new AssignRoleMasterBean();
		System.out.println("assignRoleMasterBean.getRoleId() ---> "+tempassignRoleMasterBean.getRoleId());
		System.out.println("assignRoleMasterBean.getUserId() ---> "+tempassignRoleMasterBean.getUserId());
		
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
		request.setAttribute("assignRoleMastersList", new AssignRoleMasterDaoImpl().getAllRecords(loginVO));
		AssignRoleMasterBean assignRoleMasterBean=new AssignRoleMasterBean();
		request.setAttribute("roleMasterList", new AssignRoleMasterDaoImpl().getAllRoleMsters());
		request.setAttribute("userMasterList", new AssignRoleMasterDaoImpl().getAllUser(loginVO));
		request.setAttribute("formModulesList", new AssignRoleMasterDaoImpl().getAllFormModules(loginVO));
		request.setAttribute("assignRoleMasterBean", assignRoleMasterBean);
		return mapping.findForward("addAssignRole");
	}
	
	public ActionForward checkRecord(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		log.info("checkRecord method Starts======>");
		
		String formName=request.getParameter("formName");
		response.setContentType("text/xml");
		response.setHeader( "Pragma", "no-cache" );
		response.addHeader( "Cache-Control", "must-revalidate" );
		response.addHeader( "Cache-Control", "no-cache" );
		response.addHeader( "Cache-Control", "no-store" );
		response.setDateHeader("Expires", 0);
		String s=null;
		AssignRoleMasterDaoImpl AssignRoleMasterDaoImpl=new AssignRoleMasterDaoImpl();
		try{
			boolean flag=AssignRoleMasterDaoImpl.checkRecord(formName);
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
	public ActionForward saveAssignRoleMaster(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("AccessMasterAction : Inside save method Starts======>");
		AssignRoleMasterBean assignRoleMasterBean=(AssignRoleMasterBean)form;
		AssignRoleMasterBean[] assignRoleMasterArray=new AssignRoleMasterBean[assignRoleMasterBean.getFormModuleIdList().length];
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
		System.out.println("assignRoleMasterBean.getUserId() ---> "+assignRoleMasterBean.getUserId());
		if(assignRoleMasterBean.getUserId()==null || assignRoleMasterBean.getUserId().equalsIgnoreCase("0")||assignRoleMasterBean.getUserId().equalsIgnoreCase("")){
			assignRoleMasterBean.setUserId(null);
		}
		if(assignRoleMasterBean.getRoleId()==null || assignRoleMasterBean.getRoleId().equalsIgnoreCase("0")||assignRoleMasterBean.getRoleId().equalsIgnoreCase("")){
			assignRoleMasterBean.setRoleId(null);
		}
		for(int i=0;i<assignRoleMasterBean.getFormModuleIdList().length;i++){
			AssignRoleMasterBean assignRoleMaster=new AssignRoleMasterBean(assignRoleMasterBean.getRoleId(),assignRoleMasterBean.getUserId(),assignRoleMasterBean.getFormModuleIdList()[i],assignRoleMasterBean.getPermissionCharList()[i]);
			assignRoleMaster.setCreatedBy(loginVO.getUserid());
			assignRoleMaster.setCreatedDate(DateUtil.getPresentDate());
			System.out.println("Object To Save Is --> "+assignRoleMaster);
			assignRoleMasterArray[i]=assignRoleMaster;
		}
		new AssignRoleMasterDaoImpl().save(assignRoleMasterArray);
		log.info("AccessMasterAction : Inside save method Ends======>");
		
		return mapping.findForward("saveAssignRole");
	}
	
	public ActionForward deleteAssignRoleMaster(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("AccessMasterAction : Inside deleteRole method Starts======>");
		AssignRoleMasterBean assignRoleMasterBean=(AssignRoleMasterBean)form;
		System.out.println("role Master Bean Id--> "+assignRoleMasterBean.getId());
		System.out.println(assignRoleMasterBean);
		/*List<FormModuleMasterBean> listForEdit=new AssignRoleMasterDaoImpl().getAllFormModulesForDelete(assignRoleMasterBean.getId());
		AssignRoleMaster[] assignRoleMasterArray=new AssignRoleMaster[listForEdit.size()];
		System.out.println(assignRoleMasterBean);
		for(int i=0;i<listForEdit.size();i++){
			AssignRoleMaster assignRoleMaster=new AssignRoleMasterDaoImpl().getRecordFromId(listForEdit.get(i).getFormOrderNo()+"");
			assignRoleMasterArray[i]=assignRoleMaster;
		}*/
		boolean status=new AssignRoleMasterDaoImpl().deleteRecordFromId(assignRoleMasterBean.getId());
		System.out.println("status Is --> "+status);
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
		request.setAttribute("assignRoleMastersList", new AssignRoleMasterDaoImpl().getAllRecords(loginVO));
		request.setAttribute("roleMasterList", new AssignRoleMasterDaoImpl().getAllRoleMsters());
		request.setAttribute("userMasterList", new AssignRoleMasterDaoImpl().getAllUser(loginVO));
		request.setAttribute("formModulesList", new AssignRoleMasterDaoImpl().getAllFormModules(loginVO));
		
		request.setAttribute("assignRoleMasterBean",new AssignRoleMasterBean());
		return mapping.findForward("addAssignRole");
	}
	public ActionForward editAssignRoleMaster(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("AccessMasterAction : Inside Edit method Starts======>");
		AssignRoleMasterBean assignRoleMasterBean=(AssignRoleMasterBean)form;
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
		request.setAttribute("assignRoleMastersList", new AssignRoleMasterDaoImpl().getAllRecords(loginVO));
		System.out.println("role Master Bean Id--> "+assignRoleMasterBean.getId());
		/*AssignRoleMaster assignRoleMaster=new AssignRoleMasterDaoImpl().getRecordFromId(assignRoleMasterBean.getId());
		if(assignRoleMaster!=null){
			BeanUtils.copyProperties(assignRoleMasterBean,assignRoleMaster);
		}*/
		assignRoleMasterBean.setEditId(assignRoleMasterBean.getId());
//		System.out.println(assignRoleMaster);
//		System.out.println(assignRoleMasterBean);
		
		request.setAttribute("roleMasterList", new AssignRoleMasterDaoImpl().getAllRoleMsters());
		request.setAttribute("userMasterList", new AssignRoleMasterDaoImpl().getAllUser(loginVO));
		Map<String, Set<FormModuleMasterBean>> listForEdit=new AssignRoleMasterDaoImpl().getAllFormModulesForEdit(assignRoleMasterBean,loginVO);
		request.setAttribute("formModulesListForEdit",listForEdit ); 
		request.setAttribute("assignRoleMasterBean", assignRoleMasterBean);
		return mapping.findForward("editAssignRole");
	}
	public ActionForward updateAssignRoleMaster(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("AccessMasterAction : Inside updateRole method Starts======>");
		AssignRoleMasterBean assignRoleMasterBean=(AssignRoleMasterBean)form;
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
		assignRoleMasterBean.setUpdatedBy(loginVO.getUserid());
		new AssignRoleMasterDaoImpl().saveOrUpdate2(assignRoleMasterBean);
		return mapping.findForward("updateAssignRole");
	}
}
