package com.ghiath.sampleapp.ui;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ghiath.sampleapp.R;
import com.ghiath.sampleapp.databinding.CategoriesFragmentBinding;
import com.ghiath.sampleapp.db.entity.CategoryEntity;
import com.ghiath.sampleapp.viewmodel.CategoriesViewModel;

import java.util.List;

public class CategoriesFragment extends Fragment implements MessageViewer {

    private CategoriesViewModel mViewModel;
    CategoryAdapter categoryAdapter;
    CategoriesFragmentBinding  binding;

    public static CategoriesFragment newInstance() {
        return new CategoriesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding =DataBindingUtil.inflate(inflater,R.layout.categories_fragment, container, false);
        categoryAdapter=new CategoryAdapter(categoryClickCallback);
        binding.categoriesRec.setAdapter(categoryAdapter);
        binding.loadingTv.setOnClickListener(
                l->mViewModel.refereshCategories()
        );
        binding.noConnection.setOnClickListener(l->mViewModel.refereshCategories());
        binding.categoriesRec.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.categoriesRec.setItemAnimator(new DefaultItemAnimator());

        return binding.getRoot();
    }

    public CategoryClickCallback categoryClickCallback=new CategoryClickCallback() {
        @Override
        public void onClick(CategoryEntity customerEntity) {

            Intent intent=new Intent(getActivity(),PostsActivity.class);
            intent.putExtra(PostsActivity.CATEGORY_KEY,String.valueOf(customerEntity.getId()));
            intent.putExtra(PostsActivity.PAGE_KEY,"1");
            intent.putExtra(PostsActivity.LIMIT_KEY,"10");
            intent.putExtra(PostsActivity.Q_KEY,"");
            getActivity().startActivity(intent);
        }
    };
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        CategoriesViewModel.Factory factory = new CategoriesViewModel.Factory(getActivity().getApplication(),this);
        mViewModel = ViewModelProviders.of(this,factory).get(CategoriesViewModel.class);

        if(mViewModel.getmObservableCategories()!=null)
        mViewModel.getmObservableCategories().observe(this, new Observer<List<CategoryEntity>>() {
            @Override
            public void onChanged(List<CategoryEntity> categoryEntities) {
                if (categoryEntities != null) {
                    binding.setIsLoading(false);
                    categoryAdapter.setmCustomerList(categoryEntities);
                    categoryAdapter.notifyDataSetChanged();
                    if (!categoryEntities.isEmpty())
                        hideNoConnection();

                } else
                    binding.setIsLoading(true);
                binding.executePendingBindings();

            }
        });
//        categoryEntities -> {
//            if (categoryEntities != null) {
//                binding.setIsLoading(false);
//                categoryAdapter.setmCustomerList(categoryEntities);
//                categoryAdapter.notifyDataSetChanged();
//                if (!categoryEntities.isEmpty())
//                    hideNoConnection();
//
//            } else
//                binding.setIsLoading(true);
//            binding.executePendingBindings();
//        });
    }
    private void hideNoConnection()
    {
        if(binding.noConnection.getVisibility()!=View.GONE)
            binding.noConnection.setVisibility(View.GONE);

    }
    @Override
    public void showMsg(String message) {
        Toast.makeText(getActivity(),message,Toast.LENGTH_LONG).show();
    }

    @Override
    public void showNoConnection() {
        if(getActivity()!=null)
            getActivity().runOnUiThread(()->{
                binding.categoriesRec.setVisibility(View.GONE);
                binding.loadingTv.setVisibility(View.GONE);
                binding.noConnection.setVisibility(View.VISIBLE);
            });
    }
}
