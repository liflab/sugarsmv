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

import java.util.HashSet;

/**
 * Set of values that a variable can take.
 * @author sylvain
 *
 */
public class Domain extends HashSet<Constant>
{
  /**
   * Dummy UID
   */
  private static final long serialVersionUID = 1L;
  
  @Override
  public String toString()
  {
    StringBuilder out = new StringBuilder();
    out.append("{");
    boolean first = true;
    for (Constant c : this)
    {
      if (first)
      {
        first = false;
      }
      else
      {
        out.append(",");
      }
      out.append(c);
    }
    out.append("}");
    return out.toString();
  }

}
