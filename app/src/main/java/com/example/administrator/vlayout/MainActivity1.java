package com.example.administrator.vlayout;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;

import java.util.LinkedList;
import java.util.List;

public class MainActivity1 extends AppCompatActivity {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        VirtualLayoutManager layoutManager = new VirtualLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);

        //设置线性布局
        LinearLayoutHelper linearLayoutHelper = new LinearLayoutHelper();
        linearLayoutHelper.setItemCount(4);
        linearLayoutHelper.setMarginBottom(100);

        //设置Adapter列表
        List<DelegateAdapter.Adapter> adapters = new LinkedList<>();


    }


    public static class RvAdapter extends  DelegateAdapter.Adapter<RvAdapter.viewHolder> {

        private Context context;
        private LayoutHelper layoutHelper;

        public RvAdapter(Context context, LayoutHelper layoutHelper) {
            this.context = context;
            this.layoutHelper=layoutHelper;
        }

        public void setData(List<String> hoursinfoList) {
            this.data = hoursinfoList;
            super.notifyDataSetChanged();
        }

        private List<String> data = null;

        @Override
        public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new viewHolder(LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.rv_item, parent, false));
        }

        @Override
        public void onBindViewHolder(viewHolder holder, int position) {
            holder.setDate();
        }

        @Override
        public int getItemCount() {
            return data == null ? 0 : data.size();
        }

        @Override
        public LayoutHelper onCreateLayoutHelper() {
            return layoutHelper;
        }

        class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            private TextView content;

            public viewHolder(View itemView) {
                super(itemView);
                content = (TextView) itemView.findViewById(R.id.content);
                itemView.setOnClickListener(this);
            }

            public void setDate() {
                content.setText(data.get(getAdapterPosition()));
            }

            @Override
            public void onClick(View v) {
                Log.e("dxq",data.get(getAdapterPosition())+" getAdapterPosition");
            }
        }

    }

}
