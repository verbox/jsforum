/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jsforum.beans.userbeans;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.jsforum.model.User;

/**
 *
 * @author piotr
 */
@ManagedBean
@SessionScoped
public class CurrentUserBean implements Serializable{

    public static String getMD5(String string) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(string.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 255) | 256).substring(1, 3));
            }
            return sb.toString();
        }
        catch (Exception ex) { //TODO - ładnie obsłużyć wyjątek (rzucić jakiś komunikat itp.)
            ex.printStackTrace();
            return null;
        }
    }

    private User currentUser;

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
    
    /**
     * Creates a new instance of CurrentUserBean
     */
    public CurrentUserBean() {
    }
    
    public String logout() {
        currentUser = null;
        return "index";
    }
    
    @PostConstruct
    public void init() {
        
    }
}
