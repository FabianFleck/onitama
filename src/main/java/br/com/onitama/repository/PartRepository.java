package br.com.onitama.repository;

import br.com.onitama.model.entity.PartEntity;
import br.com.onitama.model.entity.PlayerEntity;
import br.com.onitama.model.entity.PositionPart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartRepository extends JpaRepository<PartEntity, Long> {
    PartEntity findByPlayerAndPosition(PlayerEntity player, PositionPart position);
}
