package jpa.training;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class MainSE {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence
                .createEntityManagerFactory("jpaDatabase");
    }
}
