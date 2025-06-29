package com.jcomm.junit5examples.templates;

public class UserIdGeneratorImpl implements UserIdGenerator {
    private final boolean isFeatureEnabled;

    public UserIdGeneratorImpl(boolean isFeatureEnabled) {
        this.isFeatureEnabled = isFeatureEnabled;
    }

    public String generate(String firstName, String lastName) {
        String initialAndLastName = firstName.substring(0, 1).concat(lastName);
        return isFeatureEnabled ? "bael".concat(initialAndLastName) : initialAndLastName;
    }
}
