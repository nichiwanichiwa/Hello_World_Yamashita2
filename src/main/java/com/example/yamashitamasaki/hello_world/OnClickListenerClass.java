package com.example.yamashitamasaki.hello_world;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

/**
 * Created by yamashita.masaki on 2015/04/30.
 */
//遷移クラス
class OnClickListenerClass implements View.OnClickListener {

    //メンバ変数
    public Intent intent;   //インテント
    public Activity act;    //アクティビティ

    //コンストラクタ(アクティビティのみ)
    public OnClickListenerClass(Activity act) {
        this.act = act; //アクティビティデータの挿入
    }

    //コンストラクタ（引数あり）
    public OnClickListenerClass( String SPackage, String name,Activity act ) {
        intent = new Intent();
        intent.setClassName(SPackage,name);
        //アクティビティを保存しない(維持しない)
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        this.act = act; //アクティビティデータの挿入
    }

    @Override
    public void onClick(View v) {

        if (intent == null) {
            //ホームボタンの時アクティビティを閉じる
            act.finish();
        } else {
            //それ以外はアクティビティを表示
            act.startActivity(intent);
        }
    }

}