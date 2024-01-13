package com.ipi.javaio.web;

import com.ipi.javaio.export.SalarieAideADomicileMoisExportPdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("export")
public class SalarieAideADomicileMoisExportPdfController {

    @Autowired
    private final SalarieAideADomicileMoisExportPdfService salarieAideADomicileMoisExportPdfService;

    public SalarieAideADomicileMoisExportPdfController(SalarieAideADomicileMoisExportPdfService salarieAideADomicileMoisExportPdfService) {
        this.salarieAideADomicileMoisExportPdfService = salarieAideADomicileMoisExportPdfService;
    }

    @GetMapping("/salarieAideADomicileMois/pdf")
    public void salarieAideADomicileMoisXlsx(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"export_mois.pdf\"");
        this.salarieAideADomicileMoisExportPdfService.export(response.getOutputStream());
    }

    /**
     * PAS ENCORE IMPLEMENTEE
     * @param salarieId
     * @param request
     * @param response
     * @throws IOException
     */
    @GetMapping("/salarieAideADomicileMois/pdf/{salarieId}")
    public void salarieAideADomicileMoisXlsx(@PathVariable("salarieId") Long salarieId,
            HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"export_mois_" + salarieId + ".pdf\"");
        /// TODO this.salarieAideADomicileMoisExportPdfService.export(response.getOutputStream(), salarieId);
    }

    /**
     * PAS ENCORE IMPLEMENTEE
     * @param salarieId
     * @param annee
     * @param request
     * @param response
     * @throws IOException
     */
    @GetMapping("/salarieAideADomicileMois/pdf/{salarieId}/{annee}")
    public void salarieAideADomicileMoisXlsx(@PathVariable("salarieId") Long salarieId, @PathVariable("annee") int annee,
                                            HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"export_mois" + salarieId + "_" + annee + ".pdf\"");
        /// TODO this.salarieAideADomicileMoisExportPdfService.export(response.getOutputStream(), salarieId, annee);
    }

}
