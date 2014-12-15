/*
    Syntactical sugar for NuSMV
    Copyright (C) 2014-2015 Sylvain Hallé

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
import java.io.File;
import java.io.IOException;

import ca.uqac.lif.bullwinkle.BnfParser;
import ca.uqac.lif.bullwinkle.BnfParser.InvalidGrammarException;
import ca.uqac.lif.bullwinkle.ParseNode;
import ca.uqac.lif.bullwinkle.output.GraphvizVisitor;
import ca.uqac.lif.util.FileReadWrite;

/**
 * Parser for classical input files for the
 * <a href="http://nusmv.fbk.eu/">NuSMV</a> model checker.
 * @author sylvain
 */
public class NusmvKripkeParser extends KripkeParser
{
  /**
   * The Bullwinkle parser used to read NuSMV files.
   * A single parser instance is shared by all instances of
   * the NusmvKripkeParser, and its grammar is created
   * only once (here).
   */
  protected static BnfParser s_parser = createParser();

  @Override
  public KripkeStructure parse(BufferedReader reader) throws IOException,
      ParseException
  {
    KripkeStructure kripke = new KripkeStructure();
    String doc_contents = sanitizeInput(reader);
    try
    {
      ParseNode parse_tree = s_parser.parse(doc_contents);
      if (parse_tree == null)
      {
        // Something bad happened when parsing
        throw new ParseException("Parse tree is null");
      }
      GraphvizVisitor visitor = new GraphvizVisitor();
      parse_tree.prefixAccept(visitor);
      System.out.println(visitor.toOutputString());
      
    }
    catch (ca.uqac.lif.bullwinkle.BnfParser.ParseException e)
    {
      throw new ParseException(e.toString());
    }
    return kripke;
  }
  
  /**
   * Creates the BNF grammar that the Bullwinkle parser uses
   * to read files. 
   * @return An instance of a Bullwinkle {@link BnfParser}
   */
  protected static BnfParser createParser()
  {
    BnfParser parser = null;
    try
    {
      parser = new BnfParser(new File("grammars/NuSMV.bnf"));
      parser.setDebugMode(false);
    }
    catch (IOException e)
    {
      // Not supposed to happen!
      e.printStackTrace();
    }
    catch (InvalidGrammarException e)
    {
      // Did I say it's not supposed to happen?
      e.printStackTrace();
    }
    return parser;
  }
  
  /**
   * Sanitizes the NuSMV input file (removing comments, etc.)
   * and turns it into a string
   * @param reader A BufferedReader to a source of characters (a file,
   *   string, etc.) containing the document to parse
   * @return A sanitized string based on the contents of the document
   * @throws IOException If something bad happens when attempting to
   *   read the source
   * @return
   */
  protected static String sanitizeInput(BufferedReader reader) throws IOException
  {
    StringBuilder out = new StringBuilder();
    String line = "";
    while ((line = reader.readLine()) != null)
    {
      // Trim lines of useless spaces
      line = line.trim();
      if (line.isEmpty()) // Remove empty lines
        continue;
      if (line.startsWith("--")) // Remove comment lines
        continue;
      // This is a meaningful line; add it to output
      out.append(line).append("\n");
    }
    return out.toString();
  }

}
