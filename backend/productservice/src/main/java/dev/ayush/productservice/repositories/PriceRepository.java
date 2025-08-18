package dev.ayush.productservice.repositories;

import dev.ayush.productservice.models.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository("pricerepository")
public interface PriceRepository
extends JpaRepository<Price, UUID> {

    @Override
    Price save(Price entity);
}
