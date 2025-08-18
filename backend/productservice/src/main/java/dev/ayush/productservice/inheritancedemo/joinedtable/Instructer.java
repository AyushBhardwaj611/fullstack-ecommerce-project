package dev.ayush.productservice.inheritancedemo.joinedtable;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "jt_instructer")
@PrimaryKeyJoinColumn(name = "userid")
public class Instructer extends User {

    private double AvgRating;
}
