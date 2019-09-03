package com.electrico.servicingAPI.repositories;

import com.electrico.servicingAPI.model.ParameterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
@Repository
public interface ParameterRepository extends JpaRepository<ParameterEntity, Long> {

    List<ParameterEntity> findOneByItemEntityId(Long itemEntityId);
}
