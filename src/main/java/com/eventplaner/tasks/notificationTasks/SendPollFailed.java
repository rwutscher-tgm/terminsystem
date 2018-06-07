package com.eventplaner.tasks.notificationTasks;

import com.eventplaner.model.Poll;
import com.eventplaner.tasks.Task;

public class SendPollFailed implements Task {

    private Poll poll;

    public SendPollFailed(Poll poll) {
        this.poll = poll;
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
            message.setSubject("There has been a problem with your poll!");

            message.setContent(messageHtml, "text/html");
            message.setText("We are sorry to inform you "
                    +"that your poll has encountered a problem"
                    +"and has not been sent successfully"
                    + "\n\n Please try again later or try to contact the support.);

            Transport.send(message);

            System.out.println("Done");
    }
}
