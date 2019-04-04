package hcmute.edu.calculator.presenter;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hcmute.edu.calculator.R;
import hcmute.edu.calculator.model.calculator.Calculator;
import hcmute.edu.calculator.model.operator.AdvOperatorWithNumber;
import hcmute.edu.calculator.model.operator.AdvancedOperator;
import hcmute.edu.calculator.model.operator.Expression;
import hcmute.edu.calculator.model.operator.NormalOperator;
import hcmute.edu.calculator.model.operator.Number;

public class CalculatorPresenter {
    private Calculator calculator;

    private View view;

    private TextView screen;

    private List<Number> lstBtnNumber;
    public static ArrayList<Expression> history = new ArrayList<>();

    public NormalOperator plus;
    public NormalOperator minus;
    public NormalOperator div;
    public NormalOperator multi;
    public NormalOperator equal;
    public NormalOperator negative;
    public NormalOperator percent;
    public Button comma;
    public Button parenthesis;

    public AdvancedOperator squareRoot, radian, sin, cos, tan, ln, log, oneDivideX, ePowN;
    public AdvancedOperator pi, euler, abs ;
    public AdvOperatorWithNumber xPowN,xFactorial,xPowTwo;



    public CalculatorPresenter(View view){

        this.view = view;

        calculator = new Calculator();

        lstBtnNumber = new ArrayList<Number>();

        setUp();
    }

    public void setUp(){
        screen = (TextView) view.findViewById(R.id.txtViewExpression);

        lstBtnNumber.add(new Number(R.id.btnNumber0,view.findViewById(R.id.btnNumber0),"0"));
        lstBtnNumber.add(new Number(R.id.btnNumber1,view.findViewById(R.id.btnNumber1),"1"));
        lstBtnNumber.add(new Number(R.id.btnNumber2,view.findViewById(R.id.btnNumber2),"2"));
        lstBtnNumber.add(new Number(R.id.btnNumber3,view.findViewById(R.id.btnNumber3),"3"));
        lstBtnNumber.add(new Number(R.id.btnNumber4,view.findViewById(R.id.btnNumber4),"4"));
        lstBtnNumber.add(new Number(R.id.btnNumber5,view.findViewById(R.id.btnNumber5),"5"));
        lstBtnNumber.add(new Number(R.id.btnNumber6,view.findViewById(R.id.btnNumber6),"6"));
        lstBtnNumber.add(new Number(R.id.btnNumber7,view.findViewById(R.id.btnNumber7),"7"));
        lstBtnNumber.add(new Number(R.id.btnNumber8,view.findViewById(R.id.btnNumber8),"8"));
        lstBtnNumber.add(new Number(R.id.btnNumber9,view.findViewById(R.id.btnNumber9),"9"));

        plus = new NormalOperator (R.id.btnPlus,(Button) view.findViewById(R.id.btnPlus),"+","+");
        minus = new NormalOperator (R.id.btnMinus,(Button) view.findViewById(R.id.btnMinus),"-","-");
        multi = new NormalOperator (R.id.btnMultiply,(Button) view.findViewById(R.id.btnMultiply),"x","*");
        div = new NormalOperator (R.id.btnDivide,(Button) view.findViewById(R.id.btnDivide),"÷","/");

        equal = new NormalOperator (R.id.btnEqual,(Button) view.findViewById(R.id.btnDivide),"","");

        negative = new NormalOperator(R.id.btnPlusMinus, (Button) view.findViewById(R.id.btnPlusMinus),"(-","(-");

        percent = new NormalOperator(R.id.btnPercent, (Button) view.findViewById(R.id.btnPercent),"%","%");

        comma = (Button) view.findViewById(R.id.btnComma);

        parenthesis = (Button) view.findViewById(R.id.btnParenthesis);

        squareRoot = new AdvancedOperator (R.id.btnSquare_root,(Button) view.findViewById(R.id.btnSquare_root),"√(","sqrt(");

        radian = new AdvancedOperator(R.id.btnRad,(Button)view.findViewById(R.id.btnRad) , "rad(", "rad(");

        sin = new AdvancedOperator(R.id.btnSin,(Button)view.findViewById(R.id.btnSin) , "sin(", "sin(");

        cos = new AdvancedOperator(R.id.btnCos,(Button)view.findViewById(R.id.btnCos) , "cos(", "cos(");

        tan = new AdvancedOperator(R.id.btnTan,(Button)view.findViewById(R.id.btnTan) , "tan(", "tan(");

        ln = new AdvancedOperator(R.id.btnLn,(Button)view.findViewById(R.id.btnLn) , "ln(", "ln(");

        log = new AdvancedOperator(R.id.btnLog,(Button)view.findViewById(R.id.btnLog) , "log(", "log10(");

        oneDivideX = new AdvancedOperator(R.id.btn1divideX,(Button)view.findViewById(R.id.btn1divideX) , "1/", "1/");

        ePowN = new AdvancedOperator(R.id.button32,(Button)view.findViewById(R.id.button32) , "e^(", "e^(");

        xPowTwo = new AdvOperatorWithNumber(R.id.button31,(Button)view.findViewById(R.id.button31) , "\u00B2", "^(2)");

        xPowN = new AdvOperatorWithNumber(R.id.button29,(Button)view.findViewById(R.id.button29) , "^(", "^(");

        abs = new AdvancedOperator(R.id.btnAbsX,(Button)view.findViewById(R.id.btnAbsX) , "abs(", "abs(");

        pi = new AdvancedOperator(R.id.btnPi,(Button)view.findViewById(R.id.btnPi) , "π", "pi");

        euler = new AdvancedOperator(R.id.btnEuler,(Button)view.findViewById(R.id.btnEuler) , "e", "e");

        xFactorial = new AdvOperatorWithNumber(R.id.btnXFactorial,(Button)view.findViewById(R.id.btnXFactorial) , "!", "!");
    }

