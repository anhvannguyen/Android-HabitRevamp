package me.anhvannguyen.android.android_habitrevamp.data;

import android.provider.BaseColumns;

/**
 * Created by anhvannguyen on 8/27/15.
 */
public class HabitContract {

    public static final class HabitEntry implements BaseColumns {
        public static final String TABLE_NAME = "habits";

        // title of habit user wish to achieve
        public static final String COLUMN_TITLE = "title";

        // date of when the user want to start
        public static final String COLUMN_START_DATE = "start_date";

    }

    public static final class HabitDayCompleteEntry implements BaseColumns {
        public static final String TABLE_NAME = "complete_days";

        // HabitEntry _id value
        public static final String COLUMN_HABIT_ID = "habit_id";

        // day of complete/skipped habit
        public static final String COLUMN_DATE = "date";

        // check if user complete the habit on the day date
        public static final String COLUMN_COMPLETE = "complete";

    }
}
