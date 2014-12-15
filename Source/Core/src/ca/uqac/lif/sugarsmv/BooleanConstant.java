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
 * Representation of a Boolean (true/false) value
 * @author sylvain
 *
 */
public abstract class BooleanConstant extends Constant
{
  /**
   * Private constructor
   * @param value
   */
  private BooleanConstant(boolean value)
  {
    super(valueToString(value));
  }
  
  /**
   * Returns an instance of the constant false
   * @return An instance of the constant false
   */
  public static BooleanConstantFalse getFalse()
  {
    return new BooleanConstant.BooleanConstantFalse();
  }
  
  /**
   * Returns an instance of the constant true
   * @return An instance of the constant true
   */
  public static final BooleanConstantTrue getTrue()
  {
    return new BooleanConstant.BooleanConstantTrue();
  }
  
  /**
   * Representation of the constant "false"
   * @author sylvain
   */
  public static final class BooleanConstantFalse extends BooleanConstant
  {
    public BooleanConstantFalse()
    {
      super(false);
    }
    
    @Override
    public String toString()
    {
      return "⊥";
    }
    
    @Override
    public int hashCode()
    {
      return "⊥".hashCode();
    }
    
    @Override
    public boolean equals(Object o)
    {
      if (o == null)
        return false;
      if (o instanceof BooleanConstantFalse)
        return equals((BooleanConstantFalse) o);
      return false;
    }
    
    protected boolean equals(BooleanConstantFalse c)
    {
      return true;
    }
  }
  
  /**
   * Representation of the constant "true"
   * @author sylvain
   */
  public static class BooleanConstantTrue extends BooleanConstant
  {
    public BooleanConstantTrue()
    {
      super(true);
    }
    
    @Override
    public String toString()
    {
      return "⊤";
    }
    
    @Override
    public int hashCode()
    {
      return "⊤".hashCode();
    }
    
    @Override
    public boolean equals(Object o)
    {
      if (o == null)
        return false;
      if (o instanceof BooleanConstantTrue)
        return equals((BooleanConstantTrue) o);
      return false;
    }
    
    protected boolean equals(BooleanConstantTrue c)
    {
      return true;
    }
  }
  
  protected static String valueToString(boolean b)
  {
    if (b)
      return "⊤";
    return "⊥";
  }
}
