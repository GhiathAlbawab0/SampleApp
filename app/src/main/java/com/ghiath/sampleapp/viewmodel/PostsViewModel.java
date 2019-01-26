package com.ghiath.sampleapp.viewmodel;

import android.app.Application;

import com.ghiath.sampleapp.BasicApplication;
import com.ghiath.sampleapp.DataRepository;
import com.ghiath.sampleapp.db.entity.PostEntity;
import com.ghiath.sampleapp.ui.MessageViewer;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class PostsViewModel extends ViewModel {
    private DataRepository mDataRepository;
    private MessageViewer mMessageViewer;

    private MediatorLiveData<List<PostEntity>> mObservablePosts;

    public PostsViewModel(Application application, MessageViewer messageViewer,String category,String page,String limit,String q) {
        if (this.mObservablePosts != null) {
            return;
        }
        this.mMessageViewer = messageViewer;


        mObservablePosts=new MediatorLiveData<>();
        mObservablePosts.setValue(null);
        mDataRepository = ((BasicApplication) application).getRepository();


        LiveData<List<PostEntity>>    posts= mDataRepository.loadPosts(messageViewer,category,page,limit,q);
        mObservablePosts.addSource(posts, mObservablePosts::setValue);

    }


    public MediatorLiveData<List<PostEntity>> getmObservablePosts() {
        return mObservablePosts;
    }

    public void refereshPosts(String category,String page,String limit,String q){
        mDataRepository.refereshPosts(mMessageViewer,category,page,limit,q);
    }


    /**
     * to inject MessageViewer
     */
    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application mApplication;
        private final MessageViewer mMessageViewer;

        String category;String page;String limit;String q;

        public Factory(@NonNull Application application, MessageViewer messageViewer
        ,String category,String page,String limit,String q) {
            mApplication = application;
            this.mMessageViewer=messageViewer;
            this.category=category;
            this.limit=limit;
            this.page=page;
            this.q=q;
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T)  new PostsViewModel(mApplication,mMessageViewer
                    ,category,page,limit,q);
        }
    }


}
