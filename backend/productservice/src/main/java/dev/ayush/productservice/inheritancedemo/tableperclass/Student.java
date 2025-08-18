package dev.ayush.productservice.inheritancedemo.tableperclass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity(name = "tpc_student")
public class Student extends User {
    private double psp;
    private String batch_name;
}
