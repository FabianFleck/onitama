package br.com.onitama.model.entity;

import br.com.onitama.model.enumeration.PartTypeEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "part")
public class PartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "player_id")
    @JsonIgnore
    private PlayerEntity player;

    @Embedded
    private PositionPart position;

    @Enumerated(EnumType.STRING)
    private PartTypeEnum partTypeEnum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PlayerEntity getPlayer() {
        return player;
    }

    public void setPlayer(PlayerEntity player) {
        this.player = player;
    }

    public PositionPart getPosition() {
        return position;
    }

    public void setPosition(PositionPart position) {
        this.position = position;
    }

    public PartTypeEnum getPartTypeEnum() {
        return partTypeEnum;
    }

    public void setPartTypeEnum(PartTypeEnum partTypeEnum) {
        this.partTypeEnum = partTypeEnum;
    }
}
