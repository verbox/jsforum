/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jsforum.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 *
 * @author piotr
 */
@Entity
@Table(name = "topics")
public class Topic implements Serializable {
    @OneToMany(mappedBy = "topic", fetch = FetchType.EAGER)
    @Cascade({CascadeType.SAVE_UPDATE})
    private Set<Post> postsSet;

    public Set<Post> getPostsSet() {
        return postsSet;
    }

    public void setPostsSet(Set<Post> postsSet) {
        this.postsSet = postsSet;
    }
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long topic_id;

    public Topic(String topicName) {
        this();
        this.topicName = topicName;
    }

    public Topic() {
        postsSet = new HashSet<Post>();
    }

    @Column(name = "name")
    private String topicName;

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }
    
    
    public Long getId() {
        return topic_id;
    }

    public void setId(Long id) {
        this.topic_id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (topic_id != null ? topic_id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Topic)) {
            return false;
        }
        Topic other = (Topic) object;
        if ((this.topic_id == null && other.topic_id != null) || (this.topic_id != null && !this.topic_id.equals(other.topic_id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jsforum.model.Topic[ id=" + topic_id + " ]";
    }
    
    //różne fajne metody
    public int getPostsCount() {
        return postsSet.size();
    }
    
}
