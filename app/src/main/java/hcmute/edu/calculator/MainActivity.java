package hcmute.edu.calculator;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private CalculatorFragment fragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction ft= getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_calculator, new CalculatorFragment());
        ft.commit();

    }

//    public void showText(String txt) {
//        CalculatorFragment calculatorFragment
//                = (CalculatorFragment) this.getSupportFragmentManager()
//                .findFragmentById(R.id.fragment_calculator);
//        calculatorFragment.showText(txt);
//    }
}
