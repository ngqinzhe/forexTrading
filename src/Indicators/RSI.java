package Indicators;
import java.util.List;
import RunProgram.Config;
import com.oanda.v20.Context;
import com.oanda.v20.instrument.Candlestick;
import com.oanda.v20.instrument.CandlestickGranularity;
import com.oanda.v20.instrument.InstrumentCandlesRequest;
import com.oanda.v20.instrument.InstrumentCandlesResponse;
import com.oanda.v20.primitives.InstrumentName;

public class RSI {
    public static double get(Long window, String instrumentName, CandlestickGranularity timeFrame)
    {
        Context ctx = Config.ctx;
        double relativeStrengthIndex = 0;
        try
        {
            InstrumentCandlesRequest request = new InstrumentCandlesRequest(new InstrumentName(instrumentName));
            request.setCount(window);
            request.setGranularity(timeFrame);
            request.setPrice("M");
            InstrumentCandlesResponse response = ctx.instrument.candles(request);
            List<Candlestick> candles = response.getCandles();

            double relativeStrength = 0;
            double previous = 0;
            double current = 0;
            double upMoves = 0;
            double downMoves = 0;

            // getting UPMOVES and DOWNMOVES
            for (Candlestick c : candles)
            {
                if (previous == 0) previous = c.getMid().getC().doubleValue();
                current = c.getMid().getC().doubleValue();
                if (current >= previous)
                {
                    upMoves += current - previous;
                }
                else
                {
                    downMoves += -(current - previous);
                }
                previous = current;
            }
            relativeStrength = (upMoves / window) / (downMoves / window);
            relativeStrengthIndex = 1 - 100 / (1 + relativeStrength);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return relativeStrengthIndex;
    }
}
