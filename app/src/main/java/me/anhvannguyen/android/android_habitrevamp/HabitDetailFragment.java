package me.anhvannguyen.android.android_habitrevamp;


import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import me.anhvannguyen.android.android_habitrevamp.data.HabitContract;


/**
 * A simple {@link Fragment} subclass.
 */
public class HabitDetailFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    public static final String HABIT_DETAIL_URI = "HABIT_DETAIL_URI";

    private static final int HABIT_DETAIL_LOADER = 0;

    private Uri mUri;

    private TextView mTitleTextView;
    private TextView mStartDateTextView;

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

        mTitleTextView = (TextView) rootView.findViewById(R.id.habit_detail_title_textview);

        mStartDateTextView = (TextView) rootView.findViewById(R.id.habit_detail_startdate_textview);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        getLoaderManager().initLoader(HABIT_DETAIL_LOADER, null, this);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        if (mUri != null) {
            return new CursorLoader(
                    getActivity(),
                    mUri,
                    null,
                    null,
                    null,
                    null
            );
        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (!data.moveToFirst()) {
            return;
        }

        int titleIndex = data.getColumnIndex(HabitContract.HabitEntry.COLUMN_TITLE);
        String title = data.getString(titleIndex);
        mTitleTextView.setText(title);

        int startDateIndex = data.getColumnIndex(HabitContract.HabitEntry.COLUMN_START_DATE);
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy");
        long dateValue = data.getLong(startDateIndex);
        mStartDateTextView.setText(sdf.format(new Date(dateValue)));

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
