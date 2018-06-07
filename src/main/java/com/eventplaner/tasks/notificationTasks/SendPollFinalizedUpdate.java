package com.eventplaner.tasks.notificationTasks;

import com.eventplaner.model.Poll;
import com.eventplaner.tasks.Task;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendPollFinalizedUpdate implements Task{

    private Poll poll;

    public SendPollFinalizedUpdate(Poll poll) {
        this.poll = poll;
    }

    @Override
    public void execute() {
        //Userdata for mail
        final String username = "termimysytemsew2018wtsm@gmail.com";
        final String password = "*T9:7N(!%gV'ruY>";

        //Setting up the properties for the mail
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");


        //Create session
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });


        try {
            Message message = new MimeMessage(session);
            //Setting the sender
            message.setFrom(new InternetAddress("termimysytemsew2018wtsm@gmail.com"));
            //Setting the recipients
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("rwutscher@student.tgm.ac.at"));
            //This is the title of the mail
            message.setSubject("The results of your poll are here!");
            //message.setContent(messageHtml, "text/html");


            //This is the main content of the mail
            message.setText(
                    "Dear User,"+
                            "\n\n"
                            +"Your poll has been completed and here is the result:"+
                            "\n\n"
                            +"<TempText1>\n"
                            +"<TempText2>\n"
                            +"<TempText3>\n"
                            +"<TempText4>\n\n"
                            +"We hope you are happy with the results and we are looking forward for your next poll"+
                            "\n\n"
                            +"Good luck,"+
                            "\n\n"
                            +"Your Terminsystem team" +
                            "\n"
            );
            //Mail gets send after everything is set up
            Transport.send(message);

            //Successlog message
            System.out.println("Done");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }



    }
}











