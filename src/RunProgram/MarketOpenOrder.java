package RunProgram;

import com.oanda.v20.Context;
import com.oanda.v20.account.Account;
import com.oanda.v20.account.AccountID;
import com.oanda.v20.instrument.InstrumentPriceRequest;
import com.oanda.v20.instrument.InstrumentPriceResponse;
import com.oanda.v20.order.*;
import com.oanda.v20.pricing.ClientPrice;
import com.oanda.v20.pricing.PricingGetRequest;
import com.oanda.v20.pricing.PricingGetResponse;
import com.oanda.v20.pricing_common.Price;
import com.oanda.v20.pricing_common.PriceValue;
import com.oanda.v20.primitives.InstrumentName;
import com.oanda.v20.trade.*;
import com.oanda.v20.transaction.OrderFillTransaction;
import com.oanda.v20.transaction.TradeReduce;
import com.oanda.v20.transaction.TransactionID;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MarketOpenOrder {


    // main method to open and close positions
    public static void main(String[] args) {

    }

    /* Gets the current price of the asset */

    /* Open Market order
     *  Enter the instrument name in format "GBP/JPY"
     *  Enter the number of units to open. Positve for LONG, negative for SHORT positions.
     * */
    public static TransactionID Open(String instrumentName, double size) {
        TransactionID tradeID;
        String type;
        if (size > 0) {
            type = "LONG";
        } else {
            type = "SHORT";
        }
        Context ctx = Config.ctx;
        try {
            // market order with ticker == instrumentName, size == lots
            OrderCreateRequest request = new OrderCreateRequest(new AccountID("101-003-12890740-001"));
            MarketOrderRequest marketorderrequest = new MarketOrderRequest();
            marketorderrequest.setInstrument(instrumentName);
            marketorderrequest.setUnits(size);
            request.setOrder(marketorderrequest);
            OrderCreateResponse response = ctx.order.create(request);
            OrderFillTransaction transaction = response.getOrderFillTransaction();
            tradeID = transaction.getId();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("Opened " + type + size + " units of " + instrumentName + ".");
        System.out.println("Order ID: " + tradeID.toString());
//        System.out.println("Stop Loss Opened at " + 154.5);
//        System.out.println("Take profit Opened at " + 155.5);
        return tradeID;
    }
}



//            // open stop loss
//            TradeID curr_ID = new TradeID(tradeID);
//            OrderCreateRequest request2 = new OrderCreateRequest(new AccountID("101-003-12890740-001"));
//            StopLossOrderRequest stoplossorderrequest = new StopLossOrderRequest();
//            stoplossorderrequest.setTriggerCondition(OrderTriggerCondition.DEFAULT);
//            stoplossorderrequest.setTradeID(curr_ID);
//            stoplossorderrequest.setPrice(153);
//            stoplossorderrequest.setType(OrderType.STOP_LOSS);
//            request2.setOrder(stoplossorderrequest);
//            OrderCreateResponse response2 = ctx.order.create(request2);
//
//
//            // open take profit
//            OrderCreateRequest request3 = new OrderCreateRequest(new AccountID("101-003-12890740-001"));
//            TakeProfitOrderRequest takeprofitorderrequest = new TakeProfitOrderRequest();
//            takeprofitorderrequest.setTriggerCondition(OrderTriggerCondition.DEFAULT);
//            takeprofitorderrequest.setTradeID(curr_ID);
//            takeprofitorderrequest.setPrice(155.5);
//            takeprofitorderrequest.setType(OrderType.TAKE_PROFIT);
//            request3.setOrder(takeprofitorderrequest);
//            OrderCreateResponse response3 = ctx.order.create(request3);
