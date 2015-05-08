package com.example.yamashitamasaki.hello_world;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import java.util.Calendar;
import java.util.List;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //activity_main.xmlを使う場合これを宣言する
        setContentView(R.layout.activity_main);

        TextView day = (TextView)findViewById(R.id.TextDay);    //文字を表示させるやつ
        Calendar cl = Calendar.getInstance();       //日付の取得

        Button DispBtn = (Button)findViewById(R.id.settingbutton);//「設定」ボタン
        Button Home = (Button)findViewById(R.id.home);//「ホーム」ボタン
        Button Stock = (Button)findViewById(R.id.bichiku);//「備蓄」ボタン
        Button hijousyoku = (Button)findViewById(R.id.hijousyoku);//「非常食」ボタン


        //非常食へ
        hijousyoku.setOnClickListener(new OnClickListenerClass("com.example.yamashitamasaki.hello_world","com.example.yamashitamasaki.hello_world.Hijousyoku",this));

        //備蓄品へ
        Stock.setOnClickListener( new OnClickListenerClass("com.example.yamashitamasaki.hello_world","com.example.yamashitamasaki.hello_world.Stock",this) );

        //設定画面へ
        DispBtn.setOnClickListener( new OnClickListenerClass("com.example.yamashitamasaki.hello_world","com.example.yamashitamasaki.hello_world.SubActivity",this ) );

        //ここに文字と日付を入れる
        day.setText( cl.get(Calendar.YEAR) + "年" + ( cl.get(Calendar.MONTH) + 1 ) + "月" + cl.get(Calendar.DATE) + "日");

        SharedPreferences pref = getSharedPreferences("Preferences",MODE_PRIVATE);

        TextView hijou_tv = (TextView)findViewById(R.id.hijou_text);
        TextView bichiku_tv = (TextView)findViewById(R.id.bichiku_text);

        //非常食の合計値
        hijou_tv.setText("合計個数：" +
                        (
                                        pref.getInt("retorutogohan_number",0) +
                                        pref.getInt("kandume_number",0) +
                                        pref.getInt("kanmen_number",0) +
                                        pref.getInt("kanpan_number",0) +
                                        pref.getInt("kandume2_number",0) +
                                        pref.getInt("retoruto_number",0) +
                                        pref.getInt("furizu_dorai_number",0) +
                                        pref.getInt("mizu_number",0)+
                                        pref.getInt("pokari_hunmatu_number",0)+
                                        pref.getInt("karori_meito_number",0)+
                                        pref.getInt("okasi_number",0)

                         ) + "個"
        );

        //備蓄品の合計値
        bichiku_tv.setText("合計個数：" +

                (
                         pref.getInt("gas_number",0) +
                         pref.getInt("match_number",0) +
                         pref.getInt("bombe_number",0) +
                         pref.getInt("whistle_number",0) +
                         pref.getInt("shitagi_number",0) +
                         pref.getInt("tissue_number",0) +
                         pref.getInt("almi_number",0) +
                         pref.getInt("gunnte_number",0)
                ) +  "個"
        );
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
}
