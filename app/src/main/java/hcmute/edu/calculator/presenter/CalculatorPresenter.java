package hcmute.edu.calculator.presenter;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hcmute.edu.calculator.R;
import hcmute.edu.calculator.model.calculator.Calculator;
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
    public Button dauNgoac;
//    public NormalOperator squareRoot;

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

        percent = new NormalOperator(R.id.btnPercent, (Button) view.findViewById(R.id.btnPercent),"%","/100");

        comma = (Button) view.findViewById(R.id.btnComma);

        dauNgoac = (Button) view.findViewById(R.id.btnParenthesis);

//        squareRoot = new NormalOperator(R.id.btnSquare_root, (Button) view.findViewById(R.id.btnSquare_root),"√","sqrt(");
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
                    screen.setText(calculator.xulyngoac());
                    break;
                }
                case ".":{
                    screen.setText(calculator.xulyCham());
                    break;
                }
                case "%":{
                    screen.setText(calculator.clickButton(percent));
                    break;
                }
                case "√":
                    if(checkValidOperation()) {
                        calculator.setDisplay(calculator.getDisplay()+"√(");
                        calculator.setDisplay(calculator.getExpression()+"sqrt(");
                        screen.setText(calculator.getDisplay());
                    }
                    break;

                case "sin":
                    if (checkValidOperation()) {
//                        String text = screen.getText().toString().trim();
                        calculator.setDisplay(calculator.getDisplay() + "sin(");
                        calculator.setExpression(calculator.getExpression() + "sin(");
                        screen.setText(calculator.getDisplay());
                    }
                    break;
                case "cos":
                    if (checkValidOperation()) {
//                        String text = screen.getText().toString().trim();
                        calculator.setDisplay(calculator.getDisplay() + "cos(");
                        calculator.setExpression(calculator.getExpression() + "cos(");
                        screen.setText(calculator.getDisplay());
                    }
                    break;
                case "tan":
                    if (checkValidOperation()) {
//                        String text = screen.getText().toString().trim();
                        calculator.setDisplay(calculator.getDisplay() + "tan(");
                        calculator.setExpression(calculator.getExpression() + "tan(");
                        screen.setText(calculator.getDisplay());
                    }
                    break;

                case "Rad":
                    if(checkValidOperation()){
                        calculator.setDisplay(calculator.getDisplay()+"rad(");
                        calculator.setExpression(calculator.getExpression()+"rad(");
                        screen.setText(calculator.getDisplay());
                    }
                    break;
                case "ln":
                    if(checkValidOperation()){
                        calculator.setDisplay(calculator.getDisplay()+"ln(");
                        calculator.setExpression(calculator.getExpression()+"ln(");
                        screen.setText(calculator.getDisplay());
                    }
                    break;

                case "log":
                    if(checkValidOperation()){
                        calculator.setDisplay(calculator.getDisplay()+"log(");
                        calculator.setExpression(calculator.getExpression()+"log(");
                        screen.setText(calculator.getDisplay());
                    }
                    break;

                case "1/x":
                    if(checkValidOperation()){
                        calculator.setDisplay(calculator.getDisplay()+"1/");
                        calculator.setExpression(calculator.getExpression()+"1/");
                        screen.setText(calculator.getDisplay());
                    }
                    break;

                case "eⁿ":
                    if(checkValidOperation()){
                        calculator.setDisplay(calculator.getDisplay()+"e^(");
                        calculator.setExpression(calculator.getExpression()+"e^(");
                        screen.setText(calculator.getDisplay());
                    }
                    break;
                case "x\\u00B2":
                    if(checkValidOperation()){
                        calculator.setDisplay(calculator.getDisplay()+"^(2)");
                        calculator.setExpression(calculator.getExpression()+"^(2)");
                        screen.setText(calculator.getDisplay());
                    }
                    break;
                case "xⁿ":
                    if(checkValidOperation()){
                        calculator.setDisplay(calculator.getDisplay()+"^(");
                        calculator.setExpression(calculator.getExpression()+"^(");
                        screen.setText(calculator.getDisplay());
                    }
                    break;

                case "|x|":
                    if(checkValidOperation()){
                        calculator.setDisplay(calculator.getDisplay()+"abs(");
                        calculator.setExpression(calculator.getExpression()+"abs(");
                        screen.setText(calculator.getDisplay());
                    }
                    break;

                case "π":
                    if(checkValidOperation()){
                        calculator.setDisplay(calculator.getDisplay()+"PI");
                        calculator.setExpression(calculator.getExpression()+"PI");
                        screen.setText(calculator.getDisplay());
                    }
                    break;

                case "e":
                    if(checkValidOperation()){
                        calculator.setDisplay(calculator.getDisplay()+"e");
                        calculator.setExpression(calculator.getExpression()+"e");
                        screen.setText(calculator.getDisplay());
                    }
                    break;
                default: {
                    screen.setText(calculator.insertNum(b.getText().toString()));
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

    private boolean checkValidOperation() {
        String text = screen.getText().toString().trim();
        if (text.isEmpty()) {
            return true;
        }
        char last = text.charAt(text.length() - 1);
        return last == '+' || last == '-' || last == '⨯' || last == '÷' || last == '(';
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
