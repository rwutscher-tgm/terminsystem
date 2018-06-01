package com.eventplaner;

import com.eventplaner.model.*;
import com.eventplaner.tasks.DeleteObject;
import com.eventplaner.tasks.SaveObject;
import com.eventplaner.tasks.pollTasks.GetPoll;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import junit.framework.TestCase;

import java.util.logging.Logger;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class EventplanerApplicationTests extends TestCase {

    static Logger log = Logger.getLogger(EventplanerApplicationTests.class.getName());
    private SessionFactory factory;


    @Override
    protected void setUp() throws Exception {
        log.info("Setting up unittests!");
    }

    @Test
	public void dirtyTest() {
	}

}
