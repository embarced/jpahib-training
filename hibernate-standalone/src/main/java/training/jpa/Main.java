package training.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import training.jpa.domain.Person;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence
                .createEntityManagerFactory("jpaDatabase");

        // Create
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Person p1 = new Person("Dieter", "Develop", "dieter.develop@oio.de");
            Person p2 = new Person("Gavin", "King", "gavin.king@jboss.com");
            Person p3 = new Person("Steve", "Ebersole", "steve.ebersol@jboss.com");
            em.persist(p1);
            em.persist(p2);
            em.persist(p3);

            tx.commit();
            em.close();

            // Read, Update
            em = emf.createEntityManager();
            em.getTransaction().begin();

            System.out.println("vor getReference()");
            Person p = em.getReference(Person.class, p3.getId());
            System.out.println("nach getReference()");
            p.setLastname("geheimer");

            em.getTransaction().commit();
            em.close();

            // Update (Merge)
            em = emf.createEntityManager();
            em.getTransaction().begin();

            p.setEmail("d.develop@oio.de");
            Person mergedUser = em.merge(p);
            System.out.println(p == mergedUser);

            em.getTransaction().commit();
            em.close();

            // Delete
            em = emf.createEntityManager();
            em.getTransaction().begin();

            p = em.getReference(Person.class, p3.getId());
            em.remove(p);
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
