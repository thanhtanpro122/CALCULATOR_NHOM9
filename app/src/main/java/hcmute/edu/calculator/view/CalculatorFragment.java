package hcmute.edu.calculator.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import hcmute.edu.calculator.R;
import hcmute.edu.calculator.model.operator.Number;
import hcmute.edu.calculator.presenter.CalculatorPresenter;

public class CalculatorFragment extends Fragment implements View.OnClickListener {
    private TextView screen;

    private MainActivity mainActivity;

    private CalculatorPresenter calculatorPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_calculator, container, false);

        calculatorPresenter = new CalculatorPresenter(v);

        for (Number num : calculatorPresenter.getLstBtnNumber()) {
            num.getBtn().setOnClickListener(this);
        }

        ImageButton ibtnBackSpace = (ImageButton) v.findViewById(R.id.btnBackspace);

        Button btnEqual = (Button) v.findViewById(R.id.btnEqual);

        Button btnClear = (Button) v.findViewById(R.id.btnClear);

        screen = (TextView) v.findViewById(R.id.txtViewExpression);


        calculatorPresenter.plus.getBtn().setOnClickListener(this);
        calculatorPresenter.minus.getBtn().setOnClickListener(this);
        calculatorPresenter.multi.getBtn().setOnClickListener(this);
        calculatorPresenter.div.getBtn().setOnClickListener(this);

        ibtnBackSpace.setOnClickListener(this);
        btnClear.setOnClickListener(this);

        btnEqual.setOnClickListener(this);

        calculatorPresenter.negative.getBtn().setOnClickListener(this);
        calculatorPresenter.dauNgoac.setOnClickListener(this);
        calculatorPresenter.comma.setOnClickListener(this);
        calculatorPresenter.percent.getBtn().setOnClickListener(this);
        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof MainActivity) {
            this.mainActivity = (MainActivity) context;
        }
    }

    @Override
    public void onClick(View v) {
        calculatorPresenter.onClickButton(v);
    }
}