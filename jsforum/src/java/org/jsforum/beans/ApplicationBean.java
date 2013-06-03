/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jsforum.beans;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.CustomScoped;
import javax.inject.Named;
import org.jsforum.beans.userbeans.AddPostBean;
import org.jsforum.beans.userbeans.CurrentUserBean;
import org.jsforum.beans.userbeans.LoginUserBean;
import org.jsforum.beans.userbeans.RegisterUserBean;
import org.jsforum.model.Post;
import org.jsforum.model.User;
import org.jsforum.model.dispatchers.PostDispatcher;
import org.jsforum.model.dispatchers.UsersDispatcher;

/**
 *
 * @author piotr
 */
@ManagedBean
@ApplicationScoped
public final class ApplicationBean implements Serializable{

    private UsersDispatcher usersDispatcher;
    private PostDispatcher postDispatcher;

    public UsersDispatcher getUsersDispatcher() {
        return usersDispatcher;
    }

    public void setUsersDispatcher(UsersDispatcher usersDispatcher) {
        this.usersDispatcher = usersDispatcher;
    }

    public PostDispatcher getPostDispatcher() {
        return postDispatcher;
    }

    public void setPostDispatcher(PostDispatcher postDispatcher) {
        this.postDispatcher = postDispatcher;
    }
    
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
        return "topicList";
    }
    
    public String registerUser(RegisterUserBean registerUserBean) {
        //TODO sprawdź, czy zgadza się password i confirmPassword
        if (usersDispatcher.addUser(registerUserBean))
            return "index";
        //TODO komunikaty
        return "registerView";
    }
    
    public String addPost(AddPostBean addPostBean, CurrentUserBean userBean) {
        //utwórz nowy post
        Post post = new Post(addPostBean.getPostText(), userBean.getCurrentUser(),
                userBean.getCurrentTopic());
        postDispatcher.addPost(post);
        return "topicList";//TODO zrobić tak, by było wyrzucenie do topicView
    }
    
    @PostConstruct
    public void init() {
        usersDispatcher = new UsersDispatcher();
        postDispatcher = new PostDispatcher();
    }
}
