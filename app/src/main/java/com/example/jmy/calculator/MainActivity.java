package com.example.jmy.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    protected int status = 0;
    protected char oppstatus;
    protected Float v;
//    protected Float v2;
//    protected Float v3;

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
                inputClearall();
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
                inputEqual();
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
                inputClear();
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
        if (status == 0) {
            outputText.setText(s);
            status = 1;
        }
        else if (status == 1 || status == 2) {
            String oldV = (String) outputText.getText();
            String newV = oldV + s;
            outputText.setText(newV);
        }
        else if (status == 3) {
            String oldV = (String) outputText.getText();
            String newV = oldV + s;
            outputText.setText(newV);
            status = 1;
        }
    }

    protected void inputOpp(Character a) {
        final TextView outputText = (TextView) findViewById(R.id.textView);
        String oldV = (String) outputText.getText();
        String temp = "";
        if (status == 1 || status == 2) {
            //String oldV = (String) outputText.getText();
            //v1 = Float.parseFloat(oldV);
            String newV = oldV + a;
            outputText.setText(newV);
            status = 3;
            oppstatus = a;
        }
        else if (status == 3){
            if (oppstatus != a){
                temp = oldV.substring(0, oldV.length() - 1);
                String newV = temp + a;
                outputText.setText(newV);
                oppstatus = a;
            }
        }

    }

    protected void inputPoint() {
        final TextView outputText = (TextView) findViewById(R.id.textView);
        if (status == 1) {
            String oldV = (String) outputText.getText();
            String newV = oldV + ".";
            outputText.setText(newV);
            status++;
        }
    }

    protected void inputClear() {
        final TextView outputText = (TextView) findViewById(R.id.textView);
        String oldV1 = (String) outputText.getText();
        String temp = "";
        if (status == 1 || status == 2 || status == 3) {
            if (oldV1.length() > 0) {
                temp = oldV1.substring(0, oldV1.length() - 1);
            }
            int x = temp.lastIndexOf('.');
            int y = findLast();
            if (y == temp.length()-1 && y != -1){
                status = 3;
            }
            else if (x > y){
                status = 2;
            }
            else if (x < y){
                status = 1;
            }
            else if (x < 0 && y < 0){
                if (temp.length() > 0){
                    status = 1;
                }
                if (temp.length() == 0){
                    status = 0;
                }
            }
            outputText.setText(temp);
        }
    }

    protected int findLast(){
        final TextView outputText = (TextView) findViewById(R.id.textView);
        String oldV1 = (String) outputText.getText();
        String temp = oldV1.substring(0, oldV1.length() - 1);
        int y1 = temp.lastIndexOf('+');
        int y2 = temp.lastIndexOf('-');
        int y3 = temp.lastIndexOf('*');
        int y4 = temp.lastIndexOf('/');
        double[] myList = {y1,y2,y3,y4};  //定义一维数组
        double num = myList[0]; //0为第一个数组下标
        for (int i = 0; i < myList.length; i++) {   //开始循环一维数组
            if (myList[i] > num) {  //循环判断数组元素
                num = myList[i]; }  //赋值给num，然后再次循环
        }
        return (int) num; //跳出循环，输出结果
    }

    protected int findFirst(String oldV1){
//        final TextView outputText = (TextView) findViewById(R.id.textView);
//        String oldV1 = (String) outputText.getText();
        int y1 = oldV1.indexOf('+');
        int y2 = oldV1.indexOf('-');
        int y3 = oldV1.indexOf('*');
        int y4 = oldV1.indexOf('/');
        int y5 = oldV1.indexOf('=');
        int[] myList = {y1,y2,y3,y4,y5};  //定义一维数组
        int i1=0;//下一个值
        int i2;//最大值
        for(int j=0;j<myList.length;j++){
            if(myList[j]>0){//大于基本值
                i2=  myList[j];//赋max值
                if(i1>i2 || i1==0){//第一次进来 i=i1<i2  所以把i2赋值给i1，以后i<i1 第二次进来 如果：i1>i2>i，则：所以把i2赋值给i1，否则不变
                    i1=i2;
                }
            }
        }
        return i1;
    }

    protected int findSecond(){
        final TextView outputText = (TextView) findViewById(R.id.textView);
        String oldV1 = (String) outputText.getText();
        int y1 = oldV1.indexOf('+',oldV1.indexOf('+')+1);
        int y2 = oldV1.indexOf('-',oldV1.indexOf('-')+1);
        int y3 = oldV1.indexOf('*',oldV1.indexOf('*')+1);
        int y4 = oldV1.indexOf('/',oldV1.indexOf('/')+1);
        double[] myList = {y1,y2,y3,y4};  //定义一维数组
        double num = myList[0]; //0为第一个数组下标
        for (int i = 0; i < myList.length; i++) {   //开始循环一维数组
            if (myList[i] < num && myList[i] > 0) {  //循环判断数组元素
                num = myList[i]; }  //赋值给num，然后再次循环
        }
        return (int) num; //跳出循环，输出结果
    }

    protected void inputClearall() {
        final TextView outputText = (TextView) findViewById(R.id.textView);
        outputText.setText("");
        status = 0;
    }

    protected void inputEqual() {
        final TextView outputText = (TextView) findViewById(R.id.textView);
        calculation();
    }

    protected void calculation() {
        final TextView outputText = (TextView) findViewById(R.id.textView);
        String oldV1 = (String) outputText.getText() + "=";
        int firstOpp = findFirst(oldV1);
        String number = oldV1.substring(0,firstOpp);
        ArrayList<Float> num = new ArrayList<>();
        v = Float.parseFloat(number);
        num.add(v);
        ArrayList<String> oppsion = new ArrayList<>();
        oppsion.add(oldV1.substring(firstOpp,firstOpp+1));

        oldV1 = oldV1.substring(firstOpp+1,oldV1.length());
        firstOpp = findFirst(oldV1);
        String number2 = oldV1.substring(0,firstOpp);
        v = Float.parseFloat(number2);
        num.add(v);
        String opp = oldV1.substring(firstOpp,firstOpp+1);


        ArrayList<Integer> prio = new ArrayList<>();
        prio.add(priority(oppsion.get(0)));
        prio.add(priority(opp));


        oldV1 = oldV1.substring(firstOpp+1,oldV1.length());

        //num.add(v1);
        while(true){
            Float result = 0f;
            firstOpp = findFirst(oldV1);
            if (firstOpp > 0) {
                String number3 = oldV1.substring(0, firstOpp);
                v = Float.parseFloat(number3);
            }
            if (oppsion.size() > 0) {
                prio.set(0, priority(oppsion.get(oppsion.size() - 1)));
            }
            else {
                prio.set(0,0);
            }
            prio.set(1,priority(opp));
            if(prio.get(0) < prio.get(1)){
                num.add(v);
                oppsion.add(opp);
                opp = oldV1.substring(firstOpp,firstOpp+1);
                oldV1 = oldV1.substring(firstOpp+1,oldV1.length());
                firstOpp = findFirst(oldV1);
//            if(oppsion.get(0).equals("+")) {
//                result = num.get(0) + v1;
//            }
//            if(oppsion.get(0).equals("-")) {
//                result = num.get(0) - v1;
//            }
//            num.set(0,result);
        }
            else if(prio.get(0) >= prio.get(1)) {
                if (oppsion.size() > 0) {
                    if (oppsion.get(oppsion.size() - 1).equals("+")) {
                        result = num.get(num.size() - 2) + num.get(num.size() - 1);
                    }
                    if (oppsion.get(oppsion.size() - 1).equals("-")) {
                        result = num.get(num.size() - 2) - num.get(num.size() - 1);
                    }
                    if (oppsion.get(oppsion.size() - 1).equals("*")) {
                        result = num.get(num.size() - 2) * num.get(num.size() - 1);
                    }
                    if (oppsion.get(oppsion.size() - 1).equals("/")) {
                        result = num.get(num.size() - 2) / num.get(num.size() - 1);
                    }
                }
                if(oppsion.size() == 0 && opp.equals("=")) {
                    break;
                }

                num.set(oppsion.size()-1,result);
                num.remove(num.size()-1);
                oppsion.remove(oppsion.size()-1);
            }
//            oppsion.add(oldV1.substring(firstOpp,firstOpp+1));
//            prio.add(priority(oldV1.substring(firstOpp,firstOpp+1)));
        }
        String s = num.get(0).toString();
        outputText.setText(s);
        status = 0;
//        switch (oppstatus) {
//            case '+': {
//                v1 = v1 + v2;
//                String s = v1.toString();
//                outputText.setText(s);
//                break;
//            }
//            case '-': {
//                v1 = v1 - v2;
//                String s = v1.toString();
//                outputText.setText(s);
//                break;
//            }
//            case '*': {
//                v1 = v1 * v2;
//                String s = v1.toString();
//                outputText.setText(s);
//                break;
//            }
//            case '/': {
//                v1 = v1 / v2;
//                String s = v1.toString();
//                outputText.setText(s);
//                break;
//            }
//        }


    }

    protected int priority(String opp){
        int prio = 0;
        if (opp.equals("+") || opp.equals("-")){
            prio = 1;
        }
        if (opp.equals("*") || opp.equals("/")){
            prio = 2;
        }
        if (opp.equals("=")){
            prio = 0;
        }
        return prio;
    }
}

