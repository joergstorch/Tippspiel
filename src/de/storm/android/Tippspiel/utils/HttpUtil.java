package de.storm.android.Tippspiel.utils;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.CookieStore;
import java.util.Date;

import de.storm.android.Tippspiel.Consts;

/**
 * Created by js on 09.02.14.
 */
public class HttpUtil {

    /**
     * Login mittels Post.
     *
     * @param email
     * @param password
     * @return
     */
    public static Cookie login(String email, String password) {
        HttpClient client = HttpClientUtil.getInstance();
        HttpPost post = new HttpPost(Consts.LOGIN_URL);

        post.getParams().setParameter("email", email);
        post.getParams().setParameter("passwort", password);

        HttpResponse response = null;

        Cookie tippspielCookie = null;

        try {
            response = client.execute(post);

            for (Header cookie : response.getHeaders("set-cookie")) {

                String value = "";
                for (String s : cookie.getValue().split(";")) {
                    if(s.trim().toLowerCase().startsWith(Consts.COOKIE_NAME)) {
                        value = s.substring(s.indexOf('=') + 1);
                        break;
                    }
                }

                if(!value.isEmpty()) {
                    tippspielCookie = new BasicClientCookie(Consts.COOKIE_NAME, cookie.getValue());
                }
                    break;

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return tippspielCookie;
    }

    public static Boolean tippCheck(BasicCookieStore cookieStore) {
        HttpClient client = HttpClientUtil.getInstance();
        HttpContext context = new BasicHttpContext();
        context.setAttribute(ClientContext.COOKIE_STORE, cookieStore);

        HttpGet get = new HttpGet(Consts.TIPP_CHECK_URL);

        HttpResponse response = null;
        try {
            response = client.execute(get);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(response.getStatusLine().getStatusCode() != 200) {
            // Fehler
        }

        String body = "";

        try {
            body = EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }

        int i = 0;

        return true;
    }
}
