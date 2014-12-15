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
package ca.uqac.lif.sugarsmv.cli;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.PosixParser;

import ca.uqac.lif.sugarsmv.KripkeParser.ParseException;
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
  protected static final String VERSION_STRING = "0.0";
  
  /**
   * @param args
   */
  public static void main(String[] args)
  {
    int verbosity = 1;
    String in_filename = null;
    
    // Parse command line arguments
    Options options = setupOptions();
    CommandLine c_line = setupCommandLine(args, options);
    assert c_line != null;
    if (c_line.hasOption("verbosity"))
    {
      verbosity = Integer.parseInt(c_line.getOptionValue("verbosity"));
    }
    if (verbosity > 0)
    {
      showHeader(System.err);
    }
    if (c_line.hasOption("i"))
    {
      in_filename = c_line.getOptionValue("i");
    }
    if (c_line.hasOption("version"))
    {
      System.err.println("(C) 2014-2015 Sylvain Hallé et al., Université du Québec à Chicoutimi");
      System.err.println("This program comes with ABSOLUTELY NO WARRANTY.");
      System.err.println("This is a free software, and you are welcome to redistribute it");
      System.err.println("under certain conditions. See the file COPYING for details.\n");
      System.exit(ERR_OK);
    }
    if (c_line.hasOption("h"))
    {
      showUsage(options);
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
    
    NusmvKripkeParser nkp = new NusmvKripkeParser();
    try
    {
      nkp.parse(new BufferedReader(new InputStreamReader(new FileInputStream(new File(in_filename)))));
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
    System.exit(ERR_OK);
  }
  
  /**
   * Prints the banner message
   * @param ps The {@link PrintStream} to print to
   */
  private static void showHeader(PrintStream ps)
  {
    ps.println("SugarSMV " + VERSION_STRING + ", syntactical sugar for NuSMV");
    ps.println("(C) 2014 Sylvain Hallé, Université du Québec à Chicoutimi\n");
  }
  
  /**
   * Show the program's usage
   * @param options The options created for the command line parser
   */
  private static void showUsage(Options options)
  {
    HelpFormatter hf = new HelpFormatter();
    hf.printHelp("java -jar SugarSMV.jar", options);
  }

  /**
   * Sets up the command line parser
   * @param args The command line arguments passed to the class' {@link main}
   * method
   * @param options The command line options to be used by the parser
   * @return The object that parsed the command line parameters
   */
  private static CommandLine setupCommandLine(String[] args, Options options)
  {
    CommandLineParser parser = new PosixParser();
    CommandLine c_line = null;
    try
    {
      // parse the command line arguments
      c_line = parser.parse(options, args);
    }
    catch (org.apache.commons.cli.ParseException exp)
    {
      // oops, something went wrong
      System.err.println("ERROR: " + exp.getMessage() + "\n");
      //HelpFormatter hf = new HelpFormatter();
      //hf.printHelp(t_gen.getAppName() + " [options]", options);
      System.exit(ERR_ARGUMENTS);
    }
    return c_line;
  }
  
  /**
   * Sets up the options for the command line parser
   * @return The options
   */
  @SuppressWarnings("static-access")
  private static Options setupOptions()
  {
    Options options = new Options();
    Option opt;
    opt = OptionBuilder
        .withLongOpt("help")
        .withDescription(
            "Display command line usage")
            .create("h");
    options.addOption(opt);
    opt = OptionBuilder
        .withLongOpt("input-file")
        .withArgName("filename")
        .hasArg()
        .withDescription(
            "Read model from filename")
            .create("i");
    options.addOption(opt);
    return options;
  }
}
