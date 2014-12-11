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
 * Representation of a set of two values, true and false
 * @author sylvain
 *
 */
public final class BooleanDomain extends Domain
{
  /**
   * Dummy UID
   */
  private static final long serialVersionUID = 1L;

  /**
   * Empty constructor
   */
  public BooleanDomain()
  {
    super();
    add(BooleanConstant.getTrue());
    add(BooleanConstant.getFalse());
  }
  
  @Override
  public void clear()
  {
    assert false;
  }
}
