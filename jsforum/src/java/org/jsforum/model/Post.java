/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jsforum.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author piotr
 */
@Entity
@Table(name = "posts")
public class Post implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Post() {
    }
    
    
    
    /*
     * NORMALNE POLA
     */
    @Column(name = "text")
    private String text;
    
    @Column(name = "addTime")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date added;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User author;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="topic_id")
    private Topic topic;

        public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

        public Date getAdded() {
        return added;
    }

    public void setAdded(Date added) {
        this.added = added;
    }

        public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public Post(String text, User author, Topic topic) {
        this.text = text;
        this.author = author;
        this.topic = topic;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Post)) {
            return false;
        }
        Post other = (Post) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jsforum.model.Post[ id=" + id + " ]";
    }
    
}
