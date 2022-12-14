package io.github.jmgarridopaz.discounter.application.ports.driving;

import io.github.jmgarridopaz.discounter.application.Amount;

/**
 * Driving Port
 */
public interface ForDiscounting {

    public Amount calculateDiscount (Amount amount );

}
