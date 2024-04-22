package br.com.onitama.service;

import br.com.onitama.model.entity.PartEntity;
import br.com.onitama.model.entity.PlayerEntity;
import br.com.onitama.model.entity.PositionPart;
import br.com.onitama.model.enumeration.PartTypeEnum;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static br.com.onitama.model.enumeration.PartTypeEnum.DISCIPLE;

@Service
public class PartService {

    public List<PartEntity> initializePartsForPlayer(PlayerEntity player, int startLine) {
        List<PartEntity> parts = new ArrayList<>();
        for (int column = 1; column <= 5; column++) {
            PartEntity part = new PartEntity();
            part.setPlayer(player);
            part.setPosition(new PositionPart(startLine, column));
            part.setPartTypeEnum(DISCIPLE);
            parts.add(part);
        }
        parts.get(2).setPartTypeEnum(PartTypeEnum.MASTER);
        return parts;
    }
}
