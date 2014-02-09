package de.storm.android.Tippspiel.communication;

import android.os.AsyncTask;

import org.apache.http.cookie.Cookie;

import de.storm.android.Tippspiel.utils.HttpUtil;

/**
 * Created by js on 09.02.14.
 */
public class LoginTask extends AsyncTask<String, Integer, Cookie> {

    @Override
    protected Cookie doInBackground(String... strings) {
        return HttpUtil.login(strings[0], strings[1]);
    }
}
