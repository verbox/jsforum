/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jsforum.model.dispatchers;

import java.io.Serializable;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.jsforum.beans.userbeans.RegisterUserBean;
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
        session.close();
        return findedUsers.get(0);
    }
    
    public boolean addUser(RegisterUserBean registerUserBean) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        User user = new User(registerUserBean.getUsername(),registerUserBean.getPassword(),
                registerUserBean.getEmail());
        //TODO sprawdzić, czy nie ma już usera o danym loginie
        session.save(user);
        session.getTransaction().commit();
        return true;
    }
    
}
