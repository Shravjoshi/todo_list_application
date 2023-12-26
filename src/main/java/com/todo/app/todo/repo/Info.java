package com.todo.app.todo.repo;

import java.util.List;

import com.todo.app.todo.classes.Task;

public interface Info
{
  String getName();
  String getUserName();
  List<Task> getTasks();
}