    public void clear(){
        screen.setText(calculator.clear());
    }

    public boolean isError(){
        return screen.getText().equals("ERROR");
    }

    public boolean isClear(){
        return screen.getText().equals("");
    }

    public void setDisplayScreen(String text){
        screen.setText(text);
    }

    /**
     * Change text size screen after click button
     */
    public void fixScreen(){
        String text = screen.getText().toString();
        if(text.length() > 8){

        }
        switch (screen.getText().length()){
            case 0:{
                screen.setTextSize(70);
                break;
            }
            case 7:{
                screen.setTextSize(70);
                break;
            }
            case 8:{
                screen.setTextSize(50);
                break;
            }
        }
    }

    /**
     * Click button
     * @param v View from CalculatorPresenter
     */
    public void onClickButton(View v){
        if(v instanceof Button) {
            Button b = (Button) v;
            if(isError()){
                clear();
            }
            Log.i("clickButton", "onClickButton: "+b.getText().toString());
            switch (b.getText().toString()) {
                case "=": {
                    if(!isClear()) {
                        String exp = screen.getText().toString();
                        String res = calculator.execute();

                        setDisplayScreen(res);

                        history.add(new Expression(exp, res));
                    }
                    break;
                }
                case "+":{
                    screen.setText(calculator.clickButton(plus));
                    break;
                }
                case "-":{
                    screen.setText(calculator.clickButton(minus));
                    break;
                }
                case "⨯":{
                    screen.setText(calculator.clickButton(multi));
                    break;
                }
                case "÷":{
                    screen.setText(calculator.clickButton(div));
                    break;
                }
                case "C":{
                    screen.setText(calculator.clear());
                    break;
                }
                case "+/-":{
                    screen.setText(calculator.clickNegative(negative));
                    break;
                }
                case "( )":{
                    screen.setText(calculator.addParenthesis());
                    break;
                }
                case ".":{
                    screen.setText(calculator.addComma());
                    break;
                }
                case "%":{
                    screen.setText(calculator.clickButton(percent));
                    break;
                }
                case "√":
                    screen.setText(calculator.clickButton(squareRoot));
                    break;

                case "sin":
                    screen.setText(calculator.clickButton(sin));
                    break;
                case "cos":
                    screen.setText(calculator.clickButton(cos));
                    break;
                case "tan":
                    screen.setText(calculator.clickButton(tan));
                    break;

                case "Rad":
                        screen.setText(calculator.clickButton(radian));
                        break;
                case "ln":
                    screen.setText(calculator.clickButton(ln));
                    break;

                case "log":
                    screen.setText(calculator.clickButton(log));
                    break;

                case "1/x":
                    screen.setText(calculator.clickButton(oneDivideX));
                    break;

                case "eⁿ":
                    screen.setText(calculator.clickButton(ePowN));
                    break;
                case "x\u00B2":
                    screen.setText(calculator.clickButton(xPowTwo));
                    break;
                case "xⁿ":
                    screen.setText(calculator.clickButton(xPowN));
                    break;
                case "|x|":
                    screen.setText(calculator.clickButton(abs));
                    break;

                case "π":
                    screen.setText(calculator.clickButton(pi));
                    break;

                case "e":
                    screen.setText(calculator.clickButton(euler));
                    break;

                case "x!":
                    screen.setText(calculator.clickButton(xFactorial));
                    break;

                default: {
                    screen.setText(calculator.insertNum(b.getText().toString()));
                    break;
                }
            }
            if(!b.getText().toString().equals("=")){
                calculator.pressEqual = false;
            }
        }else{
            if(v instanceof ImageButton){
                ImageButton imageButton = (ImageButton) v;
                if(imageButton.getId() == R.id.btnBackspace){
                    screen.setText(calculator.backSpace());
                }
                calculator.pressEqual = false;
            }
        }
        fixScreen();
    }

    public Calculator getCalculator() {
        return calculator;
    }

    public void setCalculator(Calculator calculator) {
        this.calculator = calculator;
    }

    public TextView getScreen() {
        return screen;
    }

    public void setScreen(TextView screen) {
        this.screen = screen;
    }

    public List<Number> getLstBtnNumber() {
        return lstBtnNumber;
    }

    public void setLstBtnNumber(List<Number> lstBtnNumber) {
        this.lstBtnNumber = lstBtnNumber;
    }
}
