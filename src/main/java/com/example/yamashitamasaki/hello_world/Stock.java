package com.example.yamashitamasaki.hello_world;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class Stock extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock);

        //備蓄画像
        ImageView gas_iv = (ImageView)findViewById(R.id.gasView);
        ImageView match_iv = (ImageView)findViewById(R.id.matchView);
        ImageView bombe_iv = (ImageView)findViewById(R.id.bombeView);
        ImageView whistle_iv = (ImageView)findViewById(R.id.whistleView);
        ImageView shitagi_iv = (ImageView)findViewById(R.id.shitagiView);
        ImageView tissue_iv = (ImageView)findViewById(R.id.tissueView);
        ImageView almi_iv = (ImageView)findViewById(R.id.almiView);
        ImageView gunnte_iv = (ImageView)findViewById(R.id.gunnteView);

        // 戻る画面
        Button Home = (Button)findViewById(R.id.homebutton3);//「ホーム」ボタン
        Button DispBtn = (Button)findViewById(R.id.select3);//「設定」ボタン
        Button hijousyoku = (Button)findViewById(R.id.hijousyoku3);//「非常食」ボタン

        Home.setOnClickListener( new OnClickListenerClass(this) );
        hijousyoku.setOnClickListener( new OnClickListenerClass("com.example.yamashitamasaki.hello_world","com.example.yamashitamasaki.hello_world.Hijousyoku",this ) );
        DispBtn.setOnClickListener(new OnClickListenerClass("com.example.yamashitamasaki.hello_world","com.example.yamashitamasaki.hello_world.SubActivity",this));

        //ガスコロン＿ダイアログ
        gas_iv.setOnClickListener( new DialogOnClickListenerClass("ガスコンロ","gas_number",R.drawable.gas , this) );

        //マッチ＿ダイアログ
        match_iv.setOnClickListener( new DialogOnClickListenerClass( "マッチ・ライター","match_number",R.drawable.match, this) );

        //ボンベ
        bombe_iv.setOnClickListener( new DialogOnClickListenerClass( "ガスボンベ","bombe_number",R.drawable.bombe, this) );

        //ふえ
        whistle_iv.setOnClickListener( new DialogOnClickListenerClass( "笛","whistle_number",R.drawable.whistle, this) );

        //下着
        shitagi_iv.setOnClickListener( new DialogOnClickListenerClass( "下着","shitagi_number",R.drawable.shitagi, this) );

        //ティッシュ
        tissue_iv.setOnClickListener( new DialogOnClickListenerClass( "ティッシュ","tissue_number",R.drawable.tissue, this) );

        //アルミ
        almi_iv.setOnClickListener( new DialogOnClickListenerClass( "アルミホイル","almi_number",R.drawable.almi, this) );

        //軍手
        gunnte_iv.setOnClickListener( new DialogOnClickListenerClass( "軍手","gunnte_number",R.drawable.gunnte, this) );
    }
}