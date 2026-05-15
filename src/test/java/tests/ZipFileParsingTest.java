package tests;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


public class ZipFileParsingTest {
    private final ClassLoader cl = ZipFileParsingTest.class.getClassLoader();

    @Test
    @DisplayName("Проверка наличия в zip-архиве 3х файлов и их имен")
    void zipFileParsingTest() throws Exception {

        List<String> fileNames = new ArrayList<>();

        try (ZipInputStream zis = new ZipInputStream(
                cl.getResourceAsStream("SampleZIP.zip"))) {
            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                fileNames.add(entry.getName());
            }
        }

        assertEquals(3, fileNames.size());

        List<String> expectedFiles = Arrays.asList(
                "SamplePDF.pdf",
                "SampleCSV.csv",
                "SampleXLSX.xlsx"
        );

        for (String expectedFile : expectedFiles) {
            assertTrue(fileNames.contains(expectedFile));
        }
    }

    @Test
    @DisplayName("Проверка наличия в архиве файла PDF и строки Page 1 of 10 в файле PDF")
    void pdfFileFromZipTest() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(
                cl.getResourceAsStream("SampleZIP.zip"))) {
            ZipEntry entry;
            boolean pdfFound = false;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().endsWith("pdf")) {
                    pdfFound = true;
                    PDF pdf = new PDF(zis);
                    Assertions.assertTrue(pdf.text.contains("Page 1 of 10"));
                    break;
                }
            }
            Assertions.assertTrue(pdfFound);
        }
    }

    @Test
    @DisplayName("Проверка наличия в архиве файла CSV, его размерности и строки 9")
    void csvFileFromZipTest() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(
                cl.getResourceAsStream("SampleZIP.zip"))) {
            ZipEntry entry;
            boolean csvFound = false;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().endsWith("csv")) {
                    csvFound  = true;
                    CSVReader csvReader = new CSVReader(new InputStreamReader(zis));
                    List<String[]> data = csvReader.readAll();
                    Assertions.assertEquals(11, data.size());
                    Assertions.assertArrayEquals(
                            new String[] {"8","Emma","Lopez","emma.lopez8@demo.io","30","Greenville","146867.05","2015-12-15"},
                            data.get(8));
                    break;
                }
            }
            Assertions.assertTrue(csvFound);
        }
    }

    @Test
    @DisplayName("Проверка наличия в архиве файла XLSX и нахождения в нем в строке 10 столбце 5 слова Engeneering")
    void xlsxFileFromZipTest1() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(
                cl.getResourceAsStream("SampleZIP.zip"))) {
            ZipEntry entry;
            boolean xlsFound = false;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().endsWith("xlsx")) {
                    xlsFound  = true;
                    XLS xls = new XLS(zis);
                    String actualValue = xls.excel.getSheetAt(0).getRow(9).getCell(4).getStringCellValue();
                    Assertions.assertTrue(actualValue.contains("Engineering"));
                    break;
                }
            }
            Assertions.assertTrue(xlsFound);
        }
    }
}

