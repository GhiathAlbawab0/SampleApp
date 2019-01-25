package com.ghiath.sampleapp.cloud;

import java.io.IOException;

public class EnvelopeError extends IOException {
    Envelope<?> tEnvelope;


    public EnvelopeError(Envelope<?> tEnvelope) {
        this.tEnvelope = tEnvelope;
    }

    public Envelope<?> gettEnvelope() {
        return tEnvelope;
    }

    public void settEnvelope(Envelope<?> tEnvelope) {
        this.tEnvelope = tEnvelope;
    }
}
