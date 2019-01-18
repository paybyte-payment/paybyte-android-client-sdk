package io.paybyte.android.sdk;

import android.text.TextUtils;
import io.paybyte.android.sdk.models.Payment;
import junit.framework.Assert;
import org.json.JSONObject;
import org.junit.Test;
import java.math.BigDecimal;

public class PayByteTests
{
    /**
     * Test to ensure that the create payment completes successfully.
     */
    @Test
    public void Test_CreatePayment_Success()
    {
        PayByte paybyte = new PayByte(true);

        Payment payment = new Payment();
        payment.setAmount(0.0332f);
        payment.setCoin("BTC");
        payment.setMerchApiKey("[insert your API Key]");

        try
        {
            JSONObject resp = sgg.CreatePayment(payment);

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
        PayByte paybyte = new PayByte(true);

        Payment payment = new Payment();
        payment.setAmount(0.0332f);
        payment.setCoin("BTC");
        payment.setMerchApiKey("[insert your API Key]");

        try
        {
            // create a test transaction
            JSONObject resp = sgg.CreatePayment(payment);

            // test the get payment against the transaction just created.
            JSONObject paymentStatus = sgg.GetPayment(resp.getJSONObject("transaction").getString("payment-id"));

            double amountValue = BigDecimal.valueOf(paymentStatus.getJSONObject("transaction").getDouble("amount")).doubleValue();

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
