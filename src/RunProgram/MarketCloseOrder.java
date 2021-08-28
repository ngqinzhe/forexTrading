package RunProgram;

import com.oanda.v20.Context;
import com.oanda.v20.trade.TradeCloseRequest;
import com.oanda.v20.trade.TradeCloseResponse;
import com.oanda.v20.trade.TradeSpecifier;
import com.oanda.v20.transaction.OrderFillTransaction;
import com.oanda.v20.transaction.TradeReduce;
import com.oanda.v20.transaction.TransactionID;

import java.util.List;

public class MarketCloseOrder {

    public static void Close(TransactionID tradeID) {
        Context ctx = Config.ctx;

        try {
            TradeCloseResponse response = ctx.trade.close(
                    new TradeCloseRequest(Config.acc_ID, new TradeSpecifier(tradeID.toString())));
            OrderFillTransaction transaction = response.getOrderFillTransaction();
            List<TradeReduce> trades = transaction.getTradesClosed();
            if (trades.size() != 1) throw new RuntimeException("Only 1 trade expected to close");
            TradeReduce trade = trades.get(0);
            if (!trade.getTradeID().equals(tradeID)) throw new RuntimeException("Closed the wrong trade");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("Closed trade: " + tradeID.toString() + ". Program will now terminate.");
    }
}
