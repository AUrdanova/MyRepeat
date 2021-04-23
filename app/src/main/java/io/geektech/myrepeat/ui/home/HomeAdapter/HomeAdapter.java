package io.geektech.myrepeat.ui.home.HomeAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import io.geektech.myrepeat.OnClickItemRecyclerView;
import io.geektech.myrepeat.R;
import io.geektech.myrepeat.databinding.ItemLayoutBinding;
import io.geektech.myrepeat.ui.home.HomeFragment;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {
    private ItemLayoutBinding binding;
    private List<HomeModel> list = new ArrayList<>();
    private OnClickItemRecyclerView listener;
    private HomeFragment context;

    public HomeAdapter(OnClickItemRecyclerView listener, HomeFragment context) {
        this.listener = listener;
        this.context = context;
    }

    public OnClickItemRecyclerView getListener() {
        return listener;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemLayoutBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new HomeViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        if (position % 2 == 0) {
            holder.itemView.setBackgroundColor(Color.BLUE);
            binding.cv.setBackgroundColor(Color.YELLOW);
        } else {
            holder.itemView.setBackgroundColor(Color.GRAY);
            binding.cv.setBackgroundColor(Color.GRAY);
        }
        holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addItem(HomeModel model) {
        list.add( model);
        notifyDataSetChanged();
    }

    public List<HomeModel> getList() {
        return list;
    }

    public void deleteItem(int pos) {
        list.remove(pos);
        notifyItemRemoved(pos);
    }

    class HomeViewHolder extends RecyclerView.ViewHolder{
        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(v -> {
                listener.onClick(getAdapterPosition(), list.get(getAdapterPosition()));
            });
            itemView.setOnLongClickListener(v -> {
                listener.onLongClick(getAdapterPosition(), list.get(getAdapterPosition()));
                return true;
            });
        }

        public void onBind(HomeModel model) {
            binding.title.setText(model.getTitle());
            binding.description.setText(model.getDescription());
        }
    }
}
