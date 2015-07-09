package GarbageCollection;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.Dataset;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.XYBarDataset;
import org.jfree.data.xy.XYDataItem;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class GCChartViewer
{

	JFrame frame;
	JPanel panel;
	JComboBox<String> comboBox;
	JLabel label;
	List<GCDataStructure> gcList;
	String filePath;

	GCChartViewer( DefaultCategoryDataset dataset )
	{
		frameUI( dataset );
	}

	public void frameUI( DefaultCategoryDataset dataset )
	{

		frame = new JFrame( "GC Viewer" );
		frame.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
		frame.setSize( 800, 700 );
		frame.getContentPane().setLayout( null );
		frame.setResizable( false );
		panel = new JPanel();
		//
		label = new JLabel( "Select GC Type:" );
		label.setBounds( 10, 10, 400, 25 );
		frame.getContentPane().add( label );
		//
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.addItem( "Select.." );
		comboBox.addItem( "Parallel GC" );
		comboBox.addItem( "Serial GC" );
		comboBox.addItem( "CMS GC" );
		comboBox.addItem( "Parallel Old GC" );
		comboBox.setEditable( true );
		comboBox.setBounds( 110, 10, 140, 25 );
		frame.getContentPane().add( comboBox );
		JButton selectFileButton = new JButton( "Select File" );
		selectFileButton.setBounds( 260, 10, 100, 25 );
		frame.getContentPane().add( selectFileButton );
		final JFileChooser chooseFile = new JFileChooser();
		selectFileButton.addActionListener( new ActionListener()
		{

			public void actionPerformed( ActionEvent evt )
			{
				chooseFile.showOpenDialog( null );
				//System.out.println( chooseFile.getSelectedFile().toString() );
				filePath = chooseFile.getSelectedFile().toString();
				System.out.println( filePath );

			}
		} );

		//DefaultCategoryDataset dataset = createDataset( gcDataStructures );

		JFreeChart chart = ChartFactory.createBarChart3D( "Gc Viewer", "Gc Time -->", "Memory Usage --> ", dataset );
		//chart.getRenderingHints();
	
		CategoryPlot categoryPlot = chart.getCategoryPlot();
		BarRenderer renderer = (BarRenderer) categoryPlot.getRenderer();
		
		//renderer.set
		categoryPlot.setDataset( dataset );
		
		ChartPanel cp = new ChartPanel( chart );
		cp.createToolTip();
		cp.getAutoscrolls();
		cp.setRangeZoomable(true);
		
		panel.add( cp );
		panel.setBounds( 20, 50, 750, 900 );
		panel.setVisible( true );
		frame.add( panel );
		frame.setVisible( true );
	}

}
