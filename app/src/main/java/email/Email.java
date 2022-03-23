package email;


import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import order.Order;

public class Email {
    public Email(String email, String subject, String emailMessage) {
        String to = email;
        String from = "teamplatinumlimerick@gmail.com";
        String host = "smtp.gmail.com";

        Properties properties = System.getProperties();

        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("teamplatinumlimerick@gmail.com", "#Limerick22");

            }

        });
        session.setDebug(true);

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            message.setSubject(subject);

            /*String emailMessage = "";
            emailMessage = "This is an automated message from Platinum Hotels to confirm your recent order.\n\n\n";
            emailMessage += "You booked " + order.getRooms().size() + " room(s) from " + order.getStartDate() + " to " + order.getEndDate() + " . \n\n\n";
            emailMessage += "Your cost is $" + order.getFinalCost(); */
            message.setText(emailMessage);

            System.out.println("sending...");

            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

    }
}
