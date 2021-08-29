package Indicators;

import RunProgram.Config;
import com.oanda.v20.Context;
import com.oanda.v20.instrument.Candlestick;
import com.oanda.v20.instrument.CandlestickGranularity;
import com.oanda.v20.instrument.InstrumentCandlesRequest;
import com.oanda.v20.instrument.InstrumentCandlesResponse;
import com.oanda.v20.primitives.InstrumentName;
import java.util.List;

public class Volatility {
    public static double get(Long window, String instrumentName, CandlestickGranularity timeFrame)
    {
        Context ctx = Config.ctx;
        double volatility = 0;
        try {
            InstrumentCandlesRequest request = new InstrumentCandlesRequest(new InstrumentName(instrumentName));
            request.setCount(window);
            request.setGranularity(timeFrame);
            request.setPrice("M");
            InstrumentCandlesResponse response = ctx.instrument.candles(request);
            List<Candlestick> candles = response.getCandles();
            double movingAverage = MovingAverage.get(window, instrumentName, timeFrame);
            double variance = 0;
            for (Candlestick c : candles)
            {
                variance += Math.pow(c.getMid().getC().doubleValue() - movingAverage, 2);
            }
            volatility = Math.sqrt(variance / window);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return volatility;
    }
}
