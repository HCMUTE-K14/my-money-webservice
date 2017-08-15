package com.vn.hcmute.team.cortana.mymoney.utils;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.vn.hcmute.team.cortana.mymoney.exception.EmailException;

public class EmailUtil {

	private  String username;
	

	private String password;
	
	private EmailUtil(){
		Properties properties=ResourceUtil.getConfigProperties();
		this.username=properties.getProperty("email.username");
		this.password=properties.getProperty("email.password");
	
	}
	
	protected static final EmailUtil ins=new EmailUtil();
	
	public static final EmailUtil getInstance(){
		return ins;
	}
	
	
	public void sendMail(String to,String subject,String emailBody){
		 Properties props = new Properties();
	        props.put("mail.smtp.host", "smtp.gmail.com");
	        props.put("mail.smtp.socketFactory.port", "587");
	        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.smtp.port", "587");

	        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
	            @Override
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(username, password);
	            }
	        });
	        try {
	        	MimeMessage message = new MimeMessage(session);
	            message.setFrom(new InternetAddress(username));
	            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
	            message.setSubject(subject,"UTF-8");
	            message.setContent(emailBody,"text/html");
	            Transport.send(message);
	        } catch (Exception e) {
	           throw new EmailException("Error! cannot sent mail");
	        }
	}
}
