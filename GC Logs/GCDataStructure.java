package GarbageCollection;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class GCDataStructure
{
	private String timeStamp;
	private String gcType;
	private String youngGCTimeStamp;
	private String youngCollection;
	private String youngSpaceBeforeGC;
	private String youngSpaceAfterGC;
	private String youngTotalSpace;
	private String timeTakenForYoungGC;
	private String oldGCTimeStamp;
	private String tenuredCollection;
	private String tenuredspaceBeforeGC;
	private String tenuredspaceAfterGC;
	private String tenuredtotalSpace;
	private String timeTakenForTenuredGC;

	public void extract1( Matcher matcher1 )
	{
		int groupCount = matcher1.groupCount();

		for ( int i = 1; i <= groupCount; i++ )
		{
			switch( i )
			{
			case 1:
				timeStamp = matcher1.group( i );
				break;
			case 2:
				gcType = matcher1.group( i );
				break;
			case 3:
				youngGCTimeStamp = matcher1.group( i );
				break;
			case 4:
				youngCollection = matcher1.group( i );
				break;
			case 5:
				youngSpaceBeforeGC = matcher1.group( i );
				break;
			case 6:
				youngSpaceAfterGC = matcher1.group( i );
				break;
			case 7:
				youngTotalSpace = matcher1.group( i );
				break;
			case 8:
				timeTakenForYoungGC = matcher1.group( i );
				break;
			default:
				throw new RuntimeException();
			}
		}
	}

	public void extract2( Matcher matcher1 )
	{
		int groupCount = matcher1.groupCount();

		for ( int i = 1; i <= groupCount; i++ )
		{
			switch( i )
			{
			case 1:
				timeStamp = matcher1.group( i );
				break;
			case 2:
				gcType = matcher1.group( i );
				break;
			case 3:
				youngGCTimeStamp = matcher1.group( i );
				break;
			case 4:
				youngCollection = matcher1.group( i );
				break;
			case 5:
				youngSpaceBeforeGC = matcher1.group( i );
				break;
			case 6:
				youngSpaceAfterGC = matcher1.group( i );
				break;
			case 7:
				youngTotalSpace = matcher1.group( i );
				break;
			case 8:
				timeTakenForYoungGC = matcher1.group( i );
				break;
			case 9:
				oldGCTimeStamp = matcher1.group( i );
				break;
			case 10:
				tenuredCollection = matcher1.group( i );
				break;
			case 11:
				tenuredspaceBeforeGC = matcher1.group( i );
				break;
			case 12:
				tenuredspaceAfterGC = matcher1.group( i );
				break;
			case 13:
				tenuredtotalSpace = matcher1.group( i );
				break;
			case 14:
				timeTakenForTenuredGC = matcher1.group( i );
				break;
			default:
				throw new RuntimeException();
			}
		}
	}

	public void extract3( Matcher matcher1 )
	{
		int groupCount = matcher1.groupCount();

		for ( int i = 1; i <= groupCount; i++ )
		{
			switch( i )
			{
			case 1:
				timeStamp = matcher1.group( i );
				break;
			case 2:
				gcType = matcher1.group( i );
				break;
			case 3:
				oldGCTimeStamp = matcher1.group( i );
				break;
			case 4:
				tenuredCollection = matcher1.group( i );
				break;
			case 5:
				tenuredspaceBeforeGC = matcher1.group( i );
				break;
			case 6:
				tenuredspaceAfterGC = matcher1.group( i );
				break;
			case 7:
				tenuredtotalSpace = matcher1.group( i );
				break;
			case 8:
				timeTakenForTenuredGC = matcher1.group( i );
				break;
			default:
				throw new RuntimeException();
			}
		}
	}

	public void extract4( Matcher matcher1 )
	{
		int groupCount = matcher1.groupCount();

		for ( int i = 1; i <= groupCount; i++ )
		{
			switch( i )
			{
			case 1:
				timeStamp = matcher1.group( i );
				break;
			case 2:
				gcType = matcher1.group( i );
				break;
			case 3:
				youngCollection = matcher1.group( i );
				break;
			case 4:
				youngSpaceBeforeGC = matcher1.group( i );
				break;
			case 5:
				youngSpaceAfterGC = matcher1.group( i );
				break;
			case 6:
				youngTotalSpace = matcher1.group( i );
				break;
			case 7:
				timeTakenForYoungGC = matcher1.group( i );
				break;
			default:
				throw new RuntimeException();
			}
		}
	}

	public void extract5( Matcher matcher1 )
	{
		int groupCount = matcher1.groupCount();

		for ( int i = 1; i <= groupCount; i++ )
		{
			switch( i )
			{
			case 1:
				timeStamp = matcher1.group( i );
				break;
			case 2:
				gcType = matcher1.group( i );
				break;
			case 3:
				youngCollection = matcher1.group( i );
				break;
			case 4:
				youngSpaceBeforeGC = matcher1.group( i );
				break;
			case 5:
				youngSpaceAfterGC = matcher1.group( i );
				break;
			case 6:
				youngTotalSpace = matcher1.group( i );
				break;
			case 7:
				tenuredCollection = matcher1.group( i );
				break;
			case 8:
				tenuredspaceBeforeGC = matcher1.group( i );
				break;
			case 9:
				tenuredspaceAfterGC = matcher1.group( i );
				break;
			case 10:
				tenuredtotalSpace = matcher1.group( i );
				break;
			default:
				throw new RuntimeException();
			}
		}
	}

	public String getTimeStamp()
	{
		return timeStamp;
	}

	public void setTimeStamp( String timeStamp )
	{
		this.timeStamp = timeStamp;
	}

	public String getGcType()
	{
		return gcType;
	}

	public void setGcType( String gcType )
	{
		this.gcType = gcType;
	}

	public String getYoungGCTimeStamp()
	{
		return youngGCTimeStamp;
	}

	public void setYoungGCTimeStamp( String youngGCTimeStamp )
	{
		this.youngGCTimeStamp = youngGCTimeStamp;
	}

	public String getYoungCollection()
	{
		return youngCollection;
	}

	public void setYoungCollection( String youngCollection )
	{
		this.youngCollection = youngCollection;
	}

	public String getYoungSpaceBeforeGC()
	{
		return youngSpaceBeforeGC;
	}

	public void setYoungSpaceBeforeGC( String youngSpaceBeforeGC )
	{
		this.youngSpaceBeforeGC = youngSpaceBeforeGC;
	}

	public String getYoungSpaceAfterGC()
	{
		return youngSpaceAfterGC;
	}

	public void setYoungSpaceAfterGC( String youngSpaceAfterGC )
	{
		this.youngSpaceAfterGC = youngSpaceAfterGC;
	}

	public String getYoungTotalSpace()
	{
		return youngTotalSpace;
	}

	public void setYoungTotalSpace( String youngTotalSpace )
	{
		this.youngTotalSpace = youngTotalSpace;
	}

	public String getTimeTakenForYoungGC()
	{
		return timeTakenForYoungGC;
	}

	public void setTimeTakenForYoungGC( String timeTakenForYoungGC )
	{
		this.timeTakenForYoungGC = timeTakenForYoungGC;
	}

	public String getOldGCTimeStamp()
	{
		return oldGCTimeStamp;
	}

	public void setOldGCTimeStamp( String oldGCTimeStamp )
	{
		this.oldGCTimeStamp = oldGCTimeStamp;
	}

	public String getTenuredCollection()
	{
		return tenuredCollection;
	}

	public void setTenuredCollection( String tenuredCollection )
	{
		this.tenuredCollection = tenuredCollection;
	}

	public String getTenuredspaceBeforeGC()
	{
		return tenuredspaceBeforeGC;
	}

	public void setTenuredspaceBeforeGC( String tenuredspaceBeforeGC )
	{
		this.tenuredspaceBeforeGC = tenuredspaceBeforeGC;
	}

	public String getTenuredspaceAfterGC()
	{
		return tenuredspaceAfterGC;
	}

	public void setTenuredspaceAfterGC( String tenuredspaceAfterGC )
	{
		this.tenuredspaceAfterGC = tenuredspaceAfterGC;
	}

	public String getTenuredtotalSpace()
	{
		return tenuredtotalSpace;
	}

	public void setTenuredtotalSpace( String tenuredtotalSpace )
	{
		this.tenuredtotalSpace = tenuredtotalSpace;
	}

	public String getTimeTakenForTenuredGC()
	{
		return timeTakenForTenuredGC;
	}

	public void setTimeTakenForTenuredGC( String timeTakenForTenuredGC )
	{
		this.timeTakenForTenuredGC = timeTakenForTenuredGC;
	}

}
