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

    public static final AccountID acc_ID = new AccountID("<Enter your account ID>");
    public static final String token = "<Enter your OANDA API token>";
    public static final String url = "<Enter the url => demo / live>";
    public static final Context ctx = new Context(Config.url, Config.token);

}
