package br.usp.cata.service;

import java.io.Serializable;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;

import br.usp.cata.model.Email;
import br.usp.cata.model.EmailException;


@Component
@SessionScoped
public class EmailService implements Serializable {
	
    private static final long serialVersionUID = 7886620811055669211L;

    public static String PROP_MAIL_SMTP_FROM = "mail.smtp.from";
    public static String PROP_MAIL_SMTP_HOST = "mail.smtp.host";
    public static String PROP_MAIL_SMTP_USER = "mail.smtp.user";
    public static String PROP_MAIL_SMTP_PORT = "mail.smtp.port";
    public static String PROP_MAIL_SMTP_SSL_ENABLE = "mail.smtp.ssl.enable";
    public static String PROP_MAIL_SMTP_AUTH = "mail.smtp.auth";
    public static String PROP_MAIL_SMTP_STARTTLS_ENABLE = "mail.smtp.starttls.enable";
    public static String PROP_MAIL_SMTP_SOCKET_FACTORY_PORT = "mail.smtp.socketFactory.port";

    private static final String CONTEXT_ENV = "java:comp/env";
    private static final String CONTEXT_MAIL_SESSION = "mail/Session";
    private static final String SMTP_PROTOCOL = "smtp";
    private static final String PLAIN_TEXT = "text/html";

    private final Session session;
    private final Transport transport;

    public EmailService() throws EmailException {
	    try {
	    	final Context initCtx = new InitialContext();
	    	final Context envCtx = (Context) initCtx.lookup(CONTEXT_ENV);
		    this.session = (Session) envCtx.lookup(CONTEXT_MAIL_SESSION);
		    this.transport = session.getTransport(SMTP_PROTOCOL);
	    } catch( NamingException e) {
            throw new EmailException(e);
        } catch( NoSuchProviderException e) {
            throw new EmailException(e);
        }
    }

    public EmailService(final Session session) throws EmailException {
    	try {
            this.session = session;
            this.transport = session.getTransport(SMTP_PROTOCOL);
        } catch( NoSuchProviderException e) {
            throw new EmailException(e);
        }
    }

    public void sendEmail(final Email email) throws EmailException {
        try {
            final Message message = new MimeMessage(session);
            final int numberOfReceivers = email.getToAddresses().size();
            final InternetAddress[] receiversAddresses = new InternetAddress[numberOfReceivers];

            for(int i = 0; i < numberOfReceivers; i++)
                receiversAddresses[i] = new InternetAddress(email.getToAddresses().get(i));

            message.setFrom(new InternetAddress(email.getFromAddress()));
            message.setRecipients(Message.RecipientType.TO, receiversAddresses);

            message.setSubject(email.getSubject());
            message.setContent(email.getBody(), PLAIN_TEXT);
            message.setSentDate(new java.util.Date());

            transport.connect(email.getHostname(), email.getUser(), email.getPassword());
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch(AddressException e) {
            throw new EmailException(e);
        } catch(MessagingException e) {
            throw new EmailException(e);
        }
    }

    public Email buildEmail(final String subject, final String body,
        final String... receivers)
    {
        final Email email = new Email();
        email.setFromAddress(session.getProperty(PROP_MAIL_SMTP_FROM));
        email.setHostname(session.getProperty(PROP_MAIL_SMTP_HOST));
        email.setUser(session.getProperty(PROP_MAIL_SMTP_USER));
        email.setSubject(subject);
        email.setBody(body);

        for(String receiver : receivers)
            email.addReceiver(receiver);

        return email;
    }
}
