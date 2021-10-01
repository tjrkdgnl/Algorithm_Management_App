package com.ama.algorithmmanagement.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ama.algorithmmanagement.Base.BaseActivity;
import com.ama.algorithmmanagement.Fragment.MainFragment;
import com.ama.algorithmmanagement.R;
import com.ama.algorithmmanagement.databinding.DefaultActivityMainBinding;

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