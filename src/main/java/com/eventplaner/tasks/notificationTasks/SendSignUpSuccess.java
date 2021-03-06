package com.eventplaner.tasks.notificationTasks;

import com.eventplaner.model.Comment;
import com.eventplaner.model.CommentSystem;
import com.eventplaner.model.RegisteredUser;
import com.eventplaner.model.User;
import com.eventplaner.tasks.Task;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Teilt einem Benutzer mit, dass er erfolgreich angemeldet wurde
 */
public class SendSignUpSuccess implements Task{
    //Used to get the mail address from
    private User user;

    /**
     * Der Konstruktor für den SendSignupSuccess Task
     * @param user Der Benutzer der erfolgreich angemeldet wurde
     */
    public SendSignUpSuccess(User user){
        this.user = user;
    }

    /**
     * Führt den Task aus
     */
    @Override
    public void execute() {
        //Set up the userdata of the mail account
        final String username = "termimysytemsew2018wtsm@gmail.com";
        final String password = "*T9:7N(!%gV'ruY>";



        //Set up the properties of the mail
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

            //Set main mail content


            //Create new Message object (will be the finalized message we send
            Message message = new MimeMessage(session);


            //Set the sender
            message.setFrom(new InternetAddress("termimysytemsew2018wtsm@gmail.com"));


            //Set the recipients
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getEmail()));


            //Set the Title of the mail
            message.setSubject("Signup");

            //Set the main content of the mail
            message.setText("Your Terminplaner Account has been created successfully!"
                    + "\n\n Have fun!");


            //Send message after everything is set up
            Transport.send(message);

            //send success message in log
            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}












