package com.dianping.sansi.sansilab.utils;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zuhai.jiang on 2015/6/12.
 */
public class ExcelUtil {
    public static void main(String[] args) {
        String path="d:/tmp/a.xls";
        try
        {
            // 构造 XSSFWorkbook 对象，strPath 传入文件路径
            InputStream is = new FileInputStream(new File(path));
            XSSFWorkbook xwb = new XSSFWorkbook(is);
            // 读取第一章表格内容
            XSSFSheet sheet = xwb.getSheetAt(0);
            // 定义 row、cell
            XSSFRow row;
            XSSFCell cell;
            // 循环输出表格中的内容
            for (int i = sheet.getFirstRowNum(); i < sheet.getPhysicalNumberOfRows(); i++)
            {
                row = sheet.getRow(i);
                for (int j = row.getFirstCellNum(); j < row.getPhysicalNumberOfCells(); j++)
                {
                    // 通过 row.getCell(j).toString() 获取单元格内容，
                    cell = row.getCell(j);
                    String val=null;

                    int type = cell.getCellType();
                    if (type == HSSFCell.CELL_TYPE_NUMERIC) {
                        if (HSSFDateUtil.isCellDateFormatted(cell)) {
                            // 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            double value = cell.getNumericCellValue();
                            Date date = org.apache.poi.ss.usermodel.DateUtil
                                    .getJavaDate(value);
                            val = "D:"+sdf.format(date);
                        } else {
                            val = "N:"+cell.getRawValue();
                        }
                    } else {
                        val = "S:"+cell.getStringCellValue();
                    }
                    System.out.print(val + "\t");
                }
                System.out.println("");
            }
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}