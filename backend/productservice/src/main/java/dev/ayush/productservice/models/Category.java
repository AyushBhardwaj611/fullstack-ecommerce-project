package dev.ayush.productservice.models;

import jakarta.annotation.security.DenyAll;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.repository.cdi.Eager;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "category")
public class Category extends BaseModel{

    private String name;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "category")
    private List<Product> productsss;

}
