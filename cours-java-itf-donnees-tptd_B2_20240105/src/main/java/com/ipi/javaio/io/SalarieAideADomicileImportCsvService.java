package com.ipi.javaio.io;

import com.ipi.javaio.exception.SalarieException;
import com.ipi.javaio.model.SalarieAideADomicile;
import com.ipi.javaio.repository.SalarieAideADomicileMoisRepository;
import com.ipi.javaio.service.SalarieAideADomicileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import java.util.List;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;

@Service
public class SalarieAideADomicileImportCsvService {

    @Autowired
    private final SalarieAideADomicileService salarieAideADomicileService;

    public SalarieAideADomicileImportCsvService(
            SalarieAideADomicileMoisRepository salarieAideADomicileMoisRepository,
            SalarieAideADomicileService salarieAideADomicileService) {
        this.salarieAideADomicileService = salarieAideADomicileService;
    }

    public void importFile(String filePath) throws SalarieException {
        try (CSVReader csvReader = new CSVReader(new FileReader(filePath))) {
            List<String[]> allData = csvReader.readAll();

            for (String[] row : allData) {
                SalarieAideADomicile salarie = new SalarieAideADomicile();
                salarie.setNom(row[0]);



                // Enregistrez le salarié en base de données
                ///salarieAideADomicileService.saveSalarie(salarie);
            }
        } catch (IOException | CsvException e) {
            throw new SalarieException("Erreur lors de l'import du fichier CSV", e);
        }
    }

    /*
    public void exportFile() throws SalarieException {
        String filePath = "chemin/vers/salaries_a_importer.csv";

        try (Reader reader = new FileReader(Paths.get(filePath).toAbsolutePath().toString());
             CSVReader csvReader = new CSVReader(reader)) {

            String[] record;
            csvReader.readNext();

            while ((record = csvReader.readNext()) != null) {

                SalarieAideADomicile salarie = new SalarieAideADomicile();
                salarie.setNom(record[0]);

                salarieAideADomicileService.addSalarie(salarie);
            }

        } catch (Exception e) {
            throw new SalarieException("Erreur lors de l'import du fichier CSV.", e);
        }
    }
}
*/
    }



