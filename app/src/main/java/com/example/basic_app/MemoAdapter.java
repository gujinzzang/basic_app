package com.example.basic_app;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.basic_app.DB.DBHelper;
import java.util.ArrayList;


public class MemoAdapter extends RecyclerView.Adapter<MemoAdapter.ViewHolder> {

    private MainActivity mActivity;
    private ArrayList<Memo> mItems = new ArrayList<>();

    public MemoAdapter(MainActivity activity) {
        mActivity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Memo item = mItems.get(holder.getAdapterPosition());
        holder.tvTitle.setText(item.title);
        holder.tvTime.setText(item.getTime());
        holder.tvName.setText(item.name);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.startActivity(new Intent(mActivity, MemoDetailActivity.class)
                        .putExtra("memo", item));
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showSelectPopup(item);
                return true;
            }
        });

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit(item);
            }
        });
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete(item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void setItems(ArrayList<Memo> items) {
        mItems = items;
        notifyDataSetChanged();
    }
    private void edit(final Memo memo){
        mActivity.startActivity(new Intent(mActivity, EditActivity.class).putExtra("memo", memo));;
    }
    private void delete(final Memo memo){
        DBHelper.getInstance(mActivity).deleteMemo(memo);
        mActivity.refreshList();
    }
    private void showSelectPopup(final Memo memo) {
        String[] items = {"편집", "삭제"};
        new AlertDialog.Builder(mActivity)
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            mActivity.startActivity(new Intent(mActivity, EditActivity.class)
                                    .putExtra("memo", memo));
                        } else {
                            showDeletePopup(memo);
                        }
                    }
                })
                .show();
    }

    private void showDeletePopup(final Memo memo) {
        new AlertDialog.Builder(mActivity)
                .setMessage("해당 메모를 삭제하시겠습니까?")
                .setPositiveButton("네", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DBHelper.getInstance(mActivity).deleteMemo(memo);
                        mActivity.refreshList();
                    }
                })
                .setNegativeButton("아니오", null)
                .show();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

      //  ImageView ivMemo;
        TextView tvTitle;
        TextView tvTime;
        TextView tvName;
        ImageView btnEdit;
        ImageView btnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
         //   ivMemo = itemView.findViewById(R.id.etWriter);
            tvTitle = itemView.findViewById(R.id.txtMemo);
            tvTime = itemView.findViewById(R.id.txtDate);
            tvName = itemView.findViewById(R.id.txtMemo2);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
