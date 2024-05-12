package br.com.onitama.mapper;

import br.com.onitama.model.entity.BattleEntity;
import br.com.onitama.model.response.BattleResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BattleMapper {

    @Mapping(source = "entity.player1.user.name", target = "player1.name")
    @Mapping(source = "entity.player1.user.username", target = "player1.username")
    @Mapping(source = "entity.player2.user.name", target = "player2.name")
    @Mapping(source = "entity.player2.user.username", target = "player2.username")
    BattleResponse toBattleResponse(BattleEntity entity);
}
