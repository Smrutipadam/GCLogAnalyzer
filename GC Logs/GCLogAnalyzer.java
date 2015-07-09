package GarbageCollection;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.jfree.data.category.DefaultCategoryDataset;

public class GCLogAnalyzer
{

	HashMap<String, String> patternMap = new HashMap<String, String>();
	HashMap<String, String> patternGroupMap = new HashMap<String, String>();
	HashMap<String, ArrayList<String>> hash = new HashMap<String, ArrayList<String>>();
	Set<String> patternMapSet;
	Iterator<String> keySetIterator;
	GCChartViewer gcChartViewer;
	static DefaultCategoryDataset dataset;

	public static void main( String[] args ) throws IOException
	{

		GCLogAnalyzer readLogs = new GCLogAnalyzer();
		readLogs.initializeSerialGC();
		readLogs.initializeParallelGC();
		DefaultCategoryDataset defaultCategoryDataset = new DefaultCategoryDataset();
		dataset=defaultCategoryDataset;
		List<GCDataStructure> gcList = readLogs.readFile();
		//WriteToExcel excel = new WriteToExcel( gcList, "GCLogs.xlsx" );
		ChartDataset dataForChart = new ChartDataset();
		dataset = dataForChart.createDataset( gcList,defaultCategoryDataset );

	}

	public void initializeSerialGC()
	{

		//0.041: 
		String pattern = "[0-9]*" + "."+ "[0-9]*" + ":" + " ";
		//[GC 0.046: [Full GC  0.101: 
		String patternOne = "\\[" + "(GC|Full GC)" + " ";// + pattern;
		//896K->64K(960K),
		String patternTwo = "([0-9]*)" + "[KkMm]" +"->" + "([0-9]*)" + "[KkMm]" + "\\(" + "([0-9]*)" + "[KkMm]" + "\\)";
		String patternSeven = "[0-9]*" + "[KkMm]" + "->" + "[0-9]*"  + "[KkMm]" + "\\(" + "[0-9]*" + "[KkMm]" + "\\)";
		// 0.0026746 secs
		String patternTwoNew = " " + "([0-9]*.[0-9]*)" + " " + "secs";
		String patternEight = " " + "[0-9]*.[0-9]*" + " " + "secs";
		//[Tenured: 3776K->3799K(3800K), 0.0117668 secs]
		String patternFour = "\\[" + "(Tenured)" + ":" + " " + patternTwo + "," + patternTwoNew + "\\]";
		//[Perm : 31K->26K(12288K)]
		String patternFive = "\\[Perm" + " " + ":" + " " + "([0-9]*)" + "K" + "->" + "([0-9]*)" + "K" + "\\(" + "([0-9]*)" + "K" + "\\)" + "\\]";
		String patternSix = "([0-9]*)" + "K" + "->" + "([0-9]*)" + "K" + "\\(" + "([0-9]*)" + "K" + "\\)" + "," + " " + "([0-9]*.[0-9]*)" + " " + "secs" + "\\]";
		//[DefNew: 896K->64K(960K), 0.0026746 secs]
		final String PATTERN_ONE = ".*" + "([0-9]+\\.[0-9]+)" + ":" + " " + patternOne + "([0-9]*.[0-9]*)" + ":" + " " + "\\[" + "(DefNew)" + ".*:" + " " + patternTwo + "," + patternTwoNew + "\\]" + " " + patternSeven + "," + patternEight + ".*";
		final String PATTERN_TWO = ".*" + "([0-9]+\\.[0-9]+)" + ":" + " " + patternOne + "([0-9]*.[0-9]*)" + ":" + " " + "\\[" + "(DefNew)" + ".*:" + " " + patternTwo + "," + patternTwoNew + "\\]" + "([0-9]*.[0-9]*)" + ":" + " " + patternFour + " " + patternSeven + ".*";
		final String PATTERN_THREE = ".*" + "([0-9]+\\.[0-9]+)" + ":" + " " + patternOne + "([0-9]*.[0-9]*)" + ":" + " " + patternFour + ".*";
		patternMap.put( PATTERN_ONE, "SP1" );
		patternMap.put( PATTERN_TWO, "SP2" );
		patternMap.put( PATTERN_THREE, "SP3" );

	}

