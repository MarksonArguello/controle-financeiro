import { FormGroup } from '@angular/forms';

export class Utils {
  private static readonly LOCALE = 'pt-BR';

  public formatarMoeda(valor: number): string {
    return valor.toLocaleString(Utils.LOCALE, {
      style: 'currency',
      currency: 'BRL',
    });
  }

  public formatarData(data: Date): string {
    return data.toLocaleDateString(Utils.LOCALE);
  }

  public capitalize(categoria: string): string {
    if (!categoria) return '';

    return (
      categoria.charAt(0).toUpperCase() + categoria.slice(1).toLocaleLowerCase()
    );
  }

  private static parteInteira(valor: number): string {
    return Math.trunc(valor).toString();
  }

  private static parteDecimal(valor: number): string {
    const partes = valor.toString().split('.');
    return partes.length > 1 ? partes[1] : '';
  }

  private static arredondaParteDecimal(
    valor: number,
    MAX_DECIMAL_LEN: number
  ): string {
    if (!valor) return '';
    if (Utils.parteDecimal(valor).length <= MAX_DECIMAL_LEN)
      return valor.toString();
    return valor.toString().slice(0, -1);
  }

  public static mantemValorFormatado(
    valorDigitado: number,
    formulario: FormGroup<any>
  ): void {
    if (!valorDigitado) return;

    if (isNaN(valorDigitado)) {
      formulario.patchValue({
        valor: valorDigitado.toString().slice(0, -1),
      });
    }

    const MAX_INT_LEN = 5;
    const MAX_DECIMAL_LEN = 2;
    if (Utils.parteInteira(valorDigitado).length > MAX_INT_LEN) {
      formulario
        .get('valor')
        ?.setValue(valorDigitado.toString().slice(0, MAX_INT_LEN));
    }

    if (Utils.parteDecimal(valorDigitado).length > MAX_DECIMAL_LEN) {
      formulario.patchValue({
        valor: Utils.arredondaParteDecimal(valorDigitado, MAX_DECIMAL_LEN),
      });
    }
  }
}
