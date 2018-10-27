package come.example.lika0.da_nang_sound;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToNewWorld(View v){
        Toast.makeText(getApplicationContext(),"New World",Toast.LENGTH_SHORT).show();
        Intent boat = new Intent(getApplicationContext(),Main2Activity.class);
        startActivity(boat);
    }

}
