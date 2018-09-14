package com.infotech.sgsy.userAccessControlManagement;

import java.util.ArrayList;
import java.util.List;

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
import com.infotech.sgsy.projectSetup.projectDetails.ProjectDetailsVO;
import com.infotech.sgsy.util.DateUtil;

/**
 * THIS CLASS IMPLEMENTS AccessMaster Action
 * @author 37595
 *  Date : 24.04.2009
 */
public class ProjectMappingAction extends DispatchAction {

	protected final Log log = LogFactory.getLog(getClass());
	 ActionMessages message = new ActionMessages();

	public ActionForward addProjectMapping(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		ProjectMappingBean projectMappingBean=new ProjectMappingBean();
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
		request.setAttribute("projectMappingsList", new ProjectMappingDaoImpl().getAllRecords(loginVO));
		request.setAttribute("userMasterList", new ProjectMappingDaoImpl().getAllUser(loginVO));
		List<ProjectDetailsVO> assignProjectList=(List<ProjectDetailsVO>) request.getSession().getAttribute("assignProjectDetails"); 
		request.setAttribute("projectDetailsList", assignProjectList);
		request.setAttribute("projectMappingBean", projectMappingBean);
		
		return mapping.findForward("addProjectMapping");
	}
	
	public ActionForward checkRecord(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		log.info("checkRecord method Starts======>");
		
		String projectId=request.getParameter("formName");
		response.setContentType("text/xml");
		response.setHeader( "Pragma", "no-cache" );
		response.addHeader( "Cache-Control", "must-revalidate" );
		response.addHeader( "Cache-Control", "no-cache" );
		response.addHeader( "Cache-Control", "no-store" );
		response.setDateHeader("Expires", 0);
		ProjectMappingDaoImpl ProjectMappingDaoImpl=new ProjectMappingDaoImpl();
		try{
			String data=ProjectMappingDaoImpl.checkRecord(projectId);
			response.getWriter().write(data);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	
	public ActionForward deleteProjectMapping(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("AccessMasterAction : Inside deleteRole method Starts======>");
		ProjectMappingBean projectMappingBean=(ProjectMappingBean)form;
		ProjectMapping projectMapping=new ProjectMapping();
		System.out.println("role Master Bean Id--> "+projectMappingBean.getId());
		boolean status=new ProjectMappingDaoImpl().deleteRecordFromId(projectMappingBean);
		System.out.println("status Is --> "+status);
		projectMappingBean=new ProjectMappingBean();
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
		request.setAttribute("projectMappingsList", new ProjectMappingDaoImpl().getAllRecords(loginVO));
		request.setAttribute("userMasterList", new ProjectMappingDaoImpl().getAllUser(loginVO));
		List<ProjectDetailsVO> assignProjectList=(List<ProjectDetailsVO>) request.getSession().getAttribute("assignProjectDetails"); 
		request.setAttribute("projectDetailsList", assignProjectList);
		request.setAttribute("projectMappingBean", projectMappingBean);
		
		return mapping.findForward("addProjectMapping");
	}
	public ActionForward editProjectMapping(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("AccessMasterAction : Inside Edit method Starts======>");
		ProjectMappingBean projectMappingBean=(ProjectMappingBean)form;
		
		System.out.println("role Master Bean Id--> "+projectMappingBean.getId());
		projectMappingBean.setEditId(projectMappingBean.getId());
		System.out.println(projectMappingBean);
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
		request.setAttribute("projectMappingsList", new ProjectMappingDaoImpl().getAllRecords(loginVO));
		request.setAttribute("userMasterList", new ProjectMappingDaoImpl().getAllUser(loginVO));
		List<ProjectDetailsVO> assignProjectList=(List<ProjectDetailsVO>) request.getSession().getAttribute("assignProjectDetails");
		request.setAttribute("projectDetailsList", assignProjectList);
		request.setAttribute("projectMappingBean", projectMappingBean);
		request.setAttribute("projectDetailsAssignList", new ProjectMappingDaoImpl().getProjectDetailsAssignList(projectMappingBean.getId()));
		request.setAttribute("userMasterAssign", new UserMasterDaoImpl().getRecordFromId(projectMappingBean.getId()));
		return mapping.findForward("editProjectMapping");
	}
	public ActionForward updateProjectMapping(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("AccessMasterAction : Inside updateRole method Starts======>");
		ProjectMappingBean projectMappingBean=(ProjectMappingBean)form;
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
		List<ProjectMapping> projectMappingList=new ArrayList<ProjectMapping>();
		System.out.println("role Master Bean edit Id--> "+projectMappingBean.getEditId());
		System.out.println(projectMappingBean);
		boolean status=false;
		if(projectMappingBean.getEditId()!=null){
			projectMappingBean.setId(projectMappingBean.getEditId());
			boolean status1=new ProjectMappingDaoImpl().deleteRecordFromId(projectMappingBean);
			System.out.println("status Is --> "+status1);
			
			for(int i=0;i<projectMappingBean.getSelectedProjectId().length;i++){
				for(int j=0;j<projectMappingBean.getSelectedUserId().length;j++){
					ProjectMapping projectMapping=new ProjectMapping(projectMappingBean.getSelectedProjectId()[i],projectMappingBean.getSelectedUserId()[j]);
					projectMapping.setCreatedBy(loginVO.getUserid());
					projectMapping.setCreatedDate(DateUtil.getPresentDate());
					System.out.println("Object To Save Is --> "+projectMapping);
					projectMappingList.add(projectMapping);
				}
			}
			new ProjectMappingDaoImpl().save(projectMappingList);
			
			/*for(int i=0;i<projectMappingBean.getSelectedProjectId().length;i++){
				for(int j=0;j<projectMappingBean.getSelectedUserId().length;j++){
					if(projectMappingBean.getSelectedUserId()[j]==projectMappingBean.getEditId()){
						status=true;
					}
					else{
						ProjectMapping projectMapping=new ProjectMapping(projectMappingBean.getSelectedProjectId()[i],projectMappingBean.getSelectedUserId()[j]);
						projectMapping.setCreatedBy(loginVO.getUserid());
						projectMapping.setCreatedDate(DateUtil.getPresentDate());
						System.out.println("Object To Save Is --> "+projectMapping);
						projectMappingList.add(projectMapping);
					}
				}
			}
			if(status){
				List<ProjectMapping> projectMapping=new ProjectMappingDaoImpl().getRecordsFromUserId(projectMappingBean.getEditId());
				for(int i=0;i<projectMappingBean.getSelectedProjectId().length;i++){
					
				}
			}
			else
			{
				projectMappingBean.setId(projectMappingBean.getEditId());
				boolean status1=new ProjectMappingDaoImpl().deleteRecordFromId(projectMappingBean);
				System.out.println("status Is --> "+status1);
			}*/
			
		}
		return mapping.findForward("updateProjectMapping");
	}
	public ActionForward saveProjectMapping(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("AccessMasterAction : Inside save method Starts======>");
		ProjectMappingBean projectMappingBean=(ProjectMappingBean)form;
		System.out.println("Object From Form is --> "+projectMappingBean);
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
		List<ProjectMapping> projectMappingList=new ArrayList<ProjectMapping>();
		for(int i=0;i<projectMappingBean.getSelectedProjectId().length;i++){
			for(int j=0;j<projectMappingBean.getSelectedUserId().length;j++){
				ProjectMapping projectMapping=new ProjectMapping(projectMappingBean.getSelectedProjectId()[i],projectMappingBean.getSelectedUserId()[j]);
				projectMapping.setCreatedBy(loginVO.getUserid());
				projectMapping.setCreatedDate(DateUtil.getPresentDate());
				System.out.println("Object To Save Is --> "+projectMapping);
				projectMappingList.add(projectMapping);
			}
		}
		new ProjectMappingDaoImpl().save(projectMappingList);
		log.info("AccessMasterAction : Inside save method Ends======>");
		
		return mapping.findForward("saveProjectMapping");
	}
}
