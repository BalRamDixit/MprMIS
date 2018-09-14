package com.infotech.sgsy.login;

import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.infotech.sgsy.exception.ConnectionFailedException;
import com.infotech.sgsy.exception.PasswordNotValidException;
import com.infotech.sgsy.exception.SystemFailureException;
import com.infotech.sgsy.exception.UserAlreadyLoggedIn;
import com.infotech.sgsy.exception.UserBlockedException;
import com.infotech.sgsy.exception.UserLockedException;
import com.infotech.sgsy.projectSetup.projectDetails.ProjectDetailsVO;
import com.infotech.sgsy.userAccessControlManagement.AccessModuleListForUserAndMenuBean;
import com.infotech.sgsy.userAccessControlManagement.RoleMasterDaoImpl;
import com.infotech.sgsy.userAccessControlManagement.UserMaster;
import com.infotech.sgsy.util.SGSYConstants;

/**
 * @since August 2013
 * @author mehra
 *
 */
public class LoginMasterAction extends DispatchAction {

	protected final Log log = LogFactory.getLog(getClass());
	public ActionForward login(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		log.info("NRLM Skills Login Method Calls....");
		String flag = "0";
		//check for user already login
		//added by Balram Dixit for Home Button
		LoginActionForm actionForm = (LoginActionForm) form;
		LoginService loginService = new LoginServiceImpl();
		try {
			{
				if(request.getSession().getAttribute("userID")!=null){
					String userid=(String)request.getSession().getAttribute("userID");
					System.out.println("User ID found in Session "+userid);
					String changeRoleFor = request.getParameter("rol");
					String trackerId = request.getParameter("reqtrack");
					userid = actionForm.getUserid().toUpperCase().trim();
					String password = actionForm.getPassword().trim();
					loginService.login(userid, password, request, changeRoleFor, trackerId);
					if(request.getSession().getAttribute("loginVO")!=null && request.getSession().getAttribute("userBeanData")!=null){
						request.setAttribute("logFlag", flag);
						return mapping.findForward("success");
					}
					else{
						return mapping.findForward("logout");
					}
				}
			}
			request.getSession().removeAttribute("userAlreadyLoggedIn");	
			ActionErrors errors = new ActionErrors();
			String changeRoleFor = request.getParameter("rol");
			System.out.println("changeRoleFor---> "+changeRoleFor);
			String trackerId = request.getParameter("reqtrack");
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("TRACKERID", request.getParameter("reqtrack"));
			String userid = "", password = "";
			
			if (changeRoleFor != null && changeRoleFor.equals("R")) {
				userid = (String) request.getSession().getAttribute("userID");
				password = "";
			} else {
				userid = actionForm.getUserid().toUpperCase().trim();
				password = actionForm.getPassword().trim();
			}
			
			// get the detail of login user and also check the status
			UserMaster loginVO = loginService.login(userid, password, request, changeRoleFor, trackerId);
			
			if (loginVO != null) {
				if (changeRoleFor == null) {
					if (loginVO.getLoginAttempt() > 3) {
						errors.add("userid", new ActionError("errors.loginId.blocked"));
						if (!errors.isEmpty()) {
							saveErrors(request, errors);
						}
						request.setAttribute("logFlag", flag);
						return mapping.findForward("failed");
					}

					if (loginVO.getLoginStatus() != null) {
						if (loginVO.getLoginStatus().equals("0")) {
							errors.add("userid", new ActionError("errors.lookup.unknown"));
							if (!errors.isEmpty()) {
								saveErrors(request, errors);
							}
							request.setAttribute("logFlag", flag);
							return mapping.findForward("failed");
						}
						if (loginVO.getLoginStatus().equals("N")) {
							errors.add("userid", new ActionError("errors.notAuthorised"));
							if (!errors.isEmpty()) {
								saveErrors(request, errors);
							}
							request.setAttribute("logFlag", flag);
							return mapping.findForward("failed");
						}
						if (loginVO.getLoginStatus().equals("B")) {
							errors.add("userid", new ActionError("errors.loginId.blocked"));
							request.getSession().setAttribute("failed", "User with this Id is Blocked. Please contact site Administrator.");
							if (!errors.isEmpty()) {
								saveErrors(request, errors);
							}
							request.setAttribute("logFlag", flag);
							return mapping.findForward("failed");
						}
					}
				}
				AccessModuleListForUserAndMenuBean bean=loginService.getAccessModuleListForMenu(loginVO);
				System.out.println("Menu Data is --> "+bean);
				List<ProjectDetailsVO> assignProjectList=loginService.getAssignProjectList(loginVO);
				System.out.println("assignProjectList --> "+assignProjectList);
				request.getSession().setAttribute("userBeanData",bean);
				request.getSession().setAttribute("assignProjectDetails",assignProjectList);
				//List<ProjectDetailsVO> sanctionProjectList=new LoginMasterDAOImpl().filterAndGetAllSanctionProject(assignProjectList);
				System.out.println("loginVo ==> ");
				LoginVO userVO=new LoginVO(loginVO);
				System.out.println("loginVo ==> "+userVO);
				request.getSession().setAttribute("userID", loginVO.getLoginId().toUpperCase().trim());
				request.getSession().setAttribute("userVO", loginVO);
				request.getSession().setAttribute("loginVO", userVO);
				request.getSession().setAttribute("stateName", userVO.getStateName());
				request.getSession().setAttribute("userType", new RoleMasterDaoImpl().getRecordFromId(loginVO.getRoleId()).getRoleName());
				request.getSession().setAttribute("RoleId", loginVO.getRoleId());
				if (SGSYConstants.DEFAULT_PASSWORD.equalsIgnoreCase(loginVO.getPassword())) {
					request.setAttribute("logFlag", flag);
					request.getSession().setAttribute("userBeanData",null);
					return mapping.findForward("showChangePasswdPage");
				}

				request.setAttribute("logFlag", flag);
				return mapping.findForward("success");
			}
			else {
				errors.add("userid", new ActionError("errors.lookup.unknown"));
				if (!errors.isEmpty()) {
					saveErrors(request, errors);
				}
				request.setAttribute("logFlag", flag);
				return mapping.findForward("failed");
			}
		} catch (UserAlreadyLoggedIn e) {
			//request.getSession().invalidate();
			request.getSession().setAttribute("failed", "User with this Id is already logged in on another computer. Please Try After Some Time.");
			String userid = actionForm.getUserid().toUpperCase().trim();
			String ipAddress = request.getRemoteAddr();
			try{
				LoginMasterDAOImpl.logout(userid.toUpperCase(), ipAddress);
			}
			catch(Exception ex){
				e.getStackTrace();
			}
			return mapping.findForward("failed");
		}catch (UserLockedException e) {
			request.getSession().setAttribute("failed", "User with this Id is locked. Please contact Administrator.");
			return mapping.findForward("failed");
		}catch (UserBlockedException e) {
			request.getSession().setAttribute("failed", "User with this Id is Blocked. Please contact site Administrator.");
			return mapping.findForward("failed");
		}
		catch (PasswordNotValidException e) {
			request.getSession().setAttribute("failed", "Invalid User Id or Password. Please try again.");
			return mapping.findForward("failed");
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
			return mapping.findForward("failed");
		}
	}

