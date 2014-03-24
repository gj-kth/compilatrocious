package compilatrocious.parser;

class Visitor implements MiniJavaVisitor {
  public Object visit(SimpleNode node, Object data) {
      return data;
  }
  public Object visit(ASTProgram node, Object data) {
      return data;
  }
  public Object visit(ASTMainClass node, Object data) {
      return data;
  }
  public Object visit(ASTClassBody node, Object data) {
      return data;
  }
  public Object visit(ASTClassDecl node, Object data) {
      return data;
  }
  public Object visit(ASTMethodBody node, Object data) {
      return data;
  }
  public Object visit(ASTReturn node, Object data) {
      return data;
  }
  public Object visit(ASTMainMethod node, Object data) {
      return data;
  }
  public Object visit(ASTMethodDecl node, Object data) {
      return data;
  }
  public Object visit(ASTArgList node, Object data) {
      return data;
  }
  public Object visit(ASTArg node, Object data) {
      return data;
  }
  public Object visit(ASTVarDecl node, Object data) {
      return data;
  }
  public Object visit(ASTIdentifier node, Object data) {
      return data;
  }
  public Object visit(ASTInt node, Object data) {
      return data;
  }
  public Object visit(ASTIntLiteral node, Object data) {
      return data;
  }
  public Object visit(ASTIntArray node, Object data) {
      return data;
  }
  public Object visit(ASTBoolean node, Object data) {
      return data;
  }
  public Object visit(ASTBoolLiteral node, Object data) {
      return data;
  }
  public Object visit(ASTType node, Object data) {
      return data;
  }
  public Object visit(ASTStmt node, Object data) {
      return data;
  }
  public Object visit(ASTBlock node, Object data) {
      return data;
  }
  public Object visit(ASTAssignment node, Object data) {
      return data;
  }
  public Object visit(ASTConditional node, Object data) {
      return data;
  }
  public Object visit(ASTPrint node, Object data) {
      return data;
  }
  public Object visit(ASTWhile node, Object data) {
      return data;
  }
  public Object visit(ASTExpr node, Object data) {
      return data;
  }
  public Object visit(ASTExprList node, Object data) {
      return data;
  }
  public Object visit(ASTExprListTail node, Object data) {
      return data;
  }
  public Object visit(ASTOp node, Object data) {
      return data;
  }
}
