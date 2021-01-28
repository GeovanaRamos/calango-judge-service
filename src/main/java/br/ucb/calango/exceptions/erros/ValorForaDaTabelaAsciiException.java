package br.ucb.calango.exceptions.erros;

public class ValorForaDaTabelaAsciiException extends CalangoRuntimeException {
   private static final long serialVersionUID = 1L;

   public ValorForaDaTabelaAsciiException(int valor) {
      super(br.ucb.calango.exceptions.erros.ValorForaDaTabelaAsciiException.class, valor);
   }
}
