package com.example.lebronjames.orderingsystem;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ActMain extends AppCompatActivity {

    //UI屬性
    private EditText etBlackTeaQuantity;
    private EditText etGreenTeaQuantity;
    private EditText etMilkTeaQuantity;

    private TextView tvBlackTeaPrice;
    private TextView tvGreenTeaPrice;
    private TextView tvMilkTeaPrice;

    private TextView tvTotal;

    private Button btnOk;

    static int BLACK_TEA_PRICE;
    static int GREEN_TEA_PRICE;
    static int MILK_TEA_PRICE;


    int blackTeaSum=0;
    int greenTeaSum=0;
    int milkTeaSum=0;

    int totalBlackTea = 0;
    int totalGreenTea = 0;
    int totalMilkTea = 0;
    int total = 0;

    private TextWatcher etBlackTeaQuantity_click = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String regex = "[0-9]+";
            String val = s.toString();

            if(val.matches(regex)){
                blackTeaSum = Integer.parseInt(s.toString());

            }
            else if(val.equals("")){
                Log.d("ActMain","Delete action");

                //使用者按下刪除後，價錢要從算
                blackTeaSum = 0;
                totalBlackTea = 0;
            }
            else {
                Log.d("ActMain","output:" + val);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(ActMain.this,"請輸入數字1~9",Toast.LENGTH_SHORT).show();
                    }
                });


            }

        }



        @Override
        public void afterTextChanged(Editable s) {
            int total = blackTeaSum * BLACK_TEA_PRICE;
            totalBlackTea = total;
            ShowTotal(totalBlackTea,totalGreenTea,totalMilkTea);
        }
    };

    private TextWatcher etGreenTeaQuantity_click = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            greenTeaSum=0;
            String regex = "[0-9]+";
            String val = s.toString();

            if(val.matches(regex)){
                greenTeaSum = Integer.parseInt(s.toString());
            }
            else if(val.equals("")){
                Log.d("ActMain","Delete action");
                //使用者按下刪除後，價錢要從算
                greenTeaSum = 0;
                totalGreenTea = 0;
            }
            else {
                Log.d("ActMain","output:" + val);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(ActMain.this,"請輸入數字1~9",Toast.LENGTH_SHORT).show();
                    }
                });

            }
        }



        @Override
        public void afterTextChanged(Editable s) {
            int total = greenTeaSum * GREEN_TEA_PRICE;
            totalGreenTea = total;
            ShowTotal(totalBlackTea,totalGreenTea,totalMilkTea);
        }
    };

    private TextWatcher etMilkTeaQuantity_click = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            milkTeaSum=0;
            String regex = "[0-9]+";
            String val = s.toString();
            if(val.matches(regex)){
                milkTeaSum = Integer.parseInt(s.toString());
//                ShowTotal(milkTeaSum,MILK_TEA_PRICE);
            }
            else if(val.equals("")){
                Log.d("ActMain","Delete action");

                milkTeaSum = 0;
                totalMilkTea = 0;
            }
            else {
                Log.d("ActMain","output:" + val);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(ActMain.this,"請輸入數字1~9",Toast.LENGTH_SHORT).show();
                    }
                });

                //clear();

            }
        }



        @Override
        public void afterTextChanged(Editable s) {

            int total = milkTeaSum * MILK_TEA_PRICE;
            totalMilkTea = total;
            ShowTotal(totalBlackTea,totalGreenTea,totalMilkTea);
        }
    };



    //顯示總價錢
    public void ShowTotal(int tBlackTea,int tGreenTea , int tMilkTea){
        total = tBlackTea + tGreenTea + tMilkTea;

        tvTotal.setText(total + "");

    }

    public void clear(){
        etBlackTeaQuantity.getText().clear();
        etGreenTeaQuantity.getText().clear();
        etMilkTeaQuantity.getText().clear();
        tvTotal.setText("0");
        total = 0;

    }




    //自動產生時間
    private String AutomaticGenerationTime(){
        Calendar calendar = Calendar.getInstance() ;//取得當下時間//先行定義時間格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//取得現在時間
        Date dt=new Date();//透過SimpleDateFormat的format方法將Date轉為字串
        return sdf.format(dt);
    }



    //列印訂單
    private View.OnClickListener btnOk_click=new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (v.getId() == R.id.btnOk){

                //自動產生時間
                String dts = AutomaticGenerationTime();

                String item="";
                if(blackTeaSum > 0)
                    item += "紅茶 x" + blackTeaSum + "   $" + totalBlackTea + "\n";
                if(greenTeaSum > 0)
                    item += "綠茶 x" + greenTeaSum + "   $" + totalGreenTea + "\n";
                if(milkTeaSum > 0)
                    item += "奶茶 x" + milkTeaSum + "   $" + totalMilkTea + "\n";
                String totalPrice = "\n" + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t總價 : " + total + "\n";
                String buyDate = "購買日期 : " + dts + "\n";

                String content = item + totalPrice + buyDate;

                new AlertDialog.Builder(ActMain.this)
                        .setTitle("列印訂購單")  //設定dialog 的title顯示內容
                        .setIcon(null) //設定dialog 的ICON
                        .setMessage(content)//設定dialog 的主要訊息內容
                        .setCancelable(false) //關閉 Android 系統的主要功能鍵(menu,home等...)
                        .setPositiveButton("是", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(ActMain.this,"Detail Inserted",Toast.LENGTH_LONG).show();
                                clear();
                            }

                        })
                        .show();
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
        InitialComponent();
        String p1 = tvBlackTeaPrice.getText().toString();
        BLACK_TEA_PRICE = Integer.parseInt(p1);

        String p2 = tvGreenTeaPrice.getText().toString();
        GREEN_TEA_PRICE = Integer.parseInt(p2);

        String p3 = tvMilkTeaPrice.getText().toString();
        MILK_TEA_PRICE = Integer.parseInt(p3);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("ACT_DEMO","onStart() 事件被觸發");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("ACT_DEMO","onRestart() 事件被觸發");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("ACT_DEMO","onResume() 事件被觸發");


    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("ACT_DEMO","onPause() 事件被觸發");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("ACT_DEMO","onStop() 事件被觸發");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("ACT_DEMO","onDestroy() 事件被觸發");
    }


    private void InitialComponent() {
        tvTotal = findViewById(R.id.tvTotal);
        btnOk = findViewById(R.id.btnOk);
        btnOk.setOnClickListener(btnOk_click);
        tvBlackTeaPrice = findViewById(R.id.tvBlackTeaPrice);
        tvGreenTeaPrice = findViewById(R.id.tvGreenTeaPrice);
        tvMilkTeaPrice = findViewById(R.id.tvMilkTeaPrice);
        etBlackTeaQuantity = findViewById(R.id.etBlackTeaQuantity);
        etBlackTeaQuantity.addTextChangedListener(etBlackTeaQuantity_click);
        etGreenTeaQuantity = findViewById(R.id.etGreenTeaQuantity);
        etGreenTeaQuantity.addTextChangedListener(etGreenTeaQuantity_click);
        etMilkTeaQuantity = findViewById(R.id.etMilkTeaQuantity);
        etMilkTeaQuantity.addTextChangedListener(etMilkTeaQuantity_click);
    }
}
















