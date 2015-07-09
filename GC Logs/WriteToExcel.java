package GarbageCollection;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteToExcel
{
	private XSSFWorkbook workBook;
	private XSSFSheet workSheet;
	HashMap<String, Object[]> data;

	String fileName;
	List<GCDataStructure> gcList;

	String[] headers =
	{ "GCTime", "GCType", "GC Time(Launch Of JVM)", "YoungGen?", "SpaceBeforeGC", "SpaceAfterGC", "CommittedSpace", "TimeForYoung", "GC Time(Launch Of JVM)", "OldGen?", "SpaceBeforeGC", "SpaceAfterGC", "CommittedSpace", "TimeForTenured" };

	public WriteToExcel( List<GCDataStructure> gcList, String fileName ) throws IOException
	{

		this.gcList = gcList;
		this.fileName = fileName;
	}

	public void write() throws IOException
	{
		FileOutputStream out = new FileOutputStream( new File( fileName ) );
		workBook = new XSSFWorkbook();
		
		
		workSheet = workBook.createSheet( "GC Logs" );
		
		writeHeader();

		int rowNum = 1;
		for ( GCDataStructure data : gcList )
		{
			XSSFRow row = workSheet.createRow( rowNum++ );
			writeGCData( data, row );
		}
		workBook.write( out );
		out.flush();
		out.close();
		System.out.println( "File Created Successfully" );
	}

	public void writeHeader()
	{
		XSSFRow row = workSheet.createRow( 0 );
		int colNo = 0;
		for ( String header : headers )
		{
			XSSFFont font = workBook.createFont();
			font.setBold( true );
			
			XSSFCell cell = row.createCell( colNo );
			XSSFCellStyle style = workBook.createCellStyle();
			style.setFont( font );
			style.setFillForegroundColor( HSSFColor.BLUE_GREY.index );
			workSheet.setDefaultColumnWidth( 23 );
			cell.setCellStyle( style );
			
			cell.setCellValue( header );
			colNo++;
		}
	}

	public void writeGCData( GCDataStructure data, XSSFRow row )
	{
		int colNo = 0;
		row.createCell( colNo++ ).setCellValue( data.getTimeStamp() );
		String gcType = data.getGcType();
		if ( gcType != null && gcType.equals( "GC" ) )
		{
			row.createCell( colNo++ ).setCellValue( "Minor" );
		}
		else if ( gcType != null && gcType.equals( "Full GC" ) )
		{
			row.createCell( colNo++ ).setCellValue( "Major" );
		}

		row.createCell( colNo++ ).setCellValue( data.getYoungGCTimeStamp() );
		row.createCell( colNo++ ).setCellValue( data.getYoungCollection() );
		row.createCell( colNo++ ).setCellValue( data.getYoungSpaceBeforeGC() );
		row.createCell( colNo++ ).setCellValue( data.getYoungSpaceAfterGC() );
		row.createCell( colNo++ ).setCellValue( data.getYoungTotalSpace() );
		row.createCell( colNo++ ).setCellValue( data.getTimeTakenForYoungGC() );

		row.createCell( colNo++ ).setCellValue( data.getOldGCTimeStamp() );
		row.createCell( colNo++ ).setCellValue( data.getTenuredCollection() );
		row.createCell( colNo++ ).setCellValue( data.getTenuredspaceBeforeGC() );
		row.createCell( colNo++ ).setCellValue( data.getTenuredspaceAfterGC() );
		row.createCell( colNo++ ).setCellValue( data.getTenuredtotalSpace() );
		row.createCell( colNo++ ).setCellValue( data.getTimeTakenForTenuredGC() );
	}

}