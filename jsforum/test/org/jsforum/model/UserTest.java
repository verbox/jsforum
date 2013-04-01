/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jsforum.model;

import java.io.Serializable;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.jsforum.hibernate.HibernateUtil;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author piotr
 */
public class UserTest {
    
    public UserTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getUserId method, of class User.
     */
    @Test
    public void testGetUserId() {
        //pobierzemy jakiegoś usera
        int userId = 1;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        User user = (User) session.load(User.class, 1);
        System.out.println(user.getUsername());
        //u mnie pod id=1 jest Bonifacy Stonoga
        assertEquals("Bonifacy Stonoga", user.getUsername());
        //poprawka 1.04.13 - korzystam z MD5
        assertEquals("098f6bcd4621d373cade4e832627b4f6",user.getPassword());
        session.close();
    }
    
    @Test
    public void testAddAndRemoveUser() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        User user = new User("Jan K", "test", "abc@wp.pl");
        session.save(user);
        List<User> findedUsers = session.createCriteria(User.class).add(Restrictions.like("username","Jan K")).list();
        assertEquals(findedUsers.get(0).getUsername(),"Jan K");
        session.disconnect();
    }
}