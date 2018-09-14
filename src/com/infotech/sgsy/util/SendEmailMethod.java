package com.infotech.sgsy.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.URLName;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.infotech.sgsy.util.mail.DefaultAuthenticator;
import com.sun.mail.smtp.SMTPSSLTransport;

public class SendEmailMethod {

	
	
	public void sendwelcomeEmail(String emailAddress,String msg,String subject){
		try{ 
			String email = "mprmis-mord";
			String PASSWORD = "K1%m2@?!";
			
			Properties p= System.getProperties();	
			p.put("mail.transport.protocol", "smtps") ;
		 	//p.put("mail.smtps.socketFactory.class",	"");
		    p.put("mail.smtps.socketFactory.fallback", "false");
			p.put("mail.smtps.host", "mail.nic.in") ;
			p.put("mail.smtps.port", "465") ;	
			p.put("mail.smtps.user", email) ;
			p.put("mail.smtps.password", PASSWORD) ;
			p.put("java.protocol.handler.pkgs","com.sun.net.ssl.internal.www.protocol");	
			p.put("mail.smtps.auth", "true");

			
		// java.security.Security.setProperty("ssl.SocketFactory.provider","");
		    /*javax.mail.Authenticator authenticator = new DefaultAuthenticator(email,PASSWORD);
		    Session mailSession=Session.getInstance(p,authenticator);*/
			 Session mailSession=Session.getInstance(p,new javax.mail.Authenticator() {
	             
	             @Override
	         protected PasswordAuthentication getPasswordAuthentication(){
	             return new PasswordAuthentication(email,PASSWORD);
	         }
	         });
		    
		    p.put("mail.smtps.auth", "true");
			mailSession.setDebug(true);
			MimeMessage m= new MimeMessage(mailSession);
			m.setFrom(new InternetAddress(email));
			m.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailAddress));
			//m.setRecipients(Message.RecipientType.CC,  InternetAddress.parse(""));
			/*m.setRecipients(Message.RecipientType.BCC,  InternetAddress.parse(""));*/
			m.setSentDate(new Date());
			m.setSubject(subject);
			Multipart mailBody=new MimeMultipart();
			MimeBodyPart mainBody=new MimeBodyPart();
			mainBody.setText(msg);
			mailBody.addBodyPart(mainBody);
			m.setContent(mailBody);
			URLName urln = new URLName("smtps", "mail.nic.in" , 465, "", email, PASSWORD);
			SMTPSSLTransport transport = new SMTPSSLTransport (mailSession, urln ) ;		   
			transport.connect();
			transport.sendMessage(m,m.getAllRecipients());	
			transport.close();		
			}
			catch(Exception e){
				
	    	    e.printStackTrace();    	    
				try {
					throw e;
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
	}
}
	

	
	
