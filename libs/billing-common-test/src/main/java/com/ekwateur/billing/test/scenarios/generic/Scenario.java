package com.ekwateur.billing.test.scenarios.generic;


import org.junit.jupiter.params.provider.Arguments;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public abstract class Scenario implements Arguments {

    protected final String description;

    @Override
    public Object[] get() {

        return new Object[]{this};
    }

    @Override
    public String toString() {

        return description;
    }

}
