package com.mlc.movie.model.gender;

/**
 * The Gender enum contains retrieve the person's gender.
 */
public enum Gender {
    MALE(2),
    FEMALE(1),
    UNKNOWN(0);

    private final int type;

    private Gender(int type) {
        this.type = type;
    }

    public static Gender fromInteger(int type) {
        for (Gender gender : Gender.values()) {
            if (gender.type == type) {
                return gender;
            }
        }
        return UNKNOWN;
    }

}
