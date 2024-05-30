package com.bookcode.entity;

import jakarta.persistence.*;
@Entity
@Table (name="user")
//增加此行语句来指定所映射的表名
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;

    protected User() {
    }

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format("User[id=%d,firstName='%s',lastName='%s']", id, firstName, lastName);
    }
}
