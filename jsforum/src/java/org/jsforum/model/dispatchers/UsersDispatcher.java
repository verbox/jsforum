/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jsforum.model.dispatchers;

import java.io.Serializable;
import java.security.MessageDigest;
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

    public static String getMD5(String string) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(string.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 255) | 256).substring(1, 3));
            }
            return sb.toString();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
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
        //w registerUserBean już jest hasło w md5
        user.setPlainPassword(registerUserBean.getPassword());
        //TODO sprawdzić, czy nie ma już usera o danym loginie
        session.save(user);
        session.getTransaction().commit();
        return true;
    }
    
}
