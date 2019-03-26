package hcmute.edu.calculator.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import hcmute.edu.calculator.R;
import hcmute.edu.calculator.model.operator.Expression;
import hcmute.edu.calculator.presenter.CalculatorPresenter;

public class HistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        Toolbar toolbarHistory = findViewById(R.id.toolbarHistory);
        setSupportActionBar(toolbarHistory);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("History");
        toolbarHistory.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ListView lvHistory = findViewById(R.id.lvHistory);
        // Bind data tu danh sach history da luu len tung item trong listview
        ExpressionAdapter adapter = new ExpressionAdapter(HistoryActivity.this, R.layout.item_row, CalculatorPresenter.history);
        lvHistory.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    /**
     * Adapter for listview
     */
    class ExpressionAdapter extends ArrayAdapter<Expression> {

        Context context;
        int resource; // layout item_row
        List<Expression> objects; // danh sach history

        public ExpressionAdapter(@NonNull Context context, int resource, @NonNull List<Expression> objects) {
            super(context, resource, objects);
            this.context = context;
            this.resource = resource;
            this.objects = objects;
        }

        @Override
        public int getCount() {
            return objects.size();
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(resource, parent, false); // chuyen giao dien thanh view

            TextView txtExpression = view.findViewById(R.id.txtExpression);
            TextView txtResult = view.findViewById(R.id.txtResult);

            Expression expression = objects.get(position);

            txtExpression.setText(expression.getExpression());
            txtResult.setText("= " + expression.getResult());

            return view;
        }
    }
}
