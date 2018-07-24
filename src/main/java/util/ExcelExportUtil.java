package util;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;

/**
 * Excel导出工具类
 *
 * @author 蝈蝈
 * @since 0.1.0
 */
public class ExcelExportUtil<T> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public void export(Map<String, String> headers, List<T> dataList, ByteArrayOutputStream byteArrayOutputStream) {
        try {
            // 创建Excel工作薄，一页大小60000
            Integer k = 60000;
            HSSFWorkbook book = new HSSFWorkbook();
            logger.info("dataList.size()={}", dataList.size());
            for (int i = 1; dataList.size() > k * (i - 1); i++) {
                logger.info("i={},j={}", i, k * i);
                exports(headers, book, dataList, i, k);
            }
            book.write(byteArrayOutputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void exports(Map<String, String> headers, HSSFWorkbook book, List<T> dataList, int num, Integer k) {
        try {
            // 在Excel工作薄中建一张工作表
            HSSFSheet sheet = book.createSheet("工作表" + String.valueOf(num));
            sheet.setDefaultColumnWidth((short) 20);
            // 第一行为标题行
            int n = 0;
            HSSFRow row = sheet.createRow(n);
            int count = 0;
            for (String header : headers.keySet()) {
                HSSFCell cell = row.createCell(count++);
                cell.setCellValue(header);
            }
            logger.info("num={}, k={}", num, k);
            for (Integer i = k * (num - 1); i < num * k; i++) {
                if (i >= dataList.size()) {
                    break;
                }
                String[] headerArr = (String[]) headers.values().toArray();
                Map<String, Object> map = BeanToMapUtil.objectToMap(dataList.get(i));
                // 创建所需的行数
                HSSFRow rowi = sheet.createRow(++n);
                for (int j = 0; j < headerArr.length; j++) {
                    // 设置单元格的数据类型
                    HSSFCell cell = null;
                    if (map.get(headerArr[j]) == null || "".equals(map.get(headerArr[j]))) {
                        // 设置单元格的值
                        rowi.createCell(j).setCellValue("");
                    } else {
                        // 设置单元格的值
                        rowi.createCell(j).setCellValue(map.get(headerArr[j]).toString());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
