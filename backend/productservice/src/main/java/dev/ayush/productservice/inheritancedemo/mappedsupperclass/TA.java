package dev.ayush.productservice.inheritancedemo.mappedsupperclass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.sql.Time;

@Setter
@Getter
@Entity(name = "msc_ta")
public class TA extends User{
    private Date startTime;
    private Date endTime;

}
