package com.proper.checkstyle;

import com.puppycrawl.tools.checkstyle.api.*;
import com.puppycrawl.tools.checkstyle.utils.AnnotationUtil;

/**
 * 校验Controller.java 文件中是否包含swagger的注解
 */
public class SwaggerCheck extends AbstractCheck {

    private String anno = Instance.SWAGGER_ANNOTATION;

    private String filter = Instance.FILTER;

    private String mapping = Instance.REQUEST_MAPPING;

    public void setAnno(String anno) {
        this.anno = anno;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public void setMapping(String mapping) {
        this.mapping = mapping;
    }

    @Override
    public int[] getDefaultTokens() {
        return new int[]{TokenTypes.METHOD_DEF};
    }

    @Override
    public void visitToken(DetailAST ast) {
        String str = getFileContents().getFileName();
        // filter Controller.java file
        if (str.endsWith(filter)) {
            // find all annotations types is ANNOTATION by tree node
            DetailAST child = detail(ast);
            final DetailAST detailAST = child.getFirstChild();
            final String name = FullIdent.createFullIdent(detailAST.getNextSibling()).getText();
            // while controller contains RequestMapping annotation, must have annotation 'ApiOperation'
            // otherwise,report error, log message
            typeValue(name, ast);
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

    private DetailAST detail(DetailAST ast) {
        DetailAST holder = AnnotationUtil.getAnnotationHolder(ast);
        for (DetailAST child = holder.getFirstChild(); child != null; child = child.getNextSibling()) {
            if (child.getType() == TokenTypes.ANNOTATION) {
                return child;
            }
        }
        return holder;
    }

    private void typeValue(String name, DetailAST ast) {
        if (name.endsWith(mapping)) {
            if (AnnotationUtil.containsAnnotation(ast, anno)) {
                return;
            } else {
                String message = "Failed！The methods no have swagger annotation [" + ast.getText() + "]";
                log(ast.getLineNo(), message);
            }
        }
    }

}
