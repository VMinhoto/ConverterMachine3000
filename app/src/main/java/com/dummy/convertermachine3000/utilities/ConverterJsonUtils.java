package com.dummy.convertermachine3000.utilities;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by vitorminhoto on 02/11/2017.
 */

public final class ConverterJsonUtils {

    /**
     * This method parses JSON from a web response and returns an array of Strings
     * describing the conversion rate over various currencies.
     * <p/>
     *
     * @param currencyJsonStr JSON response from server
     *
     * @return Array of Strings describing the conversion rate
     *
     * @throws JSONException If JSON data cannot be properly parsed
     */

    public static String getSimpleConvertingStringFromJson(String currencyJsonStr, String key) {

        try {

            final String RATE = "rates";


            JSONObject currencyJson = new JSONObject(currencyJsonStr);

            currencyJson = currencyJson.getJSONObject(RATE);

            String parsedCurrency = currencyJson.getString(key);

            return parsedCurrency;
        } catch(JSONException e){
            e.printStackTrace();
            return null;
        }


    }

}
