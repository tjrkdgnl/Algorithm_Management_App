package com.jeoksyeo.algorithmmanagement.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.jeoksyeo.algorithmmanagement.Base.BaseActivity;
import com.jeoksyeo.algorithmmanagement.Fragment.MainFragment;
import com.jeoksyeo.algorithmmanagement.Model.ProblemsOfClass;
import com.jeoksyeo.algorithmmanagement.Network.APIGenerator;
import com.jeoksyeo.algorithmmanagement.R;
import com.jeoksyeo.algorithmmanagement.databinding.DefaultActivityMainBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DefaultActivity extends BaseActivity<DefaultActivityMainBinding> implements View.OnClickListener {

    public DefaultActivity() {
        super(R.layout.default_activity_main);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding.buttonCallAPI.setOnClickListener(this);
        getSupportFragmentManager().beginTransaction().add(R.id.frameLayout_default, new MainFragment()).commit();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_call_API:
                startActivity(new Intent(this,CallAPIActivity.class));
                break;
        }
    }
}