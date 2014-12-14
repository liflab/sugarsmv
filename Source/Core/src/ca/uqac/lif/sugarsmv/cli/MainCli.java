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
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

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
  protected static final int ERR_OK = 0;
  protected static final int ERR_IO = 1;
  protected static final int ERR_PARSE = 2;
  
  /**
   * Version string
   */
  protected static final String s_versionString = "0.0";
  
  /**
   * @param args
   */
  public static void main(String[] args)
  {
    //printMessage(System.out);
    String in_filename = "test.smv";
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
  protected static void printMessage(PrintStream ps)
  {
    ps.println("SugarSMV " + s_versionString + ", syntactical sugar for NuSMV");
    ps.println("(C) 2014 Sylvain Hallé, Université du Québec à Chicoutimi");
  }

}
