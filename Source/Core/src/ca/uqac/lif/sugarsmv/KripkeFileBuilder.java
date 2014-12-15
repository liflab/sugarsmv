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

import ca.uqac.lif.bullwinkle.ParseNode;

/**
 * Visits a parse tree for an input file and builds a Kripke
 * structure out of its contents
 * @author sylvain
 */
public abstract class KripkeFileBuilder
{
  /**
   * Returns the Kripke structure built from the parsed file
   * @param root The parse tree resulting from the parsing of the
   *   input file
   * @return The Kripke structure, or null
   */
  public abstract KripkeStructure getKripkeStructure(ParseNode root);
}
