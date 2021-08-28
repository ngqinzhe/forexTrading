package RunProgram;
import Strategies.MACross;
import Strategies.MACrossover;
import com.oanda.v20.instrument.CandlestickGranularity;

public class Startup {
    public static void main(String[] args) {
        MACross.startTrade("EUR_USD", 10, CandlestickGranularity.S5, 20L);
        MACrossover.startTrade("EUR_USD", 10, CandlestickGranularity.S5, 10L, 20L);
    }
}
