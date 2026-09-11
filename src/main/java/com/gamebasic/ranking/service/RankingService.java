package com.gamebasic.ranking.service;

import com.gamebasic.ranking.client.RankingClient;
import com.gamebasic.ranking.dto.RankingResponse;
import com.gamebasic.ranking.dto.RankingSource;
import com.gamebasic.ranking.entity.CardType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class RankingService {

    private final RankingClient rankingClient;

    public RankingResponse getRankings() {

        RankingSource source = rankingClient.fetch();

        int excludedCount = 0;

        List<RankingSource.Record> candidates = new ArrayList<>();

        for(RankingSource.Record record : source.getRecords()){
            if (!"CLEARED".equals(record.getRun().getStatus())
                    || record.getRun().getClearedFloor() != 10) {
                continue;
            }

            if(!isValidRecord(record)){
                excludedCount++;
                continue;
            }

            candidates.add(record);
        }

        candidates.sort(
                Comparator.comparingInt(
                                (RankingSource.Record record) -> record.getRun().getDurationSeconds()
                        )
                        .thenComparing(
                                Comparator.comparingInt(
                                        (RankingSource.Record record) -> record.getRun().getFinalHp()
                                ).reversed()
                        )
                        .thenComparingLong(
                                (RankingSource.Record record) -> record.getId()
                        )
        );

        Set<String> seenPlayerIds = new HashSet<>();
        List<RankingSource.Record> finalRankings = new ArrayList<>();

        for (RankingSource.Record record : candidates) {

            String playerId = record.getPlayer().getId();

            if (seenPlayerIds.contains(playerId)) {
                continue;
            }

            seenPlayerIds.add(playerId);
            finalRankings.add(record);
        }

        List<RankingResponse.Entry> entries = new ArrayList<>();

        int rank = 1;

        for (RankingSource.Record record : finalRankings) {

            RankingResponse.Entry entry = new RankingResponse.Entry(
                    rank,
                    record.getPlayer().getName(),
                    record.getRun().getDurationSeconds(),
                    record.getRun().getFinalHp(),
                    record.getBossFight().getTotalTurns(),
                    record.getDeck().getCards().size()
            );

            entries.add(entry);
            rank++;
        }




        System.out.println("제외된 기록 수 = "+excludedCount);
        System.out.println("랭킹 후보 수 = "+candidates.size());

        return new RankingResponse(
                source.getMeta().getSeason().getId(),
                source.getRecords().size(),
                excludedCount,
                entries);
    }







    public boolean isValidRecord(RankingSource.Record record){

        RankingSource.Run run = record.getRun();

        if(run.getDurationSeconds() < run.getClearedFloor()*30){
            return false;
        }

        if(run.getFinalHp() < 1 || run.getFinalHp() > 99){
            return false;
        }

        if (record.getDeck() == null
                || record.getDeck().getCards() == null) {
            return false;
        }

        int deckSize = record.getDeck().getCards().size();

        if (deckSize < 9 || deckSize > 20) {
            return false;
        }

        if (record.getDeck().getSize() == null
                || record.getDeck().getSize() != deckSize) {
            return false;
        }


        for(RankingSource.Card card : record.getDeck().getCards()){

            if(card.getCardType() ==null){
                return false;
            }
            try{
                CardType.valueOf(card.getCardType());
            } catch (IllegalArgumentException e){
                return false;
            }

            if (card.getAcquiredFloor() == null
                    || card.getAcquiredFloor() < 0
                    || card.getAcquiredFloor() > 9){
                return false;
            }
        }

        RankingSource.BossFight bossFight = record.getBossFight();

        if (bossFight == null) {
            return false;
        }

        if (bossFight.getPhases() == null) {
            return false;
        }

        if (bossFight.getPhases().size() != 3) {
            return false;
        }

        if (!"THRONE".equals(bossFight.getPhases().get(0).getPhase())
                || !"UNBOUND".equals(bossFight.getPhases().get(1).getPhase())
                || !"ECLIPSE".equals(bossFight.getPhases().get(2).getPhase())) {
            return false;
        }

        int totalTurns = 0;

        for (RankingSource.BossPhase phase : bossFight.getPhases()) {
            if (phase.getTurns() < 1) {
                return false;
            }

            totalTurns += phase.getTurns();
        }

        if (bossFight.getTotalTurns() != totalTurns) {
            return false;
        }

        String finishingCard = bossFight.getFinishingCard();
        boolean exist = false;

        for(RankingSource.Card card : record.getDeck().getCards()){
            if(card.getCardType().equals(finishingCard)){
                exist = true;
                break;
            }
        }

        if(!exist){
            return false;
        }




        return true;
    }
}