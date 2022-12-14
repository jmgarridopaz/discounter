package io.github.jmgarridopaz.discounter.outside.forobtainingrates.file;

import io.github.jmgarridopaz.discounter.application.Amount;
import io.github.jmgarridopaz.discounter.application.BreakPointWithRate;
import io.github.jmgarridopaz.discounter.application.Rate;
import io.github.jmgarridopaz.discounter.application.ports.driven.ForObtainingRates;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

// * 	Break_Point (€)		Discount_Rate (%)
//         * 			 0				 0
//         * 		   100				 5
//         * 		 1,000				 7
//         * 		10,000				10
public class FileRateAdapter implements ForObtainingRates {

    private final String rateFileName;

    public FileRateAdapter ( String rateFileName ) {
        System.out.println("FileRateAdapter constructor...");
        if ( rateFileName==null || rateFileName.trim().isEmpty() ) {
            throw new IllegalArgumentException("RateFileName must be provided");
        }
        this.rateFileName = rateFileName;
    }


    @Override
    public ListIterator<BreakPointWithRate> getDescendingBreakPointIterator() {
        List<BreakPointWithRate> rateTable = rateTableFromFile();
        return rateTable.listIterator ( rateTable.size() );
    }

    private List<BreakPointWithRate> rateTableFromFile() {
        List<String> fileLines = new ArrayList<String>();
        try {
            fileLines = Files.readAllLines(Paths.get(this.rateFileName));
        } catch (IOException e) {
            throw new RuntimeException("An I/O error ocurred reading from the file "+this.rateFileName,e);
        }
        if ( fileLines==null || fileLines.isEmpty() ) {
            throw new RuntimeException("'"+this.rateFileName+"' file must have at least one line");
        }
        List<BreakPointWithRate> rateTable = new ArrayList<BreakPointWithRate>();
        Amount previousBreakPoint = null;
        for ( String line : fileLines ) {
           BreakPointWithRate breakPointWithRate = rateTableRowFromFileLine ( line );
           if ( previousBreakPoint != null ) {
               if ( previousBreakPoint.isGreaterThanOrEqualTo ( breakPointWithRate.getBreakPoint() ) ) {
                   throw new RuntimeException("Lines of '"+this.rateFileName+"' file must be sorted by 'breakPoint' field in strictly ascending order");
               }
           }
           previousBreakPoint = breakPointWithRate.getBreakPoint();
           rateTable.add(breakPointWithRate);
        }
        return rateTable;
    }

    private BreakPointWithRate rateTableRowFromFileLine ( String line ) {
        if ( line==null || line.trim().isEmpty() ) {
            throw new RuntimeException("Line cannot be empty");
        }
        String[] lineItems = line.split("\\s+");
        if ( lineItems.length != 2 ) {
            throw new RuntimeException("Line must have two items");
        }
        Amount breakPoint = Amount.parse(lineItems[0]);
        Rate rate = Rate.parse(lineItems[1]);
        return new BreakPointWithRate(breakPoint,rate);
    }

}
