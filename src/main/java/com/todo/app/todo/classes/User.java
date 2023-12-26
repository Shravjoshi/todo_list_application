package com.todo.app.todo.classes;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity@Getter@Setter@AllArgsConstructor@NoArgsConstructor@ToString
public class User
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int id;
  String username;
  String name;
  String password;
  @OneToMany
  List<Task> tasks;

}
