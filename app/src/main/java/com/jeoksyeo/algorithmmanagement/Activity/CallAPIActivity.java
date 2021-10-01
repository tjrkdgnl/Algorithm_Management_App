package com.jeoksyeo.algorithmmanagement.Activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.jeoksyeo.algorithmmanagement.Adapter.DefaultRecyclerViewAdapter;
import com.jeoksyeo.algorithmmanagement.Base.BaseActivity;
import com.jeoksyeo.algorithmmanagement.Model.ProblemsOfClass;
import com.jeoksyeo.algorithmmanagement.Network.APIGenerator;
import com.jeoksyeo.algorithmmanagement.R;
import com.jeoksyeo.algorithmmanagement.databinding.DefaultActivityCallApiBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CallAPIActivity extends BaseActivity<DefaultActivityCallApiBinding> {

    public CallAPIActivity() {
        super(R.layout.default_activity_call_api);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DefaultRecyclerViewAdapter adapter = new DefaultRecyclerViewAdapter();
        binding.recyclerviewCallApi.setAdapter(adapter);
        binding.recyclerviewCallApi.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerviewCallApi.setHasFixedSize(false);

        APIGenerator.getInstance().getProblemsOfClass().enqueue(new Callback<List<ProblemsOfClass>>() {
            @Override
            public void onResponse(Call<List<ProblemsOfClass>> call, Response<List<ProblemsOfClass>> response) {
                List<ProblemsOfClass> list = response.body();
                adapter.updateList(list);
            }

            @Override
            public void onFailure(Call<List<ProblemsOfClass>> call, Throwable t) {

            }
        });

    }
}
