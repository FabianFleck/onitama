package br.com.onitama.repository;

import br.com.onitama.model.entity.BattleEntity;
import br.com.onitama.model.entity.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BattleRepository extends JpaRepository<BattleEntity, String> {
    BattleEntity findByPlayer1OrPlayer2(PlayerEntity player1, PlayerEntity player2);

    // Busca de batalhas por username do usuário
    @Query("SELECT b FROM BattleEntity b WHERE b.player1.user.username LIKE :username OR b.player2.user.username LIKE :username")
    List<BattleEntity> findByUsername(@Param("username") String username);

    // Busca de batalhas por ID do usuário
    @Query("SELECT b FROM BattleEntity b WHERE b.player1.user.id = :userId OR b.player2.user.id = :userId")
    List<BattleEntity> findByUserId(@Param("userId") Long userId);

    @Query("SELECT b FROM BattleEntity b WHERE b.player1.id = :id OR b.player2.id = :id")
    BattleEntity findByPlayerId(@Param("id") Long id);
}