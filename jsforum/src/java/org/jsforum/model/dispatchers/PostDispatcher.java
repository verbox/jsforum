/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jsforum.model.dispatchers;

import org.hibernate.Session;
import org.jsforum.hibernate.HibernateUtil;
import org.jsforum.model.Post;
import org.jsforum.model.Topic;

/**
 *
 * @author piotr
 */
public class PostDispatcher {
    public void addPost(Post post) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(post);
        session.getTransaction().commit();
    }
    
    public Topic getTopic(long topicId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Topic result = (Topic) session.get(Topic.class,topicId);
        return result;
    }
    
    public boolean saveTopic(Topic topic) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(topic);
        session.getTransaction().commit();
        return true;
    }
}
