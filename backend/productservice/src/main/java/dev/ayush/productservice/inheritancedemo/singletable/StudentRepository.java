package dev.ayush.productservice.inheritancedemo.singletable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("st_studentRepo")
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Override
    <S extends Student> S save(S entity);
}
