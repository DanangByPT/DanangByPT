package jp.co.pasonatech.team_da_nang.musicwand;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class measureResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measure_reslt);

        top_title = findViewById(R.id.text_view);

        final EditText editText= findViewById(R.id.edit_text);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), DeviceDetailActivity.class);
                if(editText.getText() != null){
                    String str = editText.getText().toString();
                    intent.putExtra(EXTRA_MESSAGE, str);
                }
                startActivityForResult( intent, RESULT_SUBACTIVITY );

                // in order to clear the edittext
                editText.setText("");
            }
        });
    }

    // SubActivity からの返しの結果を受け取る
    protected void onActivityResult( int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if(resultCode == RESULT_OK && requestCode == RESULT_SUBACTIVITY &&
                null != intent) {
            String res = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
            textView.setText(res);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.measureResult);

        private void acceleration(double acceleration) {

        }

        private void humidity(){

        }

        private void temperture(int tempr){
            int lank_temp;

            if (tempr < -10) {
                lank_temp = 1;
            } else if (tempr < 0) {
                lank_temp = 2;

            } else if (tempr < 10) {
                lank_temp = 3;

            } else if (tempr < 20) {
                lank_temp = 4;

            } else if (tempr < 30) {
                lank_temp = 5;

            } else {
                lank_temp = 6;
            }
        }

        private void lux(){

        }

}