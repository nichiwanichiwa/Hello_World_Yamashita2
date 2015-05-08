package com.example.yamashitamasaki.hello_world;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;

class DialogOnClickListenerClass implements View.OnClickListener {

    String TitleName;   //ダイアログのタイトル
    String prefName;    //プレファレンスに保管されている値の名前
    int img_id;         //イメージ画像のID番号
    Activity act;        //アクティビティ

    //コンストラクタ
    public DialogOnClickListenerClass(String TitleName, String prefName, int img_id, Activity act) {
        this.TitleName  = TitleName;
        this.prefName   = prefName;
        this.img_id     = img_id;
        this.act = act;
    }

    //クリックを押したときの処理
    @Override
    public void onClick(View v) {
        //ダイアログの生成
        AlertDialog.Builder alert;
        //指定したアクティビティを格納
        alert = new AlertDialog.Builder(act);
        alert.setTitle(TitleName);

        //元のアクティビティの場所を指定
        LayoutInflater inflater = LayoutInflater.from(act);
        //final その変数は変更不可能になる
        final View viw = inflater.inflate(R.layout.activity_popup, null);
        //イメージ画像を取得
        ImageView img = (ImageView)viw.findViewById(R.id.imageView);
        //画像を変更
        img.setImageResource(img_id);

        final TextView day = (TextView)viw.findViewById(R.id.popup_day);    //文字を表示させるやつ
        Calendar cl = Calendar.getInstance();       //日付の取得。インスタンス（実体）を取得
        day.setText( loadString(prefName) );

        //テキストビューを取得する
        TextView otona_tv = (TextView)viw.findViewById(R.id.otona);
        TextView kobito_tv = (TextView)viw.findViewById(R.id.kobito);
        TextView youji_tv = (TextView)viw.findViewById(R.id.youji);

        otona_tv.setText("大人"  + loadInt("otona_people")  + "人分");
        kobito_tv.setText("小人" + loadInt("kobito_people") + "人分");
        youji_tv.setText("幼児"  + loadInt("youji_people")  + "人分");

        //EditTextを取得する
        final EditText et = (EditText)viw.findViewById(R.id.Number);

        //指定したEditTextの中に値を挿入する
        et.setText(loadInt(prefName));

        //非常食画面の時は「～食」に変更
        if( act.getClass() == Hijousyoku.class )
        {
            TextView tv = (TextView) viw.findViewById(R.id.textView26);
            tv.setText("食");
        }

        ImageView Clock_iv = (ImageView)viw.findViewById(R.id.imageCalender);

        //日付ダイアログの初期化処理
        final DatePickerDialog dpd = new DatePickerDialog(act,
                new DatePickerDialog.OnDateSetListener(){
                    //「設定」ボタンを押すと発生するイベント
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        //設定した日付を格納する
                        day.setText(String.valueOf(year) + "年" + ( String.valueOf(monthOfYear+1)  ) + "月" + String.valueOf(dayOfMonth) + "日");
                    }
                },
                cl.get(Calendar.YEAR),
                cl.get(Calendar.MONTH),
                cl.get(Calendar.DAY_OF_MONTH)
                );

        Clock_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dpd.show();
            }
        });

        //決定ボタンを押すと行われる処理
        alert.setPositiveButton("OK",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //値が格納されてないか判定する
                if(et.getText().toString().length() <= 0) {
                    //カラッポである
                    AlertDialog.Builder  null_alert = new AlertDialog.Builder(act);
                    null_alert.setTitle("入力エラー");
                    null_alert.setMessage("値が入力されていません");
                    null_alert.setPositiveButton("OK",null);
                    null_alert.show();
                }
                else {
                    //正常である
                    //データを保存する
                    saveInt(et, prefName);
                }
                //日付を保存する
                saveString(day,prefName);
            }
        });

        alert.setView(viw);
        alert.show();
    }

    //値データを取得する関数
    public String loadInt( String name )
    {
        //プリファレンスの生成
        SharedPreferences pref =
                act.getSharedPreferences("Preferences",act.MODE_PRIVATE);
        int i = 0;
        i = pref.getInt(name,0);
        String str = String.valueOf(i);

        return str;
    }

    //値データをプレファレンスで保存する関数 (エディットテキスト、名前)
    public void saveInt( EditText et, String name )
    {
        //プリファレンスの生成
        SharedPreferences pref =
                act.getSharedPreferences("Preferences", act.MODE_PRIVATE);

        //挿入できるようにする
        SharedPreferences.Editor e = pref.edit();

        //String型で文字列を入手
        String str = et.getText().toString();

        //文字列を値に変換
        int i = Integer.parseInt(str);

        //値を挿入
        e.putInt(name, i);

        //保存
        e.commit();
    }

    public String loadString( String name )
    {
        SharedPreferences pref =
                act.getSharedPreferences("Preferences2", act.MODE_PRIVATE);

        //文字がなければ、現在の日付をString型に変換した上で格納させる
        String str = pref.getString(name,
                        String.valueOf(Calendar.getInstance().get(Calendar.YEAR) ) + "年" +
                        String.valueOf(Calendar.getInstance().get(Calendar.MONTH)+1 ) + "月" +
                        String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_MONTH) ) + "日"
        );

        return str;
    }

    //文字列を格納する
    public void saveString( TextView tv, String name )
    {
        SharedPreferences pref =
                act.getSharedPreferences("Preferences2", act.MODE_PRIVATE);

        SharedPreferences.Editor e = pref.edit();

        String str = tv.getText().toString();

        e.putString(name, str );

        e.commit();
    }
}