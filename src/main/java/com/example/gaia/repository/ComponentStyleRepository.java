// src/main/java/com/example/gaia/repository/ComponentStyleRepository.java
package com.example.gaia.repository;

import com.example.gaia.model.ComponentStyle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface ComponentStyleRepository extends JpaRepository<ComponentStyle, Long> {
    // 추가적인 쿼리 메소드가 필요하면 여기에 정의
}
