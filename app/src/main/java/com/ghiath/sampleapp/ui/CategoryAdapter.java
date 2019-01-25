package com.ghiath.sampleapp.ui;


import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.ghiath.sampleapp.R;
import com.ghiath.sampleapp.databinding.CategoryItemBinding;
import com.ghiath.sampleapp.db.entity.CategoryEntity;

import java.util.List;


import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;



public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoriesHolder>{

List<CategoryEntity> mCategoryList;
    @Nullable
    final CategoryClickCallback mCustomerClickCallback;

    public CategoryAdapter(@Nullable CategoryClickCallback mCustomerClickCallback) {
        this.mCustomerClickCallback = mCustomerClickCallback;
    }

    public void setmCustomerList(List<CategoryEntity> customerList) {
        if(mCategoryList==null)
        {
            mCategoryList=customerList;
            notifyItemRangeInserted(0,customerList.size());
        }
        else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return mCategoryList.size();
                }

                @Override
                public int getNewListSize() {
                    return customerList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return mCategoryList.get(oldItemPosition).getId() ==
                            customerList.get(newItemPosition).getId();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    CategoryEntity newProduct = customerList.get(newItemPosition);
                    CategoryEntity oldProduct = mCategoryList.get(oldItemPosition);
                    return newProduct.getId() == oldProduct.getId()
                           
                            ;
                }
            });
            mCategoryList = customerList;
            result.dispatchUpdatesTo(this);
        }
    }

    @Override
    public CategoriesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CategoryItemBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.category_item,
                        parent, false);
        binding.setCallback(mCustomerClickCallback);
        return new CategoriesHolder(binding);
    }

    @Override
    public void onBindViewHolder(CategoriesHolder holder, int position) {
        holder.customerItemBinding.setCategory(mCategoryList.get(position));
        Glide.with(holder.customerItemBinding.image).load(mCategoryList.get(position).getImage());
        holder.customerItemBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mCategoryList==null?0:mCategoryList.size();
    }

    class CategoriesHolder extends RecyclerView.ViewHolder {
        final CategoryItemBinding customerItemBinding;
        public CategoriesHolder(CategoryItemBinding binding) {
            super(binding.getRoot());
            this.customerItemBinding=binding;
        }
    }

}
