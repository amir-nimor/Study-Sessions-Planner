package ir.maktabsharif.util;

import ir.maktabsharif.exception.ValidationExceptio;

public class Ruls {

    private Ruls() {
    }

    public static void check(boolean validation, String message) {
        if (validation) throw new ValidationExceptio(message);
    }
}
