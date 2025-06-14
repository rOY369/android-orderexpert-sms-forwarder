package org.scottmconway.incomingsmsgateway;

import android.Manifest;
import android.os.Bundle;
import android.widget.TextView;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.content.pm.PackageManager;

public class MainActivity extends AppCompatActivity {
    private static final int PERMISSION_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.RECEIVE_SMS,
                    Manifest.permission.READ_CONTACTS,
                    Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.READ_CALL_LOG
            }, PERMISSION_CODE);
        } else {
            showConfig();
        }

        Button btnEditSimNumbers = findViewById(R.id.btn_edit_sim_numbers);
        btnEditSimNumbers.setOnClickListener(v -> showEditSimNumbersDialog());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode != PERMISSION_CODE) {
            return;
        }
        for (int i = 0; i < permissions.length; i++) {
            if (!permissions[i].equals(Manifest.permission.RECEIVE_SMS)) {
                continue;
            }
            if (grantResults[i] == android.content.pm.PackageManager.PERMISSION_GRANTED) {
                showConfig();
            } else {
                showInfo(getResources().getString(R.string.permission_needed));
            }
            return;
        }
    }

    private void showConfig() {
        // Do not show the dialog automatically. The main screen will show the message.
    }

    private void showInfo(String text) {
        TextView notice = findViewById(R.id.info_notice);
        notice.setText(text);
    }

    private void showEditSimNumbersDialog() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View dialogView = inflater.inflate(R.layout.dialog_edit_sim_numbers, null);
        EditText sim1Input = dialogView.findViewById(R.id.input_sim1_number);
        EditText sim2Input = dialogView.findViewById(R.id.input_sim2_number);
        SharedPreferences prefs = getSharedPreferences("sim_prefs", MODE_PRIVATE);
        String sim1Saved = prefs.getString("sim1_number", "");
        String sim2Saved = prefs.getString("sim2_number", "");
        android.util.Log.d("MainActivity", "Loaded SIM1: " + sim1Saved + ", SIM2: " + sim2Saved);
        sim1Input.setText(sim1Saved);
        sim2Input.setText(sim2Saved);
        new AlertDialog.Builder(this)
            .setTitle("Edit SIM Numbers")
            .setView(dialogView)
            .setPositiveButton("Save", (dialog, which) -> {
                String sim1 = sim1Input.getText().toString().trim();
                String sim2 = sim2Input.getText().toString().trim();
                if (!sim1.matches("\\d{10}")) {
                    Toast.makeText(this, "SIM 1 number must be 10 digits", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!sim2.isEmpty() && !sim2.matches("\\d{10}")) {
                    Toast.makeText(this, "SIM 2 number must be 10 digits or empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                prefs.edit().putString("sim1_number", sim1).putString("sim2_number", sim2).apply();
                Toast.makeText(this, "SIM numbers saved", Toast.LENGTH_SHORT).show();
            })
            .setNegativeButton("Cancel", null)
            .show();
    }
}
