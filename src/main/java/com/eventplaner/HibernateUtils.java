package com.eventplaner;

import org.hibernate.cfg.Configuration;

public class HibernateUtils {

    public static Configuration getConfig(Class[] classes){
        Configuration configuration = new Configuration().configure();
        for(int i = 0; i < classes.length; i++){
            configuration.addAnnotatedClass(classes[i]);
        }
        return configuration;
    }

}
