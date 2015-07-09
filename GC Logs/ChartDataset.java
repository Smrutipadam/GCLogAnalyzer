package GarbageCollection;

import java.util.List;

import org.jfree.data.category.DefaultCategoryDataset;

public class ChartDataset
{

	public DefaultCategoryDataset createDataset( List<GCDataStructure> gcDataStructures, DefaultCategoryDataset defaultCategoryDataset )
	{

		for ( GCDataStructure gcDataStructure : gcDataStructures )
		{

			String youngSpaceBeforeGC = gcDataStructure.getYoungSpaceBeforeGC();
			defaultCategoryDataset.addValue( Integer.valueOf( youngSpaceBeforeGC == null ? "0" : youngSpaceBeforeGC ), "Before Minor GC", gcDataStructure.getTimeStamp() );
			String youngSpaceAfterGC = gcDataStructure.getYoungSpaceAfterGC();
			defaultCategoryDataset.addValue( Integer.valueOf( youngSpaceAfterGC == null ? "0" : youngSpaceAfterGC ), "After Minor GC", gcDataStructure.getTimeStamp() );
			String tenuredspaceBeforeGC = gcDataStructure.getTenuredspaceBeforeGC();
			defaultCategoryDataset.addValue( Integer.valueOf( tenuredspaceBeforeGC == null ? "0" : tenuredspaceBeforeGC ), "Befor Major GC", gcDataStructure.getTimeStamp() );
			String tenuredspaceAfterGC = gcDataStructure.getTenuredspaceAfterGC();
			defaultCategoryDataset.addValue( Integer.valueOf( tenuredspaceAfterGC == null ? "0" : tenuredspaceAfterGC ), "After Major GC", gcDataStructure.getTimeStamp() );
		}

		return defaultCategoryDataset;
	}

}
