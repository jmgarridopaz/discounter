package io.github.jmgarridopaz.discounter.outside.startup;


import com.sun.javaws.exceptions.InvalidArgumentException;
import io.github.jmgarridopaz.discounter.application.ForObtainingRates;

public class DiscounterEntryPoint {

	public static void main ( String[] args ) {

		// Select adapters for every driven actor
		String rateProviderName = "STUB";
		if ( args!=null && args.length>0 && args[0]!=null ) {
			if ( !args[0].equals("STUB") && !args[0].equals("FILE") ) {
				throw new IllegalArgumentException(args[0]);
			}
			rateProviderName = args[0];
		}
		ForObtainingRates rateProvider = RateProviderSelector.selectAdapter ( RateProvider.valueOf(rateProviderName) );

	}


	private final DiscounterApi discounterApplication;

	public DiscounterEntryPoint(DiscounterApi discounterApplication ) {
		this.discounterApplication = discounterApplication;
	}

	public void run() {

	}

}
