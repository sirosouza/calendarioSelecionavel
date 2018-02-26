package br.com.newsoftwarebrasil.calendar;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private MaterialCalendarView materialCalendarView;
    private TextView tvDates;
    private Button btnGetDates;

    private static StringBuilder stringBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        materialCalendarView = findViewById(R.id.calendarView);
        tvDates = findViewById(R.id.tv_dates);
        btnGetDates = findViewById(R.id.btn_get_dates);

        materialCalendarView.setSelectionMode(MaterialCalendarView.SELECTION_MODE_RANGE);

        OnDateSelectedListener onDateSelectedListener = new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                Log.i("Date:", date.toString());
            }
        };
        materialCalendarView.setOnDateChangedListener(onDateSelectedListener);

        btnGetDates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvDates.setText("");
                stringBuilder = new StringBuilder();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                for (int i = 0; i < materialCalendarView.getSelectedDates().size(); i++) {
                    Date date = materialCalendarView.getSelectedDates().get(i).getDate();
                    stringBuilder.append("Date " + (i + 1) + ": " + simpleDateFormat.format(date) + "\n");
                }

                tvDates.setText(stringBuilder.toString());
            }
        });
    }
}
