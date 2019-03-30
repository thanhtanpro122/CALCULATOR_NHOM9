package hcmute.edu.calculator.model.operator;

import android.widget.Button;

public class AdvOperatorWithNumber extends IOperatorImp  {
    public AdvOperatorWithNumber(int id, Button btn, String display, String displayExe) {
        super(id, btn, display, displayExe);
    }

    @Override
    public String display() {
        return sDisplay;
    }

    @Override
    public String displayExp() {
        return sDisplayExp;
    }
}
