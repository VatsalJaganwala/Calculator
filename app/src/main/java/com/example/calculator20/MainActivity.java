package com.example.calculator20;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    TextView output;
    TextView sign;
    boolean addition=false;
    boolean subtraction = false;
    boolean multiplication = false;
    boolean division = false;
    DecimalFormat df = new DecimalFormat("#.##");


    float[] temp =new float[2];
    String ans;
    String op="0";
    int i=0;
    int dig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        for (float i :temp) {
            i =0;
        }
        setContentView(R.layout.activity_main);
    }
    public void onNumberClick(View view){
//        Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();
        Button button = (Button) view;
        String num = button.getText().toString();
        Log.d(TAG, "onNumberClick: "+num);
        if(dig==9)
        {
            Toast.makeText(this, "Max 9 digit number allowed", Toast.LENGTH_SHORT).show();
        }
        else if(num.equals(".")){
//            if(op="0"){
//                op
//            }

            if(op.indexOf('.')==-1){
                op = op+num;
                output = findViewById(R.id.output);
                output.setText(op);
                if(temp[i]!=0) temp[i]=Float.parseFloat(op);
            }
//            else Toast.makeText(this, "SYNTAX ERROR", Toast.LENGTH_SHORT).show();
        }
        else
        {
            if(op.equals("0")) op="";
//            if(dig==0) op="";
            dig++;
            op = op + num;
//            Toast.makeText(this, op, Toast.LENGTH_SHORT).show();
            output = findViewById(R.id.output);
            output.setText(op);
            temp[i]=Float.parseFloat(op);
        }

    }
    public void clear(View view){
        Log.d(TAG, "clear: function called");
        i=0;
        for(float obj : temp) {
//            Toast.makeText(this, String.valueOf(obj) + " Cleared", Toast.LENGTH_SHORT).show();
            obj = 0;
        }
        Log.d(TAG, "clear: Array cleared");
        dig=0;
        output=findViewById(R.id.output);
        Log.d(TAG, "clear: output Identified");
        op="0";
        output.setText(op);
        Log.d(TAG, "clear: Output set");
        sign=findViewById(R.id.output2);
        sign.setText("");
    }
    public void symbolPressed(View view){
        
        Button button = (Button) view;
        String symbol = button.getText().toString();
        Log.d(TAG, "symbolPressed: "+symbol);
        if(temp[i]!=0){
            if (i < 1) {

//            Toast.makeText(this, String.valueOf(temp[i]), Toast.LENGTH_SHORT).show();
                i++;
                dig = 0;
                sign = findViewById(R.id.output2);
                if (symbol.equals("+")) {
                    sign.setText("+");
                    addition = true;
                } else if (symbol.equals("-")) {
                    sign.setText("-");
                    subtraction = true;
                } else if (symbol.equals("×")) {
                    sign.setText("×");
                    multiplication = true;
                }
                if (symbol.equals("÷")) {
                    sign.setText("÷");
                    division = true;
                }
                op = "0";
                output.setText("");
//            temp[i]=0;
            } else
                Toast.makeText(this, "You can only enter two number at a time", Toast.LENGTH_SHORT).show();
        }

    }
    public void answer(View view){
        Log.d(TAG, "answer: ");
//        Toast.makeText(this, String.valueOf(temp[0])+ String.valueOf(temp[1]), Toast.LENGTH_SHORT).show();
//        temp[i]=Float.parseFloat(op);
        if(i==1){
            sign.setText("");
            if (addition) {
                ans = String.valueOf(df.format(temp[0] + temp[1]));
                addition = false;
            } else if (subtraction) {
                ans = String.valueOf(df.format(temp[0] - temp[1]));
                subtraction = false;
            } else if (division) {
                if (temp[1] == 0) ans = "Undetermined";
                ans = String.valueOf(df.format(temp[0] / temp[1]));
                division = false;
            } else if (multiplication) {
                ans = String.valueOf(df.format(temp[0] * temp[1]));
                multiplication = false;
            }


            output.setText(ans);
            i = 0;
            op = "0";
//        ans = df.format(ans);
            try {
                temp[i] = Float.parseFloat(ans);
            } catch (Exception e) {
                temp[i] = 0;
            }
        }
    }
    @SuppressLint("SuspiciousIndentation")
    public void onSignClicked(View view){
        Log.d(TAG, "onSignClicked: ");
        if(Objects.equals(op, "")) op="-";
        else {
            if (op.charAt(0) == '-')
                op = op.substring(1, op.length());
            else
                op = "-" + op;
                temp[i]=Float.parseFloat(op);
        }
        output = findViewById(R.id.output);
        output.setText(op);

    }
    public void backSpace(View view){
        Log.d(TAG, "backSpace: ");
//        Toast.makeText(this, "Function Coming soon", Toast.LENGTH_SHORT).show();
        if(op.length()<2) {
            op="0";
            dig=0;
        }
        else {
            op = op.substring(0, op.length() - 1);
            dig--;
        }
        temp[i]=Float.parseFloat(op);
        output = findViewById(R.id.output);
        output.setText(op);


    }
    public void onPercentClicked(View view){
        op=op+"%";
        output=findViewById(R.id.output);
        output.setText(op);
        temp[i]= (float) (temp[i]/100);
        Toast.makeText(this, "i="+String.valueOf(i)+" "+String.valueOf(temp[i]), Toast.LENGTH_SHORT).show();
        if(addition){
            temp[i]++;
            addition=false;
            multiplication=true;
        }
        else if(subtraction){
            temp[i]=1-temp[i];
            subtraction=false;
            multiplication=true;
        }
    }
    public void onAddGst(View view){
        {
            i++;
            dig=0;
            Button button = (Button) view;
            String gst = button.getText().toString();
            op=gst;
            TextView output=findViewById(R.id.output);
            output.setText(op);
            op=op.substring(1,(op.length()-1));
//            Toast.makeText(this, op, Toast.LENGTH_SHORT).show();
            temp[i]=Float.parseFloat(op);
            temp[i]= (float) (temp[i]/100);
            temp[i]++;
//            Toast.makeText(this, String.valueOf(temp[i]), Toast.LENGTH_SHORT).show();
//            addition=false;
//            multiplication=true;
            ans = String.valueOf(df.format(temp[0]*temp[1]));
            output.setText(ans);
            i=0;
            op="0";
//        ans = df.format(ans);
            try {
                temp[i]=Float.parseFloat(ans);
            }
            catch (Exception e) {
                temp[i] = 0;
            }

        }
    }
    public void onSubGst(View view){
        i++;
        dig=0;
        Button button = (Button) view;
        String gst = button.getText().toString();
        op=gst;
        TextView output=findViewById(R.id.output);
        output.setText(op);
        op=op.substring(1,(op.length()-1));
//        Toast.makeText(this, op, Toast.LENGTH_SHORT).show();
        temp[i]=Float.parseFloat(op);
        temp[i]= (float) (100/(100+temp[i]));
//        temp[i]++;
//        Toast.makeText(this, String.valueOf(temp[i]), Toast.LENGTH_SHORT).show();
//            addition=false;
//            multiplication=true;
        ans = String.valueOf(df.format(temp[0]*temp[1]));
        output.setText(ans);
        i=0;
        op="0";
//        ans = df.format(ans);
        try {
            temp[i]=Float.parseFloat(ans);
        }
        catch (Exception e) {
            temp[i] = 0;
        }

    }




}