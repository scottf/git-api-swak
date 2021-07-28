package io.arondight;

@SuppressWarnings("rawtypes")
public class ReadUint64 {
    public static void main(String[] args) throws Exception {
        new ReadForRef("#/definitions/golang_uint64").read();
    }
}
