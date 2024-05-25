package com.samanecorp.secureapp.dao;

import com.samanecorp.secureapp.entities.UserEntity;
import com.samanecorp.secureapp.hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class UserImpl implements IUser{
    Transaction transaction = null;


    public List<UserEntity> liste() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        //Methode 1
        //return session.createCriteria(UserEntity.class).list();

        //Methode 2
        //https://www.baeldung.com/jpql-hql-criteria-query
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<UserEntity> cr = cb.createQuery(UserEntity.class);
        Root<UserEntity> root = cr.from(UserEntity.class);
        cr.select(root);

        List<UserEntity> results = session.createQuery(cr).getResultList();
        session.close();
        return results;
    }

    @Override
    public boolean addUser(UserEntity user) {
        return false;
    }

    @Override
    public boolean updateUser(UserEntity user) {
        return false;
    }

    @Override
    public boolean deleteUser(long id) {
        return false;
    }

    public boolean add(UserEntity u) {

        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(u);
            transaction.commit();
            return true;
        } catch (Exception e2) {
            return false;
        }
    }


    public boolean delete(long mat) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.delete(session.get(UserEntity.class, mat));
            transaction.commit();
            return true;
        } catch (Exception e2) {
            return false;
        }
    }


    public boolean update(UserEntity e) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.merge(e);
            transaction.commit();
            return true;
        } catch (Exception e2) {
            return false;
        }
    }


    public UserEntity get(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return (UserEntity) session.get(UserEntity.class, id);
    }
}
