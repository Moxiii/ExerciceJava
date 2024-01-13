package com.ipi.javaio.export;

import com.ipi.javaio.model.SalarieAideADomicile;
import com.ipi.javaio.service.SalarieAideADomicileService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;


@Service
public class SalarieAideADomicileExportXlsxService {

    public static final int COL_ID_SALARIE = 0;
    public static final int COL_NOM = 1;
    public static final int COL_MOIS_DEBUT_CONTRAT = 2;
    public static final int COL_ANCIENNETE = 3;
    public static final int COL_JOUR_TRAVAILLES_ANNEE_N = 4;
    public static final int COL_CONGES_PAYES_ACQUIS_ANNEE_N = 5;
    public static final int COL_JOUR_TRAVAILLES_ANNEE_N_MOINS_1 = 4;
    public static final int COL_CONGES_PAYES_ACQUIS_ANNEE_N_MOINS_1 = 5;

    @Autowired
    private final SalarieAideADomicileService salarieAideADomicileService;


    public SalarieAideADomicileExportXlsxService(
            SalarieAideADomicileService salarieAideADomicileService) {
        this.salarieAideADomicileService = salarieAideADomicileService;
    }

    public void export(ServletOutputStream outputStream) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        //Apache POI
        Sheet sheet = workbook.createSheet("Salariés");
        Row headerRow = sheet.createRow(0);

        CellStyle styleHeader = styleColor(workbook);

        Cell cellHeaderIdSalarie = headerRow.createCell(COL_ID_SALARIE);
        cellHeaderIdSalarie.setCellValue("ID salarié");
        cellHeaderIdSalarie.setCellStyle(styleHeader);

        Cell cellHeaderNom = headerRow.createCell(COL_NOM);
        cellHeaderNom.setCellValue("Nom");
        cellHeaderNom.setCellStyle(styleHeader);

        Cell cellHeaderJoursTravaillesAnneeN = headerRow.createCell(COL_JOUR_TRAVAILLES_ANNEE_N);
        cellHeaderJoursTravaillesAnneeN.setCellValue("Jours travaillés année N");
        cellHeaderJoursTravaillesAnneeN.setCellStyle(styleHeader);

        Cell cellHeaderCongesPayesAcquisAnneeN = headerRow.createCell(COL_CONGES_PAYES_ACQUIS_ANNEE_N);
        cellHeaderCongesPayesAcquisAnneeN.setCellValue("Congés payés acquis annee N");
        cellHeaderCongesPayesAcquisAnneeN.setCellStyle(styleHeader);

        Cell cellHeaderAnciennete = headerRow.createCell(COL_ANCIENNETE);
        cellHeaderAnciennete.setCellValue("Ancienneté");
        cellHeaderAnciennete.setCellStyle(styleHeader);

        CellStyle cellStyleBorder = newStyleBorder(workbook);

        List<SalarieAideADomicile> salaries = salarieAideADomicileService.getSalaries();
        int rowIndex = 1;
        for (SalarieAideADomicile salarie : salaries) {
            Row row = sheet.createRow(rowIndex++);

            Cell cellIdSalarie = row.createCell(COL_ID_SALARIE);
            cellIdSalarie.setCellValue(salarie.getId());
            cellIdSalarie.setCellStyle(cellStyleBorder);

            Cell cellNom = row.createCell(COL_NOM);
            cellNom.setCellValue(salarie.getNom());
            cellNom.setCellStyle(cellStyleBorder);

            Cell cellMoisDebutContrat = row.createCell(COL_MOIS_DEBUT_CONTRAT);
            cellMoisDebutContrat.setCellValue(salarie.getMoisDebutContrat());
            cellMoisDebutContrat.setCellStyle(cellStyleBorder);

            int anciennete = salarie.getMoisDebutContrat().until(LocalDate.now()).getYears();
            Cell cellAnciennete = row.createCell(COL_ANCIENNETE);
            cellAnciennete.setCellValue(anciennete + " ans");
            cellAnciennete.setCellStyle(cellStyleBorder);

            Cell cellJoursTravaillesAnneeN = row.createCell(COL_JOUR_TRAVAILLES_ANNEE_N);
            cellJoursTravaillesAnneeN.setCellValue(salarie.getJoursTravaillesAnneeN());
            cellJoursTravaillesAnneeN.setCellStyle(cellStyleBorder);

            Cell cellCongesPayesAcquisAnneeN = row.createCell(COL_CONGES_PAYES_ACQUIS_ANNEE_N);
            cellCongesPayesAcquisAnneeN.setCellValue(salarie.getCongesPayesAcquisAnneeN());
            cellCongesPayesAcquisAnneeN.setCellStyle(cellStyleBorder);

            Cell cellJoursTravaillesAnneeNMoins1 = row.createCell(COL_JOUR_TRAVAILLES_ANNEE_N_MOINS_1);
            cellJoursTravaillesAnneeNMoins1.setCellValue(salarie.getJoursTravaillesAnneeNMoins1());
            cellJoursTravaillesAnneeNMoins1.setCellStyle(cellStyleBorder);

            Cell cellCongesPayesAcquisAnneeNMoins1 = row.createCell(COL_CONGES_PAYES_ACQUIS_ANNEE_N_MOINS_1);
            cellCongesPayesAcquisAnneeNMoins1.setCellValue(salarie.getCongesPayesAcquisAnneeNMoins1());
            cellCongesPayesAcquisAnneeNMoins1.setCellStyle(cellStyleBorder);
        }


        //...
        workbook.write(outputStream);
        workbook.close();

    }

    private CellStyle styleColor(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        setBorderColor(style);

        Font font = workbook.createFont();
        font.setColor(IndexedColors.GREEN.getIndex());
        style.setFont(font);
        return style;
    }

    private CellStyle newStyleBorder(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        setBorderColor(style);
        return style;
    }

    private void setBorderColor(CellStyle style) {
        style.setBorderBottom(BorderStyle.MEDIUM);
        style.setBottomBorderColor(IndexedColors.BLUE.getIndex());

        style.setBorderTop(BorderStyle.MEDIUM);
        style.setTopBorderColor(IndexedColors.BLUE.getIndex());

        style.setBorderLeft(BorderStyle.MEDIUM);
        style.setLeftBorderColor(IndexedColors.BLUE.getIndex());

        style.setBorderRight(BorderStyle.MEDIUM);
        style.setRightBorderColor(IndexedColors.BLUE.getIndex());
    }


}
