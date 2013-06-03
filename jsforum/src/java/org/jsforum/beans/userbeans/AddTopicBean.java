/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jsforum.beans.userbeans;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author piotr
 */
@ManagedBean
@RequestScoped
public class AddTopicBean implements Serializable{
    private String topicName;
    private String postText;

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }
    /**
     * Creates a new instance of AddTopicBean
     */
    public AddTopicBean() {
    }
}
