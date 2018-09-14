package com.infotech.sgsy.util;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DocDownloader extends HttpServlet {

	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DocDownloader() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String fileName = request.getParameter("fName");
		String downloadFile;
		
		if(!fileName.equals("")) {		
			downloadFile = System.getProperty("file.separator") + "FileFolder"+ System.getProperty("file.separator")+ "Aajeevikaskills" 
					+ System.getProperty("file.separator") + fileName;			
			File f = new File(downloadFile);			
			int length = 0;
			
			ServletOutputStream op = response.getOutputStream();
			ServletContext context = getServletConfig().getServletContext();
			String mimetype = context.getMimeType(downloadFile);
			response.setContentType((mimetype != null) ? mimetype : "image/JPEG");
			response.setContentLength((int) f.length());
			response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

			byte[] bbuf = new byte[1024];
			DataInputStream in = new DataInputStream(new FileInputStream(f));

			while ((in != null) && ((length = in.read(bbuf)) != -1)) {
				op.write(bbuf, 0, length);
			}
			
			in.close();
			op.flush();
			op.close();
		}
		
	}

}

