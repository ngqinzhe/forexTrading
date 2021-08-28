# OANDA Forex Trading Bot (In Progress)

### This is a trading bot developed using OANDA's REST API for Java and utilizes mainly GET methods to retrieve information such as live prices and candlestick data. You can get the OANDA v20 API [here](https://github.com/oanda/v20-java). 

#### Update as of 18 June 2021: Only supports Moving Average Crossover Strategy.

In this repo there are two folders that you would need to place into the src folder of the v20 API:
1. Indicators
2. RunProgram
3. Strategies

Indicators currently only contain a moving average class which retrieves candlestick data from the API. 


```
public class MovingAverage {
    public static double get(Long window, String instrumentName, CandlestickGranularity timeframe) {}
    }
```
The MovingAverage class supports getting candlestick data from the server.
1. window: The number of candlesticks to retrieve within the timeframe. E.g. 50MA : window = 50
2. instrumentName: currency pair that you are trading E.g. "GBP_USD" !!!Do not enter "GBP/USD" as this will result in a 404 error.
3. timeframe: input a CandlestickGranularity Object E.g. CandlestickGranularity.H1 means that you are trading on the 1 hourly timeframe

## Moving Average Crossover Strategy
The main program to use here would be in the Strategies folder which contains the MACrossover.java class.

Below is an example of a static method within MACrossover that will run the bot. As you can see, it will start trading the EUR/USD pair, using purchasing 10 units, and at a timeframe of 5 seconds.
```
startTrade("EUR_USD", 10, CandlestickGranularity.S5, 10L, 20L);
```

Since this strategy is automated, it will LONG the currency when the smaller MA(10) crosses over the larger MA(20).

## To Start Trading
Make sure that you have downloaded the OANDA v20 REST API and placed this repo into the src folder. After which, open this project in an IDE of your choice.

Open Config and fill in the token and accountID from your OANDA account. The URI has been set as a demo trading account, if you wish, go to their API site to change to the live trading account.

Once you have filled in your token and accoundID, navigate to src/Strategies/MACrossover.java.

Under `public static void main(String[] args)` you will find the startTrade function. Enter your inputs as desired. The program will configure a SINGLE trade when the crossover happens and will close when another crossover happens.

**This is still under progress.




