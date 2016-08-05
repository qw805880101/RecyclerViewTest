package hxkj.launchertest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

/**
 * Created by tc on 2016/8/3.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecycleViewHolder> {
    private Context mContext;
    private List<String> mList;

    public RecyclerViewAdapter(Context context, List<String> list) {
        this.mContext = context;
        this.mList = list;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
        void onItemLongClick(View view , int position);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public RecyclerViewAdapter.RecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecycleViewHolder holder = new RecycleViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_staggered_home, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final RecyclerViewAdapter.RecycleViewHolder holder, int position) {

        // 如果设置了回调，则设置点击事件
        if (onItemClickListener != null) {
            holder.tv.setOnClickListener(new View.OnClickListener()  {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(holder.itemView, holder.getLayoutPosition());
                }
            });

            holder.tv.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onItemClickListener.onItemLongClick(holder.itemView, holder.getLayoutPosition());
                    return false;
                }
            });
        }

        holder.tv.setWidth(new Random().nextInt(400) + 100);  //随机产生100到500的随机整数
        holder.tv.setHeight(new Random().nextInt(400) + 100);
        holder.tv.setText(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class RecycleViewHolder extends RecyclerView.ViewHolder {
        TextView tv;

        public RecycleViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.letter);
        }
    }

    /**
     * 注意，这里更新数据集不是用adapter.notifyDataSetChanged()而是
     notifyItemInserted(position)与notifyItemRemoved(position)
     * @param position
     */
    public void addItem(int position) {
        mList.add(position, "Insert");
        notifyItemInserted(position);
    }

    public void removeItem(int position) {
        mList.remove(position);
        notifyItemRemoved(position);
    }
}
