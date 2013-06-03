/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jsforum.beans.userbeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author piotr
 */
@ManagedBean
@RequestScoped
public class AddPostBean {

    private String postText;

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }
    
    /**
     * Creates a new instance of addPostBean
     */
    public AddPostBean() {
    }
}
