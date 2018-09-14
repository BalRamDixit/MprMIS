package com.infotech.sgsy.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.infotech.sgsy.menuGeneratiion.FormModuleVO;

public class AuthFilter implements Filter {

	String host;
	String host1;
	String host2;
	/*
	 * String HomeLink1 = "/uploadCircular.do"; // used for upload circular
	 * String HomeLink2 = "/outerAction.do"; // used to get the common reports
	 * String HomeLink3 = "/registration.do"; // used to get the common reports
	 * String HomeLink4 = "/mmuMasterReport.do"; // used to get the common
	 * reports
	 */
	String[] HomeLink = new String[] { "/mmuMasterReport.do", "/login/stateFinancialForm.do" , 
			"/login/projectTarget.do" , "/login/tradeTarget.do" ,"/login/districtTarget.do" ,
			"/login/tcSetupEntry.do" ,"/login/tcSetupDue.do" ,"/login/tcSetupTrade.do" ,"/login/stateSanctionAction.do",
			"/login/ppwsSetup.do" ,"/login/stateHRTeam.do","/login/projectCandidateDataAction.do","/login/physicalAchieve.do",
			"/outerAction.do", "/registration.do", "/mmuMasterReport.do", "/targetSummaryAction.do", "/trainingDurationAction.do",
			"/sectorSummaryAction.do", "/tradeSummaryAction.do", "/districtSummaryAction.do", "/projectRegistrationAction.do", "/traininginformationAction.do", "/sanctionOrderReport.do" };

	String loginPattern; // used to check the loginPattern
	String loginMapping;

	public void init(FilterConfig config) throws ServletException {
		this.host1 = config.getInitParameter("host1");
		this.host2 = config.getInitParameter("host2");
		this.loginPattern = config.getInitParameter("login");
		this.loginMapping = config.getInitParameter("loginMapping");

	}

	public void doFilter(ServletRequest request, ServletResponse res, FilterChain next) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) res;

		if (req.getHeader("Referer") != null) {
			if (req.getHeader("Referer").matches("(?i).*" + host1 + ".*"))
				host = host1;
			else
				host = host2;
		}

		if (host == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher(getUrl(req));
			dispatcher.forward(request, resp);
			return;
		}

		int hostLength = host.length();
		String guardCode = null;
		String refererTrimmed = null;
		if (req.getParameter("reqtrack") != null)
			guardCode = req.getParameter("reqtrack");
		else if (req.getHeader("reqtrack") != null)
			guardCode = req.getHeader("reqtrack");
		String trackerId = (String) req.getSession().getAttribute("TRACKERID");
		if (req.getHeader("Referer") != null)
			refererTrimmed = req.getHeader("Referer").substring(0, hostLength);
		else
			refererTrimmed = host;

		if (req.getParameter("Header") != null) {
			guardCode = req.getParameter("Header");
		}
		for (String linkValidate : HomeLink) {
			if (req.getServletPath().equals(linkValidate)) {
				RequestDispatcher dispatcher = request.getRequestDispatcher(getUrl(req));
				dispatcher.forward(request, resp);
				return;
			}
		}

		/**
		 * Used to Check the UNAUTHORIZED ACCESS
		 **/
		if (guardCode == null) {
			this.setErrorPage(req, res);
			throw new ServletException("Unauthorized access");
		}

		if (host.equals(refererTrimmed)) {
			String loginUrlPattern = host;
			int loginUrlPatternLength = loginUrlPattern.length();
			int length = req.getRequestURL().length();

			String actionMapping = req.getRequestURL().toString().substring(loginUrlPatternLength);
			String urlTrimmed = req.getRequestURL().toString().substring(0, loginUrlPatternLength);

			if (loginUrlPattern.equals(urlTrimmed)) {
				if (!actionMapping.equals(loginMapping)) {
					if (req.getSession().getAttribute("userID") != null) {
						if (guardCode != null && guardCode.equals(trackerId)) {
							List moduleLevel2 = (List) req.getSession().getAttribute("formModuleList");
							Iterator itr = moduleLevel2.iterator();
							while (itr.hasNext()) {
								FormModuleVO vo = (FormModuleVO) itr.next();
								String dbURL = vo.getFormURL().substring(0, vo.getFormURL().indexOf("?"));
								dbURL = "/" + loginPattern + "/" + dbURL;
								if (actionMapping.equals(dbURL)) {
									this.setNoCache(req, resp);
									next.doFilter(req, resp);
									return;
								}
							}
							List commonURL = (List) req.getSession().getAttribute("intensiveURL");
							if (commonURL != null) {
								Iterator itr1 = commonURL.iterator();
								while (itr1.hasNext()) {
									String url = (String) itr1.next();
									url = "/" + loginPattern + "/" + url;
									if (actionMapping.equals(url)) {
										this.setNoCache(req, resp);
										next.doFilter(req, resp);
										return;
									}
								}
							}
							this.setErrorPage(req, res);
							throw new ServletException("Unauthorized access");
						} else {
							this.setErrorPage(req, res);
							throw new ServletException("Unauthorized access");
						}
					} else {
						RequestDispatcher dispatcher = request.getRequestDispatcher(getUrl(req));
						dispatcher.forward(request, resp);
						return;
					}
				} else {
					next.doFilter(request, res);
				}

			} else {
				next.doFilter(request, res);
			}
		} else {
			this.setErrorPage(req, res);
			throw new ServletException("Unauthorized access");
		}
	}

	private void setNoCache(HttpServletRequest request, HttpServletResponse response) {
		if (request.getProtocol().compareTo("HTTP/1.0") == 0) {
			response.setHeader("Pragma", "no-cache");
		} else if (request.getProtocol().compareTo("HTTP/1.1") == 0) {
			response.setHeader("Cache-Control", "no-cache");
		}
		response.setDateHeader("Expires", 0);
	}

	private void setErrorPage(HttpServletRequest request, ServletResponse res) throws IOException {
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		out.println("<HTML>");
		out.println("<HEAD><TITLE>Error</TITLE></HEAD>");
		out.println("<BODY>");
		out.println("<BIG>An Error has Occured.Click <a href='" + host + "'>here</a> to Login Again!</BIG>");
		out.println("</BODY></HTML>");
	}

	public void destroy() {
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
}
