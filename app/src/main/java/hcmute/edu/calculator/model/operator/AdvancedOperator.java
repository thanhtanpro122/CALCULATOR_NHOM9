package hcmute.edu.calculator.model.operator;

import android.widget.Button;

public class AdvancedOperator extends IOperatorImp  {
    public AdvancedOperator(int id, Button btn, String display, String displayExe) {
        super(id, btn, display, displayExe);
    }

    @Override
    public String display() {
        return sDisplay;
    }

    @Override
    public String displayExe() {
        return sDisplayExe;
    }
}
