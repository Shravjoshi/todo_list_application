package com.todo.app.todo.classes;

import java.util.Collection;

import com.todo.app.todo.repo.UserDetails;

public class UserTwo implements UserDetails{
    User user;
    public UserTwo(User user)
    {this.user=user;}
    @Override
    public Collection getAuthorities() {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return user.password;
    }
    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return user.username;
    }
    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return false;
    }
    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return false;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return false;
    }
    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return false;
    }
    

}
