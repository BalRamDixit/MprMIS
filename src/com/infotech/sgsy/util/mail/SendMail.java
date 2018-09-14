package com.infotech.sgsy.util.mail;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.URLName;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.infotech.sgsy.util.mail.DefaultAuthenticator;
import com.infotech.sgsy.util.mail.MailSetting;
import com.sun.mail.smtp.SMTPSSLTransport;



public class SendMail extends MailSetting{
	 
	/*
	// For 3i-Infotech
	
	public void sendMail(String recipients, String subject, String message
			) throws Exception {
		boolean debug = false;

		//Set the host smtp address
		Properties props = new Properties();
		props.put("mail.smtp.host", SMTP_HOST_NAME);
		props.put("mail.smtp.auth", "true");
		String from=MAIL_FROM;
		Authenticator auth = new SMTPAuthenticator();
		Session session = Session.getDefaultInstance(props, auth);

		session.setDebug(debug);

		// create a message
		Message msg = new MimeMessage(session);

		// set the from and to address
		InternetAddress addressFrom = new InternetAddress(from);
		msg.setFrom(addressFrom);

		InternetAddress addressTo = new InternetAddress(recipients);
		msg.setRecipient(Message.RecipientType.TO, addressTo);
		msg.setSubject(subject);
		msg.setContent(message, "text/plain");
		Transport.send(msg);
	}

	/**
	 * SimpleAuthenticator is used to do simple authentication
	 * when the SMTP server requires it.
	 */
	/*private class SMTPAuthenticator extends javax.mail.Authenticator {

		public PasswordAuthentication getPasswordAuthentication() {
			String username = SMTP_AUTH_USER;
			String password = SMTP_AUTH_PWD;
			return new PasswordAuthentication(username, password);
		}
	}

	*/
	// For NIC 
	
	
 	
	
	public void sendMail(String p_to,String p_subject, String p_message) throws Exception{
	try{ 
		String E_MAIL = "mis.skills.ajv";
		String PASSWORD = "Askl@321";
		Integer a = new Integer(587);		  
		Properties p= System.getProperties();	
		p.put("mail.transport.protocol", "smtps") ;
	 	p.put("mail.smtps.socketFactory.class",	"com.infotech.sgsy.util.mail.DummySSLSocketFactory");
	    p.put("mail.smtps.socketFactory.fallback", "false");
		p.put("mail.smtps.host", "mail.nic.in") ;
		p.put("mail.smtps.port", "465") ;
		p.put("mail.smtps.user", E_MAIL) ;
		p.put("mail.smtps.password", PASSWORD) ;
		p.put("java.protocol.handler.pkgs","com.sun.net.ssl.internal.www.protocol");	
		p.put("mail.smtps.auth", "true");

		
	 java.security.Security.setProperty("ssl.SocketFactory.provider","com.infotech.sgsy.util.mail.DummySSLSocketFactory");
	    javax.mail.Authenticator authenticator = new DefaultAuthenticator(E_MAIL,PASSWORD);
	    Session mailSession=Session.getInstance(p,authenticator);
		p.put("mail.smtps.auth", "true");
		mailSession.setDebug(true);
		MimeMessage m= new MimeMessage(mailSession);
		m.setFrom(new InternetAddress(E_MAIL));
		m.setRecipients(Message.RecipientType.TO, InternetAddress.parse(p_to));
		//m.setRecipients(Message.RecipientType.CC,  InternetAddress.parse("prnddugky@gmail.com"));
		/*m.setRecipients(Message.RecipientType.BCC,  InternetAddress.parse("gvsnm@nic.in"));*/
		m.setSentDate(new Date());
		m.setSubject(p_subject);
		Multipart mailBody=new MimeMultipart();
		MimeBodyPart mainBody=new MimeBodyPart();
		mainBody.setText(p_message);
		mailBody.addBodyPart(mainBody);
		m.setContent(mailBody);
		URLName urln = new URLName("smtps", "mail.nic.in" , 465, "", E_MAIL, PASSWORD);
		SMTPSSLTransport transport = new SMTPSSLTransport (mailSession, urln ) ;		   
		transport.connect();
		transport.sendMessage(m,m.getAllRecipients());	
		transport.close();		
		}
		catch(Exception e){
			StringWriter sw = new StringWriter();
    	    PrintWriter pw = new PrintWriter(sw);
    	    e.printStackTrace(pw);    	    
			throw e;
			
		}
	}
	
	
	public void sendMailWithOutPassword(String p_to,String p_subject, String p_message ) throws Exception{
		try{ 
			String E_MAIL = "mis.skills.ajv";
			String PASSWORD = "Askl@321";
			Integer a = new Integer(587);		  
			Properties p= System.getProperties();	
			p.put("mail.transport.protocol", "smtps") ;
		 	p.put("mail.smtps.socketFactory.class",	"com.infotech.sgsy.util.mail.DummySSLSocketFactory");
		    p.put("mail.smtps.socketFactory.fallback", "false");
			p.put("mail.smtps.host", "mail.nic.in") ;
			p.put("mail.smtps.port", "465") ;
			p.put("mail.smtps.user", E_MAIL) ;
			p.put("mail.smtps.password", PASSWORD) ;
			p.put("java.protocol.handler.pkgs","com.sun.net.ssl.internal.www.protocol");	
			p.put("mail.smtps.auth", "true");

			
		 java.security.Security.setProperty("ssl.SocketFactory.provider","com.infotech.sgsy.util.mail.DummySSLSocketFactory");
		    javax.mail.Authenticator authenticator = new DefaultAuthenticator(E_MAIL,PASSWORD);
		    Session mailSession=Session.getInstance(p,authenticator);
			p.put("mail.smtps.auth", "true");
			mailSession.setDebug(true);
			MimeMessage m= new MimeMessage(mailSession);
			m.setFrom(new InternetAddress(E_MAIL));
			m.setRecipients(Message.RecipientType.TO, InternetAddress.parse(p_to));
			m.setSentDate(new Date());
			m.setSubject(p_subject);
			Multipart mailBody=new MimeMultipart();
			MimeBodyPart mainBody=new MimeBodyPart();
			mainBody.setText(p_message);
			mailBody.addBodyPart(mainBody);
			m.setContent(mailBody);
			URLName urln = new URLName("smtps", "mail.nic.in" , 465, "", E_MAIL, PASSWORD);
			SMTPSSLTransport transport = new SMTPSSLTransport (mailSession, urln ) ;		   
			transport.connect();
			transport.sendMessage(m,m.getAllRecipients());	
			transport.close();		
			}
			catch(Exception e){
				StringWriter sw = new StringWriter();
	    	    PrintWriter pw = new PrintWriter(sw);
	    	    e.printStackTrace(pw);    	    
				throw e;
				
			}
	
	}
 
}