package dev.ayush.productservice.inheritancedemo.tableperclass;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("tpc_studentRepo")
public interface StudentRepository extends JpaRepository<dev.ayush.productservice.inheritancedemo.tableperclass.Student, Long> {

    @Override
    <S extends Student> S save(S entity);
}
