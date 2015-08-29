package me.anhvannguyen.android.android_habitrevamp.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

/**
 * Created by anhvannguyen on 8/28/15.
 */
public class HabitContentProvider extends ContentProvider {
    private static final UriMatcher sUriMatcher = buildUriMatcher();
    private HabitDbHelper mOpenHelper;

    private static final int HABIT = 100;
    private static final int HABIT_WITH_ID = 101;

    private static final int HABIT_DATE = 200;
    private static final int HABIT_DATE_WITH_ID = 201;
    private static final int HABIT_DATE_WITH_HABIT_ID = 202;

    private static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = HabitContract.CONTENT_AUTHORITY;

        matcher.addURI(authority, HabitContract.PATH_HABIT, HABIT);
        matcher.addURI(authority, HabitContract.PATH_HABIT + "/#", HABIT_WITH_ID);

        matcher.addURI(authority, HabitContract.PATH_HABIT_DAY, HABIT_DATE);
        matcher.addURI(authority, HabitContract.PATH_HABIT_DAY + "/#", HABIT_DATE_WITH_ID);
        matcher.addURI(authority, HabitContract.PATH_HABIT + "/#/" + HabitContract.PATH_HABIT_ALL_DAYS, HABIT_DATE_WITH_HABIT_ID);

        return matcher;
    }

    @Override
    public boolean onCreate() {
        mOpenHelper = new HabitDbHelper(getContext());
        return true;
    }

    @Override
    public String getType(Uri uri) {
        // Use the Uri Matcher to determine what kind of URI this is.
        final int match = sUriMatcher.match(uri);

        switch (match) {
            case HABIT:
                return HabitContract.HabitEntry.CONTENT_TYPE;
            case HABIT_WITH_ID:
                return HabitContract.HabitEntry.CONTENT_ITEM_TYPE;
            case HABIT_DATE:
                return HabitContract.HabitDayCompleteEntry.CONTENT_TYPE;
            case HABIT_DATE_WITH_ID:
                return HabitContract.HabitDayCompleteEntry.CONTENT_ITEM_TYPE;
            case HABIT_DATE_WITH_HABIT_ID:
                return HabitContract.HabitDayCompleteEntry.CONTENT_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor returnCursor;
        final int match = sUriMatcher.match(uri);
        SQLiteDatabase db = mOpenHelper.getReadableDatabase();

        switch (match) {
            case HABIT:
                returnCursor = db.query(
                        HabitContract.HabitEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            case HABIT_DATE:
                returnCursor = db.query(
                        HabitContract.HabitDayCompleteEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        // notify content resolver that there is a change
        returnCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return returnCursor;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
