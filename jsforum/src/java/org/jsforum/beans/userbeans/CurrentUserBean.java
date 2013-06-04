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
import org.jsforum.model.Topic;

import org.jsforum.model.User;

/**
 *
 * @author piotr
 */
@ManagedBean
@SessionScoped
public class CurrentUserBean implements Serializable{

    private User currentUser;
    private Topic currentTopic;

    public Topic getCurrentTopic() {
        return currentTopic;
    }

    public void setCurrentTopic(Topic currentTopic) {
        this.currentTopic = currentTopic;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
    
    public boolean isLogged() {
        return currentUser!=null;
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
    
    public String nextTopic(Topic topic) {
        this.setCurrentTopic(topic);
        return "topicView";
    }
}
