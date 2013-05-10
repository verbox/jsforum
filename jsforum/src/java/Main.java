
import org.hibernate.Session;
import org.jsforum.hibernate.HibernateUtil;
import org.jsforum.model.Post;
import org.jsforum.model.Topic;
import org.jsforum.model.User;
import org.jsforum.model.dispatchers.PostDispatcher;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author piotr
 */
public class Main {
    public static void main(String[] args) {
        /*
         * 
         */
        
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Topic superTopic = new Topic("test");
        User superUser = new User("x", "y", "z");
        Post post = new Post("tresc postu", superUser, superTopic);
        session.save(superUser);
        session.save(superTopic);
        session.save(post);
        session.getTransaction().commit();
        PostDispatcher pd = new PostDispatcher();
        pd.getTopic(1);
    }
}
