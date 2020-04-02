package com.mall.user;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentPBEConfig;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DatasourceTests {

    public static void main(String[] args) {
        StandardPBEStringEncryptor encrypt = new StandardPBEStringEncryptor();
        EnvironmentPBEConfig config = new EnvironmentPBEConfig();
        config.setPassword("258EAFA5-E914-47DA-95CA-C5AB0DC85B11");
        encrypt.setConfig(config);
        String plainText = "!QweQaz1014";
        String encryptedText = encrypt.encrypt(plainText);
        System.out.println(encryptedText);

        System.out.println(encrypt.decrypt(encryptedText));
    }

}
