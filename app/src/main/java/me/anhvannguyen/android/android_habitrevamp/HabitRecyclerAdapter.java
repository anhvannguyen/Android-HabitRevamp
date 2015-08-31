package me.anhvannguyen.android.android_habitrevamp;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import me.anhvannguyen.android.android_habitrevamp.data.HabitContract;

/**
 * Created by anhvannguyen on 8/26/15.
 */
public class HabitRecyclerAdapter extends RecyclerView.Adapter<HabitRecyclerAdapter.ViewHolder> {
    private Cursor mCursor;
    private Context mContext;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTitleTextView;
        public TextView mSubtitleTextView;
        public TextView mDaysCompleteTextView;
        public TextView mStartDateTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            mTitleTextView = (TextView) itemView.findViewById(R.id.habit_list_title_textview);
            mSubtitleTextView = (TextView) itemView.findViewById(R.id.habit_list_subtitle_textview);
            mDaysCompleteTextView = (TextView) itemView.findViewById(R.id.habit_list_days_complete_textview);
            mStartDateTextView = (TextView) itemView.findViewById(R.id.habit_list_start_date_textview);
        }
    }

    public HabitRecyclerAdapter(Context context) {
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.habit_list_item, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        mCursor.moveToPosition(position);

        int titleIdx = mCursor.getColumnIndex(HabitContract.HabitEntry.COLUMN_TITLE);
        String titleString = mCursor.getString(titleIdx);
        holder.mTitleTextView.setText(titleString);

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        int dateIdx = mCursor.getColumnIndex(HabitContract.HabitEntry.COLUMN_START_DATE);
        long dateValue = mCursor.getLong(dateIdx);
        holder.mStartDateTextView.setText(sdf.format(new Date(dateValue)));

        // TODO: Remove or replace placeholder values
        holder.mSubtitleTextView.setText("1 Hour");
        holder.mDaysCompleteTextView.setText("Complete 02/35");
    }

    @Override
    public int getItemCount() {
        if (mCursor == null) {
            return 0;
        }
        return mCursor.getCount();
    }

    public void swapCursor(Cursor newCursor) {
        mCursor = newCursor;
        notifyDataSetChanged();
    }

    public Cursor getCursor() {
        return mCursor;
    }

}
