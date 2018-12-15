package jp.co.pasonatech.team_da_nang.musicwand;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by lika0 on 2018/12/08.
 */

public class measureResultActivity extends AppCompatActivity {int temperture;int humidity;int lux;

  //  int tempture = 35;

 /*   protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(jp.co.pasonatech.team_da_nang.musicwand.R.layout.activity_main);
        //        tempture = (int) (Math.random() * 50);

//        TextView tempValue = (TextView) findViewById(R.id.tempVal);
//        tempValue.setText(tempture + "");

//        ImageView imageView = findViewById(R.id.tempView);
    }*/

 //   int method;
    {
         int lank_temp;

        if (temperture < -10) {
            lank_temp = 1;
        } else if (temperture < 0) {
            lank_temp = 2;

        } else if (temperture < 10) {
            lank_temp = 3;

        } else if (temperture < 20) {
            lank_temp = 4;

        } else if (temperture < 30) {
            lank_temp = 5;

        } else {
            lank_temp = 6;
        }
    }
}