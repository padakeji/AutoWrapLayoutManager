package com.padakeji.android.ui.autowraplayoutmanager.example;

import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.padakeji.android.ui.AutoWrapLayoutManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Views.
    private ViewGroup contentWrapper;
    private RecyclerView autoWrapperRecycler;

    private void findViews() {
        contentWrapper = (ViewGroup) findViewById(R.id.autoWrapperLayoutTest_wrapper_content);
        View v = getLayoutInflater().inflate(R.layout.activity_autowrapperlayout_added, contentWrapper, false);
        contentWrapper.addView(v);
        autoWrapperRecycler = (RecyclerView) findViewById(R.id.activity_autoWrapperLayoutTest_recycler);
    }

    private List<String> data = Arrays.asList(new String[] {
            "标签1", "标签2", "标签3", "标签4", "标签5", "标签6", "标签7", "标签8", "标签9", "标签10",
            "标签1", "标签2", "标签3", "标签4", "标签5", "标签6", "标签7", "标签8", "标签9", "标签10",
            "标签1", "标签2", "标签3", "标签4", "标签5", "标签6", "标签7", "标签8", "标签9", "标签10",
    });

    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        configViews();
        fillData();

    }

    private void fillData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                myAdapter.setData(data);
            }
        }, 0);
    }

    private void configViews() {
        myAdapter = new MyAdapter(this);
        autoWrapperRecycler.setLayoutManager(new AutoWrapLayoutManager(this, true));
        autoWrapperRecycler.setAdapter(myAdapter);
    }


    private static class MyAdapter extends RecyclerView.Adapter {

        Context mContext;
        private ArrayList data;

        MyAdapter(Context context) {
            mContext = context;
            data = new ArrayList();
        }

        public void setData(List data) {
            this.data.clear();
            if (data != null) {
                this.data.addAll(data);
            }
            notifyDataSetChanged();
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            ImageView image = new ImageView(mContext);
            RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(300, 300);
            image.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.ic_launcher));
            image.setLayoutParams(params);
            image.setScaleType(ImageView.ScaleType.CENTER_CROP);
            ViewHolder vh = new ViewHolder(image);
            return vh;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ViewHolder vh = (ViewHolder) holder;
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        private class ViewHolder extends RecyclerView.ViewHolder {

            ViewHolder(View itemView) {
                super(itemView);

            }

        }
    }
}
