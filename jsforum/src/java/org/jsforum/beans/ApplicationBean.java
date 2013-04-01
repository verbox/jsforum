/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jsforum.beans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.CustomScoped;
import javax.inject.Named;
import org.jsforum.beans.userbeans.CurrentUserBean;
import org.jsforum.beans.userbeans.LoginUserBean;
import org.jsforum.model.User;

/**
 *
 * @author piotr
 */
@ManagedBean
@ApplicationScoped
public final class ApplicationBean {

    /**
     * Creates a new instance of ApplicationBean
     */
    public ApplicationBean() {
    }
    
    public String loginUser(LoginUserBean loginBean, CurrentUserBean userBean) {
        //TODO na razie - okej, loguj na sztywno jakiegoś jana nowaka
        userBean.setCurrentUser(new User("Jan Nowak","dkshgf","abc@wp.pl"));
        //mechanizm logowania
        return "index";
    }
    
    @PostConstruct
    public void init() {
        
    }
}
