/*
    Syntactical sugar for NuSMV
    Copyright (C) 2014-2017 Sylvain Hallé

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package ca.uqac.lif.sugarsmv;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Parses a text document and produces a {@link KripkeStructure}
 * from it
 * @author sylvain
 */
public abstract class KripkeParser
{
  /**
   * Parses a Kripke structure from an input source
   * @param reader A BufferedReader to a source of characters (a file,
   *   string, etc.) containing the document to parse
   * @return A Kripke structure based on the contents of the document,
   *   if the parsing went without error
   * @throws IOException If something bad happens when attempting to
   *   read the source
   * @throws ParseException If the document is incorrectly formed.
   *   The exact formatting rules depend on the particular instance
   *   of parser being used.
   */
  public abstract KripkeStructure parse(BufferedReader reader) throws IOException, ParseException;
  
  /**
   * Simple exception class indicating an error in the parsing of
   * a document
   * @author sylvain
   */
  public static class ParseException extends Exception
  {
    /**
     * The message carried by this exception
     */
    protected String m_message;

    /**
     * Dummy UID
     */
    private static final long serialVersionUID = -8755602580215137731L;

    public ParseException(String message)
    {
      super();
      m_message = message;
    }

    @Override
    public String toString()
    {
      return m_message;
    }
  }
  
}
