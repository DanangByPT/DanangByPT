package jp.co.pasonatech.team_da_nang.musicwand;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.hardware.Sensor;
import android.hardware.usb.UsbDevice;
import android.media.midi.MidiDeviceInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

//import jp.co.pasonatech.team_da_nang.musicwand.service.BluetoothLeService;
//
//import jp.co.pasonatech.team_da_nang.musicwand.service.BluetoothLeService;

public class MainActivity extends AppCompatActivity  {
    private MidiControl midiControl;
    private Instrument instrument ;
    private PlayMusic playMusic ;
    private Toast toastMessage ;
    private BluetoothManager mBluetoothManager;
    private BluetoothAdapter mBluetoothAdapter;

    private pasonatech.danang.service.BluetoothLeService mBluetoothLeService;

    //thanh
    public void onCreate() {
        super.onCreate();
        initialBLE();
        startBLEService();
        startBluetoothLeService();
        measureResult();

    }

    private void initialBLE() {

        mBluetoothManager =
                (BluetoothManager) getApplicationContext().getSystemService(Context.BLUETOOTH_SERVICE);

        mBluetoothAdapter = mBluetoothManager.getAdapter();

        if (mBluetoothAdapter == null || !mBluetoothAdapter.isEnabled()) {
            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            enableIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(enableIntent);
        }
    }

    private void startBLEService() {
        Intent bindIntent = new Intent(this, pasonatech.danang.service.BluetoothLeService.class);
        startService(bindIntent);
    }

    private void startBluetoothLeService() {

        boolean f;
        Intent bindIntent = new Intent(this, pasonatech.danang.service.BluetoothLeService.class);
        startService(bindIntent);

        f = bindService(bindIntent, mServiceConnection, Context.BIND_AUTO_CREATE);

        if (f) {
           Boolean success = true;

        } else {
            Boolean success = false;

        }
    }

