/*
 * Copyright 2005,2009 Ivan SZKIBA
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

import java.util.prefs.Preferences;

class InlineCommentWrapperHelper extends java.lang.Object
{
	static String get(Preferences prefs,String key, String def,char commentToken)
	{
		java.lang.String result = def;
		try {

			java.lang.String str=prefs.get(key,null);
			int index=str.indexOf(commentToken);
			if(index>-1)
				str=str.substring(0,index).trim();
			result=str;
		} catch (NumberFormatException e) {
			// Ignoring exception causes specified default to be returned
		}

		return (result==null ? def : result);
	}

	static boolean getBoolean(Preferences prefs,String key, boolean def,char commentToken)
	{
		boolean result = def;
		try {

			java.lang.String str=get(prefs,key,null,commentToken);
			if (str != null)
				result = Boolean.getBoolean(str);
		} catch (NumberFormatException e) {
			// Ignoring exception causes specified default to be returned
		}

		return result;
	}

	static double getDouble(Preferences prefs,String key, double def,char commentToken)
	{
		double result = def;
		try {

			java.lang.String str=get(prefs,key,null,commentToken);
			if (str != null)
				result = Double.parseDouble(str);
		} catch (NumberFormatException e) {
			// Ignoring exception causes specified default to be returned
		}

		return result;
	}

	static float getFloat(Preferences prefs,String key, float def,char commentToken)
	{
		float result = def;
		try {

			java.lang.String str=get(prefs,key,null,commentToken);
			if (str != null)
				result = Float.parseFloat(str);
		} catch (NumberFormatException e) {
			// Ignoring exception causes specified default to be returned
		}

		return result;
	}

	static int getInt(Preferences prefs,String key, int def,char commentToken)
	{
		int result = def;
		try {

			java.lang.String str=get(prefs,key,null,commentToken);
			if (str != null)
				result = Integer.parseInt(str);
		} catch (NumberFormatException e) {
			// Ignoring exception causes specified default to be returned
		}

		return result;
	}

	static long getLong(Preferences prefs,String key, long def,char commentToken)
	{
		long result = def;
		try {

			java.lang.String str=get(prefs,key,null,commentToken);
			if (str != null)
				result = Long.parseLong(str);
		} catch (NumberFormatException e) {
			// Ignoring exception causes specified default to be returned
		}

		return result;
	}

}
