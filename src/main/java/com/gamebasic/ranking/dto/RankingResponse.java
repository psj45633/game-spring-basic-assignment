package com.gamebasic.ranking.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class RankingResponse {

    private String season;
    private Integer totalRecords;
    private Integer excludedCount;
    private List<Entry> entries;

    @Getter
    @AllArgsConstructor
    public static class Entry{

        private int rank;
        private String playerName;
        private int clearTimeSeconds;
        private int remainingHp;
        private int bossTurns;
        private int deckSize;
    }
}
