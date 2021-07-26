package com.group4.secbaselinebackend.infrastructure.expressions;


public class PropertyQueryExpression extends QueryExpression {

    private String property;

    public String getProperty() {
        return property;
    }

    public void setProperty(String propertyName) {
        this.property = propertyName;
    }
}
