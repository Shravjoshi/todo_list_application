package com.todo.app.todo.classes;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Component@Getter@Setter@AllArgsConstructor@NoArgsConstructor@ToString
public class loginCredentials 
{
    int status;
    String token;
    User user;
}
