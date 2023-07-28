package com;

import org.bouncycastle.crypto.generators.Argon2BytesGenerator;
import org.bouncycastle.crypto.params.Argon2Parameters;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Test {
    public static void main(String[] args) {
        byte[] hash = new byte[32];
        byte[] salt = Base64.getDecoder().decode("AAAAAAAAAAAAAAAAAAAAAA==");
        System.out.println("salt : "+ Base64.getEncoder().encodeToString(salt));
        Argon2Parameters.Builder builder = new Argon2Parameters.Builder(Argon2Parameters.ARGON2_id)
                .withVersion(Argon2Parameters.ARGON2_VERSION_13) // 19
                .withIterations(2)
                .withMemoryAsKB(66536)
                .withParallelism(1)
                .withSalt(salt);
        Argon2BytesGenerator generator = new Argon2BytesGenerator();
        generator.init(builder.build());
        generator.generateBytes("password".getBytes(StandardCharsets.UTF_8), hash, 0, hash.length);
        System.out.println("hash : "+ Base64.getEncoder().encodeToString(hash));
    }
}
/**
 * salt : AAAAAAAAAAAAAAAAAAAAAA==
 * hash : tUomAFUzyYUqcH86JdXx3aiYf1uZlJXZumwmCwkeVRE=
 */
