package com.vimal.loadmorerecyclerview.adapter;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.vimal.loadmorerecyclerview.R;

import java.io.File;
import java.util.ArrayList;


public class AdpPostDataPagination extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public final int TYPE_MOVIE = 0;
    public final int TYPE_LOAD = 1;
    static Context context;
    boolean audio = false;
    ArrayList<String> dataSet;
    OnLoadMoreListener loadMoreListener;
    boolean isLoading = false, isMoreDataAvailable = true;
    onProductClick click;
    public static MediaPlayer mediaPlayer;
    File imageDir = new File(Environment.getExternalStorageDirectory() + File.separator + "Lyrical.ly");
    String dir = imageDir + File.separator + "RingtoneOnline/";
    int selectpos = -1;

    public interface onProductClick {
        void click(int position);
    }

    public AdpPostDataPagination(Context context, ArrayList<String> data, onProductClick click) {
        this.context = context;
        this.dataSet = data;
        this.click = click;
        mediaPlayer = new MediaPlayer();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        if (viewType == TYPE_MOVIE) {
            return new MovieHolder(inflater.inflate(R.layout.dataadapter, parent, false));
        } else {
            return new LoadHolder(inflater.inflate(R.layout.row_load, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        if (position >= getItemCount() - 1 && isMoreDataAvailable && !isLoading && loadMoreListener != null) {
            isLoading = true;
            loadMoreListener.onLoadMore();
        }
        if (getItemViewType(position) == TYPE_MOVIE) {
            ((MovieHolder) holder).textViewFileNameMyAudioItem.setText(dataSet.get(position));


        }
    }

    @Override
    public int getItemViewType(int position) {
//        if (dataSet.get(position).type == null)
//            return TYPE_MOVIE;
//        else if (dataSet.get(position).type.equals("movie")) {
//            return TYPE_MOVIE;
//        } else {
        return TYPE_MOVIE;
//        }
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    static class MovieHolder extends RecyclerView.ViewHolder {
        public TextView textViewFileNameMyAudioItem;


        public MovieHolder(View itemView) {
            super(itemView);
            textViewFileNameMyAudioItem = itemView.findViewById(R.id.textViewFileNameMyAudioItem);
        }
    }

    static class LoadHolder extends RecyclerView.ViewHolder {
        public LoadHolder(View itemView) {
            super(itemView);
        }
    }

    public void setMoreDataAvailable(boolean moreDataAvailable) {
        isMoreDataAvailable = moreDataAvailable;
    }

    public void notifyDataChanged() {
        notifyDataSetChanged();
        isLoading = false;
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }

    public void setLoadMoreListener(OnLoadMoreListener loadMoreListener) {
        this.loadMoreListener = loadMoreListener;
    }


}
