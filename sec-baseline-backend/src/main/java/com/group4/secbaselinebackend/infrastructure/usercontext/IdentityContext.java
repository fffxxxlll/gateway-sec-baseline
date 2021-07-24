package com.group4.secbaselinebackend.infrastructure.usercontext;



public interface IdentityContext {

    Object getIdentity();

    boolean isAuthenticated();

    String getAuthKey();
}
