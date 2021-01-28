package br.ucb.calango.exceptions.erros;

public class AtribuicaoMetodoException extends CalangoRuntimeException {
   private static final long serialVersionUID = 1L;

   public AtribuicaoMetodoException(String nomeMetodo) {
      super(br.ucb.calango.exceptions.erros.AtribuicaoMetodoException.class, nomeMetodo);
   }
}
