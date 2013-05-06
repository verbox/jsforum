/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jsforum.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
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
    @OneToMany(mappedBy = "topic")
    @Cascade(CascadeType.ALL)
    private List<Post> postsSet;

    public List<Post> getPostsSet() {
        return postsSet;
    }

    public void setPostsSet(List<Post> postsSet) {
        this.postsSet = postsSet;
    }
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long topic_id;

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
    
}
