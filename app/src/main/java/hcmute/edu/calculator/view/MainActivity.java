package hcmute.edu.calculator.view;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import hcmute.edu.calculator.R;

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
}
