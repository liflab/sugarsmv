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
 * A state variable is a scalar quantity that represents part
 * of a system's state.
 * @author sylvain
 *
 */
public abstract class StateVariable extends VariableOrConstant
{
  /**
   * The variable's name
   */
  protected String m_name;
  
  /**
   * Sets the name of the variable
   * @param name The name
   */
  public void setName(String name)
  {
    m_name = name;
  }
  
  /**
   * Retrieves the variable's domain
   * @return The domain
   */
  public abstract Domain getDomain();
  
  /**
   * Retrieves the name of the variable
   * @return The name
   */
  public String getName()
  {
    return m_name;
  }
}
