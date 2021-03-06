/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jsforum.model.dispatchers;

import java.util.List;
import org.hibernate.Query;
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
        session.getTransaction().commit();
        return result;
    }
    
    public List<Topic> getAllTopics() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        
        Query q = session.createQuery("from Topic");
        List<Topic> result = q.list();
        session.getTransaction().commit();
        return result;
    }
    
    public boolean saveTopic(Topic topic) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(topic);
        session.getTransaction().commit();
        return true;
    }
    
    //return true jak wywalił się też temat
    public boolean deletePost(Post post) {
        long topicId = post.getTopic().getId();
        
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.delete(post);
        session.getTransaction().commit();
        
        Topic topic = getTopic(topicId);
        if (topic.getPostsCount()==0) {
            deleteTopic(topic);
            return true;
        }
        return false;
    }
    
    public boolean deleteTopic(Topic topic) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.delete(topic);
        session.getTransaction().commit();
        return true;
    }
}
