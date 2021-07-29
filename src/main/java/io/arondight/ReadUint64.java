package io.arondight;

import io.arondight.utils.DefinitionsReader;

@SuppressWarnings("rawtypes")
public class ReadUint64 {
    public static void main(String[] args) throws Exception {
        DefinitionsReader.read("#/definitions/golang_uint64",
                "https://raw.githubusercontent.com/nats-io/jsm.go/main/schema_source/jetstream/api/v1/definitions.json");
    }
}
