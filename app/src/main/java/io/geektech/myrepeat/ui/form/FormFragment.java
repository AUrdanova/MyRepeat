package io.geektech.myrepeat.ui.form;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import io.geektech.myrepeat.R;
import io.geektech.myrepeat.databinding.FragmentFormBinding;
import io.geektech.myrepeat.ui.home.HomeAdapter.HomeModel;

public class FormFragment extends Fragment {
    private NavController navController;
    private FragmentFormBinding binding;
    private String title,description;
    private HomeModel model;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        navController = NavHostFragment.findNavController(this);
        binding = FragmentFormBinding.inflate(inflater,container,false);
        model = (HomeModel) requireArguments().getSerializable("Model");
        if (model != null) {
            binding.editTitle.setText(model.getTitle());
            binding.editDescription.setText(model.getDescription());
        }
        binding.buttonSave.setOnClickListener(v -> save());
        return binding.getRoot();
    }

    public void save(){
            title = binding.editTitle.getText().toString();
            description = binding.editDescription.getText().toString();
            if (model == null) {
                model = new HomeModel(title, description);
            } else {
                model.setTitle(title);
                model.setDescription(description);
            }
            Toast.makeText(requireContext(), "title:" + title + "description:" + description, Toast.LENGTH_SHORT).show();
            Bundle bundle = new Bundle();
            bundle.putSerializable("ModelForm", model);
            getParentFragmentManager().setFragmentResult("key",bundle);
            navController.navigateUp();
    }




    }
