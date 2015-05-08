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
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import java.util.Calendar;
import java.util.Date;
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

        ImageButton hijousyoku_ib = (ImageButton)findViewById(R.id.L_graph);
        ImageButton bichiku_ib = (ImageButton)findViewById(R.id.R_graph);
        //非常食へ
        hijousyoku.setOnClickListener(new OnClickListenerClass("com.example.yamashitamasaki.hello_world","com.example.yamashitamasaki.hello_world.Hijousyoku",this));
        hijousyoku_ib.setOnClickListener(new OnClickListenerClass("com.example.yamashitamasaki.hello_world","com.example.yamashitamasaki.hello_world.Hijousyoku",this));

        //備蓄品へ
        Stock.setOnClickListener( new OnClickListenerClass("com.example.yamashitamasaki.hello_world","com.example.yamashitamasaki.hello_world.Stock",this) );
        bichiku_ib.setOnClickListener( new OnClickListenerClass("com.example.yamashitamasaki.hello_world","com.example.yamashitamasaki.hello_world.Stock",this) );
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

        //TODO:表示テスト
        TextView kigen_tv = (TextView)findViewById(R.id.kigen2);
        kigen_tv.setText( current_date("retorutogohan_number_pref","レトルトごはん") );

        DialogOnClickListenerClass listener = new DialogOnClickListenerClass("ｲﾗｯｼｬｲﾏｾ",
                  current_date("retorutogohan_number_pref","レトルトごはん") + "\n"
                + current_date("kandume_number_pref","缶詰") + "\n"
                + current_date("kanmen_number_number_pref","乾麺") + "\n"
                + current_date("kanpan_number_pref","カンパン")

                ,this);
   }



    //賞味期限メソッド（プレファレンスの名前、食品名）
    public String current_date(String prefName, String name)
    {
        SharedPreferences pref2 = getSharedPreferences(prefName,MODE_PRIVATE);
        //現在の時刻
        Calendar cl = Calendar.getInstance();
        //引数で指定した食品の賞味期限
        Calendar cl2 = Calendar.getInstance();
        cl2.set( pref2.getInt("year", cl.get(Calendar.YEAR) ), pref2.getInt("month", cl.get(Calendar.MONTH) ), pref2.getInt("day", cl.get(Calendar.DAY_OF_MONTH) ) );
        int i = cl2.compareTo(cl);//cl2 - cl
        Date date1 = cl.getTime();
        Date date2 = cl2.getTime();

        long current_time = date1.getTime();
        long retorutogohan_time = date2.getTime();

        long nokori = (retorutogohan_time - current_time) / ( 1000 * 60 * 60 * 24 );

        return ( name + "の賞味期限はあと" + String.valueOf(nokori) + "日です。" );
    }
}
