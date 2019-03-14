package hcmute.edu.calculator;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.mariuszgromada.math.mxparser.*;

import java.text.DecimalFormat;

public class CalculatorFragment extends Fragment implements View.OnClickListener {
    private TextView screen;
    private String display="";
    private String expression = "";

    private MainActivity mainActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_calculator, container, false);

        Button btn1 = (Button) v.findViewById(R.id.btnNumber1);
        Button btn2 = (Button) v.findViewById(R.id.btnNumber2);
        Button btn3 = (Button) v.findViewById(R.id.btnNumber3);
        Button btn4 = (Button) v.findViewById(R.id.btnNumber4);
        Button btn5 = (Button) v.findViewById(R.id.btnNumber5);
        Button btn6 = (Button) v.findViewById(R.id.btnNumber6);
        Button btn7 = (Button) v.findViewById(R.id.btnNumber7);
        Button btn8 = (Button) v.findViewById(R.id.btnNumber8);
        Button btn9 = (Button) v.findViewById(R.id.btnNumber9);
        Button btn0 = (Button) v.findViewById(R.id.btnNumber0);

        Button btnPlus = (Button) v.findViewById(R.id.btnPlus);
        Button btnMinus = (Button) v.findViewById(R.id.btnMinus);
        Button btnMulti = (Button) v.findViewById(R.id.btnMultiply);
        Button btnDiv = (Button) v.findViewById(R.id.btnDevide);

        ImageButton ibtnBackSpace = (ImageButton) v.findViewById(R.id.btnBackspace);

        Button btnEqual = (Button) v.findViewById(R.id.btnEqual);

        Button btnClear = (Button) v.findViewById(R.id.btnClear);

        screen = (TextView) v.findViewById(R.id.txtViewExpression);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btn0.setOnClickListener(this);

        btnPlus.setOnClickListener(this);
        btnMinus.setOnClickListener(this);
        btnMulti.setOnClickListener(this);
        btnDiv.setOnClickListener(this);

        ibtnBackSpace.setOnClickListener(this);
        btnClear.setOnClickListener(this);

        btnEqual.setOnClickListener(this);

        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof MainActivity) {
            this.mainActivity = (MainActivity) context;
        }
    }

//    public void showText(String txt) {
//        String value = screen.getText().toString() + txt;
//        screen.setText(value);
//    }

    @Override
    public void onClick(View v) {
//        onClickNumber(v);
        if(v instanceof Button) {
            Button b = (Button) v;
            if(display.equals("ERROR")){
                display = "";
                expression = "";
            }
            switch (b.getText().toString()) {
                case "=": {
                    if(!display.equals("")) {
                        try {
                            DecimalFormat df = new DecimalFormat("#.###");
                            Expression ex = new Expression(expression);
                            Double result = ex.calculate();
                            Log.i("Calculator", "onClick: Equal " + result);
                            if (result.isNaN()) throw new Exception();
                            expression = df.format(result);
                            display = df.format(result);
                        } catch (Exception e) {
                            display = "ERROR";
                            expression = "";
                        }
                    }
                    break;
                }
                case "⨯":{
                    display += b.getText();
                    expression += " * ";
                    break;
                }
                case "÷":{
                    display += b.getText();
                    expression += " / ";
                    break;
                }
                case "C":{
                    display = "";
                    expression = "";
                    break;
                }
                default: {
                    display += b.getText();
                    expression += b.getText();
                    break;
                }
            }
        }else{
            if(v instanceof ImageButton){
                ImageButton imageButton = (ImageButton) v;
                if(imageButton.getId() == R.id.btnBackspace){
                    if(!display.equals("")&&!display.equals("ERROR")) {
                        display = display.substring(0,display.length() - 1);
                        expression = expression.substring(0,expression.length() - 1);
                    }else{
                        if(display.equals("ERROR")){
                            display = "";
                            expression = "";
                        }
                    }
                }
            }
        }
        updateScreen();
    }

    private void updateScreen(){
        screen.setText(display);
    }
//    public void onClickNumber(View v){
//        Button b=(Button) v;
//        display += b.getText();
//
//    }
//    public void onClickOperator(View v){
//        Button b=(Button) v;
//        display += b.getText();
//        currentOperator= b.getText().toString();
//        updateScreen();
//    }
//    private double operatorArithmetic(String a, String b, String op){
//        switch (op){
//            case "+": return (Double.valueOf(a) + Double.valueOf(b));
//            case "-": return (Double.valueOf(a) - Double.valueOf(b));
//            case "⨯": return (Double.valueOf(a) * Double.valueOf(b));
//            case "÷":
//                try {
//                    return (Double.valueOf(a) / Double.valueOf(b));
//                }catch (Exception e){
//                    Log.d("Calc",e.getMessage());
//                }
//             default: return -1;
//        }
//    }
//    public void onClickEqual(View v){
//        String[] operation= display.split(Pattern.quote(currentOperator));
//        Double result;
//        if(operation.length<2){
//            return;
//        }
//        else {
//            result=operatorArithmetic(operation[0],operation[1],currentOperator);
//            screen.setText(display+"\n"+String.valueOf(result));
//        }
//    }
//    private void Clear(){
//        display="";
//        currentOperator="";
//    }
//    public void onClickClear(View v){
//        Clear();
//        updateScreen();
//    }
}