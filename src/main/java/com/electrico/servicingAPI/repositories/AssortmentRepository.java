package com.electrico.servicingAPI.repositories;

import com.electrico.servicingAPI.model.AssortmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AssortmentRepository extends JpaRepository<AssortmentEntity, Long>{

    Optional<AssortmentEntity> findOneByName(String name);
}
