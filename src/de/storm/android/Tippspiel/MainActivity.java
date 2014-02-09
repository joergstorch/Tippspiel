package de.storm.android.Tippspiel;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import org.apache.http.impl.client.BasicCookieStore;

import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.URI;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import de.storm.android.Tippspiel.communication.LoginTask;
import de.storm.android.Tippspiel.communication.TippCheckTask;

public class MainActivity extends Activity {

    BasicCookieStore cookieStore;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        cookieStore = new BasicCookieStore();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        LoginTask t = new LoginTask();
        t.execute("stifflerstorch@web.de", "siemens");


        try {
            // Login erfolgreich
            if(t.get() != null) {

                this.cookieStore.addCookie(t.get());

                TippCheckTask tippCheckTask = new TippCheckTask();
                tippCheckTask.execute(this.cookieStore);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_settings was selected
            case R.id.action_refresh:
                refresh();
                break;
            case R.id.action_settings:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                break;
            case R.id.action_info:

                break;
            case R.id.action_exit:
                exit();
                break;
            default:
                break;
        }

        return true;
    }

    private void refresh() {

    }

    private void exit() {

    }
}
