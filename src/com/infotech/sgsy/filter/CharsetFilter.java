package com.infotech.sgsy.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.infotech.sgsy.menuGeneratiion.FormModuleVO;
import com.infotech.sgsy.menuGeneratiion.ModuleMasterVO;

public class CharsetFilter implements Filter {
	private String encoding;

	public void init(FilterConfig config) throws ServletException {
		encoding = config.getInitParameter("requestEncoding");

		if (encoding == null)
			encoding = "UTF8";
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain next)
			throws IOException, ServletException {
		// Respect the client-specified character encoding
		// (see HTTP specification section 3.4.1)
		HttpServletRequest req = (HttpServletRequest) request;

		HttpServletResponse res = (HttpServletResponse) response;

		try {

			if (null == request.getCharacterEncoding()) {

				request.setCharacterEncoding(encoding);
			}
			next.doFilter(request, response);

			if (req.getSession() != null) {

				HttpSession session = req.getSession();

				String url = req.getRequestURL().toString();

				String uri = req.getRequestURI();
				int url_val1 = uri.lastIndexOf("/SGSY/");
				String url_sub1 = uri.replaceAll("/SGSY/", "");

				List formModuleList = (List) session.getAttribute("formModuleList");

				if (formModuleList != null) {

					Iterator itr2 = formModuleList.iterator();
					while (itr2.hasNext()) {
						FormModuleVO formModuleVO = (FormModuleVO) itr2.next();

						if (formModuleVO.getFormURL() != null) {
							int url_val = formModuleVO.getFormURL().indexOf('?');
							String url_sub = formModuleVO.getFormURL().substring(0, (url_val));
							int url_val2 = url_sub.indexOf("SGSY");
							String url_sub2 = "";
							if (url_val2 > 0) {
								url_sub2 = url_sub.substring(0, (url_val2));
							} else {
								url_sub2 = url_sub;
							}
							if (url_sub1.trim().equalsIgnoreCase(url_sub2)) {
								session.setAttribute("form_mod_code", formModuleVO.getModcd());
								session.setAttribute("form_Name", formModuleVO.getFormName());
								session.setAttribute("form_code", formModuleVO.getFormcd());
							}

						}

					}
				}

				List moduleList = (List) session.getAttribute("moduleList");
				if (moduleList != null) {
					Iterator itr1 = moduleList.iterator();
					while (itr1.hasNext()) {
						ModuleMasterVO moduleVO = (ModuleMasterVO) itr1.next();
						String mod_code = session.getAttribute("form_mod_code") + "";
						if (mod_code.trim().equals(moduleVO.getModcd().trim())) {
							session.setAttribute("mode_code", moduleVO.getModcd());
							session.setAttribute("mode_name123", moduleVO.getModName());

						}

					}
				}
			} else {

				this.setErrorPage(req, res);
				throw new ServletException("Unauthorized access");

			}

		} catch (Exception e) {

			this.setErrorPage(req, res);
			throw new ServletException("Unauthorized access");

		}

	}

	public void destroy() {
	}

	private void setErrorPage(HttpServletRequest request, ServletResponse res) throws IOException {
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();

		out.println("<HTML>");
		out.println("<HEAD><TITLE>Error</TITLE></HEAD>");
		out.println("<BODY>");
		out.println("<BIG>An Error has Occured.Click <a href='" + request.getContextPath()
				+ "'>here</a> to Login Again!</BIG>");
		out.println("</BODY></HTML>");

	}

}