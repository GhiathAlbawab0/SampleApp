package com.ghiath.sampleapp.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.ghiath.sampleapp.R;
import com.ghiath.sampleapp.databinding.ActivityPostsBinding;
import com.ghiath.sampleapp.db.entity.PostEntity;
import com.ghiath.sampleapp.viewmodel.PostsViewModel;

import java.util.List;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

public class PostsActivity extends FragmentActivity implements MessageViewer{

    ActivityPostsBinding binding;

    public static final String CATEGORY_KEY="CATEGORY_KEY";
    public static final String PAGE_KEY="PAGE_KEY";
    public static final String LIMIT_KEY="LIMIT_KEY";
    public static final String Q_KEY="Q_KEY";


    String category;
    String page;
    String limit;
    String q;

    PostsViewModel postsViewModel;

    PostAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_posts);

        category=getIntent().getStringExtra(CATEGORY_KEY);
        page=getIntent().getStringExtra(PAGE_KEY);
        limit=getIntent().getStringExtra(LIMIT_KEY);
        q=getIntent().getStringExtra(Q_KEY);

        adapter=new PostAdapter(postClickCallback,this);
        binding.postsRv.setAdapter(adapter);
        binding.postsRv.setLayoutManager(new LinearLayoutManager(this));
        binding.postsRv.setItemAnimator(new DefaultItemAnimator());
        binding.loadingTv.setOnClickListener(
                l->postsViewModel.refereshPosts(category,page,limit,q)
        );
        binding.noConnection.setOnClickListener(l->postsViewModel.refereshPosts(category,page,limit,q));


        if(category!=null && page!=null&& limit!=null)
        {
            PostsViewModel.Factory factory=new PostsViewModel.Factory(getApplication(),this
                    ,category,page,limit,q);
            postsViewModel= ViewModelProviders.of(this,factory).get(PostsViewModel.class);

            if(postsViewModel.getmObservablePosts()!=null)
                postsViewModel.getmObservablePosts().observe(this, new Observer<List<PostEntity>>() {
                    @Override
                    public void onChanged(List<PostEntity> categoryEntities) {
                        if (categoryEntities != null) {
                            binding.setIsLoading(false);
                            adapter.setmPostList(categoryEntities);
                            adapter.notifyDataSetChanged();
                            if (!categoryEntities.isEmpty())
                                hideNoConnection();

                        } else
                            binding.setIsLoading(true);
                        binding.executePendingBindings();

                    }
                });
        }
    }

    public PostClickCallback postClickCallback=new PostClickCallback() {
        @Override
        public void onClick(PostEntity customerEntity) {

        }
    };
    @Override
    public void showMsg(String message) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }

    @Override
    public void showNoConnection() {

            this.runOnUiThread(()->{
                binding.postsRv.setVisibility(View.GONE);
                binding.loadingTv.setVisibility(View.GONE);
                binding.noConnection.setVisibility(View.VISIBLE);
            });
    }

    private void hideNoConnection()
    {
        if(binding.noConnection.getVisibility()!=View.GONE)
            binding.noConnection.setVisibility(View.GONE);

    }
}
