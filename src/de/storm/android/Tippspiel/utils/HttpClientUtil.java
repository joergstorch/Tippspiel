package de.storm.android.Tippspiel.utils;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * Created by js on 09.02.14.
 */
public class HttpClientUtil {

    private static HttpClient client = null;

    public static HttpClient getInstance(){
        if (client == null)
            client = new DefaultHttpClient() {
            };
        return client;
    }

}
