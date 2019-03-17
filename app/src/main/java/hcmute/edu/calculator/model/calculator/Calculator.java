package hcmute.edu.calculator.model.calculator;

import android.widget.TextView;

import org.mariuszgromada.math.mxparser.Expression;

import java.text.DecimalFormat;

import hcmute.edu.calculator.model.operator.IOperatorImp;


public class Calculator {

    private String expression;
    private String display;



    public Calculator(){
        this.expression = "";
        this.display = "";
    }

    public String execute(){
        try {
            DecimalFormat df = new DecimalFormat("#.###");

            Expression ex = new Expression(expression);
            Double result = ex.calculate();

            if (result.isNaN()) throw new Exception();
            expression = df.format(result);
            display = expression;
            return display;
        } catch (Exception e) {
            clear();
            display = "ERROR";
            return display;
        }
    }

    public String backSpace(TextView screen){
        display = screen.getText().toString();
        if(!display.equals("")&&!display.equals("ERROR")) {
            display = display.substring(0,display.length() - 1);
            expression = expression.substring(0,expression.length() - 1);
        }else{
            if(display.equals("ERROR")){
                return clear();
            }
        }
        return display;
    }

    public String insert(String op){
        display += op;
        expression += op;
        return display;
    }

    public String clear(){
        expression = "";
        display = "";
        return display;
    }

    public String clickButton(IOperatorImp operatorImp){
        display += operatorImp.getsDisplay();
        expression += operatorImp.getsDisplayExe();
        return display;
    }
}
