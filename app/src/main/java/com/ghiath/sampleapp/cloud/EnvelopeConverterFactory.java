package com.ghiath.sampleapp.cloud;



import com.google.gson.reflect.TypeToken;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import androidx.annotation.Nullable;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

public class EnvelopeConverterFactory extends Converter.Factory {

    public static EnvelopeConverterFactory create()
    {

            return new EnvelopeConverterFactory();


    }

    private EnvelopeConverterFactory(

    ) {

    }

    @Nullable
    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(final Type type, Annotation[] annotations, Retrofit retrofit) {
        final Type envelopeType = TypeToken.getParameterized(Envelope.class, type).getType();
        final Converter<ResponseBody, Envelope<?>> delegate =
                retrofit.nextResponseBodyConverter(this, envelopeType, annotations);

        return new EnvelopeConverter(delegate);

    }
}