	public ActionForward logOut(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, SystemFailureException,
			ConnectionFailedException, Exception {

		ActionForward forward = new ActionForward();
		try {
			log.info("logOut method ... ");
			ResourceBundle resourceBundle = ResourceBundle.getBundle("ApplicationResources");
			HttpSession httpSession = request.getSession(false);
			LoginActionForm lform = (LoginActionForm) form;
			String reqToken = request.getParameter("Header");
			
			if (reqToken != null&& (reqToken.trim()).equals(httpSession.getAttribute("TRACKERID"))) {
				System.out.println("Condition is true");
				request.getSession().getAttribute("userID");
				String userId = "";
				if (lform != null) {
					if (lform.getUserid() != null)
					{
						userId = lform.getUserid();
					}
				}
				if (request.getSession().getAttribute("userID") != null){
					userId = (String) request.getSession().getAttribute("userID");
				}
				if (userId != null && !userId.equals("")) {
					String ipAddress = request.getRemoteAddr();
					request.getSession().setAttribute("ipAddress", ipAddress);
					LoginMasterDAOImpl.logout(userId.toUpperCase(), ipAddress);
				}
				
				response.setContentType("text/xml");
				response.setHeader("Pragma", "no-cache");
				response.addHeader("Cache-Control", "must-revalidate");
				response.addHeader("Cache-Control", "no-cache");
				response.addHeader("Cache-Control", "no-store");
				response.setDateHeader("Expires", 0);
				request.setAttribute("logFlag", "0");
				request.setAttribute("logoutMsg",resourceBundle.getString("logoutMsg").trim());

				request.getSession().setAttribute("flag",resourceBundle.getString("logoutMsg").trim());
				forward = mapping.findForward("logoutSuccess");
			} else {
				System.out.println("Condition is False");
				forward = mapping.findForward("logoutFailed");
			}
			if (httpSession != null) {
				httpSession.invalidate();
			}
			log.info("logOut method Ends======>");
		} catch (Exception e) {
			log.error(e.getMessage());
		} 
		return forward;
	}

	
	public static String getUrl(HttpServletRequest req) {
		String servletPath = req.getServletPath(); 
		String pathInfo = req.getPathInfo(); 
		String queryString = req.getQueryString(); 
		String url = servletPath;
		if (pathInfo != null) {
			url += pathInfo;
		}
		if (queryString != null) {
			url += "?" + queryString;
		}
		return url;
	}

	public ActionForward showChangePasswordPage(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.info("changePassword method Starts======>");

		if ("backToMainPage".equalsIgnoreCase(request.getParameter("pageClose"))) {
			return mapping.findForward("changePasswordSuccess");
		} else {
			return mapping.findForward("showChangePasswdPage");
		}
	}

	public ActionForward showMainPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("showLoginPage");
	}

