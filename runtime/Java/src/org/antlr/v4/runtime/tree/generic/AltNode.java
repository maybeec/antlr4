package org.antlr.v4.runtime.tree.generic;

/**
 * Node representing an alternative of an ambiguity. 
 */
public class AltNode {

    public final int alt;
    
    public final int stateNumber;

    public AltNode(int alt, int stateNumber) {
        this.alt = alt;
        this.stateNumber = stateNumber;
    }
    
    @Override
    public String toString() {
        return stateNumber + "::" + alt;
    }
}
