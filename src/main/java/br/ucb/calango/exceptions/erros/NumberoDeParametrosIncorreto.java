package br.ucb.calango.exceptions.erros;

public class NumberoDeParametrosIncorreto extends CalangoRuntimeException {
   private static final long serialVersionUID = 1L;

   public NumberoDeParametrosIncorreto(Integer quantidadeReal, Integer quantidadePassada) {
      super(br.ucb.calango.exceptions.erros.NumberoDeParametrosIncorreto.class, quantidadeReal, quantidadePassada);
   }
}
