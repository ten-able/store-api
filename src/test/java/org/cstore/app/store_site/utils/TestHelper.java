package org.cstore.app.store_site.utils;


import static java.lang.String.format;

import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import com.cstore.app.store_site.entity.AUser;

public class TestHelper {
    public static Optional<AUser> buildUser() {
        String uuid = UUID.randomUUID().toString();
        AUser user =new  AUser();
        user.setUsername("username-"+uuid);
        user.setEmail(format("someone-%s@gmail.com", uuid));
        return Optional.of(user);
         
    }

    public static Optional<AUser> buildUserWithId() {
        Random random = new Random();
        String uuid = UUID.randomUUID().toString();
        AUser user =new  AUser();
        user.setUsername("username-"+uuid);
        user.setEmail(format("someone-%s@gmail.com", uuid));
        return Optional.of(user);
    }
}
