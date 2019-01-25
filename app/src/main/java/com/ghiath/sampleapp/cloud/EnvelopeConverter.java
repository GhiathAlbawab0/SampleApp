package com.ghiath.sampleapp.cloud;

import android.util.Log;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

public class EnvelopeConverter<T>
        implements Converter<ResponseBody,T> {

    Converter<ResponseBody,Envelope<T>> delegate;

    public EnvelopeConverter(Converter<ResponseBody, Envelope<T>> delegate) {
        this.delegate = delegate;
    }


    @Override
    public T convert(ResponseBody value) throws IOException {


//        Log.d("envelopRes",value.string());
        Envelope<T> envelope=delegate.convert(value);
        try {
            Log.d("envelopToString", envelope.toString());
        }
        catch (Exception e)
        {e.printStackTrace();}
        if(envelope.getMeta().getSuccess()==1)
        return  envelope.getData();
        else
        {
            Log.d("envelopErr",envelope.getMeta().getMessage());
            throw new EnvelopeError(envelope);
        }


    }
//    @Wrapped
//    @FromJson
//    public T[] fromJson(Envelope<T> envelope)
//    {
//        return (T[]) envelope.data.toArray();
//    }
//
//    @ToJson
//    public Envelope<T> toJson(@Wrapped T[] value)
//    {
//        throw  new UnsupportedOperationException();
//    }


//    @Retention(RUNTIME)
//    @JsonQualifier
//    public @interface Wrapped {
//    }
}
