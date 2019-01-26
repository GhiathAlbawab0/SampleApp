package com.ghiath.sampleapp.ui;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.bumptech.glide.Glide;
import com.ghiath.sampleapp.R;
import com.ghiath.sampleapp.databinding.CategoryItemBinding;
import com.ghiath.sampleapp.databinding.PostItemBinding;
import com.ghiath.sampleapp.db.entity.MediaEntity;
import com.ghiath.sampleapp.db.entity.PostEntity;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;


public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostsHolder>
{
//        implements View.OnClickListener,
//        CompoundButton.OnCheckedChangeListener{

    PostsHolder holder;
List<PostEntity> mPostList;


    private CardPagerAdapter mCardAdapter;
    private ShadowTransformer mCardShadowTransformer;
//    private CardFragmentPagerAdapter mFragmentCardAdapter;
//    private ShadowTransformer mFragmentCardShadowTransformer;

    private boolean mShowingFragments = false;

    @Nullable
    final PostClickCallback mPostClickCallback;

    public PostAdapter(@Nullable PostClickCallback mPostClickCallback,Context context) {
        this.mPostClickCallback = mPostClickCallback;
        this.context=context;


    }

    public void setmPostList(List<PostEntity> customerList) {
        if(mPostList==null)
        {
            mPostList=customerList;
            notifyItemRangeInserted(0,customerList.size());
        }
        else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return mPostList.size();
                }

                @Override
                public int getNewListSize() {
                    return customerList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return mPostList.get(oldItemPosition).getId_post() ==
                            customerList.get(newItemPosition).getId_post();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    PostEntity newProduct = customerList.get(newItemPosition);
                    PostEntity oldProduct = mPostList.get(oldItemPosition);
                    return newProduct.getId_post() == oldProduct.getId_post()
                           
                            ;
                }
            });
            mPostList = customerList;
            result.dispatchUpdatesTo(this);
        }
    }

    @Override
    public PostsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        PostItemBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.post_item,
                        parent, false);
        binding.setCallback(mPostClickCallback);
        return new PostsHolder(binding);
    }

    Context context;
    @Override
    public void onBindViewHolder(PostsHolder holder, int position) {
        holder.postItemBinding.setPost(mPostList.get(position));


        this.holder=holder;

        mCardAdapter = new CardPagerAdapter(context);
        for (MediaEntity me: mPostList.get(position).getMedia()             )
        mCardAdapter.addCardItem(me);
//        mCardAdapter.addCardItem(new CardItem(R.string.title_2, R.string.text_1));
//        mCardAdapter.addCardItem(new CardItem(R.string.title_3, R.string.text_1));
//        mCardAdapter.addCardItem(new CardItem(R.string.title_4, R.string.text_1));
//        mFragmentCardAdapter = new CardFragmentPagerAdapter(((FragmentActivity)context).getSupportFragmentManager(),
//                dpToPixels(2, context));

        mCardShadowTransformer = new ShadowTransformer(holder.postItemBinding.viewPager, mCardAdapter);
//        mFragmentCardShadowTransformer = new ShadowTransformer(holder.postItemBinding.viewPager, mFragmentCardAdapter);

        holder.postItemBinding.viewPager.setAdapter(mCardAdapter);
        holder.postItemBinding.viewPager.setPageTransformer(false, mCardShadowTransformer);
        holder.postItemBinding.viewPager.setOffscreenPageLimit(3);
    }

    @Override
    public int getItemCount() {
        return mPostList==null?0:mPostList.size();
    }

    class PostsHolder extends RecyclerView.ViewHolder {
        final PostItemBinding postItemBinding;
        public PostsHolder(PostItemBinding binding) {
            super(binding.getRoot());
            this.postItemBinding=binding;
        }
    }


//    @Override
//    public void onClick(View view) {
//        if (!mShowingFragments) {
//            holder.postItemBinding.viewPager.setAdapter(mFragmentCardAdapter);
//            holder.postItemBinding.viewPager.setPageTransformer(false, mFragmentCardShadowTransformer);
//        } else {
//
//            holder.postItemBinding.viewPager.setAdapter(mCardAdapter);
//            holder.postItemBinding.viewPager.setPageTransformer(false, mCardShadowTransformer);
//        }
//
//        mShowingFragments = !mShowingFragments;
//    }
//
    public static float dpToPixels(int dp, Context context) {
        return dp * (context.getResources().getDisplayMetrics().density);
    }
//
//    @Override
//    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//        mCardShadowTransformer.enableScaling(b);
//        mFragmentCardShadowTransformer.enableScaling(b);
//    }
}
