package io.geektech.myrepeat.ui.onboard;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import io.geektech.myrepeat.R;
import io.geektech.myrepeat.databinding.BoardLayoutBinding;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.BoardViewHolder> {
    private BoardLayoutBinding binding;
    private String[] title = new String[] {
            "Choose delivery","By motorcycle","By skate"
    };
    private  int[] lottie = new int[] {
            R.raw.lot,
            R.raw.lott,
            R.raw.lottie
    };
    private String[] description = new String[] {
            "You can order food on device!", "Your order will be delivery on motorcycle about 15minutes",
            "If you choose on skate you will wait 1 hour"};


    @NonNull
    @Override
    public BoardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BoardViewHolder(BoardLayoutBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull BoardViewHolder holder, int position) {
        holder.bind(position);

    }

    @Override
    public int getItemCount() {
        return 3;
    }

    class BoardViewHolder extends RecyclerView.ViewHolder {

        public BoardViewHolder(@NonNull  BoardLayoutBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        public void bind(int position) {
            binding.desk.setText(title[position]);
            binding.lotti.setAnimation(lottie[position]);
            binding.description.setText(description[position]);

        }
    }
}
