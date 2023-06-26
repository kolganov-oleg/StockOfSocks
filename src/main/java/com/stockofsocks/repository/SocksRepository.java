package com.stockofsocks.repository;

import com.stockofsocks.entity.SocksEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


@Repository
//@Component
public interface SocksRepository extends JpaRepository<SocksEntity, Long> {

    Optional<SocksEntity> findByColorAndCottonPart(String color, int cottonPart);

    int countByColorAndCottonPartAndQuantityGreaterThanEqual(String color, int cottonPart, int quantity);

    int countByColorAndCottonPartGreaterThan(String color, int cottonPart);

    int countByColorAndCottonPartLessThan(String color, int cottonPart);

    int countByColorAndCottonPartEquals(String color, int cottonPart);
}