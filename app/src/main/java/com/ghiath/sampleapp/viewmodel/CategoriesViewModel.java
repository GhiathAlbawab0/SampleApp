package com.ghiath.sampleapp.viewmodel;

import android.app.Application;

import com.ghiath.sampleapp.BasicApplication;
import com.ghiath.sampleapp.DataRepository;
import com.ghiath.sampleapp.db.entity.CategoryEntity;
import com.ghiath.sampleapp.ui.MessageViewer;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class CategoriesViewModel extends ViewModel {
    private DataRepository mDataRepository;
    private MessageViewer mMessageViewer;

    private MediatorLiveData<List<CategoryEntity>> mObservableCategories;

    public CategoriesViewModel(Application application,MessageViewer messageViewer) {
        if (this.mObservableCategories != null) {
            return;
        }
        this.mMessageViewer = messageViewer;


        mObservableCategories=new MediatorLiveData<>();
        mObservableCategories.setValue(null);
        mDataRepository = ((BasicApplication) application).getRepository();


        LiveData<List<CategoryEntity>>    categories= mDataRepository.getCategories(messageViewer);
        mObservableCategories.addSource(categories, mObservableCategories::setValue);

    }


    public LiveData<List<CategoryEntity>> getmObservableCategories() {
        return mObservableCategories;
    }
    public void refereshCategories(){mDataRepository.refreshCategories(mMessageViewer);}


    /**
     * to inject MessageViewer
     */
    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application mApplication;
        private final MessageViewer mMessageViewer;

        public Factory(@NonNull Application application, MessageViewer messageViewer) {
            mApplication = application;
            this.mMessageViewer=messageViewer;
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T)  new CategoriesViewModel(mApplication,mMessageViewer);
        }
    }


}
