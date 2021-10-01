package com.jeoksyeo.algorithmmanagement.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.jeoksyeo.algorithmmanagement.Base.BaseFragment;
import com.jeoksyeo.algorithmmanagement.R;
import com.jeoksyeo.algorithmmanagement.databinding.DefaultFragmentMainBinding;

public class MainFragment extends BaseFragment<DefaultFragmentMainBinding> {

    public MainFragment(){
        super(R.layout.default_fragment_main);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        binding.textviewDefault.setText(getString(R.string.FragmentDefaultText));
    }
}
