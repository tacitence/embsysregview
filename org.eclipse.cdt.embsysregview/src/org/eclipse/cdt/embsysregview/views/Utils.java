package org.eclipse.cdt.embsysregview.views;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
	/**
	 * Converts from big endian to little endian and the other way round
	 * 
	 * @param value 32 bit big endian value
	 * @return 32 bit little endian value
	 * 
	 * In:  0xAABBCCDD
	 * Out: 0xDDCCBBAA
	 * 
	 */
	public static long convertbig2little(long value) {
		long a,b,c,d;
		
		a=value>>24;
		b=(value & (~0xFF00FFFFL))>>8;
		c=(value & (~0xFFFF00FFL))<<8;
		d=(value & (~0xFFFFFF00L))<<24;

		return( a | b | c | d );
	}
	
	private static Matcher getLineMatcher(String fulltext)
	{
		// Compile the pattern
	    String patternStr = "^(.*)$";
	    Pattern pattern = Pattern.compile(patternStr, Pattern.MULTILINE);
	    return pattern.matcher(fulltext);
	}
	
	public static String getTextLine(int number, String fulltext)
	{
		Matcher matcher = getLineMatcher(fulltext);
	    
	    for(int i=1;i<=number;i++)
	    {
	    	if(matcher.find() && i==number)
	    		return  matcher.group(1);
	    }
	    return "";
	}
	
	public static String getFirstNotEmptyTextLine(String fulltext)
	{
		Matcher matcher = getLineMatcher(fulltext);
	    
	    while(matcher.find())
	    {
	    	if(!matcher.group(1).equals(""))
	    		return matcher.group(1);
	    }
	    return "";
	}
	
	public static int countTextLines(String fulltext)
	{
		int i=0;
		
		Matcher matcher = getLineMatcher(fulltext);
		
	    while(matcher.find())
	    	i++;
	    
	    return i;
	}
	
	public static int getBitFromValue(int bitnr, long value)
	{
		return (int) ((value>>bitnr)%2);
	}
}
