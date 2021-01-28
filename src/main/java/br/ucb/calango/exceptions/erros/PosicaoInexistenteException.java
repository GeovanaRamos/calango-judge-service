package br.ucb.calango.exceptions.erros;

public class PosicaoInexistenteException extends CalangoRuntimeException {
   private static final long serialVersionUID = 1L;

   public PosicaoInexistenteException(int posicao, int limiteDoVetor) {
      super(br.ucb.calango.exceptions.erros.PosicaoInexistenteException.class, posicao, limiteDoVetor);
   }
}
