package me.anhvannguyen.android.android_habitrevamp;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.anhvannguyen.android.android_habitrevamp.data.HabitContract;


/**
 * A simple {@link Fragment} subclass.
 */
public class HabitListFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final int HABIT_LIST_LOADER = 0;

    private RecyclerView mHabitListRecyclerView;
    private HabitRecyclerAdapter mHabitRecyclerAdapter;
    private FloatingActionButton mAddNewHabitButton;
    private CoordinatorLayout mCoordinatorLayout;


    public HabitListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_habit_list, container, false);

        mCoordinatorLayout = (CoordinatorLayout) rootView.findViewById(R.id.coordinator_layout);

        mHabitRecyclerAdapter = new HabitRecyclerAdapter(getActivity(),
                new HabitRecyclerAdapter.HabitAdapterOnClickHandler() {
                    @Override
                    public void onClick(HabitRecyclerAdapter.ViewHolder viewHolder) {
                        //Snackbar.make(mCoordinatorLayout, "Position " + viewHolder.getAdapterPosition() + " Clicked", Snackbar.LENGTH_SHORT).show();
                        Intent intent = new Intent(getActivity(), HabitDetailActivity.class);
                        startActivity(intent);
                    }
                });

        mHabitListRecyclerView = (RecyclerView) rootView.findViewById(R.id.main_habit_recyclerview);
        mHabitListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mHabitListRecyclerView.setAdapter(mHabitRecyclerAdapter);

        mAddNewHabitButton = (FloatingActionButton) rootView.findViewById(R.id.add_fab);
        mAddNewHabitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), NewHabitActivity.class);
                startActivity(intent);
            }
        });

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        getLoaderManager().initLoader(HABIT_LIST_LOADER, null, this);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        getLoaderManager().restartLoader(HABIT_LIST_LOADER, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(
                getActivity(),
                HabitContract.HabitEntry.CONTENT_URI,
                null,
                null,
                null,
                null
        );
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mHabitRecyclerAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mHabitRecyclerAdapter.swapCursor(null);
    }
}
