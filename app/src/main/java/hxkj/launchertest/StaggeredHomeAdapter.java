package hxkj.launchertest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

public class StaggeredHomeAdapter extends
        RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<String> mDatas;
    private LayoutInflater mInflater;

    private List<Integer> mHeights;

    private Context con;

    private String[] name = {"账户", "消息", "设置", "会员", "xxx"};
    private int index;
    private int len = name.length / 2 + name.length % 2 - 1;

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public StaggeredHomeAdapter(Context context, List<String> datas) {
        mInflater = LayoutInflater.from(context);
        mDatas = datas;
        con = context;

        mHeights = new ArrayList<Integer>();
        for (int i = 0; i < mDatas.size(); i++) {
            mHeights.add((int) (100 + Math.random() * 300));
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = new Item_1ViewHolder(mInflater.inflate(
                R.layout.item_, parent, false));
        return holder;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Item_1ViewHolder item_1ViewHolder = (Item_1ViewHolder) holder;
        System.out.println("position = " + position);
        if (name.length > 0) {
            if (position % 2 == 0) {
                item_1ViewHolder.rl_long_1.addView(getLongView(index));
                index++;
                if (position == len) { //判断最后一行
                    if (name.length % 2 == 0) {
                        item_1ViewHolder.rl_long_1.addView(getShortView(index));
                        index++;
                    }
                } else {
                    item_1ViewHolder.rl_long_1.addView(getShortView(index));
                    index++;
                }
            } else {
                item_1ViewHolder.rl_long_1.addView(getShortView(index));
                index++;
                if (position == len) { //判断最后一行
                    System.out.println("position == len = " + position);
                    if (name.length % 2 == 0) {
                        item_1ViewHolder.rl_long_1.addView(getLongView(index));
                        index++;
                    }
                } else {
                    item_1ViewHolder.rl_long_1.addView(getLongView(index));
                    index++;
                }
            }
        }
    }

    private View getLongView(final int position) {
        View view = null;
        if (view == null) {
            view = LayoutInflater.from(con).inflate(R.layout.item_3, null);
            AutoUtils.autoSize(view);
            TextView tx = (TextView) view.findViewById(R.id.tx_long);
            tx.setText(name[position]);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickLitener.onItemClick(v, position);
                }
            });
        }
        return view;
    }

    private View getShortView(final int position) {
        View view = null;
        if (view == null) {
            view = LayoutInflater.from(con).inflate(R.layout.item_4, null);
            AutoUtils.autoSize(view);
            TextView tx = (TextView) view.findViewById(R.id.tx_short);
            tx.setText(name[position]);
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickLitener.onItemClick(v, position);
            }
        });
        return view;
    }


    @Override
    public int getItemCount() {
        return name.length / 2 + name.length % 2;
    }

    public void removeData(int position) {
        mDatas.remove(position);
        notifyItemRemoved(position);
    }

    class Item_1ViewHolder extends ViewHolder {

        AutoLinearLayout rl_long_1;

        public Item_1ViewHolder(View view) {
            super(view);
            AutoUtils.autoSize(view);
            rl_long_1 = (AutoLinearLayout) view.findViewById(R.id.bg);


        }
    }
}