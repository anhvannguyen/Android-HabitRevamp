package me.anhvannguyen.android.android_habitrevamp;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewHabitFragment extends Fragment {
    private EditText mTitleEditText;
    private EditText mSubtitleEditText;
    private Button mStartDateButton;
    private TextView mStartDateTextView;
    private Button mSaveButton;

    private Calendar mStartDate;

    public NewHabitFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_new_habit, container, false);

        // Get the current time in the default time zone with default locale
        mStartDate = Calendar.getInstance();
        // Zero out the time
        mStartDate.set(Calendar.HOUR_OF_DAY, 0);
        mStartDate.set(Calendar.MINUTE, 0);
        mStartDate.set(Calendar.SECOND, 0);
        mStartDate.set(Calendar.MILLISECOND, 0);

        mTitleEditText = (EditText) rootView.findViewById(R.id.habit_title_edittext);

        mSubtitleEditText = (EditText) rootView.findViewById(R.id.habit_subtitle_edittext);

        mStartDateButton = (Button) rootView.findViewById(R.id.habit_select_date_button);
        mStartDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(
                        getActivity(),
                        dateSetListener,
                        mStartDate.get(Calendar.YEAR),
                        mStartDate.get(Calendar.MONTH),
                        mStartDate.get(Calendar.DAY_OF_MONTH))
                        .show();
            }
        });

        mStartDateTextView = (TextView) rootView.findViewById(R.id.habit_start_date_textview);
        updateDateLabel();

        mSaveButton = (Button) rootView.findViewById(R.id.habit_save_button);
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return rootView;
    }

    private void updateDateLabel() {
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy - hh:mm:ssa zzzz");
        mStartDateTextView.setText(sdf.format(mStartDate.getTime()));
    }

    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mStartDate.set(Calendar.YEAR, year);
            mStartDate.set(Calendar.MONTH, monthOfYear);
            mStartDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateDateLabel();
        }
    };
}
