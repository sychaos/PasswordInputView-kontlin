package cloudist.cc.passwordinputview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import cloudist.cc.library.widget.InputPasswordDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputPasswordDialog dialog = InputPasswordDialog.Companion.newInstance();
                dialog.setTextChangeListener(new InputPasswordDialog.TextChangeListener() {
                    @Override
                    public void textChange(String text) {
                        Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.show(getSupportFragmentManager(), "");
            }
        });
    }
}
