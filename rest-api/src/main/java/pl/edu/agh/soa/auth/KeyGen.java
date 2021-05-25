package pl.edu.agh.soa.auth;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

public class KeyGen  implements KeyGenInterface{
    @Override
    public Key generateKey() {
        String key = "z6B8DbGdJgNjQmTqVsXv2x4z7C9EcHeKgPkRnTrWtYv3y5A8DaFcJfMhPmSpUrXuZw4z6B8EbGdJgNjQ";
        return new SecretKeySpec(key.getBytes(), 0, key.getBytes().length, "DES");
    }
}
