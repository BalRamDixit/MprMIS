package com.infotech.sgsy.uploadcircular;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.upload.FormFile;
import org.apache.struts.util.MessageResources;

import com.infotech.sgsy.common.LocationVO;
import com.infotech.sgsy.common.MasterAction;
import com.infotech.sgsy.common.uploadCircularVO;

import com.infotech.sgsy.login.LoginVO;
import com.infotech.sgsy.userManagement.UserForm;
import com.infotech.sgsy.util.Constants;
import com.infotech.sgsy.util.DateUtil;
import com.infotech.sgsy.util.SGSYConstants;

public class UploadCircular extends MasterAction {

	ActionMessages message = new ActionMessages();
	String forwardMessage = "";

	@Override
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionForward fwd = new ActionForward();

		return fwd;
	}

	@Override
	public ActionForward modify(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("schemeList", UploadCircularDAO.schemeUpload());
		message.clear();

		UploadCircularForm uploadCircularForm = (UploadCircularForm) form;
		uploadCircularVO uploadCircularVO = new uploadCircularVO();
		BeanUtils.copyProperties(uploadCircularVO, uploadCircularForm);
		uploadCircularVO.setCircularScheme_code(uploadCircularVO
				.getCircularScheme());
		uploadCircularVO.setServerDate(new DateUtil().getDateTime().toString());
		uploadCircularVO.setUploadCircularName(request.getParameter("desc"));

		// For Local System
		// String userHome = getServlet().getServletContext().getRealPath("") +
		// "/UpLoadCircular/";

		// For Live Server
		String sheme_Name = uploadCircularForm.getCircularScheme();
		String userHome = System.getProperty("file.separator") + "FileFolder"
				+ System.getProperty("file.separator") + "UpLoadCircular"
				+ System.getProperty("file.separator") + sheme_Name;
		if (uploadCircularForm.getMeetingAgendaFileEnglish().getFileName()
				.length() > 0
				&& checkFileType(uploadCircularForm
						.getMeetingAgendaFileEnglish())) {
			// Delete existing file
			String oldPath = userHome + System.getProperty("file.separator")
					+ uploadCircularForm.getId() + "_"
					+ uploadCircularForm.getUploadCircularFileName();
			File oldFile = new File(oldPath);
			boolean oldFileDeleted = oldFile.exists() ? oldFile.delete() : true;
			if (oldFileDeleted == true) {
				String path = userHome
						+ System.getProperty("file.separator")
						+ uploadCircularForm.getId()
						+ "_"
						+ uploadCircularForm.getMeetingAgendaFileEnglish()
								.getFileName();
				FileOutputStream out = new FileOutputStream(new File(path));
				out.write(uploadCircularForm.getMeetingAgendaFileEnglish()
						.getFileData());
				out.flush();
				out.close();
				uploadCircularVO.setUploadCircularFileName(uploadCircularForm
						.getMeetingAgendaFileEnglish().getFileName());
			} else {
				log.error("Unable to delete the existing english file.");
				message.add(
						SGSYConstants.MSG,
						new ActionMessage("update.fail.message", request
								.getSession().getServletContext()
								.getAttribute("label.sgsyuploadcircular")
								.toString()));
				saveMessages(request, message);
				return mapping.findForward(Constants.SHOW_MODIFY_PAGE);
			}
		} else if (uploadCircularForm.getMeetingAgendaFileEnglish()
				.getFileName().length() > 0
				&& !checkFileType(uploadCircularForm
						.getMeetingAgendaFileEnglish())) {
			message.add(SGSYConstants.MSG, new ActionMessage(
					"error.improperFileFormat"));
			saveMessages(request, message);
			return mapping.findForward(Constants.SHOW_MODIFY_PAGE);
		}

		if (uploadCircularForm.getMeetingAgendaFileHindi().getFileName()
				.length() > 0
				&& checkFileType(uploadCircularForm.getMeetingAgendaFileHindi())) {
			// Delete existing file
			String oldPath = userHome + System.getProperty("file.separator")
					+ uploadCircularForm.getId() + "_Hindi_"
					+ uploadCircularForm.getUploadCircularFileNameHindi();
			File oldFile = new File(oldPath);
			boolean oldFileDeleted = oldFile.exists() ? oldFile.delete() : true;
			if (oldFileDeleted == true) {
				String path = userHome
						+ System.getProperty("file.separator")
						+ uploadCircularForm.getId()
						+ "_Hindi_"
						+ uploadCircularForm.getMeetingAgendaFileHindi()
								.getFileName();
				FileOutputStream out = new FileOutputStream(new File(path));
				out.write(uploadCircularForm.getMeetingAgendaFileHindi()
						.getFileData());
				out.flush();
				out.close();
				uploadCircularVO
						.setUploadCircularFileNameHindi(uploadCircularForm
								.getMeetingAgendaFileHindi().getFileName());
			} else {
				log.error("Unable to delete the existing hindi file.");
				message.add(
						SGSYConstants.MSG,
						new ActionMessage("update.fail.message", request
								.getSession().getServletContext()
								.getAttribute("label.sgsyuploadcircular")
								.toString()));
				saveMessages(request, message);
				return mapping.findForward(Constants.SHOW_MODIFY_PAGE);
			}
		} else if (uploadCircularForm.getMeetingAgendaFileHindi().getFileName()
				.length() > 0
				&& checkFileType(uploadCircularForm.getMeetingAgendaFileHindi())) {
			message.add(
					SGSYConstants.MSG,
					new ActionMessage("update.fail.message", request
							.getSession().getServletContext()
							.getAttribute("label.sgsyuploadcircular")
							.toString()));
			saveMessages(request, message);
			return mapping.findForward(Constants.SHOW_MODIFY_PAGE);
		}

		boolean flag = new UploadCircularDAO().modify(uploadCircularVO);
		if (flag == true) {
			message.add(
					SGSYConstants.MSG,
					new ActionMessage("update.success.message", request
							.getSession().getServletContext()
							.getAttribute("label.sgsyuploadcircular")
							.toString()));
			uploadCircularForm.reset();
		} else if (flag == false) {
			message.add(
					SGSYConstants.MSG,
					new ActionMessage("update.fail.message", request
							.getSession().getServletContext()
							.getAttribute("label.sgsyuploadcircular")
							.toString()));
		}
		saveMessages(request, message);
		return mapping.findForward(Constants.SHOW_MODIFY_PAGE);
	}

	@Override
	public ActionForward showDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionForward fwd = new ActionForward();
		return fwd;
	}

	@Override
	public ActionForward showModify(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		UploadCircularForm uploadCircularForm = (UploadCircularForm) form;
		List<uploadCircularVO> schemeList = UploadCircularDAO.schemeUpload();
		request.setAttribute("schemeList", schemeList);
		if (uploadCircularForm.getCircularScheme() != null
				&& !"".equals(uploadCircularForm.getCircularScheme())) {
			request.setAttribute("circularDescription", UploadCircularDAO
					.CircularDetaill(uploadCircularForm.getCircularScheme()
							.trim()));
		}
		if (uploadCircularForm.getCircularScheme() != null
				&& !"".equals(uploadCircularForm.getCircularScheme())
				&& uploadCircularForm.getUploadCircularName() != null
				&& !"".equals(uploadCircularForm.getUploadCircularName())) {
			request.setAttribute("circularDescription", UploadCircularDAO
					.CircularDetaill(uploadCircularForm.getCircularScheme()
							.trim()));
			request.setAttribute("uploadCircularForm",
					UploadCircularDAO.getCircularDetails(uploadCircularForm
							.getCircularScheme().trim(), uploadCircularForm
							.getUploadCircularName()));
			request.setAttribute("show", "true");
		}
		return mapping.findForward(Constants.SHOW_MODIFY_PAGE);
	}

	public ActionForward showAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		message.clear();
		UploadCircularForm upform = (UploadCircularForm) form;

		upform.setCircularScheme("");
		upform.setOtherCircular("");
		upform.setUploadCircularName("");
		upform.setUploadCircularDate("");
		upform.setMeetingAgendaFileEnglish(null);
		upform.setMeetingAgendaFileHindi(null);

		List<uploadCircularVO> schemeList = UploadCircularDAO.schemeUpload();
		request.setAttribute("schemeList", schemeList);
		// actionForm.setServerDate(new DateUtil().getCurrentDateforCalander());

		return mapping.findForward("showUploadCircularPage");
	}

	@Override
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionMessages messages = new ActionMessages();
		FileOutputStream outputStream = null;
		FileOutputStream outputStreamHindi = null;

		try {

			message.clear();

			uploadCircularVO uploadvo = new uploadCircularVO();
			UploadCircularForm upform = (UploadCircularForm) form;
			UploadCircularDAO upd = new UploadCircularDAO();

			uploadvo.setCircularScheme(upform.getCircularScheme());
			uploadvo.setUploadCircularName(upform.getUploadCircularName());
			uploadvo.setUploadCircularDate(upform.getUploadCircularDate());
			uploadvo.setServerDate(upform.getServerDate());
			FormFile fileuploadEnglish = (FormFile) upform
					.getMeetingAgendaFileEnglish();
			FormFile fileuploadHindi = (FormFile) upform
					.getMeetingAgendaFileHindi();

			// uploadvo.setUploadCircularFile(upform.getUploadCircularFile());

			List<uploadCircularVO> schemeList = UploadCircularDAO
					.schemeUpload();
			request.setAttribute("schemeList", schemeList);

			if (checkFileType(fileuploadEnglish) == true) {

				uploadvo.setUploadCircularFileName(fileuploadEnglish
						.getFileName());
				uploadvo.setUploadCircularFileNameHindi(fileuploadHindi
						.getFileName());

				String MessLbl = "Circular Records are ";
				String sheme_Name = upform.getCircularScheme();

				if (fileuploadEnglish.getFileSize() > 0) {
					String agendafileNameEnglish = fileuploadEnglish
							.getFileName();
					String agendaFileNameHindi = fileuploadHindi.getFileName();
					byte[] agendafileData = fileuploadEnglish.getFileData();
					// File f1=new
					// File(getServlet().getServletContext().getRealPath("")+"/UpLoadCircular/"+sheme_Name);

					String userHome = System.getProperty("user.home");
					/*
					 * Below code for file upload in LIVE server
					 */
					// userHome=System.getProperty("file.separator")+"FileFolder"+
					// System.getProperty("file.separator")+"UpLoadCircular"+
					// System.getProperty("file.separator");

					/*
					 * Below code for file upload in LOCAL server
					 */

					// userHome =
					// getServlet().getServletContext().getRealPath("") +
					// "/UpLoadCircular/";

					/*
					 * Below code for file upload in LIVE server
					 */
					userHome = System.getProperty("file.separator")
							+ "FileFolder"
							+ System.getProperty("file.separator")
							+ "UpLoadCircular"
							+ System.getProperty("file.separator") + sheme_Name;

					// userHome = userHome +
					// System.getProperty("file.separator");

					/*
					 * if (new File(userHome + sheme_Name).isDirectory() ==
					 * false) { new File(userHome + sheme_Name).mkdir();
					 * 
					 * }
					 */
					System.out
							.println("file path in save method of Upload Circular is11=="
									+ userHome);
					if (new File(userHome).isDirectory() == false) {
						new File(userHome).mkdir();
					}
					String agendapath = userHome
							+ System.getProperty("file.separator")
							+ upd.maxid() + "_" + agendafileNameEnglish;
					if (upform.getMeetingAgendaFileHindi().getFileName()
							.length() > 0
							&& checkFileType(fileuploadHindi)) {
						String agendaPathHindi = userHome
								+ System.getProperty("file.separator")
								+ (upd.maxid()) + "_Hindi_"
								+ agendaFileNameHindi;
						outputStreamHindi = new FileOutputStream(new File(
								agendaPathHindi));
						outputStreamHindi.write(fileuploadHindi.getFileData());
					}
					System.out
							.println("file path in save method of Upload Circular is22=="
									+ agendapath);
					outputStream = new FileOutputStream(new File(agendapath));
					outputStream.write(agendafileData);
				}
				upd.save(uploadvo);
				message.add(SGSYConstants.MSG, new ActionMessage(
						"added.success.message", MessLbl));
				upform.setCircularScheme("");
				upform.setUploadCircularName("");
				upform.setUploadCircularDate("");
				upform.setMeetingAgendaFileEnglish(null);
				upform.setMeetingAgendaFileHindi(null);
			} else {
				message.add(SGSYConstants.MSG, new ActionMessage(
						"error.improperFileFormat"));
			}

		}

		catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			if (outputStream != null) {
				outputStream.close();
			}
			if (outputStreamHindi != null) {
				outputStreamHindi.close();
			}
		}
		saveMessages(request, message);
		return mapping.findForward(Constants.SHOW_ADD_PAGE);
	}

	public static String getUrl(HttpServletRequest req) {
		/*
		 * String scheme = req.getScheme(); // http String serverName =
		 * req.getServerName(); // hostname.com int serverPort =
		 * req.getServerPort(); // 8080
		 */// String contextPath = req.getContextPath(); // /mywebapp
		String servletPath = req.getServletPath(); // /servlet/MyServlet
		String pathInfo = req.getPathInfo(); // /a/b;c=123
		String queryString = req.getQueryString(); // d=789

		// Reconstruct original requesting URL
		String url = servletPath;
		if (pathInfo != null) {
			url += pathInfo;
		}
		if (queryString != null) {
			url += "?" + queryString;
		}

		return url;
	}

	public ActionForward downloadCircular(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String scheme_name = request.getParameter("schemeName");

		String path = getServlet().getServletContext().getRealPath("")
				+ "/FileFolder/UpLoadCircular/" + scheme_name;

		String fAddress = "http://localhost:8080/SGSY_FINAL/";

		String userHome = System.getProperty("user.home");
		/*
		 * Below code for file upload in LIVE server
		 */
		userHome = System.getProperty("file.separator") + "FileFolder"
				+ System.getProperty("file.separator") + "UpLoadCircular"
				+ System.getProperty("file.separator") + scheme_name;

		path = userHome + "/" + request.getParameter("path");

		File f = new File(path);

		/*
		 * String dataFolder = System.getProperty("user.dir") +
		 * System.getProperty("file.separator") + "data";
		 */

		int length = 0;
		ServletOutputStream op = response.getOutputStream();
		ServletContext context = getServlet().getServletContext();
		String mimetype = context.getMimeType(userHome);

		//
		// Set the response and go!
		//
		//
		response.setContentType((mimetype != null) ? mimetype
				: "application/octet-stream");
		response.setContentLength((int) f.length());
		response.setHeader("Content-Disposition",
				"attachment; filename=\"" + f.getName() + "\"");

		//
		// Stream to the requester.
		//
		byte[] bbuf = new byte[2048];

		try {

			DataInputStream in = new DataInputStream(new FileInputStream(f));

			while ((in != null) && ((length = in.read(bbuf)) != -1)) {
				op.write(bbuf, 0, length);
			}

			in.close();
			op.flush();
			op.close();

		} catch (FileNotFoundException fe) {
			return null;
		}

		return null;

	}

	public ActionForward addScheme(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String MesScm = "Circular Scheme is ";

		try {

			message.clear();

			UploadCircularForm upform = (UploadCircularForm) form;
			String shemeNameOther = upform.getOtherCircular();
			UploadCircularDAO upd = new UploadCircularDAO();
			upd.add(shemeNameOther);
			List<uploadCircularVO> schemeList = UploadCircularDAO
					.schemeUpload();
			request.setAttribute("schemeList", schemeList);

			message.add(SGSYConstants.MSG, new ActionMessage(
					"added.success.message", MesScm));
			saveMessages(request, message);
			upform.setCircularScheme("");
			upform.setUploadCircularName("");
			upform.setUploadCircularDate("");
			upform.setMeetingAgendaFileEnglish(null);
			upform.setMeetingAgendaFileHindi(null);
		}

		catch (Exception e) {
			e.printStackTrace();
		}

		return mapping.findForward(Constants.SHOW_ADD_PAGE);
	}

	public ActionForward showView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionForward fwd = new ActionForward();
		try {
			message.clear();
			boolean flag = false;
			UploadCircularForm upform = (UploadCircularForm) form;
			List<uploadCircularVO> schemeList = UploadCircularDAO.schemeUpload();
			request.setAttribute("schemeList", schemeList);

			List<uploadCircularVO> CircularDetaill = null;

			if (upform.circularScheme != "" && upform.circularScheme != null) {
				request.setAttribute("circularMessage", null);
				CircularDetaill = UploadCircularDAO.CircularDetaill(upform.circularScheme);

				if (CircularDetaill != null && CircularDetaill.size() > 0)
					request.setAttribute("CircularDetaill", CircularDetaill);
				else {
					request.setAttribute("circularMessage",	"No Record Found for the Selected Circular Scheme");
					request.setAttribute("CircularDetaill", null);
				}
			}

			fwd = mapping.findForward(Constants.SHOW_VIEW_PAGE);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return fwd;
	}

	public static boolean checkFileType(FormFile file) throws Exception {
		boolean result = false;
		String contenttype = file.getContentType();
		String fileName = file.getFileName();
		// Getting the file extension
		String extension;
		// Applying check when file name is not present . This means no file is
		// uploaded.
		/*
		 * Add ContentType application/vnd.openxmlformats for the extensions
		 * .docx .pptx .xlsx .xltx . xltm .dotx .potx .ppsx
		 */
		if (!fileName.equals("")) {
			int dotPos = fileName.lastIndexOf(".");
			extension = fileName.substring(dotPos);
			if (contenttype.equalsIgnoreCase(Constants.docF)
					&& extension.equalsIgnoreCase(Constants.docE))
				result = true;
			if (contenttype.equals("text/plain") && extension.equals(".txt")
					|| contenttype.equals("text/plain")
					&& extension.equals(".txt"))
				result = true;
			if ((contenttype.equalsIgnoreCase("application/octet-stream") && extension
					.equalsIgnoreCase(".ppt"))
					|| (contenttype
							.equalsIgnoreCase("application/octet-stream") && extension
							.equalsIgnoreCase(".pptx")))
				result = true;
			if ((contenttype.equalsIgnoreCase("application/vnd.ms-powerpoint") && extension
					.equalsIgnoreCase(".ppt"))
					|| (contenttype
							.equalsIgnoreCase("application/vnd.openxmlformats") && extension
							.equalsIgnoreCase(".pptx")))
				result = true;
			if ((contenttype.equals(Constants.pdf) && extension
					.equalsIgnoreCase(Constants.pdfE))
					|| (contenttype.equals(Constants.doc) && extension
							.equalsIgnoreCase(Constants.docE))
					|| (contenttype.equals(Constants.jpg) && extension
							.equalsIgnoreCase(Constants.jpgE))
					|| (contenttype.equals(Constants.xls) && extension
							.equalsIgnoreCase(Constants.xlsE))
					|| (contenttype.equals("text/plain") && extension
							.equals(".txt")) || (extension.equals(".rtf")))
				result = true;

			if (extension.equalsIgnoreCase(".ppt")
					|| extension.equalsIgnoreCase(".pptx"))
				result = true;
		}

		else
			result = true;
		return result;
	}

	/*
	 * public static boolean checkFileType(FormFile file) throws Exception {
	 * 
	 * boolean result = false; String contenttype = file.getContentType();
	 * String fileName = file.getFileName(); // Getting the file extension
	 * 
	 * String extension;
	 * 
	 * // Applying check when file name is not present . This means no file is
	 * // uploaded.
	 * 
	 * if (!fileName.equals("")) { int dotPos = fileName.lastIndexOf(".");
	 * extension = fileName.substring(dotPos);
	 * 
	 * if (contenttype.equalsIgnoreCase(Constants.docF) &&
	 * extension.equalsIgnoreCase(Constants.docE))
	 * 
	 * result = true;
	 * 
	 * if ((contenttype.equals(Constants.pdf) && extension
	 * .equalsIgnoreCase(Constants.pdfE)) || (contenttype.equals(Constants.doc)
	 * && extension .equalsIgnoreCase(Constants.docE)) ||
	 * (contenttype.equals(Constants.jpg) && extension
	 * .equalsIgnoreCase(Constants.jpgE)) || (contenttype.equals(Constants.xls)
	 * && extension .equalsIgnoreCase(Constants.xlsE)) ||
	 * (contenttype.equals(Constants.pptx) && extension
	 * .equalsIgnoreCase(Constants.pptxE)) || (contenttype.equals("text/plain")
	 * && extension .equals(".txt")) || (extension.equals(".rtf"))) result =
	 * true;
	 * 
	 * } else result = true;
	 * 
	 * return result;
	 * 
	 * }
	 */

	public ActionForward closePage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		log.info("inside close method");
		UploadCircularForm upform = (UploadCircularForm) form;

		upform.setCircularScheme("");
		upform.setUploadCircularName("");
		upform.setUploadCircularDate("");
		upform.setMeetingAgendaFileEnglish(null);
		upform.setMeetingAgendaFileHindi(null);

		return mapping.findForward("Close");
	}

	public ActionForward showViewNRLM(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			message.clear();
			List<uploadCircularVO> CircularDetaill = null;
			request.setAttribute("circularMessage", null);
			CircularDetaill = UploadCircularDAO.CircularDetaill("NRLM");
			if (CircularDetaill != null && CircularDetaill.size() > 0) {
				request.setAttribute("CircularDetaill", CircularDetaill);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return mapping.findForward("showViewNRLM");
	}

}
