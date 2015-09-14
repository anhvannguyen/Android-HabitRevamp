package me.anhvannguyen.android.android_habitrevamp;

import android.content.ContentValues;

import me.anhvannguyen.android.android_habitrevamp.data.HabitContract;

/**
 * Created by anhvannguyen on 9/12/15.
 */
public class Utility {

    private static long START_DATE_2015_10_01 = 1443682800000l;
    private static long END_DATE_2015_11_05 = 1446710400000l;


    public static ContentValues createHabitTestValue() {
        ContentValues values = new ContentValues();
        values.put(HabitContract.HabitEntry.COLUMN_TITLE, "Exercise");
        values.put(HabitContract.HabitEntry.COLUMN_START_DATE, START_DATE_2015_10_01);
        values.put(HabitContract.HabitEntry.COLUMN_END_DATE, END_DATE_2015_11_05);

        return values;
    }
}
