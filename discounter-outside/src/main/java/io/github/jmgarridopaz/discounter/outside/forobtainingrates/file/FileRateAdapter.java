package io.github.jmgarridopaz.discounter.outside.forobtainingrates.file;

import io.github.jmgarridopaz.discounter.application.Amount;
import io.github.jmgarridopaz.discounter.application.BreakPointWithRate;
import io.github.jmgarridopaz.discounter.application.Rate;
import io.github.jmgarridopaz.discounter.application.ports.driven.ForObtainingRates;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;


public class FileRateAdapter implements ForObtainingRates {

    private final String rateFileName;

    /**
     * If lines are not provided, it creates no file, assuming 'rateFileName' exists already.
     *
     * If lines are provided, creates the 'rateFileName' file, and writes them to it.
     * If this file already exists, overrides its content.
     */
    public FileRateAdapter ( String rateFileName, String ... lines ) {
        if ( rateFileName==null || rateFileName.trim().isEmpty() ) {
            throw new IllegalArgumentException("RateFileName must be provided");
        }
        this.rateFileName = rateFileName;
        if ( (lines!=null) && (lines.length>0) ) {
            System.out.println("Writing to file '"+rateFileName+"'...");
            try {
                Files.write ( Paths.get(rateFileName), Arrays.asList(lines) );
            } catch (IOException e) {
                throw new RuntimeException("An I/O error occurred creating or writing to the file '"+rateFileName+"'",e);
            }
        }
    }


    @Override
    public ListIterator<BreakPointWithRate> getDescendingBreakPointIterator() {
        List<BreakPointWithRate> rateTable = rateTableFromFile();
        return rateTable.listIterator ( rateTable.size() );
    }

    private List<BreakPointWithRate> rateTableFromFile() {
        List<String> fileLines = new ArrayList<String>();
        System.out.println("Reading from file '"+this.rateFileName+"'...");
        try {
            fileLines = Files.readAllLines(Paths.get(this.rateFileName));
        } catch (IOException e) {
            throw new RuntimeException("An I/O error occurred reading from the file '"+this.rateFileName+"'",e);
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