	/**Initializes various pattern that could be generated for Parallel GC
	  
	 */
	public void initializeParallelGC()
	{
		String pattern = "[0-9]*" +"." +"[0-9]*" + ":" + " ";
		//[GC 0.046: [Full GC  0.101: 
		String patternOne = "\\[" + "(GC|Full GC)" + " ";// + pattern;
		//896K->64K(960K),
		String patternTwo = "([0-9]*)" + "[KkMm]" + "->" + "([0-9]*)" + "[KkMm]" + "\\(" + "([0-9]*)" + "[KkMm]" + "\\)";
		String patternSeven = "[0-9]*[KkMm]" + "->" + "[0-9]*[KkMm]" + "\\(" + "[0-9]*[KkMm]" + "\\)";
		// 0.0026746 secs
		String patternTwoNew = " " + "([0-9]*.[0-9]*)" + " " + "secs";
		String patternEight = " " + "[0-9]*.[0-9]*" + " " + "secs";
		//[Tenured: 3776K->3799K(3800K), 0.0117668 secs]
		String patternFour = "\\[" + "(PSOldGen)" + ":" + " " + patternTwo + "\\]";
		//[Perm : 31K->26K(12288K)]
		String patternFive = "\\[PSPermGen" + " " + ":" + " " + "([0-9]*)" + "K" + "->" + "([0-9]*)" + "K" + "\\(" + "([0-9]*)" + "K" + "\\)" + "\\]";
		String patternSix = "([0-9]*)" + "K" + "->" + "([0-9]*)" + "K" + "\\(" + "([0-9]*)" + "K" + "\\)" + "," + " " + "([0-9]*.[0-9]*)" + " " + "secs" + "\\]";
		final String PATTERN_ONE = "([0-9]*\\.?[0-9]*)" + ":" + " " + patternOne + "\\[" + "(PSYoungGen)" + ".*:" + " " + patternTwo + "\\]" + " " + patternSeven + "," + patternEight + ".*";
		final String PATTERN_TWO = "([0-9]*\\.?[0-9]*)" + ":" + " " + patternOne + "\\[" + "(PSYoungGen)" + ".*:" + " " + patternTwo + "\\]" + " " + patternFour + ".*";
		final String PATTERN_THREE = "([0-9]*\\.?[0-9]*)" + ":" + " " + patternOne + "([0-9]*.[0-9]*)" + ":" + " " + patternFour + ".*";
		patternMap.put( PATTERN_ONE, "PP1" );
		patternMap.put( PATTERN_TWO, "PP2" );
		patternMap.put( PATTERN_THREE, "PP3" );
		//System.out.println(patternMap);
	}

