package com.ama.algorithmmanagement.ViewHolder;

import android.view.ViewGroup;

import com.ama.algorithmmanagement.Base.BaseViewHolder;
import com.ama.algorithmmanagement.Model.ProblemsOfClass;
import com.ama.algorithmmanagement.R;
import com.ama.algorithmmanagement.databinding.DefaultViewholderItemBinding;

public class DefaultViewHolder extends BaseViewHolder<DefaultViewholderItemBinding> {

    public DefaultViewHolder(ViewGroup parent) {
        super(parent, R.layout.default_viewholder_item);
    }

    public void setData(ProblemsOfClass data) {
        binding.textviewDeafultItem.setText(String.valueOf(data.getClass_()));
    }

}
