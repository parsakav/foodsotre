package org.example.sotre.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.annotation.processing.Generated;
import java.util.Date;

@Entity

public class Log {

    @GeneratedValue
    @Id
    private int id;

    private String email;

    private String action;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;


    @PrePersist
    protected void onCreate() {

        date = new Date();
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
