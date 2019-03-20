package hcmute.edu.calculator.model.operator;

import android.view.View;
import android.widget.Button;

public class Number {
    private int id;
    private String display;
    private View btn;
    public Number(int id, View btn, String display){
        this.id = id;
        this.btn = btn;
        this.display = display;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public View getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }
}
