package com.infotech.sgsy.util;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.CachedRowSet;

import com.infotech.skills.util.DownloaderUtil;


public class Downloader extends HttpServlet {

	 
	public static final int BUFSIZE = 2048;
	/**
	 * Constructor of the object.
	 */
	public Downloader() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	/*public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		String imageName  = request.getParameter("imageName"); // Image to Download
		String downloadFile = "";
		
		HttpSession session = request.getSession();	
		ServletContext context=session.getServletContext();
 
		try {
			
			 * String path = context.getRealPath("")+"/FileFolder/";
			 * downloadFile = path + imageName;
			 

			// for live linux server
			downloadFile = System.getProperty("file.separator") + "FileFolder"+ System.getProperty("file.separator")+ "Aajeevikaskills" 
					+ System.getProperty("file.separator") + imageName;
			// for demo linux server
			// downloadFile=System.getProperty("file.separator")+"FileFolder"+System.getProperty("file.separator")+"DemoFolder"+System.getProperty("file.separator")+request.getParameter("path");
			
			doDownload(request, response, downloadFile);
		} catch (Exception e) {
			StackTraceElement st[]=e.getStackTrace(); 
			for(int i=0;i<st.length;i++)
				System.out.println("print statck trace=="+st[i]);
		}
	}*/
	/**
	 * edited by LAxman and SAndeep for converting pdf files to jpg 
	 * ======================================================================================================
	 * PROBLEM : in PIAs report Director's photo was not showing bcz image file was in pdf
	 * so while sending file we are checking if file is in pdf format then we are changing it to a jpg file
	 * ======================================================================================================
	 * NOTE: For changing the file format we are using Imagemagik (convert command). :P there was nothing in 
	 * pure java to convert a pdf to jpg
	 * IF covert command is not installed on server then it will not work...
	 * ======================================================================================================
	 * 
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {		
		String imageName  = request.getParameter("imageName"); // Image to Download
		String downloadFile = "";
		
		HttpSession session = request.getSession();	
		ServletContext context=session.getServletContext();
		try {
			if(imageName.contains("photo.pdf") || imageName.contains("photo.PDF")) {
				// culprit found ... do some magic
				String firstArg = DownloaderUtil.getDownloadPath() + File.separator + imageName;
				String outputFileName = "/tmp/" + imageName.substring(0,imageName.lastIndexOf(".")) + ".jpg";
				String command = "convert " + firstArg + " " + outputFileName;
				System.err.println("==========================================================================\n" + 
								"Executing below *NIX command for converting pdf to jpeg file:\n" + command +
								"\n==========================================================================");
				//Runtime.getRuntime().exec("rm -rf /tmp/*photo.jpg");
				Runtime.getRuntime().exec(command);
				/*Process process = Runtime.getRuntime().exec(command);
				process.waitFor();
				BufferedReader buf = new BufferedReader(new InputStreamReader(process.getInputStream()));
				String line = "";
				while ((line=buf.readLine())!=null) {
				System.out.println(line);
				}*/
				
				downloadFile = outputFileName;
			} else {
				downloadFile = DownloaderUtil.getDownloadPath() + File.separator + imageName;
			}
			
			doDownload(request, response, downloadFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
		
		
	}

	/**
     *  Sends a file to the ServletResponse output stream.  Typically
     *  you want the browser to receive a different name than the
     *  name the file has been saved in your local database, since
     *  your local names need to be unique.
     *
     *  @param req The request
     *  @param resp The response
     *  @param filename The name of the file you want to download.
     *  @param original_filename The name the browser should receive.
     */
    private void doDownload( HttpServletRequest req, HttpServletResponse resp,
                             String filename)
        throws IOException
    {
        File                f        = new File(filename);
        int                 length   = 0;
        ServletOutputStream op       = resp.getOutputStream();
        ServletContext      context  = getServletConfig().getServletContext();
        String              mimetype = context.getMimeType( filename );
    
        //resp.setContentType( (mimetype != null) ? mimetype : "application/octet-stream" );
        resp.setContentType( (mimetype != null) ? mimetype : "image/JPEG" );
        resp.setContentLength( (int)f.length() );
        resp.setHeader( "Content-Disposition", "attachment; filename=\"" + filename + "\"" );

        //
        //  Stream to the requester.
        //
        byte[] bbuf = new byte[BUFSIZE];
        DataInputStream in = new DataInputStream(new FileInputStream(f));

        while ((in != null) && ((length = in.read(bbuf)) != -1))
        {
            op.write(bbuf,0,length);
        }

        in.close();
        op.flush();
        op.close();
    }

}
