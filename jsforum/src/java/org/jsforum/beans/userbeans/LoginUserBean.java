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
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
            this.password = new String(md.digest(password.getBytes("UTF8")),"UTF8");
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
