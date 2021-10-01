package com.jeoksyeo.algorithmmanagement.ViewHolder;

import android.view.ViewGroup;

import com.jeoksyeo.algorithmmanagement.Base.BaseViewHolder;
import com.jeoksyeo.algorithmmanagement.Model.ProblemsOfClass;
import com.jeoksyeo.algorithmmanagement.R;
import com.jeoksyeo.algorithmmanagement.databinding.DefaultViewholderItemBinding;

public class DefaultViewHolder extends BaseViewHolder<DefaultViewholderItemBinding> {

    public DefaultViewHolder(ViewGroup parent) {
        super(parent, R.layout.default_viewholder_item);
    }

    public void setData(ProblemsOfClass data) {
        binding.textviewDeafultItem.setText(String.valueOf(data.getClass_()));
    }

}
