package io.paybyte.android.sdk;

import android.text.TextUtils;
import io.paybyte.android.sdk.models.Payment;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Hashtable;

/**
 * Wrapper class which exposes the core functionality of the SetGetGo REST API.
 */
public class PayByte implements IPayByte
{
    /**
     * True to select te testnet network, False otherwise.
     */
    private boolean isTestnet;

    /**
     * Initializes a new instance of the PayByte class.
     *
     * @param isTestnet
     */
    public PayByte(boolean isTestnet)
    {
        this.isTestnet = isTestnet;
    }

    /**
     * The PayByte base URL.
     */
    private String baseUrl = "https://paybyte.io/api";

    /**
     * Creates a new payment.
     *
     * @param payment The payment data.
     * @return The JSON representation of the transaction payment.
     */
    @Override
    public JSONObject CreatePayment(Payment payment) throws Exception
    {
        if (TextUtils.isEmpty(payment.getMerchantApiKey()) || payment.getAmount() <= 0 || TextUtils.isEmpty(payment.getCoin()))
        {
            throw new IllegalArgumentException("Invalid merchant api key, amount values or selected coin.");
        }

        Hashtable<String,String> params = new Hashtable<>();
        params.put("amount", String.valueOf(payment.getAmount()));
        params.put("coin", payment.getCoin());
        params.put("api_key", payment.getMerchantApiKey());
        params.put("testnet", String.valueOf(this.isTestnet));

        if (!TextUtils.isEmpty(payment.getCallback()))
        {
            params.put("callback", payment.getCallback());
        }

        if (!TextUtils.isEmpty(payment.getReturnUrl()))
        {
            params.put("return_url", payment.getReturnUrl());
        }

        if (!TextUtils.isEmpty(payment.getAffiliateId()))
        {
            params.put("affId", payment.getAffiliateId());
        }

        return Get(baseUrl + "/create-payment", params);
    }

    /**
     * Gets a payment status by payment identifier.
     *
     * @param paymentId The payment identifier.
     * @return The JSON representation of the transaction payment.
     */
    @Override
    public JSONObject GetPayment(String paymentId) throws Exception
    {
        if (TextUtils.isEmpty(paymentId))
        {
            throw new IllegalArgumentException("Invalid payment identifier");
        }

        Hashtable<String,String> params = new Hashtable<>();
        params.put("payment_id", paymentId);
        params.put("testnet", String.valueOf(this.isTestnet));

        return Get(baseUrl + "/get-payment-status", params);
    }

    /**
     * Gets the exchange rate by currency code.
     *
     * @param currencyCode The currency code.
     * @return The JSON representation of the rates data.
     */
    @Override
    public JSONObject GetRate(String currencyCode)  throws Exception
    {
        if (TextUtils.isEmpty(currencyCode))
        {
            throw new IllegalArgumentException("Invalid currency code");
        }

        Hashtable<String,String> params = new Hashtable<>();
        params.put("currency", currencyCode);

        return Get(baseUrl + "/get-rate", params);
    }

    /**
     *
     * @param uri The request URI
     * @param parameters The GET parameters on the Query String
     * @return  The JSON representation of the GET response.
     * @throws Exception The exception if any.
     */
    private JSONObject Get(String uri, Hashtable<String, String> parameters) throws Exception
    {
        HttpURLConnection urlConnection = null;

        if (parameters != null)
        {
            uri += "?";
            StringBuilder uriBuilder = new StringBuilder(uri);
            for (String key : parameters.keySet())
            {
                uriBuilder.append(key).append("=").append(parameters.get(key)).append("&");
            }
            uri = uriBuilder.toString();
            uri = uri.substring(0, uri.length() - 1);
        }

        URL url = new URL(uri);
        urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.setReadTimeout(10000);
        urlConnection.setConnectTimeout(15000);
        urlConnection.setDoOutput(true);
        urlConnection.connect();

        int responseCode = urlConnection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK)
        {
           String response = readStream(urlConnection.getInputStream());

            return new JSONObject(response);
        }

        return null;
    }

    /**
     * Reads the String from the input Stream.
     *
     * @param in The input stream.
     * @return The String extracted from the input stream.
     */
    private String readStream(InputStream in)
    {
        BufferedReader reader = null;
        StringBuffer response = new StringBuffer();
        try
        {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null)
            {
                response.append(line);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (reader != null)
            {
                try
                {
                    reader.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return response.toString();
    }
}
