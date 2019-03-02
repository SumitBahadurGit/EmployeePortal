package com.company.consultant.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

@Service
public class MailService {
	
	public void sendEmail (String _to, String _body) throws Exception{
		
		String to = _to;

	    // Sender's email ID needs to be mentioned
	    String from = "greenconsultingsolutions.us@gmail.com";

	    // Assuming you are sending email from localhost
	    String host = "localhost";

	    // Get system properties
	    Properties props = System.getProperties();
	    

	    props.put("mail.smtp.host", "smtp.gmail.com");    
        props.put("mail.smtp.socketFactory.port", "465");    
        props.put("mail.smtp.socketFactory.class",    
                  "javax.net.ssl.SSLSocketFactory");    
        props.put("mail.smtp.auth", "true");    
        props.put("mail.smtp.port", "465"); 

        Session session = Session.getDefaultInstance(props,    
                new javax.mail.Authenticator() {    
                protected PasswordAuthentication getPasswordAuthentication() {    
                return new PasswordAuthentication(from,"green2019S");  
                }    
               });    
	    try {
	       // Create a default MimeMessage object.
	       MimeMessage message = new MimeMessage(session);

	       // Set From: header field of the header.
	       message.setFrom(new InternetAddress(from));

	       // Set To: header field of the header.
	       message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

	       // Set Subject: header field
	       message.setSubject("GREEN CONSULTING SOLUTIONS");
	       
	       // Now set the actual message
	       message.setText(_body);

	       // Send message
	       Transport.send(message);
	       System.out.println("Sent message successfully....");
	    } catch (MessagingException mex) {
	      System.out.println("Email ERROR: " + mex.getMessage());
	      throw new Exception("Error sending email");
	    }
	}


}
