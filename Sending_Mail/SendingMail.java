import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendingMail implements Runnable{

    public static Thread n=new Thread();

    public static void sendMail(String recepient) throws Exception {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        String myAccountEmail = "your_mail_id";
        String password = "your_mail_password";
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });

        Message message = prepareMessage(session, myAccountEmail, recepient);
        Transport.send(message);
        System.out.println("Message Sent successfully");
    }

    private static Message prepareMessage(Session session, String from, String to) {

        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(from));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Your subject here");
            message.setText("Your content");
            return message;
        }
        catch (AddressException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        SendingMail m = new SendingMail();
        Thread n1=new Thread(m);
        n1.start();
    }

    @Override
    public  void run() {
        try {
            sendMail("recepient_mail_id");
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}