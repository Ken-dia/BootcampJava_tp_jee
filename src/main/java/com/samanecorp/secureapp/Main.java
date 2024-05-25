package com.samanecorp.secureapp;

import com.samanecorp.secureapp.entities.UserEntity;
import com.samanecorp.secureapp.hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Main {
    public static void main(String[] args) {
        // Initialiser la SessionFactory
        Session session = HibernateUtil.getSessionFactory().openSession();

        // Commencer une transaction
        Transaction transaction = session.beginTransaction();

        // Exemple d'opération de sauvegarde
        UserEntity user = new UserEntity();
        user.setFirstName("Hamidou");
        user.setLastName("Dia");
        user.setEmail("hamidou.dia@gmail.com");
        user.setPassword("testPass");

        session.save(user);

        // Commit de la transaction
        transaction.commit();

        // Fermer la session
        session.close();

        // Fermer la SessionFactory (optionnel, typiquement à la fin de l'application)
        HibernateUtil.getSessionFactory().close();
    }
}
