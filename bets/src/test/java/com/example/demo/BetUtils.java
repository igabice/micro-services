package com.example.demo;

import com.example.demo.model.Bet;
import com.example.demo.rest.request.BetItem;

import java.util.Arrays;
import java.util.List;

public class BetUtils {

    private BetUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static List<Bet> createBetDatas() {
        Bet bet1 = new Bet();

        bet1.setName("Tallinn vs Taru");
        bet1.setAwayOdd(3.1);
        bet1.setDrawOdd(2.1);
        bet1.setHomeOdd(2.0);



        Bet bet2 = new Bet();
        bet2.setName("Spain vs Brazil");
        bet2.setAwayOdd(2.1);
        bet2.setDrawOdd(1.8);
        bet2.setHomeOdd(1.5);

        Bet bet3 = new Bet();
        bet1.setName("Portugal vs Suuth Africa");
        bet2.setAwayOdd(1.1);
        bet2.setDrawOdd(1.4);
        bet2.setHomeOdd(1.7);

        return Arrays.asList(bet1, bet2, bet3);
    }

    public static List<BetItem> createBetItems() {
        BetItem BetItem1 = new BetItem();

        BetItem1.setPosition("home");
        BetItem1.setOdd(1.5);
        BetItem1.setBetId(1L);



        BetItem BetItem2 = new BetItem();
        BetItem2.setPosition("away");
        BetItem2.setOdd(1.5);
        BetItem2.setBetId(2L);

        BetItem BetItem3 = new BetItem();
        BetItem3.setPosition("draw");
        BetItem3.setOdd(1.5);
        BetItem3.setBetId(2L);

        return Arrays.asList(BetItem1, BetItem2, BetItem3);
    }
}