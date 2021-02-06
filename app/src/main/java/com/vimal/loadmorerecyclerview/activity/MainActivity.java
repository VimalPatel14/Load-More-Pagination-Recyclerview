package com.vimal.loadmorerecyclerview.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vimal.loadmorerecyclerview.adapter.AdpPostDataPagination;
import com.vimal.loadmorerecyclerview.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewFiles;
    private ArrayList<String> data = new ArrayList<>();
    int pageno = 0;
    AdpPostDataPagination filesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewFiles = findViewById(R.id.recyclerViewFiles);
        recyclerViewFiles.setHasFixedSize(true);
        recyclerViewFiles.setLayoutManager(new GridLayoutManager(MainActivity.this, 1));
        filesAdapter = new AdpPostDataPagination(MainActivity.this, data, new AdpPostDataPagination.onProductClick() {
            @Override
            public void click(int position) {

            }
        });

        filesAdapter = new AdpPostDataPagination(MainActivity.this, data,
                new AdpPostDataPagination.onProductClick() {
                    @Override
                    public void click(int position) {

                    }
                });

        filesAdapter.setLoadMoreListener(new AdpPostDataPagination.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                recyclerViewFiles.post(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("vml", "loadmore");
                        pageno = pageno + 1;
                        LoadMore(pageno, 10);

                    }
                });
            }
        });

        recyclerViewFiles.setAdapter(filesAdapter);
        CallApi(pageno, 10);
    }

    public void CallApi(int pageno, int items) {

        for (int i = 0; i < items; i++) {
            data.add(" custom ViewPager title");
        }
        Log.e("vml", data.size() + "setadapter");
        if (data.size() > 0) {
            filesAdapter.notifyDataChanged();
            filesAdapter.setMoreDataAvailable(true);
        }

    }

    public void LoadMore(int pageno, int items) {

        for (int i = 0; i < items; i++) {
            data.add(" custom ViewPager title Next");
        }
        Log.e("vml", data.size() + "setadapter");
        if (data.size() > 0) {
            filesAdapter.notifyDataChanged();
        }

    }
}