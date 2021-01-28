package br.ucb.calango.exceptions.erros;

public class FuncaoNaoRetornouException extends CalangoRuntimeException {
   private static final long serialVersionUID = 1L;

   public FuncaoNaoRetornouException(String nomeFuncao) {
      super(br.ucb.calango.exceptions.erros.FuncaoNaoRetornouException.class, nomeFuncao);
   }
}
