package com.infotech.sgsy.language;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.infotech.sgsy.configuration.PersisterImpl;
import com.infotech.sgsy.util.PropertyModel;
import com.infotech.sgsy.util.SGSYConnectionUtil;

public class Init extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Statement stmt;
	ResultSet rs;

	String resFile;

	/**
	 * This method is used to Convert values into HeXaDecimal values
	 * 
	 * @param nums
	 * @return
	 */
	protected String convertToHex(String nums) {
		String hex = "";
		char chars[] = nums.toCharArray();
		StringBuffer output = new StringBuffer();

		for (int i = 0; i < chars.length; i++) {
			// hex += "%" + nums.charCodeAt[i].toString(16).toUpperCase();
			if ((int) chars[i] > 125) {
				String hvl = Integer.toHexString((int) chars[i]);
				// System.out.println(hvl + "-" + hvl.length());
				if (hvl.length() >= 4) {
					output.append("\\u" + Integer.toHexString((int) chars[i]));
				} else {
					if (hvl.length() == 1) {
						output.append("\\u000"+ Integer.toHexString((int) chars[i]));
					} else if (hvl.length() == 2) {
						output.append("\\u00"+ Integer.toHexString((int) chars[i]));
					} else if (hvl.length() == 3) {
						output.append("\\u0"+ Integer.toHexString((int) chars[i]));
					}
				}
			} else
				output.append(chars[i]);
		}
		hex = output.toString();
		return hex;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.GenericServlet#init(javax.servlet.ServletConfig)
	 */
	public void init(ServletConfig conf) throws ServletException {
		super.init(conf);
		// Very Important line to check()
		ServletContext context = conf.getServletContext();
		super.init(conf);
		createFile(context);

	}

	protected void doGet(HttpServletRequest arg0, HttpServletResponse arg1)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest arg0, HttpServletResponse arg1)
			throws ServletException, IOException {

	}

	public void createFile(ServletContext context) {
		BufferedWriter out;
		Connection con = null;
		ArrayList languageList = new ArrayList();
		try {
			con = PersisterImpl.getConnection();
			con.setAutoCommit(false);

			String SQL1 = "select * from resourcebundle";
			Statement st2 = con.createStatement();
			ResultSet rs2 = null;
			rs2 = st2.executeQuery(SQL1);
			while (rs2.next()) {
				System.out.println("Inside resource table");
				String fname = rs2.getString("bundle_name");
				out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(context.getRealPath("WEB-INF/classes/Application"+ fname + ".properties")), "UTF8"));
				// System.out.println(context.getRealPath("WEB-INF/classes/in/nic/resource/Sushil"+
				// fname+ ".properties"));
				
				String SQLQry = "Select * from  "+ rs2.getString("bundle_name");
				System.out.println(SQLQry);
				resFile = "";
				Statement st = null;
				st = con.createStatement();
				System.out.println("'''''");
				rs = null;
				rs = st.executeQuery(SQLQry);
				while (rs.next()) {
					resFile = rs.getString("resourceid") + "="+ convertToHex(rs.getString("resourceDesc"));
					context.setAttribute(rs.getString("resourceid"),rs.getString("resourceDesc"));
					out.write(resFile);
					out.newLine();
					// System.out.println("RESFILE---------->"+resFile);
				}
				if (out != null) {
					out.close();
				}
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				out.close();
				System.out.println("Application Properties File  created");
				// BuildPortalPage.buildportalpageforacontent(500,con);
			}
			st2 = null;
			rs = null;
			st2 = con.createStatement();
			rs = st2.executeQuery("Select * from language");
			while (rs.next()) {
				PropertyModel model = new PropertyModel();
				model.setPropertyCode(rs.getString("language_id").trim());
				model.setPropertyValue(rs.getString("language_name").trim());
				languageList.add(model);
			}
			context.setAttribute("LanguageList", languageList);
			con.commit();
			// BuildPortalPage.buildportalpage(conString,Username,Password);
		} catch (Exception e) {
			System.out.println("Exception in Creating Application File" + e + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (Exception e2) {
				con = null;
			}
		}
	}

}
