package com.setgetgo.android.sdk;

import android.text.TextUtils;

import com.setgetgo.android.sdk.Models.Payment;

import junit.framework.Assert;

import org.json.JSONObject;
import org.junit.Test;

import java.math.BigDecimal;

public class SetGetGoTests
{
    /**
     * Test to ensure that the create payment completes successfully.
     */
    @Test
    public void Test_CreatePayment_Success()
    {
        SetGetGo sgg = new SetGetGo(true);

        Payment payment = new Payment();
        payment.setAmount(0.0332f);
        payment.setMerchAddress("n1DUF2TJ3iq2mdhLqR4HPgmKrtEfo9ZJeE");

        try
        {
            JSONObject resp = sgg.CreatePayment(payment);

            Assert.assertEquals(resp.getJSONObject("transaction").getString("merchant-address"), payment.getMerchAddress());
            Assert.assertEquals(resp.getJSONObject("transaction").getDouble("amount"), payment.getAmount());
            Assert.assertTrue(!TextUtils.isEmpty(resp.getJSONObject("transaction").getString("payment-address")));
            Assert.assertTrue(!TextUtils.isEmpty(resp.getString("error")));
        }
        catch (Exception e)
        {
            Assert.fail();
        }
    }

    /**
     * Test to ensure that the get payment completes successfully.
     */
    @Test
    public void Test_GetPayment_Success()
    {
        SetGetGo sgg = new SetGetGo(true);

        Payment payment = new Payment();
        payment.setAmount(0.0332);
        payment.setMerchAddress("n1DUF2TJ3iq2mdhLqR4HPgmKrtEfo9ZJeE");

        try
        {
            // create a test transaction
            JSONObject resp = sgg.CreatePayment(payment);

            // test the get payment against the transaction just created.
            JSONObject paymentStatus = sgg.GetPayment(resp.getJSONObject("transaction").getString("payment-address"));

            double amountValue = BigDecimal.valueOf(paymentStatus.getJSONObject("transaction").getDouble("amount")).doubleValue();

            Assert.assertEquals(paymentStatus.getJSONObject("transaction").getString("merchant-address"), payment.getMerchAddress());
            Assert.assertEquals(amountValue, payment.getAmount());
            Assert.assertTrue(!TextUtils.isEmpty(paymentStatus.getJSONObject("transaction").getString("payment-address")));
            Assert.assertTrue(!TextUtils.isEmpty(paymentStatus.getString("error")));
        }
        catch (Exception e)
        {
            Assert.fail();
        }
    }

    /**
     * Test to ensure that the get payment completes successfully.
     */
    @Test
    public void Test_GetRate_Success()
    {
        SetGetGo sgg = new SetGetGo(true);

        try
        {
            JSONObject resp = sgg.GetRate("GBP");

            Assert.assertEquals(resp.getJSONObject("rate").getString("name"), "GBP");
            Assert.assertTrue(!TextUtils.isEmpty(resp.getString("error")));
        }
        catch (Exception e)
        {
            Assert.fail();
        }
    }
}
