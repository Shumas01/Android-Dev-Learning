package com.example.profilemanager;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class Profile extends Fragment
{
    EditText name, email, password;
    Spinner spin;
    String[] themes = {"Light", "Dark"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        name = view.findViewById(R.id.name);
        email = view.findViewById(R.id.email);
        password = view.findViewById(R.id.password);
        spin = view.findViewById(R.id.spin);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_dropdown_item, themes);
        spin.setAdapter(adapter);
        loadData();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }

    private void loadData() {
        SharedPreferences prefs = requireActivity()
                .getSharedPreferences("settingsData", getContext().MODE_PRIVATE);
        String uName = prefs.getString("userName", "");
        String uEmail = prefs.getString("userEmail", "");
        String uPassword = prefs.getString("userPassword", "");
        String uTheme = prefs.getString("userTheme", "");

        name.setText(uName);
        email.setText(uEmail);
        password.setText(uPassword);

        if (uTheme != null && !uTheme.isEmpty())
        {
            for (int i = 0; i < spin.getCount(); i++)
            {
                if (spin.getItemAtPosition(i).toString().equals(uTheme))
                {
                    spin.setSelection(i);
                    break;
                }
            }
        }
    }
}
