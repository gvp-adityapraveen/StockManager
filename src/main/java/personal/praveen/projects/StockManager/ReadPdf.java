package personal.praveen.projects.StockManager;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

public class ReadPdf {
	
	private final static String PASSWORD = "******";
	private DatabaseOps dbInstance = null;
	
	private DatabaseOps getDBInstance(){
		if(dbInstance == null) {
			dbInstance = new DatabaseOps();
		}
		return dbInstance;
	}

	public void ParsePdf(String fileNameWithPath) throws InvalidPasswordException, IOException, ClassNotFoundException, SQLException {
		try (PDDocument document = PDDocument.load(new File(fileNameWithPath),PASSWORD)) {
			PDFTextStripperByArea stripper = new PDFTextStripperByArea();
			stripper.setSortByPosition(true);

			PDFTextStripper tStripper = new PDFTextStripper();
			String pdfFileInText = tStripper.getText(document);
			pdfFileInText = pdfFileInText.substring(pdfFileInText.lastIndexOf("(Rs.)") + 5);
		    System.out.println("Text:" + pdfFileInText);

			// split by whitespace
			String lines[] = pdfFileInText.split("\\r?\\n");
			for (int index = lines.length-1;index>0;index--) {
				String line = lines[index];
				String columns[] = line.split(" ");
				int columnsLength = columns.length;
				// Create a Record Entity Out of this
				RecordEntity record = new RecordEntity();
				record.setBuy(columns[3]);
				//record.setNameOfSecurity(columns[4]);
				record.setShareCode(columns[columnsLength-8]);
				//record.setTradedTime(columns[columnsLength-5]);
				record.setQuantity(columns[columnsLength-3]);
				record.setPrice(columns[columnsLength-2]);
				record.setTotalTradedValue(columns[columnsLength-1]);
				String nameOfSecurity = columns[4];
				for(int i=5;i<= columnsLength-9;i++) {
					nameOfSecurity += " "+columns[i];
				}
				record.setNameOfSecurity(nameOfSecurity);
				updateRecordInDB(record);
			}

		}

	}
	
	private void updateRecordInDB(RecordEntity record) throws ClassNotFoundException, SQLException {
		dbInstance = getDBInstance();
		Connection con = dbInstance.connectToDB();
		Statement st = con.createStatement();
		
		// Inserting into StockName table
		//System.out.println("SQL:"+record.getShareCode());
		String sqlStatement = "select * from stockname where stockCode = "+"'"+record.getShareCode()+"'";
		//System.out.println("SQL:"+sqlStatement);
		ResultSet rs = st.executeQuery(sqlStatement);
		if(!rs.next()) {
			System.out.println(record.getNameOfSecurity());
			st.execute("insert into stockname(stockCode,stockName)values("+"'"+record.getShareCode()+"',"+"'"+record.getNameOfSecurity()+"')");
		}
		
		//Inserting into BUY 
		String tableName;
		if(record.isBuy()) {
			tableName = "buy";
		}else
		{
			tableName = "sell";
		}
		
		String sql = "insert into "+tableName+" (shareSymbol,Quantity,Price,TradedValue)"
				+"values("+"'"+record.getShareCode()+"',"+
				record.getQuantity()+","+record.getPrice()+
				","+record.getTotalTradedValue()+")";
		st.execute(sql);
	}
}
