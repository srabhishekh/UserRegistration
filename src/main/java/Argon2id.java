import org.bouncycastle.crypto.generators.Argon2BytesGenerator;
import org.bouncycastle.crypto.params.Argon2Parameters;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

public class Argon2id {
    public static void main(String[] args) {
        // uses Bouncy Castle
        System.out.println("Generate a 32 byte long encryption key with Argon2id");

        String password = "secret password";
        System.out.println("password: " + password);

        // ### security warning - never use a fixed salt in production, this is for compare reasons only
        byte[] salt = Base64.getDecoder().decode("AAAAAAAAAAAAAAAAAAAAAA==");
        // please use below generateSalt16Byte()
        //byte[] salt = generateSalt16Byte();
        System.out.println("salt (Base64): " + base64Encoding(salt));

        System.out.println(encode(password, salt));

        // ### the minimal parameter set is probably UNSECURE ###
        String encryptionKeyArgon2id = base64Encoding(generateArgon2idMinimal(password, salt));
        System.out.println("encryptionKeyArgon2id (Base64) minimal:     " + encryptionKeyArgon2id);

        encryptionKeyArgon2id = base64Encoding(generateArgon2idInteractive(password, salt));
        System.out.println("encryptionKeyArgon2id (Base64) interactive: " + encryptionKeyArgon2id);

        encryptionKeyArgon2id = base64Encoding(generateArgon2idModerate(password, salt));
        System.out.println("encryptionKeyArgon2id (Base64) moderate:    " + encryptionKeyArgon2id);

        encryptionKeyArgon2id = base64Encoding(generateArgon2idSensitive(password, salt));
        System.out.println("encryptionKeyArgon2id (Base64) sensitive:   " + encryptionKeyArgon2id);
    }

    public static String encode(String password, byte[] salt) {
        int opsLimit = 2;
        int memLimit = 8192;
        int outputLength = 32;
        int parallelism = 1;
        byte[] hash = new byte[outputLength];
        System.out.println("salt : "+Base64.getEncoder().encodeToString(salt));
        Argon2Parameters params = new Argon2Parameters.Builder(Argon2Parameters.ARGON2_id).
                withSalt(salt).
                withParallelism(parallelism).
                withMemoryAsKB(memLimit).
                withIterations(opsLimit).
                build();
        Argon2BytesGenerator generator = new Argon2BytesGenerator();
        generator.init(params);
        generator.generateBytes(password.toString().getBytes(StandardCharsets.UTF_8), hash, 0, hash.length);
        return Base64.getEncoder().encodeToString(hash);
    }

    // ### the minimal parameter set is probably UNSECURE ###
    public static byte[] generateArgon2idMinimal(String password, byte[] salt) {
        int opsLimit = 2;
        int memLimit = 8192;
        int outputLength = 32;
        int parallelism = 1;
        Argon2Parameters.Builder builder = new Argon2Parameters.Builder(Argon2Parameters.ARGON2_id)
                .withVersion(Argon2Parameters.ARGON2_VERSION_13) // 19
                .withIterations(opsLimit)
                .withMemoryAsKB(memLimit)
                .withParallelism(parallelism)
                .withSalt(salt);
        Argon2BytesGenerator gen = new Argon2BytesGenerator();
        gen.init(builder.build());
        byte[] result = new byte[outputLength];
        gen.generateBytes(password.getBytes(StandardCharsets.UTF_8), result, 0, result.length);
        return result;
    }

    public static byte[] generateArgon2idInteractive(String password, byte[] salt) {
        int opsLimit = 2;
        int memLimit = 66536;
        int outputLength = 32;
        int parallelism = 1;
        Argon2Parameters.Builder builder = new Argon2Parameters.Builder(Argon2Parameters.ARGON2_id)
                .withVersion(Argon2Parameters.ARGON2_VERSION_13) // 19
                .withIterations(opsLimit)
                .withMemoryAsKB(memLimit)
                .withParallelism(parallelism)
                .withSalt(salt);
        Argon2BytesGenerator gen = new Argon2BytesGenerator();
        gen.init(builder.build());
        byte[] result = new byte[outputLength];
        gen.generateBytes(password.getBytes(StandardCharsets.UTF_8), result, 0, result.length);
        return result;
    }

