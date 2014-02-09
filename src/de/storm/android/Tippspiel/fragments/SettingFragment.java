package de.storm.android.Tippspiel.fragments;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import de.storm.android.Tippspiel.R;

/**
 * Created by js on 08.02.14.
 */
public class SettingFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.preferences);
    }
}