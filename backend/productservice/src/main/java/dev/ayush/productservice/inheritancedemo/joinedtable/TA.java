package dev.ayush.productservice.inheritancedemo.joinedtable;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Setter
@Getter
@Entity(name = "jt_ta")
@PrimaryKeyJoinColumn(name = "userid")
public class TA extends User {
    private Date startTime;
    private Date endTime;

}
