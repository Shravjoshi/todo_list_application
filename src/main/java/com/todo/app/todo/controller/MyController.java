package com.todo.app.todo.controller;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.app.todo.classes.JwtUtil;
import com.todo.app.todo.classes.MySecurity;
import com.todo.app.todo.classes.Task;
import com.todo.app.todo.classes.User;
import com.todo.app.todo.classes.UserTwo;
import com.todo.app.todo.classes.loginCredentials;
import com.todo.app.todo.repo.TaskRepo;
import com.todo.app.todo.repo.UserRepo;

@RestController
@CrossOrigin
public class MyController
{
  @Autowired
  TaskRepo taskRepo;

  @Autowired
  UserRepo userRepo;

  @Autowired
  MySecurity security;

  @Autowired
  JwtUtil jwtUtil;

  @RequestMapping("login{username}/{password}")
  public loginCredentials login(@PathVariable String username, @PathVariable String password)
  {
    loginCredentials lc=new loginCredentials();
    try
    {
      
      if(userRepo.countByUsername(username)!=1)
      {
        lc.setStatus(-1);
        return lc;
      }
      User user = userRepo.findByUsername(username);
      if(!user.getPassword().equals(password))
      {
        lc.setStatus(-2);
        return lc;
      }
      UserTwo userTwo=new UserTwo(user);
      String token=jwtUtil.generateToken(userTwo);
      
      lc.setUser(user);
      lc.setToken(token);
      lc.setStatus(1);
      return lc;

    }
    catch (Exception e)
    {
      e.printStackTrace();
      lc.setStatus(0);
      return lc;
    }
  }

  

  @RequestMapping("addTask{username}and{work}and{token}")
  public Task addTask(@PathVariable String username, @PathVariable String work)
  {
    System.out.println(username);
    try
    {
      Task task=new Task(0, work,0);
      task=taskRepo.save(task);
      User user=userRepo.findByUsername(username);
      user.getTasks().add(task);
      userRepo.save(user);
      return task;
    }
    catch (Exception e)
    {
      return null;
    }
  }

  @RequestMapping("changeStatus/{taskId}and{newStatus}and{token}")
  public boolean changeStatus(@PathVariable int taskId, @PathVariable int newStatus,@PathVariable String token)
  {
    
    try
    {
      Task task=taskRepo.findById(taskId).get();
      task.setStatus(newStatus);
      System.out.println(task.getStatus());
      taskRepo.save(task);
      return true;
    }
    catch (Exception e)
    {
      return false;
    }
  }

  @RequestMapping("welcome")
  public String welcome()
  {
    return "Welcome This is Spring server";
  }

  @RequestMapping("deleteTask/{username}and{taskId}and{token}")
  public boolean deleteTask(@PathVariable String username,@PathVariable int taskId,@PathVariable String token)
  {
    
    try 
    {
      Task task=taskRepo.findById(taskId).get();
      User user = userRepo.findByUsername(username);
      List<Task> list=user.getTasks();
      list.remove(task);
      user.setTasks(list);
      userRepo.save(user);
      taskRepo.deleteTaskId(taskId);
      return true;
    } 
    catch (Exception e) 
    {
      return false;
    }
  }

  @RequestMapping("createUser/{username}/{name}/{password}")
  public boolean createUser(@PathVariable String username,@PathVariable String name,@PathVariable String password)
  {
    System.out.println(username);
    
    System.out.println(username);
    
    try 
    {
      User user=new User(0, username, name, password, null);
      userRepo.save(user);
      return true;
    } 
    catch (Exception e) 
    {
      return false;
    }
  }

  @RequestMapping("changePassword/{username}/{password}")
  public boolean changePasswords(@PathVariable String username, @PathVariable String password)
  {
    
    
    try 
    {
      userRepo.updatePassword(username, password);
      return true;
    } 
    catch (Exception e) 
    {
      return false;
    }
  }

}
