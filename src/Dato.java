/**
 * La clase Dato contiene los m�todos get para acceder a los atributos e imprimir el archivo
 */

public class Dato {
    private final String station;
    private final String name;
    private final String date;
    private final Object prcp;
    private final Object tavg;
    private final Object tmax;
    private final Object tmin;

    /**
     * Se crean los atributos privados, que son el encabezado de los archivos
     * En java todas las clases heredan de Object, por lo que hay algunos atributos de este tipo
     * Se crea el constructor de la clase Dato, y se realiza la declaraci�n e instanciaci�n this.atributo = parametro
     * Se usa la palabra reservada this
     */

    Dato (String station, String name, String date, Object prcp, Object tavg, Object tmax, Object tmin) {
        this.station = station;
        this.name = name;
        this.date = date;
        this.prcp = prcp;
        this.tavg = tavg;
        this.tmax = tmax;
        this.tmin = tmin;
    }

    /**
     * Los m�todos publicos get se realizan para acceder a los atributos privados de la clase
     */

    public String getStation() {
        return this.station;
    }

    /**
     * Los m�todos publicos get se realizan para acceder a los atributos privados de la clase
     */

    public String getName() {
        return this.name;
    }

    /**
     * Los m�todos publicos get se realizan para acceder a los atributos privados de la clase
     */

    public String getDate() {
        return this.date;
    }

    /**
     * Los m�todos publicos get se realizan para acceder a los atributos privados de la clase
     */

    public Object getPrcp() {
        return this.prcp;
    }

    /**
     * Los m�todos publicos get se realizan para acceder a los atributos privados de la clase
     */

    public Object getTavg() {
        return this.tavg;
    }

    /**
     * Los m�todos publicos get se realizan para acceder a los atributos privados de la clase
     */

    public Object getTmax() {
        return this.tmax;
    }

    /**
     * Los m�todos publicos get se realizan para acceder a los atributos privados de la clase
     */
    public Object getTmin() {
        return this.tmin;
    }

    /**
     * El m�todo imprimirDato no devuelve nada porque es vacio (void)
     * System.out.printf se usa para dar formato a los datos de salida que est�n despu�s de la ","
     * Se formatea la cadena para dejar cierto espacio y alinearla a la izquierda
     */

    public void imprimirDato() {
        System.out.printf("%-15s", this.getStation());
        System.out.printf("%-22s", this.getName());
        System.out.printf("%-15s", this.getDate());
        System.out.printf("%-10s", this.getPrcp());
        System.out.printf("%-10s", this.getTavg());
        System.out.printf("%-10s", this.getTmax());
        System.out.printf("%-10s", this.getTmin());
        System.out.println();
    }
}