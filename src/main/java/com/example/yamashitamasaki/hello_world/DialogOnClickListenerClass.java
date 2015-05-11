package com.example.yamashitamasaki.hello_world;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.nfc.NfcAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

public class DialogOnClickListenerClass implements View.OnClickListener {

    String TitleName;   //ダイアログのタイトル
    String prefName;    //プレファレンスに保管されている値の名前
    int img_id;         //イメージ画像のID番号
    Activity act;        //アクティビティ

    //コンストラクタ（単にダイアログを表示させたい場合）
    public DialogOnClickListenerClass(String TitleName, String Message, Activity act)
    {
        AlertDialog.Builder alert;
        alert = new AlertDialog.Builder(act);
        alert.setTitle(TitleName);

        //TextViewを作って、文字操作をする
        TextView tv = new TextView(act);
        tv.setTextSize(12);
        tv.setText(Message);

        //TextViewをダイアログで表示させる
        alert.setView(tv);

        alert.show();

    }

    //コンストラクタ
    public DialogOnClickListenerClass(String TitleName, String prefName, int img_id, Activity act) {
        this.TitleName  = TitleName;
        this.prefName   = prefName;
        this.img_id     = img_id;
        this.act         = act;
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

        //日付処理
        final TextView day = (TextView)viw.findViewById(R.id.popup_day);
        final Calendar cl = loadCalendar(prefName);       //日付の取得。インスタンス（実体）を取得
        day.setText( String.valueOf(cl.get(Calendar.YEAR)) + "年" + String.valueOf(cl.get(Calendar.MONTH)+1) + "月" + String.valueOf(cl.get(Calendar.DAY_OF_MONTH)) + "日" );

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

        //非常食画面の時に行う処理
        if( act.getClass() == Hijousyoku.class )
        {
            //文字を変える
            TextView tv = (TextView) viw.findViewById(R.id.textView26);
            tv.setText("食");

            //カレンダー画像の取得
            ImageView Clock_iv = (ImageView)viw.findViewById(R.id.imageCalender);

            //日付ダイアログの初期化処理
            final DatePickerDialog dpd = new DatePickerDialog(act,
                    new DatePickerDialog.OnDateSetListener(){
                        //「設定」ボタンを押すと発生するイベント
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            //設定した日付を格納する
                            day.setText(String.valueOf(year) + "年" + ( String.valueOf(monthOfYear+1)  ) + "月" + String.valueOf(dayOfMonth) + "日");
                            cl.set(year,monthOfYear,dayOfMonth);
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

        }else{
            //非表示にする
            ImageView Clock_iv = (ImageView)viw.findViewById(R.id.imageCalender);
            Clock_iv.setVisibility(View.GONE);
            day.setVisibility(View.GONE);
        }

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
                    //正常なので、
                    //データを保存する
                    saveInt(et, prefName);
                }
                if( act.getClass() == Hijousyoku.class ) {
                    //日付を保存する
                    saveCalendar(cl, prefName);
                }
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

    //日付を取得する
    public Calendar loadCalendar( String name )
    {
        SharedPreferences pref =
                act.getSharedPreferences( name + "_pref", act.MODE_PRIVATE);
        Calendar cl = Calendar.getInstance();
        cl.set( pref.getInt("year", cl.get(Calendar.YEAR) ), pref.getInt("month", cl.get(Calendar.MONTH) ), pref.getInt("day", cl.get(Calendar.DAY_OF_MONTH) ) );

        return cl;
    }

    //日付を格納する
    public void saveCalendar( Calendar cl , String name )
    {
        SharedPreferences pref =
                act.getSharedPreferences( name + "_pref", act.MODE_PRIVATE);

        SharedPreferences.Editor e = pref.edit();

        e.putInt("year", cl.get(Calendar.YEAR));
        e.putInt("month", cl.get(Calendar.MONTH) );
        e.putInt("day", cl.get(Calendar.DAY_OF_MONTH) );

        e.commit();

    }
}