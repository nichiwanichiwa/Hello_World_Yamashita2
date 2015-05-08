package com.example.yamashitamasaki.hello_world;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class Hijousyoku extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hijousyoku);

        // それぞれのボタンの情報を取得
        Button Home = (Button)findViewById(R.id.homebutton3);   //「ホーム」ボタン
        Button DispBtn = (Button)findViewById(R.id.select4);    //「設定」ボタン
        Button Stock = (Button)findViewById(R.id.bichiku4);     //「備蓄」ボタン

        //各イメージビューの取得
        //主食
        ImageView retorutogohan_iv = (ImageView)findViewById(R.id.retoruto_gohan);
        ImageView kandume_iv = (ImageView)findViewById(R.id.kandume);
        ImageView kanmen_iv = (ImageView)findViewById(R.id.kanmen);
        ImageView kanpan_iv = (ImageView)findViewById(R.id.kanpan);

        //主菜
        ImageView kandume2_iv = (ImageView)findViewById(R.id.kandume2);
        ImageView retoruto_iv = (ImageView)findViewById(R.id.retoruto);
        ImageView furizu_dorai_iv = (ImageView)findViewById(R.id.furizu_dorai);

        //その他
        ImageView mizu_iv = (ImageView)findViewById(R.id.mizu);
        ImageView pokari_hunmatu_iv = (ImageView)findViewById(R.id.pokari_hunmatu);
        ImageView karori_meito_iv = (ImageView)findViewById(R.id.karori_meito);
        ImageView okasi_iv = (ImageView)findViewById(R.id.okasi);


        // 場所を指定する
        Home.setOnClickListener( new OnClickListenerClass(this) );
        Stock.setOnClickListener( new OnClickListenerClass("com.example.yamashitamasaki.hello_world","com.example.yamashitamasaki.hello_world.Stock",this) );
        DispBtn.setOnClickListener(new OnClickListenerClass("com.example.yamashitamasaki.hello_world","com.example.yamashitamasaki.hello_world.SubActivity",this));

        //ボタンアクションの処理
        //主食
        retorutogohan_iv.setOnClickListener( new DialogOnClickListenerClass("レトルトごはん", "retorutogohan_number", R.drawable.retoruto_gohan, this ) );
        kandume_iv.setOnClickListener( new DialogOnClickListenerClass("缶詰", "kandume_number", R.drawable.kandume, this ) );
        kanmen_iv.setOnClickListener( new DialogOnClickListenerClass("乾麺", "kanmen_number", R.drawable.kanmen, this ) );
        kanpan_iv.setOnClickListener( new DialogOnClickListenerClass("乾パン", "kanpan_number", R.drawable.kanpan, this ) );

        //主菜
        kandume2_iv.setOnClickListener( new DialogOnClickListenerClass("缶詰", "kandume2_number", R.drawable.kandume, this ) );
        retoruto_iv.setOnClickListener( new DialogOnClickListenerClass("レトルト食品", "retoruto_number", R.drawable.retoruto, this ) );
        furizu_dorai_iv.setOnClickListener( new DialogOnClickListenerClass("フリーズドライ", "furizu_dorai_number", R.drawable.furizu_dorai, this ) );

        //その他
        mizu_iv.setOnClickListener( new DialogOnClickListenerClass("水", "mizu_number", R.drawable.mizu, this ) );
        pokari_hunmatu_iv.setOnClickListener( new DialogOnClickListenerClass("ポカリ粉末", "pokari_hunmatu_number", R.drawable.pokari_hunmatu, this ) );
        karori_meito_iv.setOnClickListener( new DialogOnClickListenerClass("カロリーメイト", "karori_meito_number", R.drawable.karori_meito, this ) );
        okasi_iv.setOnClickListener( new DialogOnClickListenerClass("お菓子", "okasi_number", R.drawable.okasi, this ) );


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_hijousyoku, menu);
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