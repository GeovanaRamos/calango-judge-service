package br.ucb.calango.arvore;

import org.antlr.runtime.Token;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.Tree;

import java.util.List;

public class CalangoTree extends CommonTree {
   public CalangoTree(Token payload) {
      super(payload);
   }

   public br.ucb.calango.arvore.CalangoTree getCalangoChild(int i) {
      return (br.ucb.calango.arvore.CalangoTree)this.getChild(i);
   }

   public Tree getChild(int i) {
      return super.getChild(i);
   }

   public br.ucb.calango.arvore.CalangoTree getParent() {
      return (br.ucb.calango.arvore.CalangoTree)super.getParent();
   }

   public br.ucb.calango.arvore.CalangoTree getAncestor(int ttype) {
      return (br.ucb.calango.arvore.CalangoTree)super.getAncestor(ttype);
   }

   public br.ucb.calango.arvore.CalangoTree getFirstChildWithType(int arg0) {
      return (br.ucb.calango.arvore.CalangoTree)super.getFirstChildWithType(arg0);
   }

   public boolean parentHasChild(int childType) {
      return ((br.ucb.calango.arvore.CalangoTree)super.getParent()).getFirstChildWithType(childType) != null;
   }

   public boolean isType(int i) {
      return this.getType() == i;
   }

   public List<br.ucb.calango.arvore.CalangoTree> getChildren() {
      return (List<br.ucb.calango.arvore.CalangoTree>) super.getChildren();
   }

   public void printTree() {
      System.out.println(this.toStringTree());
   }
}