    public static byte[] generateArgon2idModerate(String password, byte[] salt) {
        int opsLimit = 3;
        int memLimit = 262144;
        int outputLength = 32;
        int parallelism = 1;
        Argon2Parameters.Builder builder = new Argon2Parameters.Builder(Argon2Parameters.ARGON2_id)
                .withVersion(Argon2Parameters.ARGON2_VERSION_13) // 19
                .withIterations(opsLimit)
                .withMemoryAsKB(memLimit)
                .withParallelism(parallelism)
                .withSalt(salt);
        Argon2BytesGenerator gen = new Argon2BytesGenerator();
        gen.init(builder.build());
        byte[] result = new byte[outputLength];
        gen.generateBytes(password.getBytes(StandardCharsets.UTF_8), result, 0, result.length);
        return result;
    }

    public static byte[] generateArgon2idSensitive(String password, byte[] salt) {
        int opsLimit = 4;
        int memLimit = 1048576;
        int outputLength = 32;
        int parallelism = 1;
        Argon2Parameters.Builder builder = new Argon2Parameters.Builder(Argon2Parameters.ARGON2_id)
                .withVersion(Argon2Parameters.ARGON2_VERSION_13) // 19
                .withIterations(opsLimit)
                .withMemoryAsKB(memLimit)
                .withParallelism(parallelism)
                .withSalt(salt);
        Argon2BytesGenerator gen = new Argon2BytesGenerator();
        gen.init(builder.build());
        byte[] result = new byte[outputLength];
        gen.generateBytes(password.getBytes(StandardCharsets.UTF_8), result, 0, result.length);
        return result;
    }

    private static byte[] generateSalt16Byte() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = new byte[16];
        secureRandom.nextBytes(salt);
        return salt;
    }

    private static byte[] generateFixedSalt16Byte() {
        // ### security warning - never use this in production ###
        byte[] salt = new byte[16]; // 16 x0's
        return salt;
    }

    private static String base64Encoding(byte[] input) {
        return Base64.getEncoder().encodeToString(input);
    }
}

