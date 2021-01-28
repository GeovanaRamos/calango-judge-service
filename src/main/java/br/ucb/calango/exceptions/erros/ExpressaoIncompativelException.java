package br.ucb.calango.exceptions.erros;

import br.ucb.calango.util.AcoesUtil;

public class ExpressaoIncompativelException extends CalangoRuntimeException {
   private static final long serialVersionUID = 1L;

   public ExpressaoIncompativelException(Class<?> c) {
      super(br.ucb.calango.exceptions.erros.ExpressaoIncompativelException.class, AcoesUtil.getTypeName(c));
   }
}
