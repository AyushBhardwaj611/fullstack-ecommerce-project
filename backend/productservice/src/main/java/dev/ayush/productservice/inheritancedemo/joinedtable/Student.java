package dev.ayush.productservice.inheritancedemo.joinedtable;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity(name = "jt_student")
@PrimaryKeyJoinColumn(name = "userid")
public class Student extends User {
    private double psp;
    private String batch_name;
}
