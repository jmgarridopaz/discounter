package io.github.jmgarridopaz.discounter.application.ports.driven;

import io.github.jmgarridopaz.discounter.application.BreakPointWithRate;
import java.util.ListIterator;

/**
 * Driven Port
 */
public interface ForObtainingRates {

    public ListIterator<BreakPointWithRate> getDescendingBreakPointIterator();

}
