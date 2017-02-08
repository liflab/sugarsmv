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
 * Generic representation of any value that a variable can take
 * @author sylvain
 */
public abstract class Constant extends VariableOrConstant
{
  /**
   * The constant's value
   */
  protected final String m_value;

  /**
   * Constructor with value
   * @param value The value to give the constant
   */
  public Constant(String value)
  {
    super();
    m_value = value;
  }
}
