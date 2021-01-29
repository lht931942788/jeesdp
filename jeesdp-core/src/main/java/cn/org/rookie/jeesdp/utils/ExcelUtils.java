package cn.org.rookie.jeesdp.utils;

import cn.org.rookie.jeesdp.component.Record;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Locale;
import java.util.Properties;

/**
 * Excel工具类
 *
 * @author LIHAITAO
 */

public abstract class ExcelUtils {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0");

    public static void main(String[] args) throws IOException {
        Properties properties = System.getProperties();
        Locale locale = Locale.getDefault();
        System.out.println(locale);
        properties.forEach((s, s2) -> System.out.println(s + ":\t" + s2));



        /*Workbook wb = new XSSFWorkbook(new FileInputStream("D:\\Work\\demo.xlsx"));
        Sheet sheet = wb.getSheetAt(0);
        read(sheet, 1, 0);*/
    }

    public static Record read(HSSFWorkbook wb) {
        HSSFSheet sheet = wb.getSheetAt(0);
        return null;
    }

    public static Record read(Sheet sheet, int startRow, int startCell, String... names) {
        int rowNum = sheet.getLastRowNum();
        for (int i = startRow; i <= rowNum; i++) {
            Row row = sheet.getRow(i);
            short cellNum = row.getLastCellNum();
            Object[] rowData = new Object[cellNum];
            for (short j = (short) startCell; j <= cellNum; j++) {
                Cell cell = row.getCell(j);
                if (cell != null) {
                    CellType cellType = cell.getCellType();
                    switch (cellType) {
                        case STRING:
                            rowData[j] = cell.getStringCellValue();
                            break;
                        case NUMERIC:
                            short format = cell.getCellStyle().getDataFormat();
                            if (format == 0) {
                                rowData[j] = DECIMAL_FORMAT.format(cell.getNumericCellValue());
                            } else {
                                String pattern = "yyyy-MM-dd HH:mm";
                                SimpleDateFormat dateFormat = null;
                                if (format == 20 || format == 32) {
                                    pattern = "HH:mm";
                                } else if (format == 57) {
                                    pattern = "yyyy-MM";
                                } else if (format == 14 || format == 31 || format == 58) {
                                    pattern = "yyyy-MM-dd";
                                } else if (format == 22 || format == 176) {
                                    pattern = "yyyy-MM-dd HH:mm";
                                }
                                DATE_FORMAT.applyPattern(pattern);
                                rowData[j] = DATE_FORMAT.format(DateUtil.getJavaDate(cell.getNumericCellValue()));
                            }
                            break;
                        case BOOLEAN:
                            rowData[j] = cell.getBooleanCellValue();
                            break;
                        default:
                            rowData[j] = null;
                            break;
                    }
                }
            }
            System.out.println(Arrays.toString(rowData));
        }
        return null;
    }
}
