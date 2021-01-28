package br.ucb.calango.exceptions.erros;

public class NumeroInvalidoException extends CalangoRuntimeException {
   private static final long serialVersionUID = 1L;

   public NumeroInvalidoException(String numero) {
      super(br.ucb.calango.exceptions.erros.NumeroInvalidoException.class, numero);
   }
}
