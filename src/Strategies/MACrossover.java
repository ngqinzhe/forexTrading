package Strategies;

import Indicators.MovingAverage;
import RunProgram.Config;
import RunProgram.MarketCloseOrder;
import RunProgram.MarketOpenOrder;
import com.oanda.v20.Context;
import com.oanda.v20.account.AccountID;
import com.oanda.v20.instrument.CandlestickGranularity;
import com.oanda.v20.order.*;
import com.oanda.v20.primitives.InstrumentName;
import com.oanda.v20.trade.*;
import com.oanda.v20.transaction.OrderFillTransaction;
import com.oanda.v20.transaction.TradeReduce;
import com.oanda.v20.transaction.TransactionID;
import java.util.List;

public class MACrossover {

    /* Open Market order
     *  Enter the instrument name in format "GBP/JPY"
     *  Enter the number of units to open. Positve for LONG, negative for SHORT positions.
     * */
    public static void startTrade(String instrumentName, double size, CandlestickGranularity timeframe, Long smallWindow, Long bigWindow) {
        TransactionID tradeID = null;
        Context ctx = Config.ctx;

        /*
        * GET SMA RESULTS
        * So we will be using a 20SMA and 50SMA to calculate. When 20 crosses 50, we will enter a trade
        * So, lets get the market data for the moving averages first*/


        while (true) {
            double sma_small = MovingAverage.get(smallWindow, instrumentName, timeframe);
            double sma_large = MovingAverage.get(bigWindow, instrumentName, timeframe);
            int size_trade = 0;
            try {
                List<Trade> num_trades = ctx.trade.listOpen(Config.acc_ID).getTrades();
                size_trade = num_trades.size();
            } catch (Exception e) {
                throw new RuntimeException("Cannot retrieve transaction list.");
            }
            /*
            * Open the trade if there are no positions opened.
            * However, need to constantly check the moving averages.
            * Declare the MA again inside a loop to generate it*/
            if (size_trade == 0) {
                if (sma_small < sma_large) {
                    while (sma_small < sma_large){
                        double smaller_ma = MovingAverage.get(10L, instrumentName, timeframe);
                        double larger_ma = MovingAverage.get(20L, instrumentName, timeframe);
                        System.out.println(smaller_ma + " " + larger_ma);
                        if (smaller_ma >= larger_ma) break;
                    }
                    tradeID = MarketOpenOrder.Open(instrumentName, size);
                } else {
                    while (sma_small > sma_large){
                        double smaller_ma = MovingAverage.get(10L, instrumentName, timeframe);
                        double larger_ma = MovingAverage.get(20L, instrumentName, timeframe);
                        System.out.println(smaller_ma + " " + larger_ma);
                        if (smaller_ma <= larger_ma) break;
                    }
                    tradeID = MarketOpenOrder.Open(instrumentName, -size);
                }
            }
            else { // close the positions
                if (sma_small < sma_large) {
                    while (sma_small < sma_large){
                        double smaller_ma = MovingAverage.get(10L, instrumentName, timeframe);
                        double larger_ma = MovingAverage.get(20L, instrumentName, timeframe);
                        System.out.println(smaller_ma + " " + larger_ma);
                        if (smaller_ma >= larger_ma) break;
                    }
                    MarketCloseOrder.Close(tradeID);
                    break;
                }
                else {
                    while (sma_small > sma_large){
                        double smaller_ma = MovingAverage.get(10L, instrumentName, timeframe);
                        double larger_ma = MovingAverage.get(20L, instrumentName, timeframe);
                        System.out.println(smaller_ma + " " + larger_ma);
                        if (smaller_ma <= larger_ma) break;
                    }
                    MarketCloseOrder.Close(tradeID);
                    break;
                }
            }
        }
    }
}
