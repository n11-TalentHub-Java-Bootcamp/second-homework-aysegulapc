package com.aysegulapc.springboot.entity;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;

/**
 * id
 * adi
 * soyadi
 * email
 * telefon
 * */
@Entity
@Table(name = "USERS")
@Component
public class User implements Serializable {
    @SequenceGenerator(name = "generator", sequenceName = "FIRST_ENTITY_ID_SEQ")
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "FIRSTNAME", nullable = false, length = 50)
    private String first_name;

    @Column(name = "LASTNAME", nullable = false, length = 50)
    private String last_name;

    @Column(name = "EMAIL", nullable = false, length = 50)
    private String email;

    @Column(name = "PHONE", nullable = false, length = 15)
    private Long phone;

    @Column(name = "USERNAME", nullable = false, length = 20)
    private String userName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return last_name;
    }

    public void setLastName(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getFirstName() {
        return first_name;
    }

    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return id == null ? "" : id.toString();
    }
}
