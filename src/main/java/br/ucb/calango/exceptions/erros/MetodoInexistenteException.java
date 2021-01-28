package br.ucb.calango.exceptions.erros;

public class MetodoInexistenteException extends CalangoRuntimeException {
   private static final long serialVersionUID = 1L;

   public MetodoInexistenteException(String nomeFuncao) {
      super(br.ucb.calango.exceptions.erros.MetodoInexistenteException.class, nomeFuncao);
   }
}
