package org.jsforum.model;
// Generated 2013-03-17 12:01:10 by Hibernate Tools 3.2.1.GA



/**
 * Users generated by hbm2java
 */
public class User  implements java.io.Serializable {


     private Integer userId;
     private String username;
     private String password;
     private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User() {
    }

    public User(String username, String password, String email) {
       this.username = username;
       this.password = password;
       this.email = email;
    }
   
    public Integer getUserId() {
        return this.userId;
    }
    
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }




}


