package br.com.onitama.model.response;

import io.swagger.v3.oas.annotations.media.Schema;

public class BattleSimpleResponse {
    @Schema(description = "Identificador da batalha.", example = "28c4a87a-95a0-4929-be13-e0961b60f3eb")
    private String id;

    public BattleSimpleResponse() {
    }

    public BattleSimpleResponse(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
