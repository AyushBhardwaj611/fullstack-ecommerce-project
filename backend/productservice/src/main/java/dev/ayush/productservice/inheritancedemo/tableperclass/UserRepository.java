package dev.ayush.productservice.inheritancedemo.tableperclass;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("tpc_userRepo")
public interface UserRepository extends JpaRepository<dev.ayush.productservice.inheritancedemo.tableperclass.User, Long> {
    @Override
    <S extends User> S save(S entity);
}
