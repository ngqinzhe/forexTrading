package RunProgram;

import com.oanda.v20.Context;
import com.oanda.v20.pricing.ClientPrice;
import com.oanda.v20.pricing.PricingGetRequest;
import com.oanda.v20.pricing.PricingGetResponse;
import com.oanda.v20.pricing_common.PriceValue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CurrentPrice {

    public static PriceValue get(String instrumentName) {
        Context ctx = Config.ctx;
        List<String> instrument = new ArrayList<>(Arrays.asList(instrumentName));
        PriceValue curr_price;
        try {
            PricingGetRequest pricinggetrequest = new PricingGetRequest(Config.acc_ID, instrument);
            PricingGetResponse pricinggetresponse = ctx.pricing.get(pricinggetrequest);
            ClientPrice price = pricinggetresponse.getPrices().get(0);
            curr_price = price.getCloseoutAsk();
        } catch (Exception e) {
            throw new RuntimeException("Unable to retrieve prices. Program will terminate.");
        }
        return curr_price;
    }
}
