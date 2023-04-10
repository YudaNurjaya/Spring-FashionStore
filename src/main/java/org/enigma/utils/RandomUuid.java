package org.enigma.utils;

import java.util.UUID;

public class RandomUuid {
    public String random() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

}
