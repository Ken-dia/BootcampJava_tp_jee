package com.samanecorp.secureapp.entities;

import com.samanecorp.secureapp.config.PasswordUtil;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="users")
public class UserEntity implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name", nullable = false, length = 255)
    private String firstName;
    @Column(name="last_name", nullable = false, length = 200)
    private String lastName;
    @Column(name="email", nullable = false, unique = true, length = 200)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;

    public UserEntity() {
        super();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = PasswordUtil.hashPassword(password);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