    private ServiceConnection mServiceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mBluetoothLeService = ((pasonatech.danang.service.BluetoothLeService.LocalBinder) service)
                    .getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }

    };


    private void measureResult(){
        Intent intent = new Intent( this, DeviceDetailActivity.class );
        int requestcode=1001;
        startActivityForResult( intent, requestCode );

        public void onActivityResult( int requestCode, int resultCode, Intent intent )
        {
            // startActivityForResult()の際に指定した識別コードとの比較
            if( requestCode == 1001 ){

                // 返却結果ステータスとの比較
                if( resultCode == Activity.RESULT_OK ){

                    // 返却されてきたintentから値を取り出す
                    String str = intent.getStringExtra( "key" );
                }
            }
        }
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            sensor_X = event.values[0];
            sensor_Y = event.values[1];
            sensor_Z = event.values[2];

            String strTmp = "加速度センサー\n"
                    + " X: " + sensor_X + "\n"
                    + " Y: " + sensor_Y + "\n"
                    + " Z: " + sensor_Z;
            textView.setText(strTmp);

            showInfo(event);
        }
    }



    class OnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }

    protected OnClickListener onClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TextView textView ;

        super.onCreate(savedInstanceState);
 //       this.onClickListener = new OnClickListener() ;

     //   setContentView(android.R.layout.activity_main);
      //  Toolbar toolbar = (Toolbar) findViewById(jp.co.pasonatech.team_da_nang.musicwand.R.id.toolbar);
     //   setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this.onClickListener);

        this.midiControl = new jp.co.pasonatech.team_da_nang.musicwand.MidiControl();

        if( this.midiControl.Initialize(MainActivity.this)) {
            this.midiControl.openMidiDevice() ;
            MidiDeviceInfo info ;

            int retry = 0 ;
            do {
                info = this.midiControl.getSequencerInfo();
                if(null == info){
                    try {
                        Thread.sleep(100);
                    }
                    catch(InterruptedException e){
                        this.toastMessage = Toast.makeText(this, "Thread.Sleep exception!.", Toast.LENGTH_SHORT);
                        this.toastMessage.setGravity(Gravity.CENTER, 0, 0);
                        this.toastMessage.show();
                    }
                    retry++ ;
                }
            }while( null == info && 3 > retry) ;

            if (null != info) {
                Bundle prop = info.getProperties();

                // 製造元文字列（"Roland"など）
                /*
                String stringData = prop.getString(info.PROPERTY_MANUFACTURER);
                textView = (TextView) findViewById(R.id.textViewOfManufacture);
                textView.setText(stringData);

                // 製品名文字列（"EDIROL SD-90"など）
                stringData = prop.getString(info.PROPERTY_PRODUCT);
                textView = (TextView) findViewById(R.id.textViewOfProduct);
                textView.setText(stringData);

                // 機器名文字列（"Roland EDIROL SD-90"など）
                stringData = prop.getString(info.PROPERTY_NAME);
                textView = (TextView) findViewById(R.id.textViewOfName);
                textView.setText(stringData);

                // バージョン文字列（"1.16"など）
                stringData = prop.getString(info.PROPERTY_VERSION);
                textView = (TextView) findViewById(R.id.textViewOfVersion);
                textView.setText(stringData);
*/
                // Midiデバイスの接続方法確認
                UsbDevice usbDevice = (UsbDevice) prop.get(info.PROPERTY_USB_DEVICE);
                if (prop.getBoolean(info.PROPERTY_USB_DEVICE)) {
                    textView = (TextView) findViewById(jp.co.pasonatech.team_da_nang.musicwand.R.id.textViewOfConnectType);
                    textView.setText("USB connect");
                } else if (prop.getBoolean(info.PROPERTY_BLUETOOTH_DEVICE)) {
                    textView = (TextView) findViewById(jp.co.pasonatech.team_da_nang.musicwand.R.id.textViewOfConnectType);
                    textView.setText("Bluetooth connect");
                } else {
                    textView = (TextView) findViewById(jp.co.pasonatech.team_da_nang.musicwand.R.id.textViewOfConnectType);
                    textView.setText("Inner MIDI device");
                }

                // 入力ポート数確認
                int numInputs = info.getInputPortCount();
                textView = (TextView) findViewById(jp.co.pasonatech.team_da_nang.musicwand.R.id.textViewOfImports);
                textView.setText( "Number of input ports: " + Integer.toString(numInputs));

                // 出力ポート数確認
                int numOutputs = info.getOutputPortCount();
                textView = (TextView) findViewById(jp.co.pasonatech.team_da_nang.musicwand.R.id.textViewOfOutports);
                textView.setText("Number of output ports: " + Integer.toString(numOutputs));
            }
        }
        // ボタン押下時の処理を追加

        Button enevtButton = (Button)findViewById(R.id.buttonOfSoundTest) ;
        enevtButton.setOnClickListener(this);
        enevtButton = (Button)findViewById(R.id.buttonOfReset);
        enevtButton.setOnClickListener(this);

        // 楽器オブジェクトを生成
        this.instrument = new jp.co.pasonatech.team_da_nang.musicwand.Instrument(this.midiControl, 0x00, jp.co.pasonatech.team_da_nang.musicwand.MidiControl.MIDImode.Mode3) ;
        // 演奏オブジェクトを生成　引数 ： 楽器オブジェクト、キー、上限オクターブ（中心からの相対値）、下限オクターブ（中心からの相対値）
        // 演奏オブジェクトはスケール種類（メジャー、ナチュラルマイナー、メジャーペンタトニックなど）ごとにクラスを定義
        this.playMusic = new jp.co.pasonatech.team_da_nang.musicwand.PlayMusicBlueNote(instrument, jp.co.pasonatech.team_da_nang.musicwand.PlayMusic.Key.A, 2, 2) ;
        String string ;
        string = this.playMusic.getKey().toString() + " " + this.playMusic.getScale().toString();
        textView = (TextView) findViewById(jp.co.pasonatech.team_da_nang.musicwand.R.id.textViewOfKeyScale);
        textView.setText(string);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClick(View v){

        this.toastMessage = Toast.makeText(MainActivity.this, "Clicked button!", Toast.LENGTH_SHORT);
        this.toastMessage.setGravity(Gravity.CENTER, 0, 0);
        this.toastMessage.show();

        // ボタン押下時に音を出す
        if( R.id.buttonOfSoundTest == v.getId()){
            this.playMusic.Play();
        }
        else if( R.id.buttonOfReset == v.getId()){
            this.playMusic.Stop();
            this.playMusic.Reset();
        }
    }

}
