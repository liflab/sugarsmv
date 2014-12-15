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
 * State variable representing a discrete quantity taken from a
 * set of possible constants
 * @author sylvain
 *
 */
public class DiscreteStateVariable extends StateVariable
{
  /**
   * The value held by the variable
   */
  protected Constant m_value;
  
  /**
   * The domain of the variable
   */
  protected Domain m_domain;
  
  /**
   * Empty constructor
   */
  public DiscreteStateVariable()
  {
    super();
    m_domain = new Domain();
    m_value = null;
  }
  
  /**
   * Constructor with value and domain
   * @param value The value to give the variable
   */
  public DiscreteStateVariable(Domain domain, Constant value)
  {
    this();
    assert domain != null && value != null && domain.contains(value);
    m_domain = domain;
    m_value = value;
  }
  
  /**
   * Retrieves the value of the variable
   * @return The value
   */
  public Constant getValue()
  {
    return m_value;
  }
  
  /**
   * Sets the variable's domain
   * @param d The domain
   */
  public void setDomain(Domain d)
  {
    m_domain = d;
  }
  
  /**
   * Retrieves the variable's domain
   * @return
   */
  public Domain getDomain()
  {
    return m_domain;
  }
  
  /**
   * Sets the value of the variable
   * @param value The value
   */
  public void setValue(Constant value)
  {
    assert m_domain != null && m_domain.contains(value);
    m_value = value;
  }
}
