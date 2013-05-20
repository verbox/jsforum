/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jsforum.beans.userbeans;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.jsforum.model.dispatchers.UsersDispatcher;

/**
 *
 * @author piotr
 */
@ManagedBean
@RequestScoped
public class LoginUserBean {
    
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        try {
            this.password = UsersDispatcher.getMD5(password);
        }
        catch (Exception ex) {
            System.out.println("Serwer nie obsługuje szyfrowania.");
        }
    }
    
    /**
     * Creates a new instance of LoginUserBean
     */
    public LoginUserBean() {
    }
    
}
