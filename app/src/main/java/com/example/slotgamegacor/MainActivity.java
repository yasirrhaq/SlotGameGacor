package com.example.slotgamegacor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView[] imgSlots = new ImageView[3];
    private Button btnStartStop;
    int id = 1;
    private boolean[] isSpinning = new boolean[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgSlots[0] = findViewById(R.id.imgSlot);
        imgSlots[1] = findViewById(R.id.imgSlot2);
        imgSlots[2] = findViewById(R.id.imgSlot3);
        btnStartStop = findViewById(R.id.btnStartStop);
        btnStartStop.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        // Check which image view to stop spinning
        int slotToStop = -1;
        for (int i = 0; i < 3; i++) {
            if (isSpinning[i]) {
                slotToStop = i;
                break;
            }
        }

        // If all image views are stopped, start spinning them all
        if (slotToStop == -1) {
            for (int i = 0; i < 3; i++) {
                startSpinning(imgSlots[i], i);
            }
        }
        // Otherwise, stop the next image view in the array
        else {
            stopSpinning(imgSlots[slotToStop]);
            isSpinning[slotToStop] = false;
        }
    }

    private void startSpinning(final ImageView imgSlot, final int slotIndex) {
        isSpinning[slotIndex] = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (isSpinning[slotIndex]) {
                    if (id == 9) id = 1;
                    else id++;
                    try {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                imgSlot.setImageResource(Helper.getIcon(id));
                            }
                        });
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        break;
                    }
                }
            }
        }).start();
    }

    private void stopSpinning(final ImageView imgSlot) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100); // Wait for half a second before stopping the spinning
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // Set the image to the final icon
                        imgSlot.setImageResource(Helper.getIcon(id));
                    }
                });
            }
        }).start();
    }
}
