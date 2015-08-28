package me.anhvannguyen.android.android_habitrevamp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by anhvannguyen on 8/28/15.
 */
public class HabitDbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    static final String DATABASE_NAME = "habitrevamp.db";

    public HabitDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_HABIT_TABLE = "CREATE TABLE " + HabitContract.HabitEntry.TABLE_NAME + " (" +
                HabitContract.HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                HabitContract.HabitEntry.COLUMN_TITLE + " TEXT, " +
                HabitContract.HabitEntry.COLUMN_START_DATE + " INTEGER " +
                ");";

        final String SQL_CREATE_HABIT_COMPLETED_TABLE = "CREATE TABLE " + HabitContract.HabitDayCompleteEntry.TABLE_NAME + " (" +
                HabitContract.HabitDayCompleteEntry._ID + " INTEGER PRIMARY KEY, " +
                HabitContract.HabitDayCompleteEntry.COLUMN_HABIT_ID + " INTEGER, " +
                HabitContract.HabitDayCompleteEntry.COLUMN_DATE + " INTEGER, " +
                HabitContract.HabitDayCompleteEntry.COLUMN_COMPLETE + " INTEGER " +
                ");";
        
        db.execSQL(SQL_CREATE_HABIT_TABLE);
        db.execSQL(SQL_CREATE_HABIT_COMPLETED_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
