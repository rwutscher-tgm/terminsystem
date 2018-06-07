package com.eventplaner.tasks.notificationTasks;



import com.eventplaner.model.Comment;
import com.eventplaner.model.CommentSystem;
import com.eventplaner.model.RegisteredUser;
import com.eventplaner.tasks.Task;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;




public class SendSignUpSuccess implements Task{

    private RegisteredUser user;

    public SendSignUpSuccess(RegisteredUser user){
        this.user = user;
    }

    @Override
    public void execute() {
        final String username = "termimysytemsew2018wtsm@gmail.com";
        final String password = "*T9:7N(!%gV'ruY>";


        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("termimysytemsew2018wtsm@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("rwutscher@student.tgm.ac.at"));
            message.setSubject("Signup");

            message.setContent(messageHtml, "text/html");
            message.setText("Your Terminplaner Account has been created successfully!"
                    + "\n\n Have fun!");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}







