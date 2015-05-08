package com.example.yamashitamasaki.hello_world;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.DialogFragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class SubActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        //データの読み込み
        loadInt( (EditText)findViewById(R.id.EditText5) , "youji_people" ); //幼児
        loadInt( (EditText)findViewById(R.id.EditText), "otona_people");    //大人
        loadInt( (EditText)findViewById(R.id.EditText2) , "kobito_people"); //小人
        loadInt( (EditText)findViewById(R.id.EditText3) , "kiniti_day");    //期日
        loadInt( (EditText)findViewById(R.id.EditText4) , "sitei_day");     //設定日数

        // 戻る画面
        Button Home = (Button)findViewById(R.id.homebutton2);          //「ホーム」ボタン
        Button Stock = (Button)findViewById(R.id.bichiku2);           //「備蓄」ボタン
        Button hijousyoku = (Button)findViewById(R.id.hijousyoku2);  //「非常食」ボタン

        Home.setOnClickListener( new OnClickListenerClass() );
        Stock.setOnClickListener( new OnClickListenerClass("com.example.yamashitamasaki.hello_world","com.example.yamashitamasaki.hello_world.Stock") );
        hijousyoku.setOnClickListener( new OnClickListenerClass("com.example.yamashitamasaki.hello_world","com.example.yamashitamasaki.hello_world.Hijousyoku") );

    }

    //遷移クラス
    class OnClickListenerClass implements View.OnClickListener {

        public Intent intent;

        public OnClickListenerClass(){}

        public OnClickListenerClass( String SPackage, String name ) {
            intent = new Intent();
            intent.setClassName(SPackage,name);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        }

        @Override
        public void onClick(View v) {

            //値に問題がないかチェック
            if(

                    saveInt( (EditText)findViewById(R.id.EditText5)  , "youji_people")   &&
                            saveInt( (EditText)findViewById(R.id.EditText)  , "otona_people")   &&
                            saveInt( (EditText)findViewById(R.id.EditText2) , "kobito_people")  &&
                            saveInt( (EditText)findViewById(R.id.EditText3) , "kiniti_day")     &&
                            saveInt( (EditText)findViewById(R.id.EditText4) , "sitei_day")
            ) {
                if(intent == null) {
                    //ホームボタンの時アクティビティを閉じる
                    finish();
                }else{
                    //それ以外はアクティビティを表示
                    startActivity(intent);
                }
            }
            else {
                //歯抜けがあれば、警告文を表示する
                Builder alertDialog = new Builder(SubActivity.this);

                alertDialog.setTitle("Error");
                alertDialog.setMessage("値が入力されていません");

                alertDialog.setPositiveButton("はい", null);

                alertDialog.create();
                alertDialog.show();
            }
        }
    }

    //値データを取り出す関数
    public void  loadInt( EditText et , String name )
    {
        SharedPreferences pref =
                getSharedPreferences("Preferences",MODE_PRIVATE);
        //(保存している値の名前,何も入っていなかった時の初期値)
        int i = pref.getInt( name, 0 );
        //値を文字に変換して挿入
        String str = String.valueOf(i);
        et.setText(str);
    }

    //値データをプレファレンスで保存する関数 (エディットテキスト、名前)
    // true: 成功 false: 失敗
    public boolean saveInt( EditText et, String name )
    {
        //プレファレンスの生成
        SharedPreferences pref;
        pref = getSharedPreferences("Preferences",MODE_PRIVATE);//棚を作る
        Editor e = pref.edit();

        //int型に変換して挿入
        String str = et.getText().toString();

        //何も入っていないとき、失敗
        if( str.length() <= 0 )
        {
            str = "0";
        }

        int i = Integer.parseInt(str);

        //名前をつけて値を格納
        e.putInt( name, i );

        //Preferences棚に保管する
        e.commit();

        // 成功である
        return true;
    }

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sub, menu);
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
    */
}