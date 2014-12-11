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

import java.util.Vector;

/**
 * Representation of a logical <i>n</i>-ary connective.
 * @author sylvain
 */
public abstract class NaryConnective extends Connective
{
  /**
   * The inner connectives
   */
  protected Vector<Connective> m_inner;
  
  /**
   * The symbol used
   */
  protected String m_symbol;
  
  @Override
  public String toString()
  {
    StringBuilder out = new StringBuilder();
    if (m_inner.size() == 1)
    {
      out.append(m_symbol).append( "(").append(m_inner.firstElement()).append(")");
    }
    else
    {
      boolean first = true;
      for (Connective c : m_inner)
      {
        if (first)
        {
          first = false;
        }
        else
        {
          out.append(m_symbol).append(" ");
        }
        out.append("(").append(c).append(")");
      }
    }
    return out.toString();
  }
  
  @Override
  public int hashCode()
  {
    int out = m_symbol.hashCode();
    for (Connective c : m_inner)
    {
      out += c.hashCode();
    }
    return out;
  }
  
  @Override
  public boolean equals(Object o)
  {
    if (o == null)
      return false;
    if (o instanceof NaryConnective)
      return equals((NaryConnective) o);
    return false;
  }
  
  protected boolean equals(NaryConnective c)
  {
    if (c == null || c.m_inner == null)
      return false;
    if (c.m_inner.size() != m_inner.size())
      return false;
    if (c.m_symbol.compareTo(m_symbol) != 0)
      return false;
    for (int i = 0; i < m_inner.size(); i++)
    {
      Connective c1 = m_inner.elementAt(i);
      Connective c2 = c.m_inner.elementAt(i);
      if (!c1.equals(c2))
        return false;
    }
    return true;
  }
  
  /**
   * Adds an operand to the connective
   * @param c The operand to add
   */
  public void addOperand(Connective c)
  {
    assert c != null;
    m_inner.add(c);
  }
  
  /**
   * Sets the <i>n</i>-th operand of the connective
   * @param c The operand to add
   */
  public void setOperand(Connective c, int position)
  {
    assert c != null && position < m_inner.size();
    m_inner.setElementAt(c, position);
  }
}
