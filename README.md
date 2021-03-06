<p>
  <img src="https://paybyte.io/Content/images/paybyte_logo_2.png" height="80" title="PayByte Logo">
</p>

# PayByte Android SDK

The Android SDK allows you to accept Crypto payments from your Java Android app using the PayByte REST API.
The SDK has been designed to act as a wrapper for all the main PayByte functionalities, 
allowing the developers to focus on the actual purchasing flow rather than on the API's implementation details.

## Create a new payment

```csharp
PayByte paybyte = new PayByte(true);
Payment payment = new Payment();
payment.setAmount(0.0332f);
payment.setCoin("BTC");
payment.setMerchantApiKey("[insert your API Key]");
var paymentResponse = payByte.createPayment(payment);
```

The paymentResponse will contain the JSONObject representation of the payment response. 

## Get a payment data

Simply provide the payment identifier to retrieve all data related to a transaction.

```csharp
var payByte = new PayByte(true);
var paymentId = "cf565888-28f5-430e-85af-b34b945ce20f";
JSONObject paymentStatus = payByte.GetPayment(paymentId);
```

The SDK will return a JSONObject representation of the transaction data.

## Get rates

Get the exchange rates of all supported crypto currencies against the provided fiat currency code.

```csharp
var payByte = new PayByte(true); 
var rates = payByte.getRate("GBP");
```

The SDK will return a JSONObject representation of exchange rate data against GBP fiat.

## What do I need
In order to use PayByte Android SDK, you will need to create a Merchant account on PayByte and get an API KEY.
Just go to [https://paybyte.io](https://paybyte.io) to register as a merchant and get your API KEY.

## License
PayByte is released under the MIT License. Please refer to the License file that accompanies this project for more information including complete terms and conditions.
