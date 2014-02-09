package de.storm.android.Tippspiel.communication;

import android.os.AsyncTask;
import org.apache.http.impl.client.BasicCookieStore;
import de.storm.android.Tippspiel.utils.HttpUtil;

/**
 * Created by js on 09.02.14.
 */
public class TippCheckTask extends AsyncTask<BasicCookieStore, Integer, Boolean> {
    @Override
    protected Boolean doInBackground(BasicCookieStore... cookieStores) {
        return  HttpUtil.tippCheck(cookieStores[0]);
    }
}
