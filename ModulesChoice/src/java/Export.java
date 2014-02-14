package java;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

public class Export {

	public void exportExcel(String moduleName, ArrayList<ArrayList<String>> eleves) {		
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet(moduleName+" Candidatures");
		
		HSSFFont bold = workbook.createFont();
		bold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		bold.setFontHeightInPoints((short) 12);
		HSSFCellStyle style = workbook.createCellStyle();
		style.setFont(bold);
		style.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
		style.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
		style.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
		style.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
		
		HSSFFont bold1 = workbook.createFont();
		bold1.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		bold1.setFontHeightInPoints((short) 10);
		HSSFCellStyle style1 = workbook.createCellStyle();
		style1.setFont(bold1);
		style1.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
		style1.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
		style1.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
		style1.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
		
		HSSFCellStyle style2 = workbook.createCellStyle();
		style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		
		HSSFRow row0 = sheet.createRow(0);
		Cell cell0 = row0.createCell(0);
		cell0.setCellValue("Liste des candidats de   ");
		cell0.setCellStyle(style);
		Cell cell01 = row0.createCell(1);
		cell01.setCellValue(moduleName+"   ");
		cell01.setCellStyle(style);	
		HSSFRow row1 = sheet.createRow(2);
		Cell cell1 = row1.createCell(0);
		cell1.setCellValue("Name");
		cell1.setCellStyle(style1);
		Cell cell2 = row1.createCell(1);
		cell2.setCellValue("Surname");
		cell2.setCellStyle(style1);
		Cell cell3 = row1.createCell(2);
		cell3.setCellValue("Choice  ");
		cell3.setCellStyle(style1);
		Cell cell4 = row1.createCell(3);
		cell4.setCellValue("Preference  ");
		cell4.setCellStyle(style1);
		
		for(int i=0;i<eleves.size();i++){
			HSSFRow row2 = sheet.createRow(3+i);
			for(int j=0;j<4;j++) {
			Cell cell = row2.createCell(j);
			cell.setCellValue(eleves.get(i).get(j));
			cell.setCellStyle(style2);
			}
		}
		sheet.autoSizeColumn(0);
		sheet.autoSizeColumn(1);
		sheet.autoSizeColumn(2);
		sheet.autoSizeColumn(3);
		
		
		try {
		    FileOutputStream out =
		            new FileOutputStream(new File(moduleName+".xls"));
		    workbook.write(out);
		    out.close();
		    System.out.println("Excel written successfully..");
		     
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}

}
