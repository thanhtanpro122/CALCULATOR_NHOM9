package hcmute.edu.calculator.model.operator;

import android.widget.Button;

public class NormalOperator extends IOperatorImp {

    public NormalOperator(int id, Button btn, String display, String displayExe) {
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
