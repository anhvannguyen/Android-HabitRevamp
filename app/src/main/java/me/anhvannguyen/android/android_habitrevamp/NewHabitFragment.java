package me.anhvannguyen.android.android_habitrevamp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewHabitFragment extends Fragment {
    private EditText mTitleEditText;
    private EditText mSubtitleEditText;
    private Button mStartDateButton;
    private TextView mStartDateTextView;
    private Button mSaveButton;

    public NewHabitFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_new_habit, container, false);

        mTitleEditText = (EditText) rootView.findViewById(R.id.habit_title_edittext);

        mSubtitleEditText = (EditText) rootView.findViewById(R.id.habit_subtitle_edittext);

        mStartDateButton = (Button) rootView.findViewById(R.id.habit_select_date_button);
        mStartDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });

        mStartDateTextView = (TextView) rootView.findViewById(R.id.habit_start_date_textview);

        mSaveButton = (Button) rootView.findViewById(R.id.habit_save_button);
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return rootView;
    }


}
