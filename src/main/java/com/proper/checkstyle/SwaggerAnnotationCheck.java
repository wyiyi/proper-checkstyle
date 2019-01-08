package com.proper.checkstyle;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.FullIdent;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import com.puppycrawl.tools.checkstyle.utils.AnnotationUtil;

public class SwaggerAnnotationCheck extends AbstractCheck {

    private static final String ANNOTATION_SWAGGER_API = "Api";
    private static final String ANNOTATION_SWAGGER_OPERATION = "ApiOperation";

    private static final String ANNOTATION_CONTROLLER = "Controller";
    private static final String ANNOTATION_REST_CONTROLLER = "RestController";

    private static final String REQUEST_MAPPING_SUFFIX = "Mapping";

    @Override
    public int[] getDefaultTokens() {
        return new int[]{TokenTypes.CLASS_DEF};
    }

    @Override
    public void visitToken(DetailAST ast) {
        if (!isController(ast)) {
            return;
        }

        if (hasOnClass(ast)) {
            checkMethods(ast);
        }
    }

    private boolean isController(DetailAST ast) {
        return AnnotationUtil.containsAnnotation(ast, ANNOTATION_REST_CONTROLLER) || AnnotationUtil.containsAnnotation(ast, ANNOTATION_CONTROLLER);
    }

    private boolean hasOnClass(DetailAST ast) {
        if (AnnotationUtil.containsAnnotation(ast, ANNOTATION_SWAGGER_API)) {
            return true;
        } else {
            String message = "Failed！There must be swagger annotation '@Api' on the class!";
            log(ast.getLineNo(), message);
            return false;
        }
    }

    private void checkMethods(DetailAST ast) {
        // 通过 CLASS_DEF 的树节点获取每一个 METHOD_DEF
        DetailAST firstMethod = ast.findFirstToken(TokenTypes.OBJBLOCK).findFirstToken(TokenTypes.METHOD_DEF);
        for (DetailAST method = firstMethod; method != null && method.getType() == TokenTypes.METHOD_DEF; method = method.getNextSibling()) {
            // 校验带 @RestquestMapping 的 METHOD_DEF 是否存在 swagger 注解
            checkSingleMethod(method);
        }
    }

    private void checkSingleMethod(DetailAST ast) {
        if (!ast.branchContains(TokenTypes.ANNOTATION)) {
            return;
        }
        boolean isRequestMapping = false;
        boolean hasSwaggerAnnotation = false;
        DetailAST firstAnnotation = AnnotationUtil.getAnnotationHolder(ast).getFirstChild();
        for (DetailAST child = firstAnnotation; child != null; child = child.getNextSibling()) {
            final DetailAST detailAST = child.getFirstChild();
            if (detailAST == null) {
                continue;
            }
            final String annotationName = FullIdent.createFullIdent(detailAST.getNextSibling()).getText();
            if (annotationName.endsWith(REQUEST_MAPPING_SUFFIX)) {
                isRequestMapping = true;
            } else if (annotationName.equals(ANNOTATION_SWAGGER_OPERATION)) {
                hasSwaggerAnnotation = true;
            }
        }
        if (isRequestMapping && !hasSwaggerAnnotation) {
            String message = "There must be swagger annotation '@ApiOperation'on the method!";
            log(ast.getLineNo(), message);
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
