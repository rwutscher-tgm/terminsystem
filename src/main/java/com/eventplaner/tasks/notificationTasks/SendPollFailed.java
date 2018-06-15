package com.eventplaner.tasks.notificationTasks;

import com.eventplaner.model.Poll;
import com.eventplaner.model.User;
import com.eventplaner.tasks.Task;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Teilt den Teilnemern einel Polls mit, dass der Poll abgesagt wurde
 */
public class SendPollFailed implements Task {
    //Poll to get the pollname from
    private Poll poll;
    //User to get the mail address from
    private User user;

    public SendPollFailed(Poll poll, User user) {
        this.poll = poll;
        this.user = user;
    }

    /**
     * Führt den Taks aus
     */
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
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getEmail()));


            //Title of the mail
            message.setSubject("There has been a problem with your poll!" +
                    "");
            

            //Main content of the mail
            message.setText("We are sorry to inform you "
                            +"that your poll ("+poll.getName()+") has encountered a problem"
                            +"and has not been sent successfully"
                            + "\n\n Please try again later or try to contact the support.");

                    //Send mail after setting everything up
                    Transport.send(message);

            //Send success message
            System.out.println("Done");
        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}







