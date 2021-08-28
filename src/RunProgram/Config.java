package RunProgram;

import com.oanda.v20.ContextBuilder;
import com.oanda.v20.account.AccountID;
import com.oanda.v20.Context;


// main accessor for configurations of accounts
/*
* Returns account ID
* Returns account token
* Returns url
* Returns Context of the account*/
public class Config {
    private Config() {}

    public static final AccountID acc_ID = new AccountID("101-003-12890740-001");
    public static final String token = "81f207da969bcb19632b033923580a72-c8e6f4f118bda1d74646f56b71761734";
    public static final String url = "https://api-fxpractice.oanda.com";
    public static final Context ctx = new Context(Config.url, Config.token);

}
