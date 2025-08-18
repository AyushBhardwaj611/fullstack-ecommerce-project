package dev.ayush.productservice.inheritancedemo.tableperclass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Setter
@Getter
@Entity(name = "tpc_ta")
public class TA extends User {
    private Date startTime;
    private Date endTime;

}