	public void initializeOldParallelGC()
	{
		String pattern = "[0-9]*.[0-9]*" + ":" + " ";
		//[GC 0.046: [Full GC  0.101: 
		String patternOne = "\\[" + "(GC|Full GC)" + " ";// + pattern;
		//896K->64K(960K),
		String patternTwo = "([0-9]*[KkMm])" + "->" + "([0-9]*[KkMm])" + "\\(" + "([0-9]*[KkMm])" + "\\)";
		String patternSeven = "[0-9]*[KkMm]" + "->" + "[0-9]*[KkMm]" + "\\(" + "[0-9]*[KkMm]" + "\\)";
		// 0.0026746 secs
		String patternTwoNew = " " + "([0-9]*.[0-9]*)" + " " + "secs";
		String patternEight = " " + "[0-9]*.[0-9]*" + " " + "secs";
		//[Tenured: 3776K->3799K(3800K), 0.0117668 secs]
		String patternFour = "\\[" + "(ParOldGen)" + ":" + " " + patternTwo + "\\]";
		//[Perm : 31K->26K(12288K)]
		String patternFive = "\\[PSPermGen" + " " + ":" + " " + "([0-9]*)" + "K" + "->" + "([0-9]*)" + "K" + "\\(" + "([0-9]*)" + "K" + "\\)" + "\\]";
		String patternSix = "([0-9]*)" + "K" + "->" + "([0-9]*)" + "K" + "\\(" + "([0-9]*)" + "K" + "\\)" + "," + " " + "([0-9]*.[0-9]*)" + " " + "secs" + "\\]";
		final String PATTERN_ONE = ".*" + "([0-9]+\\.[0-9]+)" + ":" + " " + patternOne + "\\[" + "(PSYoungGen)" + ".*:" + " " + patternTwo + "\\]" + " " + patternSeven + "," + patternEight + ".*";
		//0.087: [Full GC [PSYoungGen: 256K->211K(1792K)] [PSOldGen: 1238K->1279K(4096K)] 1494K->1491K(5888K) [PSPermGen: 1728K->1728K(12288K)], 0.0064541 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
		final String PATTERN_TWO = ".*" + "([0-9]+\\.[0-9]+)" + ":" + " " + patternOne + "\\[" + "(PSYoungGen)" + ".*:" + " " + patternTwo + "\\]" + " " + patternFour + ".*";
		final String PATTERN_THREE = ".*" + "([0-9]+\\.[0-9]+)" + ":" + " " + patternOne + "([0-9]*.[0-9]*)" + ":" + " " + patternFour + ".*";
		patternMap.put( PATTERN_ONE, "OP1" );
		patternMap.put( PATTERN_TWO, "OP2" );
		patternMap.put( PATTERN_THREE, "OP3" );
		//System.out.println(patternMap);
	}

	public GCDataStructure parseLine( String test )
	{
		GCDataStructure gcData = new GCDataStructure();
		patternMapSet = patternMap.keySet();
		keySetIterator = patternMapSet.iterator();
		while ( keySetIterator.hasNext() )
		{
			String key = keySetIterator.next();
			Pattern pattern1 = Pattern.compile( key );
			Matcher matcher = pattern1.matcher( test );

			if ( matcher.matches() )
			{

				if ( patternMap.get( key ).equals( "SP1" ) )
				{
					gcData.extract1( matcher );
				}
				else if ( patternMap.get( key ).equals( "SP2" ) )
				{
					gcData.extract2( matcher );
				}

				else if ( patternMap.get( key ).equals( "SP3" ) )
				{
					gcData.extract3( matcher );
				}
				else if ( patternMap.get( key ).equals( "PP1" ) )
				{
					gcData.extract4( matcher );
				}

				else if ( patternMap.get( key ).equals( "PP2" ) )
				{
					gcData.extract5( matcher );
				}
				else if ( patternMap.get( key ).equals( null ) )
				{

				}

			}
		}
		return gcData;
	}

	public List<GCDataStructure> readFile()
	{
		gcChartViewer = new GCChartViewer( dataset );
		//gcChartViewer.frameUI();
		//System.out.println( "Please enter the file path!" );
		//Scanner scanFileName = new Scanner( System.in );
		//String fileName = scanFileName.nextLine();
		String fileName = gcChartViewer.filePath;
		//System.out.println( fileName );
		List<GCDataStructure> listOfGCs = new ArrayList<>();
		while ( fileName == null )
		{
			fileName = gcChartViewer.filePath;
			if ( fileName != null )
			{
				try
				{
					FileReader fir = new FileReader( fileName );
					BufferedReader br = new BufferedReader( fir );
					String line = null;
					try
					{
						while ( ( line = br.readLine() ) != null )
						{
							listOfGCs.add( parseLine( line ) );
						}
					}
					catch ( IOException e )
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				catch ( FileNotFoundException e )
				{
					System.out.println( "File not Found!!" );

				}
			}
		}

		return listOfGCs;
	}
}
