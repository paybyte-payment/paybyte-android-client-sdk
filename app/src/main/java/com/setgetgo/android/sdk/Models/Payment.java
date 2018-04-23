package com.setgetgo.android.sdk.Models;

/**
 * Model class which defines a transaction payment.
 */
public class Payment
{
    /**
     * The amount.
     */
    private double amount;

    /**
     * The merchant address.
     */
    private String merchAddress;

    /**
     * The callback.
     */
    private String callback;

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
     * Gets the merchant address.
     * @return
     */
    public String getMerchAddress()
    {
        return merchAddress;
    }

    /**
     * Sets the merchant address.
     * @return
     */
    public void setMerchAddress(String merchAddress)
    {
        this.merchAddress = merchAddress;
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
