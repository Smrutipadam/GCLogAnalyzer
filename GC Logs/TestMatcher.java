package GarbageCollection;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.hql.ast.origin.hql.parse.HQLParser.mainEntityPersisterReference_return;

public class TestMatcher
{
	
	public static void main( String[] args )
	{
		StringBuffer strBuffer = new StringBuffer( "hello" );
		strBuffer.append( '1' );
		System.out.println(strBuffer.charAt( 2 ));
		System.out.println(strBuffer.toString());
		
		
		
	}
}
