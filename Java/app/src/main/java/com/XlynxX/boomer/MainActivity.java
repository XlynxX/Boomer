package com.XlynxX.boomer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import com.XlynxX.boomer.asynctask.MainAsyncTask;
import com.XlynxX.boomer.databinding.ActivityMainBinding;
import com.XlynxX.boomer.logging.Logger;

public class MainActivity extends AppCompatActivity {
    private static ActivityMainBinding binding;
    private MainAsyncTask task;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.phoneNumberField.setHint("Номер");
        binding.periodFieldDescription.setText("Периодичность (в секундах)");
        binding.periodField.setText("5");
        binding.enableCallsCheckBox.setText("Разрешить звонки");
        binding.launchButton.setText("Запустить");

        binding.launchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (binding.phoneNumberField.getText() != null && task == null)
                {
                    Logger.Info("Starting main task...");
                    new Runnable(){
                        public void run() {
                            task = new MainAsyncTask();
                            task.execute(binding.phoneNumberField.getText().toString());
                        }
                    }.run();
                    Logger.Info("Main task started.");
                    binding.launchButton.setText("Остановить");
                    return;
                }

                if (binding.phoneNumberField.getText() != null && task.isCancelled()) {
                    Logger.Info("Starting main task...");
                    new Runnable(){
                        public void run() {
                            task = new MainAsyncTask();
                            task.execute(binding.phoneNumberField.getText().toString());
                        }
                    }.run();
                    Logger.Info("Main task started.");
                    binding.launchButton.setText("Остановить");
                    return;
                }

                task.stopScheduler();
                task.cancel(true);
                Logger.Info("Main task has stopped");
                binding.launchButton.setText("Запустить");
            }
        });

        Logger.Info("App launched");
    }

    public static boolean isCallAllowed() {
        return binding.enableCallsCheckBox.isChecked();
    }
    public static int getPeriod() { return Integer.parseInt(binding.periodField.getText().toString()); }
}