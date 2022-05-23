/**
 * La clase Dato contiene los métodos get para acceder a los atributos e imprimir el archivo
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
     * Se crea el constructor de la clase Dato, y se realiza la declaración e instanciación this.atributo = parametro
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
     * Los métodos publicos get se realizan para acceder a los atributos privados de la clase
     */

    public String getStation() {
        return this.station;
    }

    /**
     * Los métodos publicos get se realizan para acceder a los atributos privados de la clase
     */

    public String getName() {
        return this.name;
    }

    /**
     * Los métodos publicos get se realizan para acceder a los atributos privados de la clase
     */

    public String getDate() {
        return this.date;
    }

    /**
     * Los métodos publicos get se realizan para acceder a los atributos privados de la clase
     */

    public Object getPrcp() {
        return this.prcp;
    }

    /**
     * Los métodos publicos get se realizan para acceder a los atributos privados de la clase
     */

    public Object getTavg() {
        return this.tavg;
    }

    /**
     * Los métodos publicos get se realizan para acceder a los atributos privados de la clase
     */

    public Object getTmax() {
        return this.tmax;
    }

    /**
     * Los métodos publicos get se realizan para acceder a los atributos privados de la clase
     */
    public Object getTmin() {
        return this.tmin;
    }

    /**
     * El método imprimirDato no devuelve nada porque es vacio (void)
     * System.out.printf se usa para dar formato a los datos de salida que están después de la ","
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