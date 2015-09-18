package me.anhvannguyen.android.android_habitrevamp;


import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
    private static final int HABIT_DAY_LOADER = 1;

    private Uri mUri;
    private long mId;

    private TextView mTitleTextView;
    private TextView mStartDateTextView;

    public HabitDetailFragment() {
        // Required empty public constructor
        setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_habit_detail, container, false);

        Bundle arguments = getArguments();
        if (arguments != null) {
            mUri = arguments.getParcelable(HABIT_DETAIL_URI);
            mId = Long.valueOf(HabitContract.HabitEntry.getHabitId(mUri));
        }

        mTitleTextView = (TextView) rootView.findViewById(R.id.habit_detail_title_textview);

        mStartDateTextView = (TextView) rootView.findViewById(R.id.habit_detail_startdate_textview);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        getLoaderManager().initLoader(HABIT_DETAIL_LOADER, null, this);
        getLoaderManager().initLoader(HABIT_DAY_LOADER, null, this);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        if (mUri != null) {

            switch (id) {
                case HABIT_DETAIL_LOADER:
                    return new CursorLoader(
                            getActivity(),
                            mUri,
                            null,
                            null,
                            null,
                            null
                    );
                case HABIT_DAY_LOADER:
                    return new CursorLoader(
                            getActivity(),
                            HabitContract.DayCompleteEntry.buildAllDayUri(mId),
                            null,
                            null,
                            null,
                            HabitContract.DayCompleteEntry.COLUMN_DATE + " ASC"
                    );
            }

        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (!data.moveToFirst()) {
            return;
        }

        if (loader.getId() == HABIT_DETAIL_LOADER) {
            int titleIndex = data.getColumnIndex(HabitContract.HabitEntry.COLUMN_TITLE);
            String title = data.getString(titleIndex);
            mTitleTextView.setText(title);

            int startDateIndex = data.getColumnIndex(HabitContract.HabitEntry.COLUMN_START_DATE);
            SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy");
            long dateValue = data.getLong(startDateIndex);
            mStartDateTextView.setText(sdf.format(new Date(dateValue)));
        } else if (loader.getId() == HABIT_DAY_LOADER) {
            // TODO: Load data when view is created
        }

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_habit_detail, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_delete_item:
//                getActivity().getContentResolver().delete(
//                        HabitContract.DayCompleteEntry.CONTENT_URI,
//                        HabitContract.DayCompleteEntry.COLUMN_HABIT_ID + " = ?",
//                        new String[]{String.valueOf(mId)}
//                );
                getActivity().getContentResolver().delete(
                        mUri,
                        null,
                        null
                );
                getActivity().finish();
                return true;
            case R.id.action_settings:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
