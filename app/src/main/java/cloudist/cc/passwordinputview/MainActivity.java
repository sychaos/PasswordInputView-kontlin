package cloudist.cc.passwordinputview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import cloudist.cc.library.NumKeyboard;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NumKeyboard KeyboardView = findViewById(R.id.KeyboardView_pay);
        //设置键盘
        KeyboardView.init();
    }
}
