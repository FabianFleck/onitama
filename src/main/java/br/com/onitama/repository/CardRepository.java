package br.com.onitama.repository;

import br.com.onitama.model.entity.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CardRepository extends JpaRepository<CardEntity, Long> {

    @Query("SELECT c FROM CardEntity c ORDER BY c.name ASC")
    List<CardEntity> findAllOrderedByName();
}
