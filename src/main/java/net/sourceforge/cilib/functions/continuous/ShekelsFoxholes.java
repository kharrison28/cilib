/*
 * ShekelsFoxholes.java
 *
 * Created on June 4, 2003, 1:46 PM
 *
 * 
 * Copyright (C) 2003 - 2006 
 * Computational Intelligence Research Group (CIRG@UP)
 * Department of Computer Science 
 * University of Pretoria
 * South Africa
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA 
 *   
 */
package net.sourceforge.cilib.functions.continuous;

import net.sourceforge.cilib.functions.ContinuousFunction;
import net.sourceforge.cilib.type.types.Vector;

/**
 * <p>Title: CILib</p>
 * <p>Description: CILib (Computational Intelligence Library)</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author Clive Naicker
 * @version 1.0
 */

public class ShekelsFoxholes extends ContinuousFunction {

    private double[][] A = new double[2][25];

    public ShekelsFoxholes() {
        int index = 0;
        for (int j=-32; j<=32; j+=16) {
            for (int i=-32; i<=32; i+=16) {
                A[0][index] = i;
                A[1][index] = j;
                index ++;
            }
        }

        //constraint.add(new DimensionValidator(2));
        setDomain("R(-65.536, 65.536)^2");
    }

    public Object getMinimum() {
        return new Double(0.9980038);
    }
    
    public double evaluate(Vector x) {

        double result = 0.002;
        double resultI = 0.0;
        for (int i=1; i<=25; i++) {
            double resultJ = 0.0;
            for (int j=0; j<2; j++) {
                resultJ += Math.pow(x.getReal(j) - A[j][i-1], 6);
            }
            resultJ = i + resultJ;
            resultI += 1/resultJ;
        }
        resultI = 0.002 + resultI;

        result = 1.0/resultI;

        return result;
    }

}