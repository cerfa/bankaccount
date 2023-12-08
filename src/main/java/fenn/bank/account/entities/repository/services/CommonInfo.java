package fenn.bank.account.entities.repository.services;

import java.util.UUID;

public final class CommonInfo {
    private CommonInfo() {
     //Just for the sonar.
    }
    public static String generateAccountId() {
        UUID uuid = UUID.randomUUID();
        String uuidAsString = uuid.toString().replace("-", "");
        return  "ACC".concat(uuidAsString);
    }
}
