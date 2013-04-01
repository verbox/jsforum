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
        try {
            this.password = getMD5(password);
        }
        catch (Exception ex) {
            System.out.println("Serwer nie obsługuje szyfrowania.");
        }
    }
    
    private String getMD5(String string) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] array = md.digest(string.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
          sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
        }
        return sb.toString();
    }
    
    /**
     * Creates a new instance of LoginUserBean
     */
    public LoginUserBean() {
    }
    
}
