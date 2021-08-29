package Indicators;

import com.oanda.v20.Context;
import RunProgram.Config;
import com.oanda.v20.instrument.*;
import com.oanda.v20.primitives.InstrumentName;
import java.util.List;

public class MovingAverage {

    public static double get(Long window, String instrumentName, CandlestickGranularity timeframe) {
        Context ctx = Config.ctx;
        double movingAverage = 0;
        try {
            InstrumentCandlesRequest request = new InstrumentCandlesRequest(new InstrumentName(instrumentName));
            request.setCount(window);
            request.setGranularity(timeframe);
            request.setPrice("M");
            InstrumentCandlesResponse response = ctx.instrument.candles(request);
            // Retrieving candles
            List<Candlestick> candles = response.getCandles();
            // getting the SMA
            double sum = 0;
            for (Candlestick c : candles) {
                sum += c.getMid().getC().doubleValue();
            }
            movingAverage = sum / window;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return movingAverage;
    }

    public static void main(String[] args) {
        // Test run
        System.out.println(get(50L, "GBP_JPY", CandlestickGranularity.M15));
    }
}
