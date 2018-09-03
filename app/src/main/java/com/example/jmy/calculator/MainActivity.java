package com.example.jmy.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    protected int status = 0;
    protected char oppstatus;
    protected Float v1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView outputText = (TextView) findViewById(R.id.textView);
        outputText.setText("");

        Button percent = (Button) findViewById(R.id.buttonPercent);
        percent.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                inputOpp('%');
            }
        });

        Button zero = (Button) findViewById(R.id.button0);
        zero.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                inputNumber(0);
            }
        });

        Button point = (Button) findViewById(R.id.buttonPoint);
        point.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                inputPoint();
            }
        });

        Button one = (Button) findViewById(R.id.button1);
        one.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                inputNumber(1);
            }
        });

        Button C = (Button) findViewById(R.id.buttonC);
        C.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                inputClearall('C');
            }
        });

        Button div = (Button) findViewById(R.id.buttonDiv);
        div.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                inputOpp('/');
            }
        });

        Button plus = (Button) findViewById(R.id.buttonPlus);
        plus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                inputOpp('+');
            }
        });

        Button equal = (Button) findViewById(R.id.buttonEqual);
        equal.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                inputEqual('=');
            }
        });

        Button minus = (Button) findViewById(R.id.buttonMinus);
        minus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                inputOpp('-');
            }
        });

        Button c = (Button) findViewById(R.id.buttonc);
        c.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                inputClear('c');
            }
        });

        Button multi = (Button) findViewById(R.id.buttonMulti);
        multi.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                inputOpp('*');
            }
        });

        Button two = (Button) findViewById(R.id.button2);
        two.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                inputNumber(2);
            }
        });

        Button three = (Button) findViewById(R.id.button3);
        three.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                inputNumber(3);
            }
        });

        Button four = (Button) findViewById(R.id.button4);
        four.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                inputNumber(4);
            }
        });

        Button five = (Button) findViewById(R.id.button5);
        five.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                inputNumber(5);
            }
        });

        Button six = (Button) findViewById(R.id.button6);
        six.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                inputNumber(6);
            }
        });

        Button seven = (Button) findViewById(R.id.button7);
        seven.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                inputNumber(7);
            }
        });

        Button eight = (Button) findViewById(R.id.button8);
        eight.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                inputNumber(8);
            }
        });

        Button nine = (Button) findViewById(R.id.button9);
        nine.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                inputNumber(9);
            }
        });


    }

    protected void inputNumber(Integer i) {
        final TextView outputText = (TextView) findViewById(R.id.textView);
        String s = i.toString();
        if (status == 0 || status == 6) {
            outputText.setText(s);
            status = 1;
        }
        else if (status == 1 || status == 2 || status == 4 || status == 5) {
            String oldV = (String) outputText.getText();
            String newV = oldV + s;
            outputText.setText(newV);
        }
        else if (status == 3) {
            outputText.setText(s);
            status = 4;
        }
    }

    protected void inputOpp(Character a) {
        final TextView outputText = (TextView) findViewById(R.id.textView);
        if (status == 1 || status == 2 || status == 6) {
            String oldV = (String) outputText.getText();
            v1 = Float.parseFloat(oldV);
            status = 3;
        }
        else if (status == 4 || status == 5) {
            calculation();
            status = 3;
        }
        oppstatus = a;

    }

    protected void inputPoint() {
        final TextView outputText = (TextView) findViewById(R.id.textView);
        if (status == 1 || status == 4) {
            String oldV = (String) outputText.getText();
            String newV = oldV + ".";
            outputText.setText(newV);
            status++;
        }
    }

    protected void inputClear(Character c) {
        final TextView outputText = (TextView) findViewById(R.id.textView);
        String oldV1 = (String) outputText.getText();
        String temp = "";
        if (status == 1 || status == 2 || status == 4 || status == 5) {
            if (oldV1.length() > 0) {
                temp = oldV1.substring(0, oldV1.length() - 1);
            }
            int x = temp.indexOf('.');
            if (x < 0){
                if (status == 2 || status == 5){
                    status--;
                }
            }
            outputText.setText(temp);
        }
    }

    protected void inputClearall(Character C) {
        final TextView outputText = (TextView) findViewById(R.id.textView);
        outputText.setText("");
        status = 0;
    }

    protected void inputEqual(Character e) {
        final TextView outputText = (TextView) findViewById(R.id.textView);
        if (status == 4 || status == 5) {
            calculation();
            status = 6;
        }
    }

    protected void calculation() {
        final TextView outputText = (TextView) findViewById(R.id.textView);
        String oldV1 = (String) outputText.getText();
        float v2 = Float.parseFloat(oldV1);
        switch (oppstatus) {
            case '+': {
                v1 = v1 + v2;
                String s = v1.toString();
                outputText.setText(s);
                break;
            }
            case '-': {
                v1 = v1 - v2;
                String s = v1.toString();
                outputText.setText(s);
                break;
            }
            case '*': {
                v1 = v1 * v2;
                String s = v1.toString();
                outputText.setText(s);
                break;
            }
            case '/': {
                v1 = v1 / v2;
                String s = v1.toString();
                outputText.setText(s);
                break;
            }
        }
    }
}

