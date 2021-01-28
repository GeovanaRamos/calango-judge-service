package br.ucb.calango.exceptions.erros;

import br.ucb.calango.util.AcoesUtil;

public class NegacaoLogicaInvalidaException extends CalangoRuntimeException {
   private static final long serialVersionUID = 1L;

   public NegacaoLogicaInvalidaException(Class<?> c) {
      super(br.ucb.calango.exceptions.erros.NegacaoLogicaInvalidaException.class, AcoesUtil.getTypeName(c));
   }
}
