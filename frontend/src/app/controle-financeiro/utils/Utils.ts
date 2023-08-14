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
}
