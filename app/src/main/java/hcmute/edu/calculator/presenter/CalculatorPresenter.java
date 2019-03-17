package hcmute.edu.calculator.presenter;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hcmute.edu.calculator.R;
import hcmute.edu.calculator.model.calculator.Calculator;
import hcmute.edu.calculator.model.operator.NormalOperator;
import hcmute.edu.calculator.model.operator.Number;

public class CalculatorPresenter {
    private Calculator calculator;

    private View view;

    private TextView screen;

    private List<Number> lstBtnNumber;

    public NormalOperator plus;
    public NormalOperator minus;
    public NormalOperator div;
    public NormalOperator multi;
    public NormalOperator equal;

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

    public void onClickButton(View v){
        if(v instanceof Button) {
            Button b = (Button) v;
            if(isError()){
                clear();
            }
            switch (b.getText().toString()) {
                case "=": {
                    if(!isClear()) {
                        setDisplayScreen(calculator.execute());
                    }
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
                default: {
                    screen.setText(calculator.insert(b.getText().toString()));
                    break;
                }
            }
        }else{
            if(v instanceof ImageButton){
                ImageButton imageButton = (ImageButton) v;
                if(imageButton.getId() == R.id.btnBackspace){
                    screen.setText(calculator.backSpace(screen));
                }
            }
        }
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
