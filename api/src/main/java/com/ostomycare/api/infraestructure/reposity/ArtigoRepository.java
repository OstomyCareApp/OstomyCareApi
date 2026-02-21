package com.ostomycare.api.infraestructure.reposity;

import com.ostomycare.api.infraestructure.entity.ArtigoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtigoRepository extends JpaRepository<ArtigoEntity, Long> {
}
