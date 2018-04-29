package com.cnu.blackjack;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThan;


public class EvaluatorTest {
    Player player;
    private Map<String, Player> playerMap = new HashMap<>();
    @Test
    public void 게임초기화시_모든플레이어는_2장의카드를_받는다() {
        Player player1 = new Player(1000, new Hand(new Deck(1)));
        playerMap.put("Lee", player1);
        Evaluator evaluator = new Evaluator(playerMap);
        assertThat(player1.getHand().getCardList().size(), is(2));
    }

    @Test
    public void 각_플레이어는_16이하면_히트한다() {
        Player player1 = new Player(1000, new Hand(new Deck(1)));
        playerMap.put("Lee", player1);
        Evaluator evaluator = new Evaluator(playerMap);
        evaluator.start();
        assertThat(player1.getHand().getCardList().size(), greaterThanOrEqualTo(3));
    }

    @Test
    public void 블랙잭이나오면_2배로_보상받고_해당_플레이어의_턴은_끝난다() {
        Player player1 = new Player(1000, new Hand(new Deck(1)));
        playerMap.put("Lee", player1);
        Evaluator evaluator = new Evaluator(playerMap);
        player1.placeBet(20);
        evaluator.start();
        assertThat(player1.getBalance(),is(1020));
    }

    @Test
    public void 각_플레이어는_17이상이면_스테이한다() {
        Player player1 = new Player(1000, new Hand(new Deck(1)));
        playerMap.put("Lee", player1);
        Evaluator evaluator = new Evaluator(playerMap);
        evaluator.start();
        assertThat(evaluator.Sum(player1),greaterThanOrEqualTo(17));
    }
}
