package com.example.profilemanager;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Settings extends Fragment implements AdapterView.OnItemSelectedListener
{

    Button btn;
    Button btnClear;
    EditText name, email, password;
    Spinner spin;
    String[] themes = {"Default Mode", "Light Mode", "Dark Mode"};
    boolean isFirstTime = true;

    public Settings() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        btn = view.findViewById(R.id.btn);
        name = view.findViewById(R.id.userName);
        email = view.findViewById(R.id.userEmail);
        password = view.findViewById(R.id.userPassword);
        spin = view.findViewById(R.id.spin);
        btnClear = view.findViewById(R.id.clear);

        ArrayAdapter<String> adapterT = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, themes);

        adapterT.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapterT);
        spin.setOnItemSelectedListener(this);

        loadData();

        btn.setOnClickListener(v -> saveData());

        btnClear.setOnClickListener(v -> clearData());

        return view;
    }
    private void clearData()
    {
        name.setText("");
        email.setText("");
        password.setText("");
        spin.setSelection(0);
        SharedPreferences prefs = requireActivity().getSharedPreferences("settingsData", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.apply();
        Toast.makeText(requireContext(), "All settings cleared!", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        if (isFirstTime)
        {
            isFirstTime = false;
            return;
        }
        switch (position)
        {
            case 0:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                break;

            case 1:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                break;

            case 2:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                break;
        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent)
    {}

    private void saveData()
    {
        String uName = name.getText().toString().trim();
        String uEmail = email.getText().toString().trim();
        String uPass = password.getText().toString().trim();
        String theme = spin.getSelectedItem().toString();
        if (uName.isEmpty())
        {
            Toast.makeText(requireContext(), "Enter a valid Username", Toast.LENGTH_SHORT).show();
            return;
        }
        if (uEmail.isEmpty() || !uEmail.contains("@") || !uEmail.contains("."))
        {
            Toast.makeText(requireContext(), "Enter a valid Email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (uPass.length() < 6)
        {
            Toast.makeText(requireContext(), "Password must be at least 6 characters", Toast.LENGTH_SHORT).show();
            return;
        }
        SharedPreferences prefs = requireActivity().getSharedPreferences("settingsData", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("userName", uName);
        editor.putString("userEmail", uEmail);
        editor.putString("userPassword", uPass);
        editor.putString("userTheme", theme);
        editor.apply();
        Toast.makeText(requireContext(), "Settings Saved Successfully!", Toast.LENGTH_SHORT).show();
    }

    private void loadData()
    {
        SharedPreferences prefs = requireContext().getSharedPreferences("settingsData", MODE_PRIVATE);
        String uName = prefs.getString("userName", "");
        String uEmail = prefs.getString("userEmail", "");
        String uPass = prefs.getString("userPassword", "");
        String theme = prefs.getString("userTheme", "");
        name.setText(uName);
        email.setText(uEmail);
        password.setText(uPass);
        if (!theme.equals(""))
        {
            for (int i = 0; i < spin.getCount(); i++)
            {
                if (spin.getItemAtPosition(i).toString().equals(theme))
                {
                    spin.setSelection(i);
                    break;
                }
            }
        }
    }
}
