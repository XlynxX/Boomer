package com.XlynxX.boomer;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.XlynxX.boomer.AsyncTasks.sendPostRequest;
import com.XlynxX.boomer.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private static ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.phoneNumberField.setHint("Номер");
        binding.enableCallsCheckBox.setText("Разрешить звонки");
        binding.launchButton.setText("Запустить");

        binding.launchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (binding.phoneNumberField.getText() != null) {
                    Request request = new Request();
                    request.setPhoneNumber(binding.phoneNumberField.getText().toString());
                    request.setCallPermission(binding.enableCallsCheckBox.isChecked());
                    new sendPostRequest().execute(request);
                }
            }
        });
    }
}