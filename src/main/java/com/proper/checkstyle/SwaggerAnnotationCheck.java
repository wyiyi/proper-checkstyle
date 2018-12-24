package com.proper.checkstyle;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.FullIdent;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import com.puppycrawl.tools.checkstyle.utils.AnnotationUtil;

public class SwaggerAnnotationCheck extends AbstractCheck {

    public String anno = "ApiOperation";

    public String restController = "RestController";

    public String controller = "Controller";

    public String api = "Api";

    public String mapping = "Mapping";

    @Override
    public int[] getDefaultTokens() {
        return new int[]{TokenTypes.CLASS_DEF};
    }

    @Override
    public void visitToken(DetailAST ast) {
        // 过滤Controller 文件并校验在类上是否有 @Api 注解
        if (classContains(ast)) {
            // 通过 CLASS_DEF 的树节点获取每一个 METHOD_DEF
            DetailAST method = ast.findFirstToken(TokenTypes.OBJBLOCK).findFirstToken(TokenTypes.METHOD_DEF);
            for (DetailAST detailAST = method; detailAST != null; detailAST = detailAST.getNextSibling()) {
                // 校验带 @RestquestMapping 的 METHOD_DEF 是否存在 swagger 注解
                annotation(detailAST);
            }
        }
    }

    private boolean classContains(DetailAST ast) {
        if (AnnotationUtil.containsAnnotation(ast, restController) || AnnotationUtil.containsAnnotation(ast, controller)) {
            if (AnnotationUtil.containsAnnotation(ast, api)) {
                return true;
            } else {
                String message = "Failed！There must be swagger annotation '@Api' on the class!";
                log(ast.getLineNo(), message);
            }
        }
        return false;
    }

    private void annotation(DetailAST ast) {
        if (ast.branchContains(TokenTypes.ANNOTATION) && ast.getType() == TokenTypes.METHOD_DEF) {
            getChild(ast);
        }
    }

    private void getChild(DetailAST ast) {
        for (DetailAST child = AnnotationUtil.getAnnotationHolder(ast).getFirstChild(); child != null; child = child.getNextSibling()) {
            final DetailAST detailAST = child.getFirstChild();
            final String name = FullIdent.createFullIdent(detailAST.getNextSibling()).getText();
            if (name.endsWith(mapping)) {
                validApiOperation(ast);
                break;
            }
        }
    }

    private void validApiOperation(DetailAST ast) {
        if (AnnotationUtil.containsAnnotation(ast, anno)) {
            return;
        } else {
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
