package me.anhvannguyen.android.android_habitrevamp;


import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import me.anhvannguyen.android.android_habitrevamp.data.HabitContract;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewHabitFragment extends Fragment {
    private static final String LOG_TAG = NewHabitFragment.class.getSimpleName();

    public static final int DAY_INACTIVE = 0;
    public static final int DAY_ACTIVE = 1;

    private EditText mTitleEditText;
    private EditText mSubtitleEditText;
    private Button mStartDateButton;
    private TextView mStartDateTextView;
    private TextView mEndDateTextView;
    private CheckBox mAllDaysCheckbox;
    private ToggleButton mSundayToggle;
    private ToggleButton mMondayToggle;
    private ToggleButton mTuesdayToggle;
    private ToggleButton mWednesdayToggle;
    private ToggleButton mThursdayToggle;
    private ToggleButton mFridayToggle;
    private ToggleButton mSaturdayToggle;
    private Button mSaveButton;

    private Calendar mStartDate;
    private Calendar mEndDate;

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

        mEndDate = Calendar.getInstance();
        mEndDate.setTime(mStartDate.getTime());
        mEndDate.add(Calendar.DATE, 35);

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
        mEndDateTextView = (TextView) rootView.findViewById(R.id.habit_end_date_textview);
        updateDateLabel();

        mAllDaysCheckbox = (CheckBox) rootView.findViewById(R.id.habit_all_days_checkbox);

        mSundayToggle = (ToggleButton) rootView.findViewById(R.id.habit_new_sunday_togglebutton);
        mMondayToggle = (ToggleButton) rootView.findViewById(R.id.habit_new_monday_togglebutton);
        mTuesdayToggle = (ToggleButton) rootView.findViewById(R.id.habit_new_tuesday_togglebutton);
        mWednesdayToggle = (ToggleButton) rootView.findViewById(R.id.habit_new_wednesday_togglebutton);
        mThursdayToggle = (ToggleButton) rootView.findViewById(R.id.habit_new_thursday_togglebutton);
        mFridayToggle = (ToggleButton) rootView.findViewById(R.id.habit_new_friday_togglebutton);
        mSaturdayToggle = (ToggleButton) rootView.findViewById(R.id.habit_new_saturday_togglebutton);

        checkAllDays();

        mAllDaysCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkAllDays();
            }
        });


        mSaveButton = (Button) rootView.findViewById(R.id.habit_save_button);
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inputHasError() == false) {
                    // TODO: move off of main thread
                    ContentValues values = generateContentValues();

                    getActivity().getContentResolver().insert(HabitContract.HabitEntry.CONTENT_URI, values);
                    getActivity().finish();
                }
            }
        });

        return rootView;
    }

    private void updateDateLabel() {
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy - hh:mm:ssa zzz");
        mStartDateTextView.setText(sdf.format(mStartDate.getTime()));
        mEndDateTextView.setText(sdf.format(mEndDate.getTime()));
    }

    /**
     * Helper method to check if the input fields has any error.
     * Currently only the title field is required.
     */
    private boolean inputHasError() {
        boolean inputError = false;

        if (mTitleEditText.length() <= 0) {
            inputError = true;
            mTitleEditText.setError("Title Required");
        } else {
            mTitleEditText.setError(null);
        }

        return inputError;
    }

    private void checkAllDays() {
        if (mAllDaysCheckbox.isChecked()) {
            mSundayToggle.setEnabled(false);
            mMondayToggle.setEnabled(false);
            mTuesdayToggle.setEnabled(false);
            mWednesdayToggle.setEnabled(false);
            mThursdayToggle.setEnabled(false);
            mFridayToggle.setEnabled(false);
            mSaturdayToggle.setEnabled(false);
        } else {
            mSundayToggle.setEnabled(true);
            mMondayToggle.setEnabled(true);
            mTuesdayToggle.setEnabled(true);
            mWednesdayToggle.setEnabled(true);
            mThursdayToggle.setEnabled(true);
            mFridayToggle.setEnabled(true);
            mSaturdayToggle.setEnabled(true);
        }
    }

    private int checkDayOfWeek(ToggleButton toggleButton) {
        return toggleButton.isChecked() ? DAY_ACTIVE : DAY_INACTIVE;
    }

    private ContentValues generateContentValues() {
        ContentValues values = new ContentValues();
        values.put(HabitContract.HabitEntry.COLUMN_TITLE, mTitleEditText.getText().toString());
        values.put(HabitContract.HabitEntry.COLUMN_START_DATE, mStartDate.getTimeInMillis());
        values.put(HabitContract.HabitEntry.COLUMN_END_DATE, mEndDate.getTimeInMillis());
        // Mark all days of the week active if the checkbox is checked
        if (mAllDaysCheckbox.isChecked()) {
            values.put(HabitContract.HabitEntry.COLUMN_ACTIVE_SUNDAY, DAY_ACTIVE);
            values.put(HabitContract.HabitEntry.COLUMN_ACTIVE_MONDAY, DAY_ACTIVE);
            values.put(HabitContract.HabitEntry.COLUMN_ACTIVE_TUESDAY, DAY_ACTIVE);
            values.put(HabitContract.HabitEntry.COLUMN_ACTIVE_WEDNESDAY, DAY_ACTIVE);
            values.put(HabitContract.HabitEntry.COLUMN_ACTIVE_THURSDAY, DAY_ACTIVE);
            values.put(HabitContract.HabitEntry.COLUMN_ACTIVE_FRIDAY, DAY_ACTIVE);
            values.put(HabitContract.HabitEntry.COLUMN_ACTIVE_SATURDAY, DAY_ACTIVE);
        } else {
            values.put(HabitContract.HabitEntry.COLUMN_ACTIVE_SUNDAY, checkDayOfWeek(mSundayToggle));
            values.put(HabitContract.HabitEntry.COLUMN_ACTIVE_MONDAY, checkDayOfWeek(mMondayToggle));
            values.put(HabitContract.HabitEntry.COLUMN_ACTIVE_TUESDAY, checkDayOfWeek(mTuesdayToggle));
            values.put(HabitContract.HabitEntry.COLUMN_ACTIVE_WEDNESDAY, checkDayOfWeek(mWednesdayToggle));
            values.put(HabitContract.HabitEntry.COLUMN_ACTIVE_THURSDAY, checkDayOfWeek(mThursdayToggle));
            values.put(HabitContract.HabitEntry.COLUMN_ACTIVE_FRIDAY, checkDayOfWeek(mFridayToggle));
            values.put(HabitContract.HabitEntry.COLUMN_ACTIVE_SATURDAY, checkDayOfWeek(mSaturdayToggle));
        }

        return values;
    }

    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mStartDate.set(Calendar.YEAR, year);
            mStartDate.set(Calendar.MONTH, monthOfYear);
            mStartDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            mEndDate.setTime(mStartDate.getTime());
            mEndDate.add(Calendar.DATE, 35);

            updateDateLabel();
        }
    };
}
