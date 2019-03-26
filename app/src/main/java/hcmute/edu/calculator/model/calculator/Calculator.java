package hcmute.edu.calculator.model.calculator;

import android.util.Log;
import android.widget.TextView;

import org.mariuszgromada.math.mxparser.Expression;

import java.text.DecimalFormat;

import hcmute.edu.calculator.model.operator.IOperatorImp;


public class Calculator {

    private String expression;
    private String display;
    public boolean pressEqual;

    public Calculator(){
        this.expression = "";
        this.display = "";
        pressEqual = false;
    }

    public String execute(){
        try {
            pressEqual = true;
            DecimalFormat df = new DecimalFormat("#.###");
          //  Log.i("test", "execute: "+expression);
            Expression ex = new Expression(replace(expression)); // thay dau phay thanh dau cham.
            Double result = ex.calculate();

            if (result.isNaN()) throw new Exception();
            expression = df.format(result);
            display = expression;
            display=themChamVao();
            return display;
        } catch (Exception e) {
            clear();
            display = "ERROR";
            return display;
        }
    }

    // Xu ly xoa so
    public String backSpace(TextView screen){
        pressEqual = false;
        display = screen.getText().toString();
        if(!display.equals("")&&!display.equals("ERROR")) {
            if(display.substring(display.length()-1,display.length()).equals("%"))
            {
                display = display.substring(0,display.length() - 1);
                expression = expression.substring(0,expression.length() - 4);
            }
            else {
                display = display.substring(0,display.length() - 1);
                expression = expression.substring(0,expression.length() - 1);
            }

         //   Log.i("test", "backSpace: "+expression);
        }else{
            if(display.equals("ERROR")){
                return clear();
            }
        }
        return display;
    }

    //Xu ly nhap so
    public String insertNum(String num){
        if(!pressEqual) { // pressEqual = false
            display=expression;
            display += num;
            expression += num;
            //display=themChamVao();
        }else{ // pressEqual = true -> vừa bấm = -> neu bam so -> xoa
            display=expression;
            display = num;
            expression = num;
            //display=themChamVao();
            pressEqual = false;
        }

        return display;
    }

    // Clear man hinh
    public String clear(){
        expression = "";
        display = "";
        return display;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String clickButton(IOperatorImp operatorImp){
        //Xu ly sau khi bang
        pressEqual = false;

        StringBuilder strDisplay = new StringBuilder(display);
        StringBuilder strExpression = new StringBuilder(expression);
        if ( display.equals("")){
            return display;
        }else{
            if(!display.substring(display.length() -  1 ,display.length()).matches("^-?\\d+$")) {
                if (display.substring(display.length() - 1, display.length()).matches("^[+-x÷]$")) {
                    display = strDisplay.delete(display.length() - 1, display.length()).toString();
                    display += operatorImp.getsDisplay();
                    expression = strExpression.delete(expression.length() - 1, expression.length()).toString();
                    expression += operatorImp.getsDisplayExe();
                    return display;
                }
            }
        }
        display += operatorImp.getsDisplay();
        expression += operatorImp.getsDisplayExe();
        return display;
    }

    public String clickNegative(IOperatorImp operatorImp){
        //Xu ly sau khi bang
        pressEqual = false;

        StringBuilder strDisplay = new StringBuilder(display);
        StringBuilder strExpression = new StringBuilder(expression);
        boolean flag= true;

        for (int i = display.length(); i>0 ; i--){
            //Log.i("Negative", "clickNegative - vi tri xet: "+display.substring(i-1,i));
            if (!display.substring(i-1,i).matches("^-?\\d+$")){
                if(display.substring(i-1,i).equals("-")){
                    if(i-2>-1){
                        if(display.substring(i-2,i-1).equals("(")){
                            display = strDisplay.delete(i-2,i).toString();
                            expression = strExpression.delete(i-2,i).toString();
                            flag= false;
                            break;
                        }
                    }

                }
                display = strDisplay.insert(i,operatorImp.getsDisplay()).toString();
                expression = strExpression.insert(i,operatorImp.getsDisplayExe()).toString();
                flag= false;
                break;
            }
        }
        if (flag == true){
            display = strDisplay.insert(0,operatorImp.getsDisplay()).toString();
            expression = strExpression.insert(0,operatorImp.getsDisplayExe()).toString();
        }

        //Log.i("test", "clickNegative: "+expression);
        return display;
    }

    // dành cho máy samsung.
    public String replace(String ex)
    {
        String temp = "";
        temp = ex.replaceAll(",",".");

        return temp;
    }


    public String xulyngoac() {
        //Xu ly sau khi bang
        pressEqual = false;

        if(display.equals("")){
            display = "(";
            expression = "(";
        }
        else {
            String lastChar = display.substring(display.length()-1, display.length());
            if(display.matches("^-?\\d+$")){
                display+="x(";
                expression+="*(";
            }
            else {
                if(display.indexOf("(") == -1){

                    if(lastChar.matches("^-?\\d+$")){
                        display+="x(";
                        expression+="*(";
                    }
                    else {
                        display+="(";
                        expression+="(";
                    }
                }else {
                    if(cout(display,"(")-cout(display,")")>0){
                        if(lastChar.matches("^-?\\d+$")||lastChar.equals(")")){
                            display+=")";
                            expression+=")";
                        }
                        else {
                            display+="(";
                            expression+="(";
                        }
                    }else {
                        if(lastChar.matches("^-?\\d+$")||lastChar.equals(")")){
                            display+="x(";
                            expression+="*(";
                        }
                        else {
                            display+="(";
                            expression+="(";
                        }
                    }
                }
            }

        }
        return display;
    }

    public int cout(String chuoi, String x){
        int dem = 0;
        for (int i=0 ; i< chuoi.length(); i++){
            if(chuoi.substring(i,i+1).equals(x)){
                dem++;
            }
        }
        return dem;
    }

    public String xulyCham(){
        //Xu ly sau khi bang

        if(pressEqual){ // pressEqual = true
            clear();
            pressEqual = false;

        }

        boolean haveDot = false;

        for (int i = display.length(); i>0 ;i--){
            if(!display.substring(i-1,i).matches("^-?\\d+$")){

                if(display.substring(i-1,i).equals(".")){
                    haveDot= true;
                    break;
                }
                break;
            }
        }
        if(haveDot == false){
            display+=".";
            expression+=".";
        }
        return display;
    }

    // Xu li them phay. Vi du nhu 1000 thi se hien thi la 1,000
    public String themChamVao(){
        for(int i=display.length(); i>0;i-=3){
            if(i<display.length()) {
                if (!display.substring(i, i + 1).equals(",")) {

                    String s =
                            (new StringBuilder().append(display.substring(0, i)).append(",").
                                    append(display.substring(i, display.length())).toString());
                    display = s;
                }
            }
        }
        return display;
    }
}
