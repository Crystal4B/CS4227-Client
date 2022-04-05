package hotelsystem.email;


import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * @author Eoin McDonough
 * Email class to handle sending emails
 */
public class Email {
    /**
     * Sends email
     *@param email Users email
     *@param subject Subject of email
     *@param emailMessage Message to send
     */
    public Email(String email, String subject, String emailMessage) {
        String from = "teamplatinumlimerick@gmail.com";
        String host = "smtp.gmail.com";

        Properties properties = System.getProperties();

        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("teamplatinumlimerick@gmail.com", "#Limerick22");

            }

        });
        session.setDebug(false);

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));

            message.setSubject(subject);
            message.setText(emailMessage);

            Transport.send(message);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

    }
}
