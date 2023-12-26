package com.todo.app.todo.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.todo.app.todo.classes.Task;

public interface TaskRepo extends JpaRepository<Task,Integer>
{

    @Transactional@Modifying
    @Query(nativeQuery=true,value="DELETE FROM `todo`.`task` WHERE (`id` = ?1);")
    public void deleteTaskId(int taskId);
    
}
