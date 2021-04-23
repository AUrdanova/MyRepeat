package io.geektech.myrepeat.ui.home;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import io.geektech.myrepeat.OnClickItemRecyclerView;
import io.geektech.myrepeat.R;
import io.geektech.myrepeat.databinding.FragmentHomeBinding;
import io.geektech.myrepeat.ui.home.HomeAdapter.HomeAdapter;
import io.geektech.myrepeat.ui.home.HomeAdapter.HomeModel;

public class HomeFragment extends Fragment implements OnClickItemRecyclerView {
    private NavController navController;
    private FragmentHomeBinding binding;
    private HomeAdapter homeAdapter;
    private boolean click;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeAdapter = new HomeAdapter(this, this);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        navController = NavHostFragment.findNavController(this);
        binding = FragmentHomeBinding.inflate(inflater,container,false);
        getDataInForm();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.recyclerView.setAdapter(homeAdapter);
        setupListeners();
    }
    private void getDataInForm(){
        //принятие данных
        getParentFragmentManager().setFragmentResultListener("key", getViewLifecycleOwner(), new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                HomeModel model = (HomeModel) result.getSerializable("ModelForm");
                if (!click)
                    homeAdapter.addItem(model);
                else
                    homeAdapter.notifyDataSetChanged();
                Log.e("TAG ", "onFragmentResult: " + "name" + model.getTitle());
            }
        });
    }

    private void setupListeners(){
        binding.fab.setOnClickListener(v -> {
            click = false;
            Bundle bundle = new Bundle();
            bundle.putSerializable("Model", null);
            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
            navController.navigate(R.id.action_navigation_home_to_formFragment, bundle);
        });
    }

    @Override
    public void onClick(int pos, HomeModel model) {
        click = true;
        Bundle bundle = new Bundle();
        bundle.putSerializable("Model", model);
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        navController.navigate(R.id.action_navigation_home_to_formFragment, bundle);
    }

    @Override
    public void onLongClick(int pos, HomeModel model) {
        new AlertDialog.Builder(getContext())
                .setTitle("Хотите удалить ?")
                .setMessage("Запись будет удалена")
                .setPositiveButton("OK", (dialog, which) ->
                {
                    homeAdapter.deleteItem(pos);
                })
                .setNegativeButton("Нет", null)
                .show();
    }
}
