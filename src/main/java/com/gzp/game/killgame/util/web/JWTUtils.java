package com.gzp.game.killgame.util.web;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.DirectDecrypter;
import com.nimbusds.jose.crypto.DirectEncrypter;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.text.ParseException;

public class JWTUtils {

    private static final SecretKeySpec SECRET_KEY = new SecretKeySpec(
            DatatypeConverter.parseHexBinary(
                    "698fdb47bf26be1156c245e4936b397630f99ecedd52b62b930c826b7e28a3a6"),
            "AES"
    );

    private static final DirectEncrypter ENCRYPTER;
    private static final DirectDecrypter DECRYPTER;

    static {
        try {
            ENCRYPTER = new DirectEncrypter(SECRET_KEY);
            DECRYPTER = new DirectDecrypter(SECRET_KEY);
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }


    public static String encrypt(String cookie) {
        JWEObject jwe = null;
        try {
            Payload payload = new Payload(cookie);
            JWEHeader header = new JWEHeader(JWEAlgorithm.DIR, EncryptionMethod.A256GCM);
            jwe = new JWEObject(header, payload);
            jwe.encrypt(ENCRYPTER);

        } catch (JOSEException e) {
            e.printStackTrace();
        }
        return jwe.serialize();
    }


    public static String decrypt(String cookie) {
        JWEObject jwe = null;
        try {
            jwe = JWEObject.parse(cookie);
            jwe.decrypt(DECRYPTER);
        } catch (ParseException | JOSEException e) {
            e.printStackTrace();
        }
        return jwe.getPayload().toString();
    }


}
