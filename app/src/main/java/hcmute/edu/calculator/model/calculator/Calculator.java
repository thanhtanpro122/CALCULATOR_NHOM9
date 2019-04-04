package hcmute.edu.calculator.model.calculator;

import android.util.Log;
import android.widget.TextView;

import org.mariuszgromada.math.mxparser.Expression;

import java.text.DecimalFormat;

import hcmute.edu.calculator.model.operator.AdvOperatorWithNumber;
import hcmute.edu.calculator.model.operator.AdvancedOperator;
import hcmute.edu.calculator.model.operator.IOperatorImp;
import hcmute.edu.calculator.model.operator.NormalOperator;


public class Calculator {

    //Initialize view's properties and controller's properties
    private String expression;
    private String display;
    public boolean pressEqual;

    private String TAG = "Calculator";

    public Calculator(){
        this.expression = "0";
        this.display = "0";
        pressEqual = false;
    }

    /**
     * Handling when press Equal Button
     * @return display of result or ERROR
     */
    public String execute(){
        try {
            if(!display.matches("^-?\\d+$")){
                pressEqual = true;
                findCloseParenthesis();
                Log.i("test", "execute: "+expression);
                Expression ex = new Expression(removeFormatThousand(expression)); // xoa dau ','
                Double result = ex.calculate();

                if (result.isNaN()) {
                    throw new Exception();
                }else{
                    setResult(result);
                    return display;
                }
            }
            return display;
        } catch (Exception e) {
            clear();
            display = "ERROR";
            return display;
        }
    }

    /**
     * Use to find and add close parenthesis when you press Equal Button
     */
    public void findCloseParenthesis(){
        int countDauNgoac = cout(display, "(")-cout(expression, ")");
        if(countDauNgoac > 0){
            for(int i = 0 ; i < countDauNgoac ; i++){
                display += ")";
                expression += ")";
            }
        }
    }

    /**
     * Set result after format for display and expression
     * @param result value of expression calculate
     */
    public void setResult(double result){
        expression = formatDecimal(result);
        display = formatDecimal(result);
    }

    //Handling erase button

    /**
     * Handling erase button
     * @return display after delete last string
     */
    public String backSpace(){
        pressEqual = false;
        String lastChar = getLastString();
        if(display.length() == 1){
            return clear();
        }else {
            if (!display.equals("ERROR")) {
                if (lastChar.equals("√")) {
                    deleteSquareRoot();
                } else {
                    deleteLastString();
                }
            } else {
                if (display.equals("ERROR")) {
                    return clear();
                }
            }
        }
        return display;
    }

    /**
     * Use to delete 1 last string
     */
    public void deleteLastString(){
        display = display.substring(0, display.length() - 1);
        expression = expression.substring(0, expression.length() - 1);
    }

    /**
     * Use to delete square root
     * In display √
     * In expression sqrt
     */
    public void deleteSquareRoot(){
        display = display.substring(0, display.length() - 1);
        expression = expression.substring(0, expression.length() - 4);
    }

    /**
     * Dùng để thêm 1 số vào display
     * * Nếu chưa bấm ' = '
     * * * Nếu ban đầu ( display.equals("0") ) //display.length() = 0
     * * * * Sẽ gán trực tiếp số vừa nhập vào display | expression
     * * * Nếu không ( đã nhập 1 lần )
     * * * * Nếu kí tự cuối là ! | % )
     * * * * * Cộng thêm dấu nhân
     * * * * Cộng số vừa nhập vào display, expression
     * * Nếu không ( đã bấm ' = ' trước đó )
     * * * Gán trực tiếp số vào display
     * @param num số bấm từ bàn phím
     * @return display chuỗi in ra màn hình
     */
    public String insertNum(String num){
        if(!pressEqual) {
            if(display.equals("0")){
                display = num;
                expression = num;
            } else{
                if(checkAfterSpecialSymbol()) {
                    display += "x";
                    expression += "*";
                }
                display += num;
                expression += num;
            }
        }
        // pressEqual = true -> vừa bấm = -> neu bam so -> xoa
        else{
            display = num;
            expression = num;
//            pressEqual = false;
        }

        return display;
    }

