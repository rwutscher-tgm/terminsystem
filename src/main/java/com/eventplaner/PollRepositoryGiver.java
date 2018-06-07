package com.eventplaner;

import com.eventplaner.model.repositories.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class PollRepositoryGiver {
    @Autowired
    PollRepository pollRepository;

    public PollRepositoryGiver() {
    }

    public PollRepository getPollRepository() {
        return pollRepository;
    }
}
