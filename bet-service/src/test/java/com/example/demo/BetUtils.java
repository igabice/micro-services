package com.example.demo;

import com.example.demo.dto.BetData;
import com.example.demo.rest.request.BetItem;

import java.util.Arrays;
import java.util.List;

public class BetUtils {

    private BetUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static List<BetData> createBetDatas() {
        BetData betData1 = new BetData();

        betData1.setName("Tallinn vs Taru");
        betData1.setAwayOdd(3.1);
        betData1.setDrawOdd(2.1);
        betData1.setHomeOdd(2.0);



        BetData betData2 = new BetData();
        betData2.setName("Spain vs Brazil");
        betData2.setAwayOdd(2.1);
        betData2.setDrawOdd(1.8);
        betData2.setHomeOdd(1.5);

        BetData betData3 = new BetData();
        betData1.setName("Portugal vs Suuth Africa");
        betData2.setAwayOdd(1.1);
        betData2.setDrawOdd(1.4);
        betData2.setHomeOdd(1.7);

        return Arrays.asList(betData1, betData2, betData3);
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