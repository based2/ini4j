/*
 * Copyright 2019 Juergen KEY
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
		return InlineCommentWrapperHelper.get(this,key,def,commentToken);
	}

	@Override
	public boolean getBoolean(String key, boolean def)
	{
		return InlineCommentWrapperHelper.getBoolean(this,key,def,commentToken);
	}

	@Override
	public double getDouble(String key, double def)
	{
		return InlineCommentWrapperHelper.getDouble(this,key,def,commentToken);
	}

	@Override
	public float getFloat(String key, float def)
	{
		return InlineCommentWrapperHelper.getFloat(this,key,def,commentToken);
	}

	@Override
	public int getInt(String key, int def)
	{
		return InlineCommentWrapperHelper.getInt(this,key,def,commentToken);
	}

	@Override
	public long getLong(String key, long def)
	{
		return InlineCommentWrapperHelper.getLong(this,key,def,commentToken);
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

        return new InlineCommentWrapperForSectionPreferences(this, sec, isNew,commentToken);
    }

	class InlineCommentWrapperForSectionPreferences extends IniPreferences.SectionPreferences
	{
		private final char commentToken;

		public InlineCommentWrapperForSectionPreferences(AbstractPreferences parent, Ini.Section section, boolean isNew,char commentToken)
		{
			super(parent, section, isNew);
			this.commentToken=commentToken;
		}
		@Override
		public String get(String key, String def)
		{
			return InlineCommentWrapperHelper.get(this,key,def,commentToken);
		}

		@Override
		public boolean getBoolean(String key, boolean def)
		{
			return InlineCommentWrapperHelper.getBoolean(this,key,def,commentToken);
		}

		@Override
		public double getDouble(String key, double def)
		{
			return InlineCommentWrapperHelper.getDouble(this,key,def,commentToken);
		}

		@Override
		public float getFloat(String key, float def)
		{
			return InlineCommentWrapperHelper.getFloat(this,key,def,commentToken);
		}

		@Override
		public int getInt(String key, int def)
		{
			return InlineCommentWrapperHelper.getInt(this,key,def,commentToken);
		}

		@Override
		public long getLong(String key, long def)
		{
			return InlineCommentWrapperHelper.getLong(this,key,def,commentToken);
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

			return new InlineCommentWrapperForSectionPreferences(this, sec, isNew,commentToken);
		}
	}
}
