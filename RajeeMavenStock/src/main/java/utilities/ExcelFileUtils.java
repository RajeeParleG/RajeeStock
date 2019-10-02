package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtils
{
Workbook wb;

public ExcelFileUtils() throws Throwable
{
	FileInputStream fis=new FileInputStream("C:\\Users\\rajesh.k\\workspace\\RajeeMavenStock\\TestInputs\\InputSheet.xlsx");
	wb=WorkbookFactory.create(fis);
}

//row count
public int rowCount(String sheetname)
{
 return wb.getSheet(sheetname).getLastRowNum();
 }

//col count
public int colCount(String sheetname, int row)
    {
	return wb.getSheet(sheetname).getRow(row).getLastCellNum();
	}

//reading the data from excel file

@SuppressWarnings("deprecation")
public String getData(String sheetname,int row,int column)
{

	String data="";
	if(wb.getSheet(sheetname).getRow(row).getCell(column).getCellType()==Cell.CELL_TYPE_NUMERIC)
	{
		int celldata=(int)wb.getSheet(sheetname).getRow(row).getCell(column).getNumericCellValue();
	data=String.valueOf(celldata);
	}else
	{
		data=wb.getSheet(sheetname).getRow(row).getCell(column).getStringCellValue();
	}
	
	return data;
}

//Writing data into excel pass or fail and not executed

public void setData(String sheetname,int row,int column,String status) throws Throwable
{
	Sheet sh=wb.getSheet(sheetname);
	Row rownum=sh.getRow(row);
	Cell cell=rownum.createCell(column);
	cell.setCellValue(status);
	if(status.equalsIgnoreCase("PASS"))
	{
		//Create Cell Style
		CellStyle style=wb.createCellStyle();
		//Create Font
		Font font=wb.createFont();
		//Apply color to the text
		font.setColor(IndexedColors.GREEN.index);
		//apply bold to the text
		font.setBold(true);
		//Set Font
		style.setFont(font);
		//set cell style
		rownum.getCell(column).setCellStyle(style);
	}else
		if(status.equalsIgnoreCase("FAIL"))
		{
			//Create Cell Style
			CellStyle style=wb.createCellStyle();
			//Create Font
			Font font=wb.createFont();
			//Apply color to the text
			font.setColor(IndexedColors.RED.index);
			//apply bold to the text
			font.setBold(true);
			//Set Font
			style.setFont(font);
			//set cell style
			rownum.getCell(column).setCellStyle(style);
		}else
			if(status.equalsIgnoreCase("Not Executed"))
			{
				//Create Cell Style
				CellStyle style=wb.createCellStyle();
				//Create Font
				Font font=wb.createFont();
				//Apply color to the text
				font.setColor(IndexedColors.BLUE.index);
				//apply bold to the text
				font.setBold(true);
				//Set Font
				style.setFont(font);
				//set cell style
				rownum.getCell(column).setCellStyle(style);	
		
			}
	
	FileOutputStream fos=new FileOutputStream("C:\\Users\\rajesh.k\\workspace\\RajeeMavenStock\\TestOutput\\outputSheet.xlsx");
	wb.write(fos);
	fos.close();
}


public static void main(String[] args) throws Throwable 
{
	ExcelFileUtils excel=new ExcelFileUtils();
	
	System.out.println(excel.rowCount("Sheet1"));
	
	System.out.println(excel.colCount("Sheet1", 1));
	
	System.out.println(excel.getData("Sheet1", 1, 1));
	
	excel.setData("Sheet1", 1, 2, "Fail");
	excel.setData("Sheet1", 2, 2, "Not Executed");
	excel.setData("Sheet1", 3, 2, "Pass");
}


}
