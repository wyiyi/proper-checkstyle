package com.proper.checkstyle;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.FileContents;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import com.puppycrawl.tools.checkstyle.utils.AnnotationUtil;

public class SwaggerCheck extends AbstractCheck {

    private static final String SWAGGER_ANNOTATION = "ApiOperation";
    private String anno = SWAGGER_ANNOTATION;
    private static final String Filter_Controller = "Controller.java";
    private String filter = Filter_Controller;

    public void setAnno(String anno) {
        this.anno = anno;
    }

    @Override
    public int[] getDefaultTokens() {
        return new int[]{TokenTypes.METHOD_DEF};
    }

    @Override
    public void visitToken(DetailAST ast) {
        FileContents fileContents = getFileContents();
        String str = fileContents.getFileName();
        if (str.endsWith(filter)) {
            if (AnnotationUtil.containsAnnotation(ast, anno)) {
                return;
            } else {
                String message = "Failed！The methods no have swagger annotation";
                log(ast.getLineNo(), message);
            }
        }
    }

    @Override
    public int[] getAcceptableTokens() {
        return new int[0];
    }

    @Override
    public int[] getRequiredTokens() {
        return new int[0];
    }

}
