package cloudist.cc.passwordinputview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import cloudist.cc.library.Keyboard;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Keyboard KeyboardView = findViewById(R.id.KeyboardView_pay);
        //设置键盘
        KeyboardView.setKeyboardKeys(new String[]{
                "1", "2", "3",
                "4", "5", "6",
                "7", "8", "9",
                "<<", "0", "完成"
        });
    }
}
