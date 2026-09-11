package com.gamebasic.ranking.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class RankingSource {

    private Meta meta;
    private List<Record> records;


    @Getter
    public static class Meta {
        private Season season;
        private String generatedAt;
        private Integer schemaVersion;
        private Integer totalRecords;
    }


    @Getter
    public static class Season {
        private String id;
        private String name;
        private String startsAt;
        private String endsAt;
    }


    @Getter
    public static class Record {
        private Long id;
        private String submittedAt;
        private Client client;
        private Player player;
        private Run run;
        private BossFight bossFight;
        private Deck deck;
    }


    @Getter
    public static class Client {
        private String version;
        private String platform;
        private String locale;
    }


    @Getter
    public static class Player {
        private String id;
        private String name;
        private String region;
        private List<String> tags;
    }


    @Getter
    public static class Run {
        private String seed;
        private String status;
        private Integer clearedFloor;
        private Integer durationSeconds;
        private Integer finalHp;
        private List<Floor> floors;
    }


    @Getter
    public static class Floor {
        private Integer floor;
        private String enemy;
        private Integer turns;
        private Integer hpAfter;
        private List<Reward> rewards;
    }


    @Getter
    public static class Reward {
        private List<String> offered;
        private String picked;
    }


    @Getter
    public static class BossFight {
        private List<BossPhase> phases;
        private String finishingCard;
        private Integer totalTurns;
    }


    @Getter
    public static class BossPhase {
        private String phase;
        private Integer turns;
        private Integer damageTaken;
    }


    @Getter
    public static class Deck {
        private Integer size;
        private List<Card> cards;
    }


    @Getter
    public static class Card {
        private String cardType;
        private Integer acquiredFloor;
    }
}