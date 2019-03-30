package hcmute.edu.calculator.model.operator;

import android.widget.Button;

public abstract class IOperatorImp implements IOperator{
    protected int id;
    protected Button btn;
    protected String sDisplay;
    protected String sDisplayExp;

    public IOperatorImp(int id, Button btn ,String display, String displayExp){
        this.id = id;
        this.btn = btn;
        this.sDisplay = display;
        this.sDisplayExp = displayExp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }

    public String getsDisplay() {
        return sDisplay;
    }

    public void setsDisplay(String sDisplay) {
        this.sDisplay = sDisplay;
    }

    public String getsDisplayExp() {
        return sDisplayExp;
    }

    public void setsDisplayExp(String sDisplayExp) {
        this.sDisplayExp = sDisplayExp;
    }
}
