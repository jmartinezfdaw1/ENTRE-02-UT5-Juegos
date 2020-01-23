import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * La clase representa a una tienda on-line en la
 * que se publican los juegos que se van lanzando al mercado
 * 
 * Un objeto de esta clase guarda en un array los juegos 
 *
 * @author -Jon Martinez
 */
public class RevistaOnLineJuegos 
{
    private String nombre;
    private Juego[] juegos;
    private int total;

    /**
     * Constructor  
     * Crea el array de juegos al tamaño máximo indicado por la constante
     * e inicializa el resto de atributos
     */
    public RevistaOnLineJuegos(String nombre, int n) {
        juegos = new Juego[n];
        this.nombre = nombre;
        total = 0;
    }

    /**
     * Devuelve true si el array está completo, false en otro caso
     */
    public boolean estaCompleta() {
        return total >= juegos.length;
    }

    /**
     *    Añade un nuevo juego solo si el array no está completo y no existe otro juego
     *    ya con el mismo nombre.  Si no se puede añadir se muestra los mensajes adecuados 
     *    (diferentes en cada caso)
     *    
     *    El juego se añade de tal forma que queda insertado en orden alfabético de título
     *    (de menor a mayor)
     *     !!OJO!! No hay que ordenar ni utilizar ningún algoritmo de ordenación
     *    Hay que insertar en orden 
     *    
     */
    public void add(Juego juego) {
        if(estaCompleta()){
            System.out.println("La lista esta completa");
            total --;
        }
        else if(existeJuego(juego.getTitulo()) != -1){
            System.out.println("El juego ya existe");
            total --;
        }
        else{
            if(total == 0){
                juegos[total] = juego;
                System.out.println("El juego se ha metido");
            }else{
                for(int aux = total - 1; aux >= 0; aux--){
                    if( juegos[aux].getTitulo().compareTo(juego.getTitulo()) > 0){
                        juegos[aux + 1] = juegos[aux];
                        juegos[aux] = juego;
                        System.out.println("El juego se ha metido");
                    }else{
                        juegos[aux + 1] = juego;
                        System.out.println("El juego se ha metido");
                        aux = - 1;
                    }
                }
            }
        }
        total ++;
    }

    /**
     * Efectúa una búsqueda en el array del juego cuyo titulo se
     * recibe como parámetro. Es ndiferente mayúsculas y minúsculas
     * Si existe el juego devuelve su posición, si no existe devuelve -1
     */
    public int existeJuego(String titulo) {
        int solucion = - 1;
        for(int i = 0;i < total;i ++){
            if(juegos[i].getTitulo().compareTo(titulo) == 0){
                solucion = i;
            }
        }
        return solucion;

    }

    /**
     * Representación textual de la revista
     * Utiliza StringBuilder como clase de apoyo.
     * Se incluye el nombre de la  revista on-line.
     * (Ver resultados de ejecución)
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < total; i ++){
            if(existeJuego(juegos[i].getTitulo()) >= 0){
                sb.append("Ya esta publicado el juego " + juegos[i].getTitulo() + " en la revista on-line\n");
            }
        }
        sb.append("Los mejores juegos en nuestra revista " + nombre +"(" + total + " juegos)\n");
        for(int h = 0; h < total; h ++){
            sb.append(juegos[h].toString() + "\n--------------------\n");
        }
        return sb.toString();
    }

    /**
     *  Se puntúa el juego de título indicado con 
     *  la puntuación recibida como parámetro. 
     *  La puntuación es un valor entre 1 y 10 (asumimos esto como correcto)
     *  Si el juego no existe se muestra un mensaje en pantalla
     */
    public void puntuar(String titulo, int puntuacion) {
        if(existeJuego(titulo) == -1){
            System.out.println("El juego no existe");
        }
        else{
            juegos[existeJuego(titulo)].puntuar(puntuacion);
        }
    }

    /**
     * Devuelve un array con los nombres de los juegos 
     * con una valoración media mayor a la indicada  
     * 
     * El array se devuelve todo en mayúsculas y ordenado ascendentemente
     */
    public String[] valoracionMayorQue(double valoracion) {
        int totalMasGrande = 0;
        int posmin = 0;
        int aux = 0;
        for(int i = 0;i < total;i ++){
            if(juegos[i].getValoracionMedia() > valoracion){
                totalMasGrande ++;
            }
        }
        String[] masGrande = new String[totalMasGrande];
        for(int j = 0; j < total; j ++){
            if(juegos[j].getValoracionMedia() > valoracion){
                for(int h = 0;h < total;h ++){
                    if(juegos[h].getValoracionMedia() > juegos[j].getValoracionMedia()){
                        aux ++;
                    }
                }
                masGrande[aux] = juegos[j].getTitulo();
                aux = 0;
            }
        }
        return masGrande;
    }

    /**
     * Borrar los juegos del género indicado devolviendo
     * el nº de juegos borradas
     */
    public int borrarDeGenero(Genero genero) {
        int borrados = 0;
        for(int i = 0; i < total;i ++){
            if(genero.equals(juegos[i].getGenero())){
                for(int j = i; j < total - 1; j ++){
                    juegos[j] =juegos[j + 1];
                }
                i --;
                borrados ++;
                total--;
            }
        }
        return borrados;
    }

    /**
     * Lee de un fichero de texto los datos de los juegos
     * con ayuda de un objeto de la  clase Scanner
     * y los guarda en el array. 
     */
    public void leerDeFichero() {
        Scanner sc = null;
        try {
            sc = new Scanner(new File("juegos.txt"));
            while (sc.hasNextLine()) {
                Juego juego = new Juego(sc.nextLine());
                this.add(juego);

            }

        } catch (IOException e) {
            System.out.println("Error al leer del fichero");
        } finally {
            sc.close();
        }

    }

}
