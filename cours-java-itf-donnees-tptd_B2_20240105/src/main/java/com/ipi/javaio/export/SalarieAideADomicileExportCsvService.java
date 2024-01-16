package com.ipi.javaio.export;

import com.ipi.javaio.model.SalarieAideADomicile;
import com.ipi.javaio.service.SalarieAideADomicileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.util.Arrays;

@Service
public class SalarieAideADomicileExportCsvService {
    @Autowired
    private final SalarieAideADomicileService salarieAideADomicileService;

    public SalarieAideADomicileExportCsvService(
            SalarieAideADomicileService salarieAideADomicileService) {
        this.salarieAideADomicileService = salarieAideADomicileService;
    }

    public void export(PrintWriter writer, Long salarieId) {
        SalarieAideADomicile salarieAideADomicile = salarieAideADomicileService.getSalarie(salarieId);
        exportBase(writer, Arrays.asList(salarieAideADomicile));
    }
    public void export(PrintWriter writer) {
        Iterable<SalarieAideADomicile> salaries = salarieAideADomicileService.getSalaries();
        exportBase(writer, salaries);
    }
    public void exportBase(PrintWriter writer, Iterable<SalarieAideADomicile> salaries) {
        writer.println("Id, Nom, JoursTravaillesAnnee ,jour travailler, conge payer"  );

        for (SalarieAideADomicile salarie : salaries) {
            String line = escapeAndConcatValues(salarie.getId(), salarie.getNom(), salarie.getJoursTravaillesAnneeN(),salarie.getCongesPayesAcquisAnneeN());
            writer.println(line);
        }
    }

    private String escapeAndConcatValues(Object... values) {
        StringBuilder result = new StringBuilder();

        for (Object value : values) {
            String escapedValue = value != null ? value.toString().replace("\"", "\"\"") : "";
            result.append("\"").append(escapedValue).append("\",");
        }

        return result.deleteCharAt(result.length() - 1).toString();
    }
}
