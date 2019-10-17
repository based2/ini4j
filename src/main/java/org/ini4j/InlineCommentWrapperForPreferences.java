package org.ini4j;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;
import java.util.prefs.AbstractPreferences;
import java.util.prefs.BackingStoreException;

public class InlineCommentWrapperForPreferences extends IniPreferences
{
	private final char commentToken;

	public InlineCommentWrapperForPreferences(Ini ini,char commentToken)
	{
		super(ini);
		this.commentToken=commentToken;
	}

	public InlineCommentWrapperForPreferences(Reader input,char commentToken) throws IOException
	{
		super(input);
		this.commentToken=commentToken;
	}

	public InlineCommentWrapperForPreferences(InputStream input,char commentToken) throws IOException
	{
		super(input);
		this.commentToken=commentToken;
	}

	public InlineCommentWrapperForPreferences(URL input,char commentToken) throws IOException
	{
		super(input);
		this.commentToken=commentToken;
	}

	@Override
	public String get(String key, String def)
	{
		java.lang.String result = def;
		try {

			java.lang.String str=get(key,null);
			int index=str.indexOf(commentToken);
			if(index>-1)
				str=str.substring(0,index).trim();
			result=str;
		} catch (NumberFormatException e) {
			// Ignoring exception causes specified default to be returned
		}

		return (result==null ? def : result);
	}

	@Override
	public boolean getBoolean(String key, boolean def)
	{
		boolean result = def;
		try {

			java.lang.String str=get(key,null);
			int index=str.indexOf(commentToken);
			if(index>-1)
				str=str.substring(0,index).trim();
			if (str != null)
				result = Boolean.getBoolean(str);
		} catch (NumberFormatException e) {
			// Ignoring exception causes specified default to be returned
		}

		return result;
	}

	@Override
	public double getDouble(String key, double def)
	{
		double result = def;
		try {

			java.lang.String str=get(key,null);
			int index=str.indexOf(commentToken);
			if(index>-1)
				str=str.substring(0,index).trim();
			if (str != null)
				result = Double.parseDouble(str);
		} catch (NumberFormatException e) {
			// Ignoring exception causes specified default to be returned
		}

		return result;
	}

	@Override
	public float getFloat(String key, float def)
	{
		float result = def;
		try {

			java.lang.String str=get(key,null);
			int index=str.indexOf(commentToken);
			if(index>-1)
				str=str.substring(0,index).trim();
			if (str != null)
				result = Float.parseFloat(str);
		} catch (NumberFormatException e) {
			// Ignoring exception causes specified default to be returned
		}

		return result;
	}

	@Override
	public int getInt(String key, int def)
	{
		int result = def;
		try {

			java.lang.String str=get(key,null);
			int index=str.indexOf(commentToken);
			if(index>-1)
				str=str.substring(0,index).trim();
			if (str != null)
				result = Integer.parseInt(str);
		} catch (NumberFormatException e) {
			// Ignoring exception causes specified default to be returned
		}

		return result;
	}

	@Override
	public long getLong(String key, long def)
	{
		long result = def;
		try {

			java.lang.String str=get(key,null);
			int index=str.indexOf(commentToken);
			if(index>-1)
				str=str.substring(0,index).trim();
			if (str != null)
				result = Long.parseLong(str);
		} catch (NumberFormatException e) {
			// Ignoring exception causes specified default to be returned
		}

		return result;
	}

	@Override
	protected AbstractPreferences getChild(String nodeName) throws BackingStoreException
	{
		return super.getChild(nodeName);
	}

    /**
     * Implements the <CODE>childSpi</CODE> method as per the specification in
     * {@link java.util.prefs.AbstractPreferences#childSpi(String)}.
     * @param name child name
     * @return child node
     */
    @Override protected SectionPreferences childSpi(String name)
    {
        Ini.Section sec = getIni().get(name);
        boolean isNew = sec == null;

        if (isNew)
        {
            sec = getIni().add(name);
        }

        return new SectionPreferences(this, sec, isNew);
    }

}
