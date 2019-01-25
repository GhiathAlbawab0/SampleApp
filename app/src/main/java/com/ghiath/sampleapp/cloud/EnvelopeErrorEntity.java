package com.ghiath.sampleapp.cloud;

import android.content.Context;

import com.ghiath.sampleapp.R;

import java.io.Serializable;

public class EnvelopeErrorEntity implements Serializable {

    Envelope<?> envelope;

    String message;


    public EnvelopeErrorEntity(Envelope<?> envelope) {
        this.envelope = envelope;
        message=envelope.getMeta().message;
    }

    public String getMessage() {
        return message;
    }
}
