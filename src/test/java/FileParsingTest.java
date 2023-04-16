import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.assertj.core.api.Assertions.assertThat;

class FileParsingTest {

    private ClassLoader cl = FileParsingTest.class.getClassLoader();

    @Test
    void readAndCheckPdfFromZipTest() throws Exception {

        try (InputStream is = cl.getResourceAsStream("Lesson_9.zip"); ZipInputStream zs = new ZipInputStream(is)) {
            ZipEntry entry;
            boolean found = false;
            while ((entry = zs.getNextEntry()) != null) {
                if (entry.getName().equals("Profile.pdf")) {
                    found = true;
                    PDF pdf = new PDF(zs);
                    Assertions.assertEquals("Resume", pdf.title);
                    Assertions.assertTrue(pdf.text.contains("Dmitry Akula"));
                }
            }
            if (!found) {
                Assertions.fail("File not found");
            }
        }
    }

    @Test
    void readAndCheckCSVFromZipTest() throws Exception {

        try (InputStream is = cl.getResourceAsStream("Lesson_9.zip"); ZipInputStream zs = new ZipInputStream(is)) {
            ZipEntry entry;
            boolean found = false;
            while ((entry = zs.getNextEntry()) != null) {
                if (entry.getName().equals("Variable mapping.csv")) {
                    found = true;
                    CSVReader csvReader = new CSVReader(new InputStreamReader(zs));
                    List<String[]> content = csvReader.readAll();
                    Assertions.assertArrayEquals(new String[]{"Source value 1", "Source value 2", "Target value"}, content.get(0));
                }
            }
            if (!found) {
                Assertions.fail("File not found");
            }
        }
    }

    @Test
    void readAndCheckXlsxFromZipTest() throws Exception {

        try (InputStream is = cl.getResourceAsStream("Lesson_9.zip"); ZipInputStream zs = new ZipInputStream(is)) {
            ZipEntry entry;
            boolean found = false;
            while ((entry = zs.getNextEntry()) != null) {
                if (entry.getName().equals("test.xlsx")) {
                    found = true;
                    XLS xls = new XLS(zs);
                    assertThat(xls.excel.getSheetAt(0).getRow(2).getCell(2).getStringCellValue());
                }
            }
            if (!found) {
                Assertions.fail("File not found");
            }
        }
    }
}
