package io.geektech.myrepeat.ui.onboard;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator;

import io.geektech.myrepeat.R;
import io.geektech.myrepeat.databinding.FragmentBoardBinding;


public class BoardFragment extends Fragment {
    private FragmentBoardBinding binding;
    private NavController navController;
    private BoardAdapter adapter;
    private SpringDotsIndicator indicator;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        adapter = new BoardAdapter();
        navController = NavHostFragment.findNavController(this);
        binding = FragmentBoardBinding.inflate(inflater,container,false);
        binding.pager2.setAdapter(adapter);
        binding.dotsIndicator.setViewPager2(binding.pager2);
        binding.pager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position) {
                    case 0:
                        binding.btnSkip.setVisibility(View.VISIBLE);
                        binding.btnSkip.setOnClickListener(v -> {
                            navController.navigateUp();
                        });
                        binding.btnBack.setVisibility(View.INVISIBLE);
                        binding.btnNext.setVisibility(View.VISIBLE);
                        binding.btnNext.setOnClickListener(v -> {
                            binding.pager2.setCurrentItem(position+1);
                        });
                        binding.smth.setVisibility(View.INVISIBLE);
                        break;
                    case 1:
                        binding.btnSkip.setVisibility(View.INVISIBLE);
                        binding.btnBack.setVisibility(View.VISIBLE);
                        binding.btnBack.setOnClickListener(v -> {
                            binding.pager2.setCurrentItem(position-1);
                        });
                        binding.btnNext.setVisibility(View.VISIBLE);
                        binding.btnNext.setOnClickListener(v -> {
                            binding.pager2.setCurrentItem(position+1);
                        });
                        binding.smth.setVisibility(View.INVISIBLE);
                        break;
                    case 2:
                        binding.btnSkip.setVisibility(View.INVISIBLE);
                        binding.btnBack.setVisibility(View.VISIBLE);
                        binding.btnBack.setOnClickListener(v -> {
                            binding.pager2.setCurrentItem(position-1);
                        });
                        binding.btnNext.setVisibility(View.INVISIBLE);
                        binding.smth.setVisibility(View.VISIBLE);
                        binding.smth.setOnClickListener(v -> {
                            navController.navigateUp();
                        });
                        break;
                }
            }
        });
        return binding.getRoot();
    }
}
