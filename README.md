# Forex Trading - OANDA v20 REST API

#### Developed a program to retrieve information such as live prices and candlestick data via OANDA's API. You can get the OANDA v20 API [here](https://github.com/oanda/v20-java). Still in progress, looking to implement backtesting libraries into this.
![Chart](https://www.tradingview.com/x/jNxLxklf)

###

### src 
`com.oanda.v20` contains the API provided by OANDA for data retrieval and information. 

`Indicators` contains relvant indicators to be used for backtesting, trading etc. Current available indicators include Moving Averages, RSI and Volatility.

`Strategies` contains the 2 strategies using a Moving Average cross over strategy. As long as the smaller MA crosses over the larger MA, a long position will open and will close when the opposite happens. For short positions, also happens vice versa. However, no risk management is in place at the moment.

`Run Program` this is where you will run the program. I have simplified the process and to start connecting to the OANDA exchange, you can use the Config file and input the relevant details. For more info, you can check out the API documentation. `Startup` will be the main program running the trading.


