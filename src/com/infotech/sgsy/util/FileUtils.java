/*
 * Created on Oct 4, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.infotech.sgsy.util;

/**
 * @author 33930
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
//import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;
//import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.upload.FormFile;

//import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;


public class FileUtils {
	
	/**
	 * This class is used to save the input data to a file.
	 * The input data is accepted as a parameter.The path of 
	 * the file to write to is read from a properties file. 
	 * If the path does not exist it creates the path and the new file
	 * else overwrites the existing file. 
	 *   
	 * @param iStream
	 * @return No of bytes written to the file if success else -1.
	 */
public static String uploadFile(HttpServletRequest request , HttpServletResponse response, FormFile file,String path)throws Exception {
	
	   String fileName = "";
		try{
			 
			FileOutputStream out=null;
		    FormFile minFILE=(FormFile)file;
		    if(checkFileType(file)){
		    
		    	if(minFILE.getFileName()!=null && !minFILE.getFileName().equals(""))
		    	{
				    fileName=Util.getCurrentDate(Util.REPORT_TIME_STAMP) + "_"+minFILE.getFileName();
				    
				    path=path+""+fileName;
				    
				    System.out.println("Exact File path in Upload File in FILE UTILS JAVA=="+path);
				    
				    
				 	out=new FileOutputStream(path); 
					out.write(minFILE.getFileData());  
					out.close();
		    	}
		    }
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return fileName;
	}

	public static int writer(InputStream iStream,String fileName,String filePath){
		int bytesWrote = 0;
		OutputStream bos = null;
		InputStream propFile = null;
		
		if (iStream != null){
			try {
					if(filePath != null){
					File f = new File(filePath);
					// create the directories from the file path
					f.mkdirs();
					filePath = filePath.replaceAll("\"","").concat("/" + fileName);
					
					//filePath ="E:/IGNOAPS_IMAGES".concat("/" + fileName);
							
					// Set the outputstream for the file
					bos = new FileOutputStream(filePath);
					byte[] buffer = new byte[8192];
					int bytesRead = 0;
					while ((bytesRead = iStream.read(buffer, 0, 8192)) != -1) {
						bos.write(buffer, 0, bytesRead);
						bytesWrote += bytesRead; 
					}
				}
				else {
					bytesWrote = -1;
				}
			}
			catch (FileNotFoundException fe){
				//logger.info("FileUtils.writer(InputStream,String,String)"+fe.getMessage());
				bytesWrote = -1;
			}
			catch (IOException ioe){
				//logger.info("FileUtils.writer(InputStream,String,String)"+ioe);
				bytesWrote = -1;
			}
			finally {
				try {
					if (bos != null ){
						bos.close();
					}
					if (iStream != null) {
						iStream.close();
					}
				}
				catch (IOException e){
					//logger.info("FileUtils.writer(InputStream,String,String)"+e.getMessage());
					bytesWrote = -1;
				}
			}
		}
		return bytesWrote;
	}
	
	/**
	 * This class is used to save the read data from a file.
	 * The file path is accepted as a parameter. 
	 *   
	 * @param filePath
	 * @return Bytes read from the file if success else null.
	 */
	public static byte[] reader(String filePath) {
		byte[] buffer = null;
		ByteArrayOutputStream boas = null;
		File f = new File(filePath);
		InputStream fis = null;
		
		try {
			if (f!=null){
				fis = new FileInputStream(f);
				int count = 0;
				buffer = new byte[8192];
				int bytesRead = 0;
				boas = new ByteArrayOutputStream();
				while ((bytesRead = fis.read(buffer, 0, 8192)) != -1) {
					boas.write(buffer,0,bytesRead);
				}				
				//logger.info("boas.size()"+boas.size());
				buffer = boas.toByteArray();
			}
			//logger.info("buffer = " + buffer.toString());
		}
		catch(FileNotFoundException fnfe) {			
			//logger.info("FileUtils.reader(String)"+fnfe.getMessage());
			buffer = null;
		}
		catch(IOException ioe){			
			//logger.info("FileUtils.reader(String)"+ioe.getMessage());
			buffer = null;
		}
		finally {
			try {
				if (fis != null){
					fis.close();
				}
				if (boas != null){
					boas.close();
				}
				if (f != null) {
					f = null;
				}
			}
			catch(IOException e){				
				//logger.info("FileUtils.reader(String)"+e.getMessage());
				buffer = null;
			}
			fis = null;
			boas = null;
			f = null;
		}
		return buffer;
	}
	
    public ArrayList readFromFile(File file)    throws Exception{    	
    ArrayList arraylist = new ArrayList();
    try
    {
        FileReader filereader = new FileReader(file);
        BufferedReader bufferedreader = new BufferedReader(filereader);
        Object obj = null;
        do
        {
            String s = bufferedreader.readLine();
            if(s == null)
                break;
            arraylist.add(s);
        } while(true);
        bufferedreader.close();
        filereader.close();
    }
    catch(Exception exception)
    {
        arraylist = new ArrayList();
        //logger.info("FileUtils.reader(String)"+exception.getMessage());
        return arraylist;
    }
    return arraylist;
}
    
    
	  public static String getUploadFileLocation(HttpServletRequest request, String foldername) throws Exception
	     {
	     	String path=null;     	
	     	
	     	try{
	     		   path = request.getContextPath();
	     		//   System.out.println("Path - 1  " + path);
	     		   path = request.getRealPath("XYZ.properties");
	     		//   System.out.println("Path - 2  " + path);
	     		   int index =path.indexOf("XYZ.properties");
	     		//  System.out.println("Path - 3  " + path);
	     		   path = path.substring(0,index);
	     		//  System.out.println("Path - 4  " + path);
	     		 //  path = path + foldername; //servletContext.getAttribute("rpcuploadpath");
	     		   path = path + "WebContent/userImages" ;
	     		//  System.out.println("Path - 5  " + path);
	     		   path = path.replace('\\','/');
	     		//  System.out.println("Path - 6  " + path);
	     		  
				 // path="/IGNOAPS/WebContent/user" ;
	     	}catch(Exception e)
			{
	     		e.printStackTrace();
	     		 //logger.info("FileUtils.getUploadFileLocation(HttpServletRequest,String)"+e.getMessage());
			}
	    // 	path = resourceBundle.getString("imagepath");
			return path;
	   
	     }
	  
	  public static String getUniqueFileName(String userId, String actualFileName) throws Exception
	     {
	     	String uniqueFileName=null;
	     	SimpleDateFormat sdfDDMMYYYHHMMSS = null;
	     	
	     	try{
	     		
				sdfDDMMYYYHHMMSS = new SimpleDateFormat("ddMMyyyyhhmmss");
				uniqueFileName = userId+"_"+sdfDDMMYYYHHMMSS.format(new java.util.Date())+".jpg";
	     	}catch(Exception e)
			{
	     		throw e;	     		
			}
			return uniqueFileName;
	   
	     }
	  /*public static String uploadHomePageDocuments(ProposalVO masterVO,String path) throws Exception {
			 
			FormFile uploadDocument = masterVO.getFile();
			String uploadFilePath=null;
		 
			String fileName = null;
	 	
			if (uploadDocument.getFileSize() > 0) {

				//Get the file name
				fileName = Util.getCurrentDate(Util.REPORT_TIME_STAMP) + "_"+ uploadDocument.getFileName();

				 Save file on the server 
				if (!fileName.equals("")) {

					//Create file
					File fileToCreate = new File(path, fileName);

					//If file does not exists create file                      
					if (!fileToCreate.exists()) {
						FileOutputStream fileOutStream = null;
						try {
							fileOutStream = new FileOutputStream(fileToCreate);
							fileOutStream.write(uploadDocument.getFileData());
							fileOutStream.flush();
							fileOutStream.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
				uploadFilePath =  fileName;

			}

			return uploadFilePath;
		}*/
	  
	  public static boolean checkFileType(FormFile file) throws Exception {

			boolean result=false;
			String contenttype=file.getContentType();
			String fileName=file.getFileName();
			// Getting the file extension
			
			 String extension;
			 
			// Applying check when file name is not present . This means no file is uploaded.
			 
			if(!fileName.equals("")) { 
		      int dotPos = fileName.lastIndexOf(".");
		      extension = fileName.substring(dotPos);
		      
		      
		    /*
		     * 
		     * arrFileExtension[0] = "exe";
				arrFileExtension[1] = "dll";
				arrFileExtension[2] = "backup"; 
				arrFileExtension[3] = "dat";
				arrFileExtension[4] = "bat";
				arrFileExtension[5] = "wmp";
				arrFileExtension[6] = "mp3";
				arrFileExtension[7] = "mp4";
				arrFileExtension[8] = "vmp"; 
				arrFileExtension[9] = "java";
				arrFileExtension[10] = "jsp";
				arrFileExtension[11] = "jar";    
		     * 
		     * */  
		      
		      
		   /* if(contenttype.equalsIgnoreCase(Constants.docF) && extension.equalsIgnoreCase(Constants.docE)) 
		      
		    	result=true;
		    	
			if((contenttype.equals(Constants.pdf) && extension.equalsIgnoreCase(Constants.pdfE)) || (contenttype.equals(Constants.doc) && extension.equalsIgnoreCase(Constants.docE)) || (contenttype.equals(Constants.jpg) && extension.equalsIgnoreCase(Constants.jpgE)) || (contenttype.equals(Constants.xls) && extension.equalsIgnoreCase(Constants.xlsE)) || (extension.equals(Constants.txtE) && contenttype.equals(Constants.txt)))
				result=true;*/
			
		      
		      if(!extension.equalsIgnoreCase(".exe") || !extension.equalsIgnoreCase(".dll") || !extension.equalsIgnoreCase(".backup") ||!extension.equalsIgnoreCase(".dat") ||!extension.equalsIgnoreCase(".dll") 
		       || !extension.equalsIgnoreCase(".bat") ||!extension.equalsIgnoreCase(".wmp") ||!extension.equalsIgnoreCase(".mp3") 
		       ||!extension.equalsIgnoreCase(".mp4") ||!extension.equalsIgnoreCase(".vmp") ||!extension.equalsIgnoreCase(".java") ||!extension.equalsIgnoreCase(".jsp") ||!extension.equalsIgnoreCase(".war") ||
		       !extension.equalsIgnoreCase(".exe") ) 
			      
			    	result=true;
		      
			}else
				result=true;
			
			
			return result;

		} 
	  
}
    

   
   
	


