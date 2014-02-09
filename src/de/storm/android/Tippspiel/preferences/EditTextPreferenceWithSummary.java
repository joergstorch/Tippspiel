package de.storm.android.Tippspiel.preferences;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Erweitertes EditText-Feld, was direkt die Summary füllt. Desweiteren wird beim Attrribute "password" = true immer ***** ausgegeben.
 * Created by js on 09.02.14.
 */
public class EditTextPreferenceWithSummary extends android.preference.EditTextPreference {
    private Boolean isPasswordField = false;

    public EditTextPreferenceWithSummary(Context context, AttributeSet attrs) {
        super(context, attrs);

        isPasswordField = attrs.getAttributeBooleanValue("http://schemas.android.com/apk/res/android", "password", false);
    }

    @Override
    protected void onDialogClosed(boolean positiveResult) {
        super.onDialogClosed(positiveResult);

        setSummary(getSummary());
    }

    @Override
    public CharSequence getSummary() {

        return isPasswordField ? "*****" : this.getText();
    }
}