    /**
     * Khởi tạo lại màn hình ban đầu
     * @return display chuỗi in ra màn hình
     */
    public String clear(){
        expression = "0";
        display = "0";
        return display;
    }

    /**
     * Click operator button
     * @param operatorImp operator you click
     * @return display
     */
    public String clickButton(IOperatorImp operatorImp){
        if ( display.equals("0")){
            if(operatorImp instanceof AdvancedOperator){
                addOperatorReplaceDisplay(operatorImp);
            }
            return display;
        }
        else{
            if(operatorImp instanceof NormalOperator) {
                if (!checkAfterNumber()) {
                    if (checkAfterNormalOperater()) {
                        changeNormalOperator(operatorImp);
                        return display;
                    }
                }
            }else{
                if(operatorImp instanceof AdvancedOperator){
                    if(!checkAfterOpenParenthesis()) {
                        addAdvOperatorAfterNumber(operatorImp);
                        return display;
                    }else{
                        addOperator(operatorImp);
                        return display;
                    }
                }else{
                    if(operatorImp instanceof AdvOperatorWithNumber)
                    {
                        if(checkAfterNumber()) {
                            addOperator(operatorImp);
                            return display;

                        }
                    }
                }
            }
        }
        addOperator(operatorImp);
        return display;
    }

    /**
     * Get last 1 string
     * @return 1 string
     */
    public String getLastString(){
        return display.substring(display.length() - 1, display.length());
    }

    /**
     * Check after open parenthesis
     * @return TRUE | FALSE
     */
    public boolean checkAfterOpenParenthesis(){
        return getLastString().matches("^[(]$");
    }

    /**
     * Check if last string is number
     * @return TRUE : before is number | FALSE : not
     */
    public boolean checkAfterNumber(){
        return getLastString().matches("^-?\\d+$");
    }

    /**
     * Check if last string is NormalOperator | it use for NormalOperator | when you change operator
     * @return TRUE : before is NormalOperator | FALSE : not
     */
    public boolean checkAfterNormalOperater(){
        return getLastString().matches("^[+-x÷]$");
    }

    /**
     * When open or after clear, you click AdvancedOperator,it will replace 0
     * @param operatorImp what you click
     */
    public void addOperatorReplaceDisplay(IOperatorImp operatorImp){
        display = operatorImp.getsDisplay();
        expression = operatorImp.getsDisplayExp();
    }

    /**
     * Add 1 operater
     * @param operatorImp what you click
     */
    public void addOperator(IOperatorImp operatorImp){
        display += operatorImp.getsDisplay();
        expression += operatorImp.getsDisplayExp();
    }

    /**
     * Add AdvanceOperator with Multi
     * @param operatorImp what you click
     */
    public void addOperatorWithMulti(IOperatorImp operatorImp){
        display += "x" + operatorImp.getsDisplay();
        expression += "*" + operatorImp.getsDisplayExp();
    }

    /**
     * If before is number and you click AdvanceOperator, it will auto add multi before AdvanceOperator
     * @param operatorImp what you click
     */
    public void addAdvOperatorAfterNumber(IOperatorImp operatorImp){
        addOperatorWithMulti(operatorImp);
    }

    /**
     * If before it is a NormalOperator and you clich another NormalOperator, it wil change this NormalOperator
     * @param operatorImp what you click
     */
    public void changeNormalOperator(IOperatorImp operatorImp){
        StringBuilder strDisplay = new StringBuilder(display);
        StringBuilder strExpression = new StringBuilder(expression);

        display = strDisplay.delete(display.length() - 1, display.length()).toString();
        display += operatorImp.getsDisplay();
        expression = strExpression.delete(expression.length() - 1, expression.length()).toString();
        expression += operatorImp.getsDisplayExp();
    }

