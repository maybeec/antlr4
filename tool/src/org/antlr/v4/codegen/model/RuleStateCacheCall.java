package org.antlr.v4.codegen.model;

import org.antlr.v4.codegen.OutputModelFactory;
import org.antlr.v4.tool.ast.GrammarAST;

public class RuleStateCacheCall extends OutputModelObject{

    public String ruleName;
    
    public int atnState;

    public RuleStateCacheCall(OutputModelFactory factory, GrammarAST ast, String ruleName, int atnState) {
        super();
        this.factory = factory;
        this.ast = ast;
        this.ruleName = ruleName;
        this.atnState = atnState;
    }
    
}
