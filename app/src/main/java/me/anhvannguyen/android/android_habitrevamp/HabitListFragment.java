package me.anhvannguyen.android.android_habitrevamp;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class HabitListFragment extends Fragment {

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

        mHabitListRecyclerView = (RecyclerView) rootView.findViewById(R.id.main_habit_recyclerview);
        mHabitListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

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


}
