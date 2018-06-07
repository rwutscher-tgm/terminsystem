package com.eventplaner.tasks.pollTasks;

import com.eventplaner.model.*;
import com.eventplaner.model.repositories.PollRepository;
import com.eventplaner.model.repositories.PollTopicRepository;
import com.eventplaner.tasks.Task;

public class AddPollTopic implements Task{

    private Poll poll;
    private String description;

    private PollRepository pollRepository;
    private PollTopicRepository pollTopicRepository;

    public AddPollTopic(Poll poll, String description, PollRepository pollRepository, PollTopicRepository pollTopicRepository) {
        this.poll = poll;
        this.description = description;
        this.pollRepository = pollRepository;
        this.pollTopicRepository = pollTopicRepository;
    }

    @Override
    public void execute() {

        /*Configuration config = HibernateUtils.getConfig(new Class[]{
                Poll.class,
                PollTopic.class,
                User.class,
                UnregisteredUser.class,
                RegisteredUser.class,
                Comment.class,
                CommentSystem.class
        });

        SessionFactory factory = null;
        Session session = null;

        try{
            factory = config.buildSessionFactory();
            session = factory.openSession();

            session.beginTransaction();*/

            PollTopic topic = new PollTopic(this.description);
            pollTopicRepository.save(topic);

            this.poll.addPollTopic(topic);
            pollRepository.save(poll);
/*
            session.getTransaction().commit();

            session.close();
            factory.close();
        }catch(Exception e){
            e.printStackTrace();
        }
*/
    }
}
