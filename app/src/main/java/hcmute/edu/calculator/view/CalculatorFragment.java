package hcmute.edu.calculator.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

        try {
            Button btnDiary = v.findViewById(R.id.btnDiary);
            btnDiary.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), HistoryActivity.class);
                    startActivity(intent);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

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

        // Map and add event for landscape buttons
        Button btnSin = v.findViewById(R.id.btnSin);
        if (btnSin != null) {
            btnSin.setOnClickListener(this);
            Button btnCos = v.findViewById(R.id.btnCos);
            btnCos.setOnClickListener(this);
            Button btnTan = v.findViewById(R.id.btnTan);
            btnTan.setOnClickListener(this);
        }

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof MainActivity) {
            this.mainActivity = (MainActivity) context;
        }
    }

//    @Override
//    public void onConfigurationChanged(Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//        if(newConfig.orientation==Configuration.ORIENTATION_LANDSCAPE){
//
//        }
//    }

    @Override
    public void onClick(View v) {
        calculatorPresenter.onClickButton(v);
    }
}