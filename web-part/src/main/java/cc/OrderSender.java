package cc;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;

import Model.GoodItem;
import Model.Order;
import Model.Row;

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
//			MimeMessage mess = sender.createMimeMessage();
//			MimeMessageHelper helper = new MimeMessageHelper(mess);
//			helper.setTo("vankor@bigmir.net");
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
			
			
//			StringBuilder str = new StringBuilder();
//			str.append("New order from site! \n");
//			if(ord.getUsr()!=null){
//			}
//			List<GoodItem> items = ord.getGoods();
//			int i = 1;
//			for(GoodItem g:items){
//			str.append(""+i+") "+g.getName()+"    "+g.getPrice()+"\n");
//			i++;			}
//			str.append("Order id is " + ord.getId());
			
			mess.setText(result.toString());

			Transport.send(mess);
		}

	}
