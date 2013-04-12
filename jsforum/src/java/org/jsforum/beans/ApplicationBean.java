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
import org.jsforum.beans.userbeans.RegisterUserBean;
import org.jsforum.model.User;
import org.jsforum.model.dispatchers.UsersDispatcher;

/**
 *
 * @author piotr
 */
@ManagedBean
@ApplicationScoped
public final class ApplicationBean {

    private UsersDispatcher usersDispatcher;
    
    /**
     * Creates a new instance of ApplicationBean
     */
    public ApplicationBean() {
        init();
    }
    
    public String loginUser(LoginUserBean loginBean, CurrentUserBean userBean) {
        User user = usersDispatcher.getUser(loginBean.getUsername(), loginBean.getPassword());
        userBean.setCurrentUser(user);
        //mechanizm logowania
        return "index";
    }
    
    public String registerUser(RegisterUserBean registerUserBean) {
        //TODO sprawdź, czy zgadza się password i confirmPassword
        if (usersDispatcher.addUser(registerUserBean))
            return "index";
        //TODO komunikaty
        return "registerView";
    }
    
    @PostConstruct
    public void init() {
        usersDispatcher = new UsersDispatcher();
    }
}
