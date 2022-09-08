package io.github.jmgarridopaz.discounter.application;

import java.math.BigDecimal;

/**
 * Driver Port
 */
public interface ForCalculatingDiscount {

    public BigDecimal calculateDiscount ( BigDecimal amount );

}
