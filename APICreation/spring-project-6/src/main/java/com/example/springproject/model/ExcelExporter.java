package com.example.springproject.model;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelExporter {
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private List<library> listUsers;
	
	public ExcelExporter(List<library> listUsers) {
		this.listUsers = listUsers;
		workbook = new XSSFWorkbook();
	}
	
	private void writeHeaderLine() {
			sheet = workbook.createSheet("Users");
			Row row = sheet.createRow(0);
			CellStyle style = workbook.createCellStyle();
			XSSFFont font = workbook.createFont();
			font.setBold(true);
			font.setFontHeight(16);
			style.setFont(font);
			createCell(row, 0, "BookID", style);
			createCell(row, 1, "Book Name", style);
			createCell(row, 2, "Book Author", style);
			createCell(row, 3, "Book Category", style);
			createCell(row, 4, "Book Description", style);
	}

	private void createCell(Row row, int columnCount, Object value, CellStyle style) {
		sheet.autoSizeColumn(columnCount);
		Cell cell = row.createCell(columnCount);
		if (value instanceof Integer) {
			cell.setCellValue((Integer) value);
		} else if (value instanceof Boolean) {
			cell.setCellValue((Boolean) value);
		}else {
			cell.setCellValue((String) value);
			}
			cell.setCellStyle(style);
		}
	private void writeDataLines() {
		int rowCount = 1;
		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setFontHeight(14);
		style.setFont(font);
		for(library lib :listUsers) {
			Row row=sheet.createRow(rowCount++);
			int colCount=0;
			createCell(row, colCount++, lib.getBookid(), style);
			createCell(row, colCount++, lib.getBookname(), style);
			createCell(row, colCount++, lib.getBookauthor(), style);
			createCell(row, colCount++, lib.getBookcategory(), style);
			createCell(row, colCount++, lib.getBookdescriptions(), style);
			}
		}
		public void export(HttpServletResponse response) throws IOException {
			writeHeaderLine();
			writeDataLines();
			ServletOutputStream outputStream = response.getOutputStream();
			workbook.write(outputStream);
			workbook.close();
			outputStream.close();
		}
}