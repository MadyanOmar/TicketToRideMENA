package com.tickettoride.tickettoride.Server.Management;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UsernameManager {
    private final List<String> availableUsernames;

    public UsernameManager() {
        availableUsernames = new ArrayList<>(List.of(
                "IbnKhaldun", "AlBiruni", "AlMasudi", "AlFarabi"
        ));

        Collections.shuffle(availableUsernames);
    }

    public synchronized String getUniqueRandomUsername() {
        if (availableUsernames.isEmpty()) {
            return "Traveler_" + (int)(Math.random() * 1000);
        }
        return availableUsernames.remove(0);
    }
}