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

import java.util.List;

import ca.uqac.lif.bullwinkle.ParseNode;

public class NusmvKripkeBuilder extends KripkeFileBuilder
{ 
  @Override
  public KripkeStructure getKripkeStructure(ParseNode root)
  {
    KripkeStructure kripke = new KripkeStructure();
    
    // Fetch variable declarations
    List<ParseNode> node_list;
    node_list = root.getChildren();
    for (ParseNode c : node_list)
    {
      if (c.getToken().compareTo("<varsection>") == 0)
      {
        processVarSection(c, kripke);
      }
    }
    return kripke;
  }
  
  protected void processVarSection(ParseNode n, KripkeStructure kripke)
  {
    String var_name = "";
    List<ParseNode> node_list = n.getChildren();
    for (ParseNode c : node_list)
    {
      if (c.getToken().compareTo("<varsection>") == 0)
      {
        // Recurse
        processVarSection(c, kripke);
      }
      else if (c.getToken().compareTo("<varname>") == 0)
      {
        // What's under varname is label/the name
        var_name = c.getChildren().get(0).getChildren().get(0).getToken();
      }
      else if (c.getToken().compareTo("<vartype>") == 0)
      {
        ParseNode type_node = c.getChildren().get(0);
        String type = type_node.getValue();
        switch (type)
        {
          case "<type_intrange>":
            List<ParseNode> children = type_node.getChildren();
            String lower_bound = children.get(1).getChildren().get(0).getToken();
            String upper_bound = children.get(3).getChildren().get(0).getToken();
            Domain d = createDomainFromRange(lower_bound, upper_bound);
            DiscreteStateVariable dv = new DiscreteStateVariable();
            dv.setName(var_name);
            dv.setDomain(d);
            kripke.addVariable(dv);
            break;
          case "<type_boolean>":
            BooleanStateVariable bv = new BooleanStateVariable();
            bv.setName(var_name);
            kripke.addVariable(bv);
            break;
        }
      }
    }
  }
  
  protected void processInitSection(ParseNode n, KripkeStructure kripke)
  {
    Connective phi = parseFormulaFromTree(n);
    if (phi != null)
      kripke.setInitialExpression(phi);
  }
  
  protected void processTransitionSection(ParseNode n, KripkeStructure kripke)
  {
    Connective phi = parseFormulaFromTree(n);
    if (phi != null)
      kripke.setTransitionExpression(phi);
  }
  
  protected static Connective parseFormulaFromTree(ParseNode n)
  {
    // TODO
    return null;
  }
  
  /**
   * Convenience method to create a range from strings instead of
   * integers
   * @param lower_bound String containing a number (the lower bound
   *   of the range)
   * @param upper_bound String containing a number (the upper bound
   *   of the range)
   * @return The domain
   */
  protected static Domain createDomainFromRange(String lower_bound, String upper_bound)
  {
    int l_bound = Integer.parseInt(lower_bound);
    int u_bound = Integer.parseInt(upper_bound);
    return createDomainFromRange(l_bound, u_bound);
  }
  
  /**
   * Creates a discrete domain out of a range of integers
   * @param lower_bound The lower bound of the range
   * @param upper_bound The upper bound of the range
   * @return The domain
   */
  protected static Domain createDomainFromRange(int lower_bound, int upper_bound)
  {
    Domain d = new Domain();
    for (int i = lower_bound; i <= upper_bound; i++)
    {
      Constant c = new DiscreteConstant(Integer.toString(i));
      d.add(c);
    }
    return d;
  }
}
