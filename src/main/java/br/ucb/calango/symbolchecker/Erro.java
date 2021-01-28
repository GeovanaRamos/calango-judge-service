package br.ucb.calango.symbolchecker;

public class Erro implements Comparable<br.ucb.calango.symbolchecker.Erro> {
   private Integer linha;
   private String mensagem;

   public Erro(Integer linha, String mensagem) {
      this.linha = linha;
      this.mensagem = mensagem;
   }

   public Integer getLinha() {
      return this.linha;
   }

   public String getMensagem() {
      return this.mensagem;
   }

   public int compareTo(br.ucb.calango.symbolchecker.Erro o) {
      return this.getLinha().compareTo(o.getLinha());
   }
}
