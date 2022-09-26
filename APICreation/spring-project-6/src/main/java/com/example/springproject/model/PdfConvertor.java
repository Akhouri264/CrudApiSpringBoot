package com.example.springproject.model;

import javax.servlet.http.HttpServletResponse;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

import java.awt.Color;
import java.io.IOException;
import java.util.List;
public class PdfConvertor {
	private List<library> libBook;
	
	public PdfConvertor(List<library> libBook) {
		super();
		this.libBook = libBook;
	}
	private void writeTableHeader(PdfPTable table) {
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.BLUE);
		cell.setPadding(5);
		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(Color.WHITE);
		cell.setPhrase(new Phrase("BookID", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Book Name", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Book Author", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Book Category", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Book Description", font));
		table.addCell(cell);
		}
	
	private void writeTableData(PdfPTable table) {
		PdfPCell cell = new PdfPCell();
		for (library lib : libBook) {
			table.addCell(String.valueOf(lib.getBookid()));
			table.addCell(lib.getBookname());
			if(lib.getBookauthor()==null) {
				cell.setBackgroundColor(Color.CYAN);
//				table.addCell();
			}
			table.addCell(lib.getBookauthor());
			table.addCell(lib.getBookcategory());
			table.addCell(lib.getBookdescriptions());
        }
	}
	
		public void export(HttpServletResponse response) throws DocumentException, IOException {
	Document document = new Document(PageSize.A4);
	PdfWriter.getInstance(document, response.getOutputStream());
	document.open();   
	Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
	font.setSize(18);
	font.setColor(Color.BLUE);
	Paragraph p = new Paragraph("Library Books", font);
	p.setAlignment(Paragraph.ALIGN_CENTER);
	document.add(p);
            
	PdfPTable table = new PdfPTable(5);
	table.setWidthPercentage(100f);
	table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f, 6.5f});
	table.setSpacingBefore(10);
            
	writeTableHeader(table);
	writeTableData(table);
            
	document.add(table);
            
	document.close();
    }
}
