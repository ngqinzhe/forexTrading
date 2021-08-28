package Strategies;

import Indicators.MovingAverage;
import RunProgram.Config;
import RunProgram.CurrentPrice;
import RunProgram.MarketCloseOrder;
import RunProgram.MarketOpenOrder;
import com.oanda.v20.Context;
import com.oanda.v20.pricing_common.PriceValue;
import com.oanda.v20.instrument.CandlestickGranularity;
import com.oanda.v20.transaction.TransactionID;

public class MACross {

    public static void startTrade(String instrumentName, double size, CandlestickGranularity timeframe, Long window) {
        TransactionID tradeid = null;
        Context ctx = Config.ctx;
        int count = 0;
        /* Get the current price of the asset and then check for the cross over the 200MA, close when the price crosses back*/
        while (true) {
            double curr_price = CurrentPrice.get(instrumentName).doubleValue();
            double moving_average = MovingAverage.get(window, instrumentName, timeframe);

            if (curr_price > moving_average) {              // short position downwards
                while (curr_price > moving_average) {
                    double curr_p = CurrentPrice.get(instrumentName).doubleValue();
                    double curr_ma = MovingAverage.get(window, instrumentName, timeframe);
                    System.out.println("Price: " + curr_p + " MA: " + curr_ma);
                    if (curr_p <= curr_ma) break;
                }
                tradeid = MarketOpenOrder.Open(instrumentName, -size);
                count = 1;
                break;
            }
            else {
                while (curr_price <= moving_average) {          // long position
                    double curr_p = CurrentPrice.get(instrumentName).doubleValue();
                    double curr_ma = MovingAverage.get(window, instrumentName, timeframe);
                    System.out.println("Price: " + curr_p + " MA: " + curr_ma);
                    if (curr_p > curr_ma) break;
                }
                tradeid = MarketOpenOrder.Open(instrumentName, size);
                count = 2;
                break;
            }
        }

        while (true) {
            double curr_price = CurrentPrice.get(instrumentName).doubleValue();
            double moving_average = MovingAverage.get(window, instrumentName, timeframe);
            if (count == 1) {
                while (true) {
                    double curr_p = CurrentPrice.get(instrumentName).doubleValue();
                    double curr_ma = MovingAverage.get(window, instrumentName, timeframe);
                    System.out.println("Price: " + curr_p + " MA: " + curr_ma);
                    if (curr_p > curr_ma) break;
                }
                MarketCloseOrder.Close(tradeid);
                break;
            }
            else {
                while (true) {
                    double curr_p = CurrentPrice.get(instrumentName).doubleValue();
                    double curr_ma = MovingAverage.get(window, instrumentName, timeframe);
                    System.out.println("Price: " + curr_p + " MA: " + curr_ma);
                    if (curr_p <= curr_ma) break;
                }
                MarketCloseOrder.Close(tradeid);
                break;
            }
        }
    }
}
