package App;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {

	private  String nomeProdotto;
	private  String prezzoProdotto;
	protected String destinatario;
	protected String text;
	protected String myAccountEmail = "pricetrackers.p.a@gmail.com";
	protected String myAccountPassword = "computer1X";
	protected String oggetto;
	public String defaultText ="Gentile cliente"+"\n"+"\n"+"\n"+"\n"+"Grazie per aver ulilizzato la nostra applicazione Traccia Prezzi"+"\n"+"\n"+"Il costo del prodotto messo sotto osservazione: "+nomeProdotto+" è sceso sotto la soglia prestabilita. Ora costa"+prezzoProdotto;
	public String defaultOggetto="PRODOTTO IN SCONTO";
	
	
	public Mail() {
		super();
		this.text="";
		this.oggetto="";
	}
	
	
	public Mail(String text, String oggetto) {
		super();
		
		this.text = text;
		this.oggetto = oggetto;
	}
	
	public Mail( Prodotto p) {
		super();
		nomeProdotto=p.getNome();
		prezzoProdotto=p.getPrezzo();
		
		this.text = defaultText;
		this.oggetto = defaultOggetto;
		}
	
	public String getDestinatario() {
		return destinatario;
	}
	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getMyAccountEmail() {
		return myAccountEmail;
	}
	public void setMyAccountEmail(String myAccountEmail) {
		this.myAccountEmail = myAccountEmail;
	}
	public String getMyAccountPassword() {
		return myAccountPassword;
	}
	public void setMyAccountPassword(String myAccountPassword) {
		this.myAccountPassword = myAccountPassword;
	}
	public String getOggetto() {
		return oggetto;
	}
	public void setOggetto(String oggetto) {
		this.oggetto = oggetto;
	}
	
	
	public void send (String destinatario) {
		
		System.out.println("Preparing to send the email");
		Properties properties=new Properties();   //Properties è lista di coppie chiave-valore
		
		//Per mandare una mail bisogna configurare come minimo questi 4 campi
		
		//mail.smtp.out              -> defines wether an autentification is needed or not
		properties.put("mail.smtp.auth", "true");
		//mail.smtp.starttls.enable     -> encription
		properties.put("mail.smtp.starttls.enable", "true");
		//mail.smtp.host             -> smtp.gmail.com
		properties.put("mail.smtp.host", "smtp.gmail.com");
		//mail.smtp.port             -> 587 if we use tls encription; 465 if we use ssl encription
		properties.put("mail.smtp.port", "587");
		
		Session session=Session.getInstance(properties , new Authenticator() {
			
		protected PasswordAuthentication getPasswordAuthentication()
																	{
				return new PasswordAuthentication(myAccountEmail,myAccountPassword);
																	}			
											});
		
		try {
			Message message=new MimeMessage(session);
			
			message.setFrom(new InternetAddress (myAccountEmail));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
			message.setSubject(oggetto);
			//message.setHeader("ciao", "value");
			message.setText(text);
		
			Transport.send(message);
	        System.out.println("Messaggio inviato correttamente....");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		
	}

}
