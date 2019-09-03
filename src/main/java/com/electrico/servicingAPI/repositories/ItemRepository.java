package com.electrico.servicingAPI.repositories;

import com.electrico.servicingAPI.model.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Long>{

    List<ItemEntity> findOneByAssortmentEntityId(Long assortmentId);
}
