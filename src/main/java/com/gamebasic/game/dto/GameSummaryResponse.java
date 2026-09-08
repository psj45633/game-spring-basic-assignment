package com.gamebasic.game.dto;

import com.gamebasic.game.entity.GamePhase;
import com.gamebasic.game.entity.GameStatus;
import jakarta.persistence.Column;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GameSummaryResponse {

    private Long id;
    private String playerName;
    private Integer currentFloor;
    private Integer currentHp;
    private GamePhase phase;
    private GameStatus status;
    private Integer deckSize;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public GameSummaryResponse(
            Long id,
            String playerName,
            Integer currentFloor,
            Integer currentHp,
            GamePhase phase,
            GameStatus status,
            Integer deckSize,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        this.id = id;
        this.playerName = playerName;
        this.currentFloor = currentFloor;
        this.currentHp = currentHp;
        this.phase = phase;
        this.status = status;
        this.deckSize = deckSize;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
