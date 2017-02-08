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
package ca.uqac.lif.sugarsmv.cli;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

import ca.uqac.lif.util.CliParser;
import ca.uqac.lif.util.CliParser.Argument;
import ca.uqac.lif.util.CliParser.ArgumentMap;

import ca.uqac.lif.sugarsmv.KripkeParser.ParseException;
import ca.uqac.lif.sugarsmv.KripkeStructure;
import ca.uqac.lif.sugarsmv.NusmvKripkeParser;

/**
 * Main class for the CLI interface of SugarSMV
 * @author sylvain
 */
public class MainCli
{
  /**
   * Return codes
   */
  public static final int ERR_OK = 0;
  public static final int ERR_PARSE = 2;
  public static final int ERR_IO = 3;
  public static final int ERR_ARGUMENTS = 4;
  public static final int ERR_RUNTIME = 6;
  
  /**
   * Build string to identify versions
   */
  protected static final String VERSION_STRING = "0.1-alpha";
  
  /**
   * @param args
   */
  public static void main(String[] args)
  {
    int verbosity = 1;
    String in_filename = null;
    
    // Parse command line arguments
    CliParser c_line = new CliParser();
    setupOptions(c_line);
    ArgumentMap arguments = c_line.parse(args);
    if (arguments == null)
    {
    	// Incorrect arguments
    	c_line.printHelp("java -jar sugarsmv.jar", System.err);
        System.exit(ERR_ARGUMENTS);
    }
    if (arguments.hasOption("verbosity"))
    {
      verbosity = Integer.parseInt(arguments.getOptionValue("verbosity"));
    }
    if (verbosity > 0)
    {
      showHeader(System.err);
    }
    if (arguments.hasOption("i"))
    {
      in_filename = arguments.getOptionValue("i");
    }
    if (arguments.hasOption("version"))
    {
      System.err.println("(C) 2014-2017 Sylvain Hallé et al., Université du Québec à Chicoutimi");
      System.err.println("This program comes with ABSOLUTELY NO WARRANTY.");
      System.err.println("This is a free software, and you are welcome to redistribute it");
      System.err.println("under certain conditions. See the file COPYING for details.\n");
      System.exit(ERR_OK);
    }
    if (arguments.hasOption("h"))
    {
      c_line.printHelp("java -jar sugarsmv.jar", System.err);
      System.exit(ERR_OK);
    }
    
    // Check if CL arguments are valid
    if (in_filename == null)
    {
      System.err.println("ERROR: no input filename specified");
      System.exit(ERR_ARGUMENTS);
    }
    File in_file = new File(in_filename);
    if (!in_file.exists())
    {
      System.err.println("ERROR: file not found " + in_filename);
      System.exit(ERR_IO);
    }
    
    // Parse the input file
    NusmvKripkeParser nkp = new NusmvKripkeParser();
    KripkeStructure kripke = null;
    try
    {
      kripke = nkp.parse(new BufferedReader(new InputStreamReader(new FileInputStream(new File(in_filename)))));
    }
    catch (FileNotFoundException e)
    {
      System.err.println("File not found " + in_filename);
      System.exit(ERR_IO);
    }
    catch (IOException e)
    {
      System.err.println("Error reading file " + in_filename);
      System.exit(ERR_IO);
    }
    catch (ParseException e)
    {
      System.err.println("Error parsing file " + in_filename);
      e.printStackTrace();
      System.exit(ERR_PARSE);
    }
    System.out.println(kripke);

    System.exit(ERR_OK);
  }
  
  /**
   * Prints the banner message
   * @param ps The {@link PrintStream} to print to
   */
  private static void showHeader(PrintStream ps)
  {
    ps.println("SugarSMV " + VERSION_STRING + ", syntactical sugar for NuSMV");
    ps.println("(C) 2014-2017 Sylvain Hallé, Université du Québec à Chicoutimi\n");
  }
  
  /**
   * Sets up the options for the command line parser
   * @return The options
   */
  private static void setupOptions(CliParser parser)
  {
    parser.addArgument(new Argument().withLongName("help").withDescription("Display command line usage").withShortName("h"));
    parser.addArgument(new Argument().withLongName("input-file").withShortName("i").withDescription("Read model from filename").withArgument("filename"));
  }
}
