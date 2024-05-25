package com.samanecorp.secureapp.dao;


import com.samanecorp.secureapp.config.PasswordUtil;
import com.samanecorp.secureapp.entities.UserEntity;
import com.samanecorp.secureapp.hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Optional;

public class LoginDao {

    Logger logger = LoggerFactory.getLogger(LoginDao.class);
//    public Optional<UserEntity> log(String email, String password) {
//        UserEntity result = null;
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        CriteriaBuilder builder = session.getCriteriaBuilder();
//        CriteriaQuery<UserEntity> criteria = builder.createQuery(UserEntity.class);
//        Root<UserEntity> user = criteria.from(UserEntity.class);
//
//        Predicate predicateEmail = builder.equal(user.get("email"), email);
//        Predicate predicatePassword = builder.equal(user.get("password"), password);
//        Predicate finalPredicate = builder.and(predicateEmail, predicatePassword);
//        criteria.select(user).where(finalPredicate);
//        try {
//            result = session.createQuery(criteria).uniqueResult();
//            logger.info("connexion réussie");
//        } catch (Exception e) {
//            logger.error("error:",e);
//        } finally {
//            session.close();
//        }
//        return Optional.ofNullable(result);
//
//    }

    public Optional<UserEntity> logException(String email, String password) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<UserEntity> criteria = builder.createQuery(UserEntity.class);
        Root<UserEntity> user = criteria.from(UserEntity.class);
        Predicate predicateEmail = builder.equal(user.get("email"), email);
        Predicate predicatePassword = builder.equal(user.get("password"), password);
        Predicate finalPredicate = builder.and(predicateEmail, predicatePassword);
        criteria.select(user).where(finalPredicate);
        return Optional.ofNullable(session.createQuery(criteria).uniqueResult());
    }
    public Optional<UserEntity> login (String email, String password) {

        if (email.equals("contact@samanecorporation.com") && password.equals("passer123@")) {
            UserEntity user = new UserEntity();
            user.setEmail(email);
            user.setPassword(password);

            return Optional.of(user) ;
        } else {
            return Optional.ofNullable(null);
        }

    }

    public Optional<UserEntity> loginUser(String email, String password) {
        UserEntity userEntity = null;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            userEntity = (UserEntity) session.createQuery("SELECT u FROM UserEntity u WHERE u.email = :email")
                    .setParameter("email", email)
                    .uniqueResult();
            if (userEntity != null && PasswordUtil.checkPassword(password, userEntity.getPassword())) {
                return Optional.ofNullable(userEntity);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                //	transaction.rollback();
            }
            logger.error("Error ", e);
        }
        return Optional.ofNullable(userEntity);
    }
}
