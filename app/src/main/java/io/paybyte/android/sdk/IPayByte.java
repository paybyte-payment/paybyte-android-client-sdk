package io.paybyte.android.sdk;

import io.paybyte.android.sdk.models.Payment;

import org.json.JSONObject;

public interface IPayByte
{
    /**
     * @param payment The payment data.
     * @return The JSON representation of the payment response.
     */
    JSONObject CreatePayment(Payment payment) throws Exception;

    /**
     * @param paymentId The payment identifier.
     * @return The JSON representation of the payment status.
     */
    JSONObject GetPayment(String paymentId) throws Exception;

    /**
     * @param currencyCode The currency code.
     * @return The JSON representation of the rate data..
     */
    JSONObject GetRate(String currencyCode) throws Exception;
}