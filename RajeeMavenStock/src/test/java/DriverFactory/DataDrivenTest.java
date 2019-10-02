package DriverFactory;

import commonFunLibrary.StockAccounting;
import utilities.ExcelFileUtils;

public class DataDrivenTest {

	public static void main(String[] args) throws Throwable 
	{
		StockAccounting ra=new StockAccounting();
		ExcelFileUtils Excel=new ExcelFileUtils();
		ra.applaunch("Http://webapp.qedge.com");
		ra.appLogin("admin", "master");
		
		for(int i=1; i<=Excel.rowCount("Sheet1"); i++)
		{
			String sName=Excel.getData("Sheet1", i, 0);
			String address=Excel.getData("Sheet1", i, 1);
			String city=Excel.getData("Sheet1", i, 2);
			String country=Excel.getData("Sheet1", i, 3);
			String cP=Excel.getData("Sheet1", i, 4);
			String phNo=Excel.getData("Sheet1", i, 5);
			String eMail=Excel.getData("Sheet1", i, 6);
			String mNo=Excel.getData("Sheet1", i, 7);
			String Notes=Excel.getData("Sheet1", i, 8);
			
			String results=ra.SupplierCreation(sName, address, city, country, cP, phNo, eMail, mNo, Notes);
			Excel.setData("Sheet1", i, 9, results);
		}
		
		ra.appLogout();
		ra.appClose();
	}

}
