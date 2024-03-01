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

  public static mantemValorFormatado(
    valorDigitado: string,
    formulario: FormGroup<any>
  ): void {
    if (!valorDigitado) return;

    const MAX_INT_LEN = 5;
    const MAX_DECIMAL_LEN = 2;
    if (valorDigitado.toString().split('.')[0].length > MAX_INT_LEN) {
      formulario
        .get('valor')
        ?.setValue(valorDigitado.toString().slice(0, MAX_INT_LEN));
    }
    // Check if the value has more than 2 decimal places, if so, round it to 2 decimal places
    if (
      valorDigitado &&
      valorDigitado.toString().split('.')[1]?.length > MAX_DECIMAL_LEN
    ) {
      const valorFormatado = parseFloat(valorDigitado).toFixed(MAX_DECIMAL_LEN);
      formulario.get('valor')?.setValue(valorFormatado);
    }
  }
}
