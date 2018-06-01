package com.eventplaner.tasks;

import javafx.concurrent.Task;

public interface GetterTask<T>{
    public T execute();
}