	public ActionForward checkOldPassword(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.info("checkOldPassword method Starts======>");

		String password = request.getParameter("oldPassword");
		
		response.setContentType("text/html");
		response.setHeader("Pragma", "no-cache");
		response.addHeader("Cache-Control", "must-revalidate");
		response.addHeader("Cache-Control", "no-cache");
		response.addHeader("Cache-Control", "no-store");
		response.setDateHeader("Expires", 0);
		String s = "false";

		LoginService loginService = new LoginServiceImpl();
		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");
		try {
			boolean flag = loginService.checkOldPassword(password, loginVO.getUserid(),(String)request.getSession().getAttribute("TRACKERID"));
			System.out.println("Flag is --> "+flag);
			if (flag == false) {
				response.getWriter().write("error");
			} else {
				s = "true";
				response.getWriter().write(s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ActionForward changePassword(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		log.info("changePassword method Starts ... ");
		try {
			LoginActionForm actionForm = (LoginActionForm) form;
			LoginService loginService = new LoginServiceImpl();
			ResourceBundle resourceBundle = ResourceBundle.getBundle("ApplicationResources");
			LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");
			String newPassword = actionForm.getNewPassword();
			String oldPassword = actionForm.getOldPassword();
			int flag = loginService.changePassword(newPassword, oldPassword, loginVO.getUserid());
			request.setAttribute("passwordChanged", "Password Changed Successfully! Please Login Again.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("changePasswordSuccess");
	}

	public ActionForward home(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, SystemFailureException,
			ConnectionFailedException {

		HttpSession httpSession = request.getSession(false);
		if (httpSession != null) {
			httpSession.invalidate();
		}
		return mapping.findForward("home");
	}

	
	public ActionForward closePage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, SystemFailureException,
			ConnectionFailedException {

		return mapping.findForward("closePage");
	}

}
