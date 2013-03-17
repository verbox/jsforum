/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jsforum.model;

import org.hibernate.Session;
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
        assertEquals("test",user.getPassword());
    }
}