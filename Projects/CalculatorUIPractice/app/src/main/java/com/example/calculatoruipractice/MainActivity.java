package com.example.calculatoruipractice;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView tView;
    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, btnAdd, btnSub, btnMul, btnDiv, btnEq, btnAC, btnCross, btnDot, btnPer, btnPlusMinus;
    Button btn2nd, btnSq, btnCube, btnxY, btneX, btn10X, btn1BYX, btnSqR2, btnSqR3, btnSqRY, btnLn, btnLg10, brac1, brac2;
    Button btnFact, btnSin, btnCos, btnTan, btnE, btnEE, btnRand, btnSinh, btnCosh, btnTanh, btnPie, btnRad;
    float value1 = 0, value2 = 0;
    boolean addition = false, substraction = false, product = false, division = false, percentage = false, power = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tView = findViewById(R.id.ResultScreen);
        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        brac1 = findViewById(R.id.btnBrac1);
        brac2 = findViewById(R.id.btnBrac2);

        btnAdd = findViewById(R.id.btnAdd);
        btnSub = findViewById(R.id.btnSubstract);
        btnMul = findViewById(R.id.btnMultiply);
        btnDiv = findViewById(R.id.btnDivide);
        btnDot = findViewById(R.id.btnDot);
        btnEq = findViewById(R.id.btnEqual);
        btnPer = findViewById(R.id.btnPercentage);
        btnAC = findViewById(R.id.btnAC);
        btnCross = findViewById(R.id.btnCross);
        btnPlusMinus = findViewById(R.id.btnPlusMinus);

        btn2nd = findViewById(R.id.btnSecond);
        btnSq = findViewById(R.id.btnSqaure);
        btnCube = findViewById(R.id.btnCube);
        btneX = findViewById(R.id.btnEPowerX);
        btnxY = findViewById(R.id.btnXPowerY);
        btn10X = findViewById(R.id.btn10PowerX);
        btn1BYX = findViewById(R.id.btn1OverX);
        btnSqR2 = findViewById(R.id.btn2SqRoot);
        btnSqR3 = findViewById(R.id.btn3SqRoot);
        btnSqRY = findViewById(R.id.btnYSqRoot);
        btnLn = findViewById(R.id.btnLN);
        btnLg10 = findViewById(R.id.btnLog10);

        btnFact = findViewById(R.id.btnfact);
        btnSin = findViewById(R.id.btnSin);
        btnCos = findViewById(R.id.btnCos);
        btnTan = findViewById(R.id.btnTan);
        btnE = findViewById(R.id.btnE);
        btnEE = findViewById(R.id.btnEE);

        btnRand = findViewById(R.id.btnRand);
        btnSinh = findViewById(R.id.btnSinh);
        btnCosh = findViewById(R.id.btnCosh);
        btnTanh = findViewById(R.id.btnTanh);
        btnPie = findViewById(R.id.btnPie);
        btnRad = findViewById(R.id.btnRad);

        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tView.setText(tView.getText() + "0");
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tView.setText(tView.getText() + "1");
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tView.setText(tView.getText() + "2");
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tView.setText(tView.getText() + "3");
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tView.setText(tView.getText() + "4");
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tView.setText(tView.getText() + "5");
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tView.setText(tView.getText() + "6");
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tView.setText(tView.getText() + "7");
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tView.setText(tView.getText() + "8");
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tView.setText(tView.getText() + "9");
            }
        });
        brac1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tView.setText(tView.getText() + "(");
            }
        });
        brac2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tView.setText(tView.getText() + "acha ");
            }
        });


        btnDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tView.setText(tView.getText() + ".");
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value1 = Float.parseFloat(tView.getText() + "");
                addition = true;
                tView.setText("");
            }
        });

        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value1 = Float.parseFloat(tView.getText() + "");
                substraction = true;
                tView.setText("");
            }
        });

        btnMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value1 = Float.parseFloat(tView.getText() + "");
                product = true;
                tView.setText("");
            }
        });

        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                value1 = Float.parseFloat(tView.getText() + "");
                division = true;
                tView.setText("");
            }
        });

        btnAC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value1 = Float.parseFloat(tView.getText() + "");
                tView.setText("0");
            }
        });

        btnPer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value1 = Float.parseFloat(tView.getText() + "");
                percentage = true;
                tView.setText("");
            }
        });

        btnCross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = tView.getText().toString();
                if (value.length() > 0) {
                    value = value.substring(0, value.length() - 1); // remove last char
                    if (value.isEmpty()) value = "0"; // if all removed, show 0
                    tView.setText(value);
                }
            }
        });

        btnPlusMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = tView.getText().toString();
                if (value.isEmpty() || value.equals("0"))
                {
                    return;
                }
                if (value.startsWith("-"))
                {
                    tView.setText(value.substring(1));
                } else
                {
                    tView.setText("-" + value);
                }
            }
        });

        btnSq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value1 = Float.parseFloat(tView.getText()+"");
                tView.setText(value1*value1+"");
            }
        });
        btnCube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value1 = Float.parseFloat(tView.getText()+"");
                tView.setText(value1*value1*value1+"");
            }
        });
        btnxY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value1 = Float.parseFloat(tView.getText() + "");
                power = true;
                tView.setText("");
            }
        });

        btn10X.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int x = Integer.parseInt(tView.getText()+"");
                double result = 1;
                for (int i = 1; i <= x; i++) {
                    result *= 10;
                }
                tView.setText(result+"");
            }
        });

        btneX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!tView.getText().toString().isEmpty()) {
                    value1 = Float.parseFloat(tView.getText()+"");
                    float e = 2.718281828F;
                    double result = 1;
                    for (int i = 1; i <= value1; i++) {
                        result *= e;
                    }
                    tView.setText(result+"");
                }
            }
        });

        btn2nd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value1 = Float.parseFloat(tView.getText()+"");
                float base = 2;
                float result = 1;
                for (int i = 1; i <= value1; i++) {
                    result *= base;
                }
                tView.setText(result+"");
            }
        });

        btn1BYX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value1 = Float.parseFloat(tView.getText()+"");
                tView.setText(1 / value1 + "");
            }
        });

        btnSqR2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value1 = Float.parseFloat(tView.getText()+"");
                tView.setText((int) Math.sqrt(value1));
            }
        });

        btnSqR3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value1 = Float.parseFloat(tView.getText()+"");
                tView.setText((int) Math.cbrt(value1));
            }
        });

        btnSqRY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value1 = Float.parseFloat(tView.getText()+"");
                tView.setText("");
                division = true;
            }
        });

        btnLn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value1 = Float.parseFloat(tView.getText()+"");
                tView.setText(Math.log(value1)+"");
            }
        });


        btnLg10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value1 = Float.parseFloat(tView.getText()+"");
                tView.setText(Math.log10(value1)+"");
            }
        });

        btnFact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int value = Integer.parseInt(tView.getText()+"");
                int fact = 1;
                for (int i = 1; i <= value; i++) {
                    fact *= i;
                }
                tView.setText(fact+"");
            }
        });

        btnSin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value1 = Float.parseFloat(tView.getText()+"");

                tView.setText(Math.sin(Math.toRadians(value1))+"");
            }
        });

        btnCos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value1 = Float.parseFloat(tView.getText()+"");

                tView.setText(Math.cos(Math.toRadians(value1))+"");
            }
        });

        btnTan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value1 = Float.parseFloat(tView.getText()+"");

                tView.setText(Math.tan(Math.toRadians(value1))+"");
            }
        });

        btnE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value1 = Float.parseFloat(tView.getText()+"");
                tView.setText(Math.exp(value1)+"");
            }
        });

        btnEE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = tView.getText().toString();
                tView.setText(text + "E");
            }
        });

        btnRand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tView.setText(Math.random()+"");
            }
        });

        btnRad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value1 = Float.parseFloat(tView.getText()+"");
                tView.setText(value1 + " rad");
            }
        });

        btnPie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value1 = Float.parseFloat(tView.getText().toString());
                value1 = (float) (value1 + Math.PI);
                tView.setText(String.valueOf(value1));
            }
        });

        btnSinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value1 = Float.parseFloat(tView.getText()+"");

                tView.setText(Math.sinh(Math.toRadians(value1))+"");
            }
        });

        btnCosh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value1 = Float.parseFloat(tView.getText()+"");

                tView.setText(Math.cosh(Math.toRadians(value1))+"");
            }
        });

        btnTanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value1 = Float.parseFloat(tView.getText()+"");

                tView.setText(Math.tanh(Math.toRadians(value1))+"");
            }
        });

        btnEq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value2 = Float.parseFloat(tView.getText()+ "");
                if(addition == true)
                {
                    tView.setText(value1+ value2 +" ");
                    addition = false;
                }
                else if (substraction == true)
                {
                    tView.setText(value1 - value2+ " ");
                    substraction = false;
                }
                else if (product == true)
                {
                    tView.setText(value1*value2+ " ");
                    product = false;
                }
                else if (division == true)
                {
                    tView.setText(value1/value2+ " ");
                    division = false;
                }
                else if (percentage == true)
                {
                    tView.setText((value1 * value2 / 100)+ " ");
                    percentage = false;
                }
                else if(power == true)
                {
                    value2 = Float.parseFloat(tView.getText()+"");
                    float power = 1;
                    for (int i = 1; i <= value2; i++) {
                        power *= value1;
                    }
                    tView.setText(power+"");
                    product = false;
                }
                else if (division == true) {
                    double result = Math.pow(value2, 1.0 / value1);
                    tView.setText(String.valueOf(result));
                    division = false;
                }
            }
        });
    }
}