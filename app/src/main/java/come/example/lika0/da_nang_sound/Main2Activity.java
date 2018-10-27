package come.example.lika0.da_nang_sound;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Main2Activity extends AppCompatActivity {


    int tempture =35;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tempture=(int)(Math.random()*50);

        TextView tempValue = (TextView) findViewById(R.id.tempVal);
        tempValue.setText(tempture + "");

        ImageView imageView = findViewById(R.id.tempVal);

        if (tempture > 31) {
            Toast.makeText(getApplicationContext(),"熱中症にお注意ください",Toast.LENGTH_SHORT).show();
            imageView.setBackground(getResources().getDrawable(R.drawable.sunny));

            //熱中症に注意
        } else if (tempture > 2) {
            //筋トレよ
            Toast.makeText(getApplicationContext(),"暑いね",Toast.LENGTH_SHORT).show();
            imageView.setBackground(getResources().getDrawable(R.drawable.kingyobachi));
        } else if (tempture > 15) {
            //春がきた8
            Toast.makeText(getApplicationContext(),"春だねー",Toast.LENGTH_SHORT).show();
            imageView.setBackground(getResources().getDrawable(R.drawable.spring));
        } else if (tempture > 0) {
            //寒い
            Toast.makeText(getApplicationContext(),"寒い",Toast.LENGTH_SHORT).show();
            imageView.setBackground(getResources().getDrawable(R.drawable.snowman));
        }

    }
}
