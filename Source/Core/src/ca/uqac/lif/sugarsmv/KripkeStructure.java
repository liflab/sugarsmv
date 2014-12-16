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

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class KripkeStructure
{
  /**
   * The state variables of the structure
   */
  protected Map<String,StateVariable> m_variables;
  
  /**
   * The Boolean expression representing the initial states
   */
  protected Connective m_initialExpression;
  
  /**
   * The Boolean expression representing the transition relation
   */
  protected Connective m_transitionExpression;
  
  /**
   * Empty constructor
   */
  public KripkeStructure()
  {
    super();
    m_variables = new HashMap<String,StateVariable>();
    m_initialExpression = null;
    m_transitionExpression = null;
  }
  
  /**
   * Adds a variable declaration to the structure
   * @param v The variable to add
   */
  public void addVariable(StateVariable v)
  {
    if (v == null)
      return;
    m_variables.put(v.getName(), v);
  }
  
  /**
   * Sets the initial expression of the Kripke structure
   * @param c A propositional formula representing the condition
   *   for initial states
   */
  public void setInitialExpression(Connective c)
  {
    m_initialExpression = c;
  }
  
  /**
   * Sets the transition expression of the Kripke structure
   * @param c A propositional formula representing the condition
   *   for the transition relation
   */
  public void setTransitionExpression(Connective c)
  {
    m_transitionExpression = c;
  }
  
  @Override
  public String toString()
  {
    StringBuilder out = new StringBuilder();
    out.append("Variable declarations\n");
    Set<String> var_names = m_variables.keySet();
    for (String name : var_names)
    {
      StateVariable v = m_variables.get(name);
      out.append(name).append(" ∈ ").append(v.getDomain()).append("\n");
    }
    return out.toString();
  }
  
}
