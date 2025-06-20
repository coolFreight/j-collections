package com.jcomm.junit5examples.templates;

public class UserIdGeneratorTestCase {
    private boolean isFeatureEnabled;
    private String firstName;
    private String lastName;
    private String expectedUserId;

    public boolean isFeatureEnabled() {
        return isFeatureEnabled;
    }

    public void setFeatureEnabled(boolean featureEnabled) {
        isFeatureEnabled = featureEnabled;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getExpectedUserId() {
        return expectedUserId;
    }

    public void setExpectedUserId(String expectedUserId) {
        this.expectedUserId = expectedUserId;
    }
}
