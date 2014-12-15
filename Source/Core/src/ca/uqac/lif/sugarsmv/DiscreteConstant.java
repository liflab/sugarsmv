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

/**
 * Any discrete value represented by some symbol.
 * @author sylvain
 */
public class DiscreteConstant extends Constant
{
  
  /**
   * Constructor with value
   * @param value The value
   */
  public DiscreteConstant(String value)
  {
    super(value);
  }
  
  @Override
  public int hashCode()
  {
    return m_value.hashCode();
  }
  
  @Override
  public boolean equals(Object o)
  {
    if (o == null)
      return false;
    if (o instanceof DiscreteConstant)
      return equals((DiscreteConstant) o);
    return false;
  }
  
  protected boolean equals(DiscreteConstant c)
  {
    if (c == null || c.m_value == null)
      return false;
    return m_value.compareTo(c.m_value) == 0;
  }
  
  @Override
  public String toString()
  {
    return m_value;
  }
}
