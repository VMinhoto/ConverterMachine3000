package com.dummy.convertermachine3000.utilities;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by vitorminhoto on 01/11/2017.
 */

public class NetworkUtils {
    //TODO Pesquisar api disto para dar parse do URI em condições e não somar strings

    final static String YAHOO_BASE_URL =
            "http://download.finance.yahoo.com/d/quotes?f=sl1d1t1&s=";

    final static String YAHOO_FINAL_URL =
            "=X";

    public static URL buildUrl(String codeOne, String codeTwo){
        Uri builtUri = Uri.parse(YAHOO_BASE_URL+codeOne+codeTwo+YAHOO_FINAL_URL);
        URL url = null;

        try{
            url = new URL(builtUri.toString());
        }catch (MalformedURLException e){
            e.printStackTrace();
        }

        return url;
    }

    public static String getResponseFromHttpUrl (URL url) throws IOException{
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try{
            InputStream in =urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput){

                return scanner.next();
            } else{
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
