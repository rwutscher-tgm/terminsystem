package com.eventplaner.tasks.notificationTasks;

import com.eventplaner.model.Poll;
import com.eventplaner.tasks.Task;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendPollFailed implements Task {

    private Poll poll;

    public SendPollFailed(Poll poll) {
        this.poll = poll;
    }

    @Override
    public void execute() {
        //Here we set up the userdata of the mail account
        final String username = "termimysytemsew2018wtsm@gmail.com";
        final String password = "*T9:7N(!%gV'ruY>";

        //Set the properties that the mail should have
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");


        //Create a new session
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);

            //Set the sender
            message.setFrom(new InternetAddress("termimysytemsew2018wtsm@gmail.com"));

            //Set the recipients
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("rwutscher@student.tgm.ac.at"));

            //Title of the maik
            message.setSubject("There has been a problem with your poll!" +
                    "");

            //Set the type of content, that the mail should be in
            //message.setContent(messageHtml, "text/html");

            //Main content of the mail
            message.setText("We are sorry to inform you "
                            +"that your poll has encountered a problem"
                            +"and has not been sent successfully"
                            + "\n\n Please try again later or try to contact the support.");

                    //Send mail after setting everything up
                    Transport.send(message);

            //Send success message
            System.out.println("Done");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }
}







