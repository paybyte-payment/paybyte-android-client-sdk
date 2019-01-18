package io.paybyte.android.sdk.models;

/**
 * Model class which defines a PayByte transaction payment.
 */
public class Payment
{
    /**
     * The amount.
     */
    private double amount;

     /**
     * The coin.
     */
    private String coin;

    /**
     * The merchant API key.
     */
    private String apiKey;

    /**
     * The callback.
     */
    private String callback;

     /**
     * The return URL.
     */
    private String returnUrl;

    /**
     * The affiliate identifier.
     */
    private String affiliateId;

    /**
     * Gets the amount.
     * @return
     */
    public double getAmount()
    {
        return amount;
    }

    /**
     * Sets the amount.
     * @return
     */
    public void setAmount(double amount)
    {
        this.amount = amount;
    }

     /**
     * Gets the coin.
     * @return
     */
    public double getCoin()
    {
        return coin;
    }

    /**
     * Sets the coin.
     * @return
     */
    public void setCoin(String coin)
    {
        this.coin = coin;
    }

    /**
     * Gets the merchant API key.
     * @return
     */
    public String getMerchantApiKey()
    {
        return apiKey;
    }

    /**
     * Sets the merchant API key.
     * @return
     */
    public void setMerchantApiKey(String apiKey)
    {
        this.apiKey = apiKey;
    }

    /**
     * Gets the callback URL.
     * @return
     */
    public String getCallback()
    {
        return callback;
    }

    /**
     * Sets the callback URL.
     * @return
     */
    public void setCallback(String callback)
    {
        this.callback = callback;
    }

      /**
     * Gets the return URL.
     * @return
     */
    public String getReturnUrl()
    {
        return returnUrl;
    }

    /**
     * Sets the return URL.
     * @return
     */
    public void setReturnUrl(String url)
    {
        this.returnUrl = url;
    }

    /**
     * Gets the affiliate identifier.
     * @return
     */
    public String getAffiliateId()
    {
        return affiliateId;
    }

    /**
     * Sets the affiliate identifier.
     * @return
     */
    public void setAffiliateId(String affiliateId)
    {
        this.affiliateId = affiliateId;
    }
}