/*
/Library/Java/JavaVirtualMachines/jdk-17.jdk/Contents/Home/bin/java -javaagent:/Applications/IntelliJ IDEA CE.app/Contents/lib/idea_rt.jar=57106:/Applications/IntelliJ IDEA CE.app/Contents/bin -Dfile.encoding=UTF-8 -classpath /Users/srabhishekh/Work/Login/loginapp/target/classes:/Users/srabhishekh/.m2/repository/org/springframework/boot/spring-boot-starter/3.1.2/spring-boot-starter-3.1.2.jar:/Users/srabhishekh/.m2/repository/org/springframework/boot/spring-boot/3.1.2/spring-boot-3.1.2.jar:/Users/srabhishekh/.m2/repository/org/springframework/spring-context/6.0.11/spring-context-6.0.11.jar:/Users/srabhishekh/.m2/repository/org/springframework/boot/spring-boot-autoconfigure/3.1.2/spring-boot-autoconfigure-3.1.2.jar:/Users/srabhishekh/.m2/repository/org/springframework/boot/spring-boot-starter-logging/3.1.2/spring-boot-starter-logging-3.1.2.jar:/Users/srabhishekh/.m2/repository/ch/qos/logback/logback-classic/1.4.8/logback-classic-1.4.8.jar:/Users/srabhishekh/.m2/repository/ch/qos/logback/logback-core/1.4.8/logback-core-1.4.8.jar:/Users/srabhishekh/.m2/repository/org/apache/logging/log4j/log4j-to-slf4j/2.20.0/log4j-to-slf4j-2.20.0.jar:/Users/srabhishekh/.m2/repository/org/apache/logging/log4j/log4j-api/2.20.0/log4j-api-2.20.0.jar:/Users/srabhishekh/.m2/repository/org/slf4j/jul-to-slf4j/2.0.7/jul-to-slf4j-2.0.7.jar:/Users/srabhishekh/.m2/repository/jakarta/annotation/jakarta.annotation-api/2.1.1/jakarta.annotation-api-2.1.1.jar:/Users/srabhishekh/.m2/repository/org/springframework/spring-core/6.0.11/spring-core-6.0.11.jar:/Users/srabhishekh/.m2/repository/org/springframework/spring-jcl/6.0.11/spring-jcl-6.0.11.jar:/Users/srabhishekh/.m2/repository/org/yaml/snakeyaml/1.33/snakeyaml-1.33.jar:/Users/srabhishekh/.m2/repository/org/slf4j/slf4j-api/2.0.7/slf4j-api-2.0.7.jar:/Users/srabhishekh/.m2/repository/org/springframework/boot/spring-boot-starter-web/3.1.2/spring-boot-starter-web-3.1.2.jar:/Users/srabhishekh/.m2/repository/org/springframework/boot/spring-boot-starter-json/3.1.2/spring-boot-starter-json-3.1.2.jar:/Users/srabhishekh/.m2/repository/com/fasterxml/jackson/core/jackson-databind/2.15.2/jackson-databind-2.15.2.jar:/Users/srabhishekh/.m2/repository/com/fasterxml/jackson/core/jackson-annotations/2.15.2/jackson-annotations-2.15.2.jar:/Users/srabhishekh/.m2/repository/com/fasterxml/jackson/core/jackson-core/2.15.2/jackson-core-2.15.2.jar:/Users/srabhishekh/.m2/repository/com/fasterxml/jackson/datatype/jackson-datatype-jdk8/2.15.2/jackson-datatype-jdk8-2.15.2.jar:/Users/srabhishekh/.m2/repository/com/fasterxml/jackson/datatype/jackson-datatype-jsr310/2.15.2/jackson-datatype-jsr310-2.15.2.jar:/Users/srabhishekh/.m2/repository/com/fasterxml/jackson/module/jackson-module-parameter-names/2.15.2/jackson-module-parameter-names-2.15.2.jar:/Users/srabhishekh/.m2/repository/org/springframework/boot/spring-boot-starter-tomcat/3.1.2/spring-boot-starter-tomcat-3.1.2.jar:/Users/srabhishekh/.m2/repository/org/apache/tomcat/embed/tomcat-embed-core/10.1.11/tomcat-embed-core-10.1.11.jar:/Users/srabhishekh/.m2/repository/org/apache/tomcat/embed/tomcat-embed-el/10.1.11/tomcat-embed-el-10.1.11.jar:/Users/srabhishekh/.m2/repository/org/apache/tomcat/embed/tomcat-embed-websocket/10.1.11/tomcat-embed-websocket-10.1.11.jar:/Users/srabhishekh/.m2/repository/org/springframework/spring-web/6.0.11/spring-web-6.0.11.jar:/Users/srabhishekh/.m2/repository/org/springframework/spring-beans/6.0.11/spring-beans-6.0.11.jar:/Users/srabhishekh/.m2/repository/io/micrometer/micrometer-observation/1.11.2/micrometer-observation-1.11.2.jar:/Users/srabhishekh/.m2/repository/io/micrometer/micrometer-commons/1.11.2/micrometer-commons-1.11.2.jar:/Users/srabhishekh/.m2/repository/org/springframework/spring-webmvc/6.0.11/spring-webmvc-6.0.11.jar:/Users/srabhishekh/.m2/repository/org/springframework/spring-aop/6.0.11/spring-aop-6.0.11.jar:/Users/srabhishekh/.m2/repository/org/springframework/spring-expression/6.0.11/spring-expression-6.0.11.jar:/Users/srabhishekh/.m2/repository/org/springframework/security/spring-security-crypto/6.0.3/spring-security-crypto-6.0.3.jar:/Users/srabhishekh/.m2/repository/org/bouncycastle/bcpkix-jdk15on/1.65/bcpkix-jdk15on-1.65.jar:/Users/srabhishekh/.m2/repository/org/bouncycastle/bcprov-jdk15on/1.65/bcprov-jdk15on-1.65.jar Argon2id
Generate a 32 byte long encryption key with Argon2id
password: secret password
salt (Base64): AAAAAAAAAAAAAAAAAAAAAA==
encryptionKeyArgon2id (Base64) minimal:     e9G7+HHmftUaCEP2O1NwCSJkfyAT0QBzod3Szm1elf0=
encryptionKeyArgon2id (Base64) interactive: FZcsUwo7wf7V24qWTwKeSN9//+Pxy2gCKN35KZX2hXs=
encryptionKeyArgon2id (Base64) moderate:    gdizE6kia1W/CgTA3bRKKjtaf8cgZL1BIe6jeDegg0c=
encryptionKeyArgon2id (Base64) sensitive:   19Uym9wI6e/l5f0NocZmNEaouoHvsSyVfrp9iRYl/C8=
 */