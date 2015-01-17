package controllers;


import Model.others.Row;
import Model.entity.Order;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;


	public class OrderSender {
		private JavaMailSenderImpl sender;
		final String username = "vankor1991";
		final String password = "fanbrat826421999";
		
		public void setMailSender(JavaMailSenderImpl sender){
			this.sender = sender;
		}
		
		public void send(Order ord) throws MessagingException{
			
			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");
	 
			javax.mail.Session session = javax.mail.Session.getInstance(props,
			  new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			  });
			
			Message mess = new MimeMessage(session);
			mess.setFrom(new InternetAddress("vankor1991@gmail.com"));
			mess.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse("vankor@bigmir.net"));
			mess.setSubject("Testing Subject");
			
			
			StringBuilder result = new StringBuilder("<br>Order #<b>" + ord.getId() + " is created at " + new Date().toString() + " and will be handled) </b><br>");
			if(ord.getUsr()!=null){
				result.append("User "+ord.getUsr().getName()+"have ordered goods: \n");
				} 
			else{
				result.append("Order positions:<br>");} 
			 int i = 1;
			 for(Row r: ord.getRows()){
				 result.append(""+i+")"+r.getGood().getName()+". Цена: "+r.getGood().getPrice()+". Количество: "+r.getGoodcount());
				 i++;
			 }

			mess.setText(result.toString());

			Transport.send(mess);
		}

	}
