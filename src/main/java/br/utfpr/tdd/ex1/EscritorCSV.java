package br.utfpr.tdd.ex1;

import java.io.BufferedWriter;
import java.io.IOException;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

/**
 *
 * @author andreendo
 */
class EscritorCSV {
    CSVPrinter csvPrinter;
    private static final Logger LOGGER = Logger.getLogger(EscritorCSV.class.getName());
    
    void escrever(String ra, String nome, double notaFinal, String situacao) {
        try {
            Locale locale  = new Locale("en", "UK");
            DecimalFormat df = (DecimalFormat)
                                NumberFormat.getNumberInstance(locale);
            df.applyPattern(".#");
            df.setRoundingMode(RoundingMode.DOWN);
            csvPrinter.printRecord(ra, nome, df.format(notaFinal), situacao);
            csvPrinter.flush();            
        }
        catch(IOException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    void setArquivoSaida(String filePath) throws IOException {
        try {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath));

            csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                .withHeader("RA", "Nome", "NF", "Situacao"));
            csvPrinter.flush();            
        }
        catch(IOException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }finally{
            if (csvPrinter == null) csvPrinter.close();
        }
        
    }
    
}
