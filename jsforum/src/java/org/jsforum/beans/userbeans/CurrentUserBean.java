/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jsforum.beans.userbeans;

import java.io.Serializable;
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
    
    @PostConstruct
    public void init() {
        
    }
}
