package br.com.onitama.repository;

import br.com.onitama.model.entity.BattleEntity;
import br.com.onitama.model.entity.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BattleRepository extends JpaRepository<BattleEntity, String> {
    BattleEntity findByPlayer1OrPlayer2(PlayerEntity player1, PlayerEntity player2);
}