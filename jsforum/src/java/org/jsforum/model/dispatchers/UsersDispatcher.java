/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jsforum.model.dispatchers;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.jsforum.hibernate.HibernateUtil;
import org.jsforum.model.User;

/**
 *
 * @author piotr
 */
public class UsersDispatcher {
    
    public User getUser(String username, String md5password) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.like("username",username)); //TODO nieładne - chyba było coś z criteria i spójnikiem AND
        criteria.add(Restrictions.like("password",md5password));
        List<User> findedUsers = criteria.list();
        if (findedUsers.size()>1) {
            //TODO jakiś zgrabny wyjątek
        }
        if (findedUsers.isEmpty()) return null;
        return findedUsers.get(0);
    }
    
}
