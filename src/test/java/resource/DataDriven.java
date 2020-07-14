package resource;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataDriven {

	@SuppressWarnings("deprecation")
	public ArrayList<String> getData(String testCaseName, String testDataSheetName) throws IOException {
		ArrayList<String> al = new ArrayList<String>();

		// identiy testcases column by scanning entire 1st row
		// once column is identify scan entire testcase to get the purchase

		FileInputStream fis = new FileInputStream("C:\\Users\\vio\\Documents\\Demodata.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);

		int sheets = workbook.getNumberOfSheets();

		for (int i = 0; i < sheets; i++) {
			if (workbook.getSheetName(i).equalsIgnoreCase(testDataSheetName)) {
				XSSFSheet sheet = workbook.getSheetAt(i);

				// identiy testcases column by scanning entire 1st row
				Iterator<Row> rows = sheet.iterator(); // sheet is a collection of rows
				Row firstrow = rows.next();
				Iterator<Cell> cell = firstrow.cellIterator(); // row is a collection of cells
				int counter = 0;
				int column = 0;
				while (cell.hasNext()) {
					Cell value = cell.next();
					if (value.getStringCellValue().equalsIgnoreCase("Testcases")) {
						// desired column by taking that index
						column = counter;

					}
					counter++;

				}
				System.out.println(column);

				// once column is identify scan entire testcase to get the purchase
				while (rows.hasNext()) {
					Row r = rows.next();
					if (r.getCell(column).getStringCellValue().equalsIgnoreCase(testCaseName)) {
						Iterator<Cell> cv = r.cellIterator();
						while (cv.hasNext()) {
							Cell c = cv.next();
							if (c.getCellType() == CellType.STRING) {
								al.add(c.getStringCellValue());
							} else {
								al.add(NumberToTextConverter.toText(c.getNumericCellValue()));
							}
						}
					}

				}

			}

		}
		return al;
	}
}

//public static void main(String[] args) {}}