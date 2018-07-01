package com.example.ubfac.myjournalapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ubfac.myjournalapp.model.Journal;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class JournalAdapter extends RecyclerView.Adapter<JournalAdapter.JournalViewHolder> {
    private static final String TAG = JournalAdapter.class.getSimpleName();
    final private ListItemClickListener mOnclickListener;
    Context context;
    List<Journal> journalList = new ArrayList<>();


    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    public JournalAdapter(Context context, ListItemClickListener listener) {
        this.context = context;
        mOnclickListener = listener;
    }

    @Override
    public JournalAdapter.JournalViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.journal_list;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        JournalViewHolder viewHolder = new JournalViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull JournalAdapter.JournalViewHolder holder, int position) {
        Log.d(TAG, "#" + position);
        Log.e("ATG", "position data " + position);
        Journal journal = journalList.get(position);
        holder.bind(journal);
    }


    @Override
    public int getItemCount() {
        return journalList.size();
    }

    public void setData(List<Journal> journalList){
        Log.e("ATG", "setting data");
        this.journalList.clear();
        this.journalList = journalList;
        notifyDataSetChanged();

    }
    // implementing onclickListener in JournalViewHolder
    class JournalViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title, contentTv, date, time;
        public JournalViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            contentTv = itemView.findViewById(R.id.textView_content);
            time = itemView.findViewById(R.id.textView_time);
            date = itemView.findViewById(R.id.textView_date);
            // setOnclcikListener on the  View passed in to  constructor(use this code blow)
            itemView.setOnClickListener(this);
        }

        void bind(Journal journal) {
            title.setText(journal.getTitle());
            contentTv.setText(journal.getContent());
            date.setText(String.valueOf(journal.getDate()));
            time.setText(String.valueOf(journal.getTime()));
            //contentTv.setText(journal.getContent());

        }

        /**
         * Called when a view has been clicked.
         *
         * @param v The view that was clicked.
         */
        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mOnclickListener.onListItemClick(clickedPosition);
        }
    }
}
