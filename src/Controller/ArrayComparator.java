/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.util.Comparator;

/**
 *
 * @author Dolores Pot Hol
 */
public class ArrayComparator<T extends Comparable<T>> implements Comparator<T[]> {

    @Override
    public int compare(T[] o1, T[] o2) {
        if(o1==o2) return 0;
        
        int compare;
        
        for(int index=0; index<o1.length; index++) {
            if(index<o2.length) {
                if((compare=o1[index].compareTo(o2[index])) != 0) {
                    return compare;
                } 
            } else {
                    return 1;
            }
        }
        
        if(o1.length==o2.length) {
            return 0;
        } else {
            return -1;
        }       
    }    
}
