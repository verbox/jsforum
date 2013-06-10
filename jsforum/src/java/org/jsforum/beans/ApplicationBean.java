/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jsforum.beans;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.ViewHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.webapp.FacesServlet;
import javax.ws.rs.core.Response;
import org.jsforum.beans.userbeans.AddPostBean;
import org.jsforum.beans.userbeans.AddTopicBean;
import org.jsforum.beans.userbeans.CurrentUserBean;
import org.jsforum.beans.userbeans.LoginUserBean;
import org.jsforum.beans.userbeans.RegisterUserBean;
import org.jsforum.model.Post;
import org.jsforum.model.Topic;
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
        /*
        * mocno naciagane 
        * Wystapi, gdy user zostal dodany wczesniej i zrobiony adminem, a teraz
        * jest dodawany, jako zwykly uzytkownik.
        */
        if(user.getUsername().equals("errorUser"))
            return "loginView";
        //mechanizm logowania
        return "topicList";
    }
    
    public String registerUser(RegisterUserBean registerUserBean) {
        
        if(!registerUserBean.getConfirmPassword().equals(registerUserBean.getPassword()))
           return "error";
       
        //zarejestrowano uzytkownika
        if (usersDispatcher.addUser(registerUserBean))
            return "index";

        return "error";
    }
    
    public String addPost(AddPostBean addPostBean, CurrentUserBean userBean) {
        //utwórz nowy post
        Post post = new Post(addPostBean.getPostText(), userBean.getCurrentUser(),
                userBean.getCurrentTopic());
        postDispatcher.addPost(post);
        //trzeba odświeżyć topic
        userBean.setCurrentTopic(postDispatcher.getTopic(userBean.getCurrentTopic().getId()));
        //refresh();
        return "addedPost";//TODO zrobić tak, by było wyrzucenie do topicView
    }
    
    public String addTopic(AddTopicBean topicBean, CurrentUserBean userBean) {
        Topic topic = new Topic(topicBean.getTopicName());
        Post post = new Post(topicBean.getPostText(), userBean.getCurrentUser(), topic);
        postDispatcher.saveTopic(topic);
        postDispatcher.addPost(post);
        return "topicList";
    }
    
    public String deletePost(Post post, CurrentUserBean userBean) {
        //na wszelki wypadek
        if (userBean.getCurrentUser().getType()=='A') {
            boolean result = postDispatcher.deletePost(post);
            if (result) return "topicList";
            userBean.setCurrentTopic(postDispatcher.getTopic(userBean.getCurrentTopic().getId()));
        }
        return null;
    }
    
    public void refresh() {
        FacesContext context = FacesContext.getCurrentInstance();
        String viewId = context.getViewRoot().getViewId();
        ViewHandler handler = context.getApplication().getViewHandler();
        UIViewRoot root = handler.createView(context, viewId);
        root.setViewId(viewId);
        context.setViewRoot(root);
    }
    
    @PostConstruct
    public void init() {
        usersDispatcher = new UsersDispatcher();
        postDispatcher = new PostDispatcher();
    }
}
