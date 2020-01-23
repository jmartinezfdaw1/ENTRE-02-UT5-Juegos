/**
 * Punto de entrada a la aplicación
 * 
 * @author - Jon Martinez
 */
public class AppRevistaJuegosOnLine 
{
    public static void main(String[] args)
    {
        if(args.length != 2){
            System.out.println("Error en nº argumentos, Sintaxis: java DemoCD <revista> <n>");
        }else{
            String nombre = args[0];
            int juegos = Integer.parseInt(args[1]);
            RevistaOnLineJuegos revista = new RevistaOnLineJuegos(nombre, juegos);
            revista.leerDeFichero();
            System.out.println(revista.toString());
            revista.puntuar("Planet Zoo", 8);
            revista.puntuar("Steep", 7);
            revista.puntuar("Catastronauts", 9);
            revista.puntuar("Wattam", 9);
            System.out.println(revista.toString());
            revista.valoracionMayorQue(8.2);
            revista.borrarDeGenero(Genero.valueOf("ROL".toUpperCase()));
            System.out.println(revista.toString());
        }
    }
}
