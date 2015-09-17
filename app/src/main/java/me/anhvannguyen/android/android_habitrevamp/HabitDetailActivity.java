package me.anhvannguyen.android.android_habitrevamp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class HabitDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_detail);

        if (savedInstanceState == null) {
            Bundle arguments = new Bundle();
            arguments.putParcelable(HabitDetailFragment.HABIT_DETAIL_URI, getIntent().getData());

            HabitDetailFragment fragment = new HabitDetailFragment();
            fragment.setArguments(arguments);

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.habit_detail_container, fragment)
                    .commit();
        }
    }


}
