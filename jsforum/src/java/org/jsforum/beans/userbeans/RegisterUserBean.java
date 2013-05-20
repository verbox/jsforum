/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jsforum.beans.userbeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.jsforum.model.dispatchers.UsersDispatcher;

/**
 *
 * @author piotr
 */
@ManagedBean
@RequestScoped
public class RegisterUserBean {
    private String username;
    private String password;
    private String confirmPassword;
    private String email;
    //TODO zrobić wstrzyknięcie ApplicationBean
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
        this.password = UsersDispatcher.getMD5(password);
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = UsersDispatcher.getMD5(confirmPassword);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Creates a new instance of RegisterUserBean
     */
    public RegisterUserBean() {
    }
}
