package dev.ayush.productservice.inheritancedemo.singletable;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Setter
@Getter
@Entity(name = "st_ta")
@DiscriminatorValue("2")
public class TA extends User {
    private Date startTime;
    private Date endTime;

}
