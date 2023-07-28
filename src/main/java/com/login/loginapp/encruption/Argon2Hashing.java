package com.login.loginapp.encruption;

import org.bouncycastle.crypto.generators.Argon2BytesGenerator;
import org.bouncycastle.crypto.params.Argon2Parameters;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Argon2Hashing implements PasswordEncoder {

    private static final int DEFAULT_HASH_LENGTH = 32;
    private static final int DEFAULT_PARALLELISM = 1;
    private static final int DEFAULT_MEMORY = 66536;
    private static final int DEFAULT_ITERATIONS = 2;
    private final int hashLength;
    private final int parallelism;
    private final int memory;
    private final int iterations;
    private byte[] salt;

    public Argon2Hashing(int hashLength, int parallelism, int memory, int iterations, byte[] salt) {
        this.hashLength = hashLength;
        this.parallelism = parallelism;
        this.memory = memory;
        this.iterations = iterations;
        this.salt = salt;
    }

    public Argon2Hashing(byte[] salt) {
        this(DEFAULT_HASH_LENGTH,
                DEFAULT_PARALLELISM, DEFAULT_MEMORY, DEFAULT_ITERATIONS, salt);
    }

    @Override
    public String encode(CharSequence rawPassword) {
        byte[] hash = new byte[hashLength];
        System.out.println("salt : "+Base64.getEncoder().encodeToString(salt));
        Argon2Parameters.Builder builder = new Argon2Parameters.Builder(Argon2Parameters.ARGON2_id)
                .withVersion(Argon2Parameters.ARGON2_VERSION_13) // 19
                .withIterations(iterations)
                .withMemoryAsKB(memory)
                .withParallelism(parallelism)
                .withSalt(salt);
        Argon2BytesGenerator generator = new Argon2BytesGenerator();
        generator.init(builder.build());
        generator.generateBytes(rawPassword.toString().getBytes(StandardCharsets.UTF_8), hash, 0, hash.length);
        return Base64.getEncoder().encodeToString(hash);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return false;
    }

    @Override
    public boolean upgradeEncoding(String encodedPassword) {
        return false;
    }
}
