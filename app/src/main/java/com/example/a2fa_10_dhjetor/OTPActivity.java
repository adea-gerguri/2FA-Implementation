package com.example.a2fa_10_dhjetor;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
public class OTPActivity extends AppCompatActivity {

    private EditText inputFieldOtp;
    private Button validateOtpButton, resendOtpButton;

    private String recipient;
    private String generatedOtp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        inputFieldOtp = findViewById(R.id.editTextNumber);
        validateOtpButton = findViewById(R.id.buttonOtp);
        resendOtpButton = findViewById(R.id.buttonResend);


        recipient = getIntent().getStringExtra("recipient");


        Log.d("OTPActivity", "Email received: " + recipient);

        if (recipient == null || recipient.isEmpty()) {
            Toast.makeText(this, "No email provided", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }


        generatedOtp = generateOtp();
        Log.d("OTPActivity", "Generated OTP: " + generatedOtp);
        Service.sendEmail(recipient, "Your One Time Password", generatedOtp);

        validateOtpButton.setOnClickListener(v -> {
            String userOtp = inputFieldOtp.getText().toString().trim();

            if (userOtp.equals(generatedOtp)) {
                Toast.makeText(this, "OTP Verified Successfully", Toast.LENGTH_SHORT).show();
                navigateToMain();
            } else {
                Toast.makeText(this, "Invalid OTP. Try again.", Toast.LENGTH_SHORT).show();
            }
        });

        resendOtpButton.setOnClickListener(v -> resendOtp());
    }

    private void resendOtp() {
        generatedOtp = generateOtp();
        Service.sendEmail(recipient, "Your One Time Password (Resent)", generatedOtp);
        Toast.makeText(this, "OTP Resent to " + recipient, Toast.LENGTH_SHORT).show();
    }

    private String generateOtp() {
        int otp = (int) (Math.random() * 1000000);
        return String.format("%06d", otp);
    }

    private void navigateToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
