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
    public static final String PATH_HABIT_DAY = "habitday";

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

        // content://me.anhvannguyen.android.android_habitrevamp/habit/{id}
        public static String getHabitId(Uri uri) {
            return uri.getPathSegments().get(1);
        }

        public static Uri buildHabitUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

    }

    public static final class HabitDayCompleteEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_HABIT_DAY).build();

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_HABIT_DAY;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_HABIT_DAY;

        public static final String TABLE_NAME = "complete_days";

        // HabitEntry _id value
        public static final String COLUMN_HABIT_ID = "habit_id";

        // day of complete/skipped habit
        public static final String COLUMN_DATE = "date";

        // check if user complete the habit on the day date
        public static final String COLUMN_COMPLETE = "complete";

        public static Uri buildHabitDayUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

    }
}
