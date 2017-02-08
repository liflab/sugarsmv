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

/**
 * Representation of an equality between variables and/or constants 
 * @author sylvain
 *
 */
public class GroundTermEquality extends GroundTerm
{
  /**
   * Left-hand side of the equality
   */
  VariableOrConstant m_left;
  
  /**
   * Right-hand side of the equality
   */
  VariableOrConstant m_right;
  
  /**
   * Empty constructor
   */
  public GroundTermEquality()
  {
    super();
    m_left = null;
    m_right = null;
  }
  
  /**
   * Constructor with values
   * @param left The left-hand side of the equality
   * @param right The right-hand side of the equality
   */
  public GroundTermEquality(VariableOrConstant left, VariableOrConstant right)
  {
    super();
    assert left != null && right != null;
    m_left = left;
    m_right = right;
  }
  
  @Override
  public String toString()
  {
    StringBuilder out = new StringBuilder();
    out.append(m_left).append(" = ").append(m_right);
    return out.toString();
  }
}
