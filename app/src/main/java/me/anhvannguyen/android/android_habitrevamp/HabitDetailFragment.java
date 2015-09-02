package me.anhvannguyen.android.android_habitrevamp;


import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class HabitDetailFragment extends Fragment {
    public static final String HABIT_DETAIL_URI = "HABIT_DETAIL_URI";

    private Uri mUri;


    public HabitDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_habit_detail, container, false);

        Bundle arguments = getArguments();
        if (arguments != null) {
            mUri = arguments.getParcelable(HABIT_DETAIL_URI);
        }

        TextView textView = (TextView) rootView.findViewById(R.id.detail_uri_textview);
        textView.setText(mUri.toString());

        return rootView;
    }


}
