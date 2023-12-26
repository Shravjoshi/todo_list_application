package com.todo.app.todo.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.todo.app.todo.classes.User;

public interface UserRepo extends JpaRepository<User,Integer>
{

    int countByUsername(String username);
    User findByUsername(String username);
    
    

    @Transactional@Modifying
    @Query(nativeQuery=true,value="UPDATE `todo`.`user` SET `password` = ?2 WHERE (`username` = ?1)")
    void updatePassword(String username, String password);
    

}
