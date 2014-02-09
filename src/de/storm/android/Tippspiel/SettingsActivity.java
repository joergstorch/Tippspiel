package de.storm.android.Tippspiel;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import de.storm.android.Tippspiel.fragments.SettingFragment;

/**
 * Created by js on 08.02.14.
 */
public class SettingsActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences prefs =
                PreferenceManager.getDefaultSharedPreferences(this);

        PreferenceChangeListener listener = new PreferenceChangeListener();

        prefs.registerOnSharedPreferenceChangeListener(listener);

        // Display the fragment as the main content.
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingFragment())
                .commit();
    }


    private class PreferenceChangeListener implements SharedPreferences.OnSharedPreferenceChangeListener {

        @Override
        public void onSharedPreferenceChanged(SharedPreferences prefs, String key) {
            if (key.equals("key_email")) {
                Preference emailPref = findPreference(key);
                // Set summary to be the user-description for the selected value
                emailPref.setSummary(prefs.getString(key, ""));
            } else if (key.equals("key_password")) {
                Preference passwordPref = findPreference(key);
                if (prefs.getString(key, "").length() > 0) {
                    passwordPref.setSummary("*****");
                } else {
                    passwordPref.setSummary("");
                }
            }

        }
    }
}