    /**
     * Process when you click plus/sub method, it will change positive to negative and vice versa
     * @param operatorImp plus/sub button
     * @return display string
     */
    public String clickNegative(IOperatorImp operatorImp){
        //Xu ly sau khi bang
//        pressEqual = false;

        StringBuilder strDisplay = new StringBuilder(display);
        StringBuilder strExpression = new StringBuilder(expression);

        boolean flag= true;

        //Duyệt từ cuối chuỗi
        for (int i = display.length(); i>0 ; i--){
            //If kí tự đang xét KHÔNG là số
            if (!display.substring(i-1,i).matches("^-?\\d+$")){
                //If kí tự đang xét là dấu " - "
                if(display.substring(i-1,i).equals("-")){
                    if(i-1>0){
                        if(display.substring(i-2,i-1).equals("(")){
                            display = strDisplay.delete(i-2,i).toString();
                            expression = strExpression.delete(i-2,i).toString();
                            flag= false;
                            break;
                        }
                    }
                }
                display = strDisplay.insert(i,operatorImp.getsDisplay()).toString();
                expression = strExpression.insert(i,operatorImp.getsDisplayExp()).toString();
                flag= false;
                break;
            }
        }
        if (flag == true){
            display = strDisplay.insert(0,operatorImp.getsDisplay()).toString();
            expression = strExpression.insert(0,operatorImp.getsDisplayExp()).toString();
        }
        return display;
    }

    /**
     * Format before calculate expression
     * @param ex string need format
     * @return string after format
     */
    public String removeFormatThousand(String ex){
        return ex.replaceAll(",","");
    }

    /**
     * Format number after calculate
     * @param ex number need format
     * @return string after format
     */
    public String formatDecimal(Double ex){
        DecimalFormat df = new DecimalFormat("#,###.##########");
        return df.format(ex);
    }
    /**
     * Format number after calculate
     * @param ex string need format
     * @return string after format
     */
    public String formatDecimal(String ex){
        DecimalFormat df = new DecimalFormat("#,###.##########");
        return df.format(ex);
    }


//    /**
//     * It for Samsung bug
//     * @param ex string need format
//     * @return string after format
//     */
//    public String replace(String ex)
//    {
//        String temp = "";
//        temp = ex.replaceAll(",",".");
//
//        return temp;
//    }

    //Xử lý dấu ngoặc
    public String addParenthesis() {
        //Xu ly sau khi bang
//        pressEqual = false;

        if(display.equals("")){
            display = "(";
            expression = "(";
        }
        else {
            String lastChar = display.substring(display.length()-1, display.length());
            if(display.indexOf("(") == -1){
                if(lastChar.matches("^-?\\d+$") || lastChar.matches("^[!%)πe]$")){
                    display+="x(";
                    expression+="*(";
                }
                else {
                    display+="(";
                    expression+="(";
                }
            }else {
                if(cout(display,"(")-cout(display,")")>0){
                    if(lastChar.matches("^-?\\d+$")||checkAfterSpecialSymbol()){
                        display+=")";
                        expression+=")";
                    }
                    else {
                        display+="(";
                        expression+="(";
                    }
                }else {
                    if(lastChar.matches("^-?\\d+$") || checkAfterSpecialSymbol()){
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
        return display;
    }

    /**
     * Check if it after special symbol ( ! , % , ) , π , e ), after this must be insert *
     * @return TRUE | FALSE
     */
    public boolean checkAfterSpecialSymbol(){
        return getLastString().matches("^[!%)πe]$");
    }

    /**
     *  Count the number of duplicates in a string
     * @param string string check
     * @param x what check in string
     * @return
     */
    public int cout(String string, String x){
        int count = 0;
        for (int i=0 ; i< string.length(); i++){
            if(string.substring(i,i+1).equals(x)){
                count++;
            }
        }
        return count;
    }


    /**
     * Handling when have dots in expression
     * @return display
     */
    public String addComma(){
        if(pressEqual){
            clear();
//            pressEqual = false;
        }
        boolean haveDot = false;
        //Check if have dots in expression
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
            if(!checkAfterNumber()){
                insertNum("0.");
            }
            else{
                insertNum(".");
            }

        }
        return display;
    }
}
