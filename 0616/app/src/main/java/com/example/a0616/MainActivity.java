package com.example.a0616;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int win = 0;
    int draw = 0;
    int lose = 0;

    int winflg = 0;
    int drawflg = 0;
    int loseflg = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView text = (TextView)findViewById(R.id.text);
        final TextView cpu2 = (TextView)findViewById(R.id.cpu2);
        final TextView you2 = (TextView)findViewById(R.id.you2);
        final TextView title = (TextView)findViewById(R.id.title);

        final ImageView rock = (ImageView)findViewById(R.id.rock);
        final ImageView scissors = (ImageView)findViewById(R.id.scissors);
        final ImageView paper = (ImageView)findViewById(R.id.paper);

        final ImageView rockyou = (ImageView) findViewById(R.id.rockyou);
        final ImageView rockcpu = (ImageView) findViewById(R.id.rockcpu);
        final ImageView sciyou = (ImageView) findViewById(R.id.sciyou);
        final ImageView scicpu = (ImageView) findViewById(R.id.scicpu);
        final ImageView papyou = (ImageView) findViewById(R.id.papyou);
        final ImageView papcpu = (ImageView) findViewById(R.id.papcpu);

        final ImageView winimg = (ImageView) findViewById(R.id.winimg);
        final ImageView loseimg = (ImageView) findViewById(R.id.loseimg);
        final ImageView drawimg = (ImageView) findViewById(R.id.drawimg);

        final TextView WinC = (TextView)findViewById(R.id.WinC);
        final TextView DrawC = (TextView)findViewById(R.id.DrawC);
        final TextView LoseC = (TextView)findViewById(R.id.LoseC);

        // グーボタン押下時の処理
        rock.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {
                title.setVisibility(View.INVISIBLE);
                //　画像非表示処理　※自分のグーの画像のみ表示
                rockcpu.setVisibility(View.INVISIBLE);
                scicpu.setVisibility(View.INVISIBLE);
                papcpu.setVisibility(View.INVISIBLE);

                rockyou.setVisibility(View.VISIBLE);
                sciyou.setVisibility(View.INVISIBLE);
                papyou.setVisibility(View.INVISIBLE);

                cpu2.setVisibility(View.INVISIBLE);
                you2.setVisibility(View.INVISIBLE);

                winimg.setVisibility(View.INVISIBLE);
                loseimg.setVisibility(View.INVISIBLE);
                drawimg.setVisibility(View.INVISIBLE);

                int opponentHand = decideOpponentHand();
                String opponentHandText = changeTextOpponentHand(opponentHand);
                decideGame(1, opponentHand, title);

                // じゃんけんの出した手の表示は画像表示に変更のためコメント化
                // cpu2.setText(opponentHandText);
                // you2.setText("グー");

                //　勝利、引き分け、敗北数カウント
                WinC.setText(String.valueOf(win));
                DrawC.setText(String.valueOf(draw));
                LoseC.setText(String.valueOf(lose));

                // じゃんけんの相手の出した手表示
                if (winflg == 1){
                    // じゃんけん勝利時に通知を行う
                    normalNotification();
                    winflg = 0;
                    scicpu.setVisibility(View.VISIBLE);
                    winimg.setVisibility(View.VISIBLE);
                }else if (loseflg == 1){
                    loseflg = 0;
                    papcpu.setVisibility(View.VISIBLE);
                    loseimg.setVisibility(View.VISIBLE);
                }else if (drawflg == 1){
                    drawflg = 0;
                    rockcpu.setVisibility(View.VISIBLE);
                    drawimg.setVisibility(View.VISIBLE);
                }
            }
        });

        // チョキボタン押下時の処理
        scissors.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {
                title.setVisibility(View.INVISIBLE);
                //　画像非表示処理　※自分のチョキの画像のみ表示
                rockcpu.setVisibility(View.INVISIBLE);
                scicpu.setVisibility(View.INVISIBLE);
                papcpu.setVisibility(View.INVISIBLE);

                rockyou.setVisibility(View.INVISIBLE);
                sciyou.setVisibility(View.VISIBLE);
                papyou.setVisibility(View.INVISIBLE);

                cpu2.setVisibility(View.INVISIBLE);
                you2.setVisibility(View.INVISIBLE);

                winimg.setVisibility(View.INVISIBLE);
                loseimg.setVisibility(View.INVISIBLE);
                drawimg.setVisibility(View.INVISIBLE);

                int opponentHand = decideOpponentHand();
                String opponentHandText = changeTextOpponentHand(opponentHand);
                decideGame(2, opponentHand, title);

                // じゃんけんの出した手の表示は画像表示に変更のためコメント化
                // cpu2.setText(opponentHandText);
                // you2.setText("グー");

                //　勝利、引き分け、敗北数カウント
                WinC.setText(String.valueOf(win));
                DrawC.setText(String.valueOf(draw));
                LoseC.setText(String.valueOf(lose));

                // じゃんけんの相手の出した手表示
                if (winflg == 1){
                    // じゃんけん勝利時に通知を行う
                    normalNotification();
                    winflg = 0;
                    papcpu.setVisibility(View.VISIBLE);
                    winimg.setVisibility(View.VISIBLE);
                }else if (loseflg == 1){
                    rockcpu.setVisibility(View.VISIBLE);
                    loseflg = 0;
                    loseimg.setVisibility(View.VISIBLE);
                }else if (drawflg == 1){
                    drawflg = 0;
                    scicpu.setVisibility(View.VISIBLE);
                    drawimg.setVisibility(View.VISIBLE);
                }
            }
        });

        // パーボタン押下時の処理
        paper.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {
                title.setVisibility(View.INVISIBLE);
                //　画像非表示処理　※自分のパーの画像のみ表示
                rockcpu.setVisibility(View.INVISIBLE);
                scicpu.setVisibility(View.INVISIBLE);
                papcpu.setVisibility(View.INVISIBLE);

                rockyou.setVisibility(View.INVISIBLE);
                sciyou.setVisibility(View.INVISIBLE);
                papyou.setVisibility(View.VISIBLE);

                cpu2.setVisibility(View.INVISIBLE);
                you2.setVisibility(View.INVISIBLE);

                winimg.setVisibility(View.INVISIBLE);
                loseimg.setVisibility(View.INVISIBLE);
                drawimg.setVisibility(View.INVISIBLE);

                int opponentHand = decideOpponentHand();
                String opponentHandText = changeTextOpponentHand(opponentHand);
                decideGame(3, opponentHand, title);

                // じゃんけんの出した手の表示は画像表示に変更のためコメント化
                // cpu2.setText(opponentHandText);
                // you2.setText("グー");

                //　勝利、引き分け、敗北数カウント
                WinC.setText(String.valueOf(win));
                DrawC.setText(String.valueOf(draw));
                LoseC.setText(String.valueOf(lose));

                // じゃんけんの相手の出した手表示
                if (winflg == 1){
                    // じゃんけん勝利時に通知を行う
                    normalNotification();
                    winflg = 0;
                    rockcpu.setVisibility(View.VISIBLE);
                    winimg.setVisibility(View.VISIBLE);
                }else if (loseflg == 1){
                    loseflg = 0;
                    scicpu.setVisibility(View.VISIBLE);
                    loseimg.setVisibility(View.VISIBLE);
                }else if (drawflg == 1){
                    drawflg = 0;
                    papcpu.setVisibility(View.VISIBLE);
                    drawimg.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    // 相手の手のテキスト表示用
    String changeTextOpponentHand(int hand) {
        String handText = "";
        if (hand == 1) {
            handText = "グー";
        } else if (hand == 2) {
            handText = "チョキ";
        } else if (hand == 3) {
            handText = "パー";
        }
        return handText;
    }

    // Randamを利用して相手のじゃんけんの手を出力
    int decideOpponentHand() {
        Random rnd = new Random();
        int hand = rnd.nextInt(3)+1;
        return hand;
    }

    // じゃんけんの手の計算用
    void decideGame(int playerHand, int opponentHand, TextView decidetext) {
        String decision;

        if(playerHand == opponentHand) {
            decision = "あいこ";
            draw = draw + 1;
            drawflg = 1;
        } else if((playerHand == 3 && opponentHand == 1) || (playerHand+1 == opponentHand)) {
            decision = "あなたの勝ち";
            win = win + 1;
            winflg = 1;
        } else {
            decision = "あなたの負け";
            lose = lose + 1;
            loseflg = 1;
        }
        decidetext.setText(decision);
    }

    // 以下通知発行用処理を分離
    private void normalNotification(){
        Notification notification1 = null;
        Notification notification2 = null;
        // システムから通知マネージャー取得
        final NotificationManager notificationManager =
                (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        // アプリ名をチャンネルIDとして利用
        String chID = getString(R.string.app_name);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {     //APIが「26」以上の場合
            //通知チャンネルIDを生成してインスタンス化
            NotificationChannel notificationChannel = new NotificationChannel(chID, chID, NotificationManager.IMPORTANCE_DEFAULT);
            //通知の説明のセット
            notificationChannel.setDescription(chID);
            //通知チャンネルの作成
            notificationManager.createNotificationChannel(notificationChannel);
            //通知の生成と設定とビルド
            notification1 = new Notification.Builder(this, chID)
                    .setContentTitle("勝利のお知らせ")  //通知タイトル
                    .setContentText("じゃんけん勝利")        //通知内容
                    .setSmallIcon(R.drawable.icon)                  //通知用アイコン
                    .build();                                       //通知のビルド
            notification2 = new Notification.Builder(this, chID)
                    .setContentTitle("勝利のお知らせ")  //通知タイトル
                    .setContentText("じゃんけん通算" + win + "勝達成")        //通知内容
                    .setSmallIcon(R.drawable.icon)                  //通知用アイコン
                    .build();                                       //通知のビルド
        }
        // 5の倍数勝利したときだけ通知メッセージを変える
        if (win % 5 == 0){notificationManager.notify(1, notification2);}
        else{notificationManager.notify(1, notification1); }
    }
}