package me.anhvannguyen.android.android_habitrevamp.data;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by anhvannguyen on 8/27/15.
 */
public class HabitContract {

    public static final String CONTENT_AUTHORITY = "me.anhvannguyen.android.android_habitrevamp";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_HABIT = "habit";
    public static final String PATH_DAY = "day";
    public static final String PATH_ALL_DAYS = "alldays";

    public static final class HabitEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_HABIT).build();

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_HABIT;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_HABIT;

        public static final String TABLE_NAME = "habits";

        // title of habit user wish to achieve
        public static final String COLUMN_TITLE = "title";

        // date of when the user want to start
        public static final String COLUMN_START_DATE = "start_date";

        // date of when the habit tracker stops (currently 35 days from start)
        public static final String COLUMN_END_DATE = "end_date";

        // day of week where the habit is active
        public static final String COLUMN_ACTIVE_SUNDAY = "active_sunday";
        public static final String COLUMN_ACTIVE_MONDAY = "active_monday";
        public static final String COLUMN_ACTIVE_TUESDAY = "active_tuesday";
        public static final String COLUMN_ACTIVE_WEDNESDAY = "active_wednesday";
        public static final String COLUMN_ACTIVE_THURSDAY = "active_thursday";
        public static final String COLUMN_ACTIVE_FRIDAY = "active_friday";
        public static final String COLUMN_ACTIVE_SATURDAY = "active_saturday";

        // content://me.anhvannguyen.android.android_habitrevamp/habit/{id}
        public static String getHabitId(Uri uri) {
            return uri.getPathSegments().get(1);
        }

        public static Uri buildHabitUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

    }

    public static final class DayCompleteEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_DAY).build();

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_DAY;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_DAY;

        public static final String TABLE_NAME = "complete_days";

        // HabitEntry _id value
        public static final String COLUMN_HABIT_ID = "habit_id";

        // day of complete/skipped habit
        public static final String COLUMN_DATE = "date";

        // check if user complete the habit on the day date
        public static final String COLUMN_COMPLETE = "complete";

        public static String getHabitDateId(Uri uri) {
            return uri.getPathSegments().get(1);
        }

        public static Uri buildHabitDayUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildAllDayUri(long id) {
            return HabitEntry.buildHabitUri(id).buildUpon()
                    .appendPath(PATH_ALL_DAYS)
                    .build();

        }

    }
}
