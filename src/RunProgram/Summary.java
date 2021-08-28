package RunProgram;

import com.oanda.v20.Context;
import com.oanda.v20.trade.TradeSummary;
import com.oanda.v20.account.AccountID;
import com.oanda.v20.account.AccountSummary;

public class Summary {
    public static void main(String[] args) {
        Context ctx = new Context(
                "https://api-fxpractice.oanda.com",
                "81f207da969bcb19632b033923580a72-c8e6f4f118bda1d74646f56b71761734");
        TradeSummary trade_summary = new TradeSummary();

        try {
            AccountSummary summary = ctx.account.summary(
                    new AccountID("101-003-12890740-001")).getAccount();
            System.out.println(summary);
            System.out.println(trade_summary.getInstrument());
            System.out.println(trade_summary.getRealizedPL());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}