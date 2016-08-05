package hxkj.launchertest;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.zhy.autolayout.config.AutoLayoutConifg;

import java.util.ArrayList;
import java.util.List;

public class StaggeredGridLayoutActivity extends Activity implements StaggeredHomeAdapter.OnItemClickLitener{

    private Context mContext;
    private List<String> mList;
    private RecyclerView mRecyclerView;
    private StaggeredHomeAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AutoLayoutConifg.getInstance().useDeviceSize();
        setContentView(R.layout.activity_single_recyclerview);

        mContext = this;

        initData();
        mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerview);
        //1.设置布局管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));  //线性管理器，支持横向、纵向

        //纵向的GridView，可以纵向滑动
//        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2)); //网格布局管理器，第二个参数为列
        //横向的GridView，可以横向滑动
//        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4, GridLayoutManager.HORIZONTAL, false)); //网格布局管理器，第二个参数为行

        /**
         * StaggeredGridLayoutManager构造的第二个参数传一个orientation，
         * 如果传入的是StaggeredGridLayoutManager.VERTICAL代表有多少列，可以纵向滑动
         * 那么传入的如果是StaggeredGridLayoutManager.HORIZONTAL就代表有多少行，可以横向滑动
         */
//        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL)); // 瀑布式布局管理器
        //2.设置Adapter
        mAdapter = new StaggeredHomeAdapter(mContext, mList);
        mAdapter.setOnItemClickLitener(this);
        mRecyclerView.setAdapter(mAdapter);

        //3.添加分割线
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
//        mRecyclerView.addItemDecoration(new DividerGridItemDecoration(this, 30));
        //4.设置item动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }
    private String[] name = {"账户", "消息", "设置", "会员", "xxx"};
    private int len = name.length / 2 + name.length % 2 - 1;
    private void initData() {
        mList = new ArrayList<String>();
        for (int i = 0; i < len; i++) {
            mList.add("" + (char) i);
        }
    }



    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "pos = " + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemLongClick(View view, int position) {

    }
}
