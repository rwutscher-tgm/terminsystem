package com.eventplaner.tasks.notificationTasks;

import com.eventplaner.model.Poll;
import com.eventplaner.tasks.Task;

public class SendPollFinalizedUpdate implements Task{

    private Poll poll;

    public SendPollFinalizedUpdate(Poll poll) {
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
            message.setSubject("The results of your poll are here!");

            message.setContent(messageHtml, "text/html");
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

            Transport.send(message);

            System.out.println("Done");
    }
}











