package io.arondight;

import io.arondight.utils.Reader;

@SuppressWarnings("rawtypes")
public class ReadUint64 {
    public static void main(String[] args) throws Exception {
        new Reader("#/definitions/golang_uint64").read();
    }
}
