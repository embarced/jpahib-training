package jpa.training;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jpa.training.shop.domain.User;

public class MainSE {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence
                .createEntityManagerFactory("jpaDatabase");

        // Create
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            User u1 = new User("ddevelop", "geheim", "dieter.develop@oio.de");
            User u2 = new User("gking", "geheim", "gavin.king@jboss.com");
            User u3 = new User("sebersole", "geheim", "steve.ebersol@jboss.com");
            em.persist(u1);
            em.persist(u2);
            em.persist(u3);

            tx.commit();
            em.close();

            // Read, Update
            em = emf.createEntityManager();
            em.getTransaction().begin();

            User u = em.find(User.class, u1.getId());
            u.setPassword("geheimer");

            em.getTransaction().commit();
            em.close();

            // Update (Merge)
            em = emf.createEntityManager();
            em.getTransaction().begin();

            u.setEmail("d.develop@oio.de");
            em.merge(u);

            em.getTransaction().commit();
            em.close();

            // Delete
            em = emf.createEntityManager();
            em.getTransaction().begin();

            u = em.getReference(User.class, u3.getId());
            em.remove(u);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}

