/**
 * java.util.* importa todas las clases en el java.util package
 * java.io.* importa todas las clases en el java.io. package
 */

import java.util.*;
import java.io.*;

/**
 * Esta clase hace referencia al DataFrame, aquí se realizan los métodos para las operaciónes estadísticas y de filtro
 */

public class MarcoDeDatos {
    private final ArrayList<Dato> DataSet;
    private final ArrayList<ArrayList<Object>> dsMatrix;
    private final String nombreArchivo;

    /**
     * Los atributos de la clase MarcoDeDatos es un ArrayList de tipo Dato llamado DataSet
     * Un ArrayList de un ArrayList tipo Object (que resulta siendo una matriz) llamado dsMatrix
     * Un String llamado nombreArchivo, todos estos son privados
     * Se crea el contructor de la clase MarcoDeDatos que recibe parámetro de tipo String
     * Declaración e instanciación this.atributo = parametro, para DataSet y dsMatrix se crean ArrayList
     * Se crea una instancia de la clase file la cual importamos y este representa un archivo
     * La clase File se usa para obtener información sobre un archivo, además permite crear y eliminar archivos
     */

    MarcoDeDatos(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
        File archivo = new File(nombreArchivo);

        /**
         * La clase Scanner facilita la lectura de datos
         * El bloque try será el código sobre el que se intentará capturar un error si se produce y una vez capturado hacer algo con él
         */

        try {
            this.DataSet = new ArrayList<>();
            this.dsMatrix = new ArrayList<>();
            Scanner input = new Scanner(archivo);
            String linea;

            /**
             * .hasNextLine() devuelve verdadero si hay otra línea en la entrada de este scanner
             * .split sirve para separar un string o cadena de texto en varios strings
             * Se recorre el arreglo que se creo separando la cadena y se verifica la condición que si:
             * la posición i es igual a ",," o ",,," entonces la posicion i va a ser " "
             */

            while (input.hasNextLine()) {
                linea = input.nextLine();
                String[] arrLinea = linea.split("\"");
                for (int i = 0; i < arrLinea.length; i++) {
                    if (arrLinea[i].equals(",,") || arrLinea[i].equals(",,,")) {
                        arrLinea[i] = " ";
                    }
                }

                /**
                 * Se crea un ArrayList tipo object llamado arrLinea2
                 * Se utiliza un for-each para recorrer el arrLinea
                 * El condicional verifica que sea diferente a "," y sea diferente a "",
                 * se realiza un condicional por si s cumple esNumerico
                 *  la clase  Double es un método que devuelve un nuevo doble inicializado al valor representado por el string
                 *  Si se cumple la condición, se agrega d al array dinámico arrLinea2, sino se agrega s
                 */

                ArrayList<Object> arrLinea2 = new ArrayList<>();
                for (String s : arrLinea) {
                    if (!Objects.equals(s, ",") && !Objects.equals(s, "")) {
                        if (esNumerico(s)) {
                            Double d = Double.parseDouble(s);
                            arrLinea2.add(d);
                        } else {
                            arrLinea2.add(s);
                        }
                    }
                }

                /**
                 * Mientras la longitud de arrLinea2 sea menor que 7, se adiciona " " al array
                 */

                while (arrLinea2.size()<7) {
                    arrLinea2.add(" ");
                }

                /**
                 * Se agrega a this.dsMatrix el nuevo array arrLinea2
                 * Se agrega a this.DataSet lo que esté desde la posición 0 hasta la 6 del arrLinea2
                 */

                this.dsMatrix.add(arrLinea2);
                this.DataSet.add(new Dato((String) arrLinea2.get(0), (String) arrLinea2.get(1), (String) arrLinea2.get(2), arrLinea2.get(3), arrLinea2.get(4), arrLinea2.get(5), arrLinea2.get(6)));
            }
            /**
             * Con el bloque catch se define el tratamiento del problema capturado con el bloque try anterior
             * Es decir, cuando se produce un error o excepción en el código que se encuentra dentro de un bloque try, se pasa al bloque catch.
             * FileNotFoundException -> Excepción generada cuando se intenta abrir un fichero y este no se encuentra en la ruta especificada
             * throw new -> Arrojar una nueva excepción. RuntimeException -> ocurren durante el tiempo de ejecución
             */
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Método get para acceder al atributo privado nombreArchivo
     */

    public String getNombreArchivo() {
        return this.nombreArchivo;
    }

    /**
     * Método para determinar si el dato es numérico
     * Se intenta capturar y tratar un error
     * intenta convertir una cadena en un doble y si captura un error lo pasa al catch
     * NumberFormatException ocurre cuando trata de covertir un String a un valor numérico y se indica que falló la conversión
     */

    public static boolean esNumerico(String s) {
        try {
            double d = Double.parseDouble(s);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    /**
     * Se realiza un for-each para this.DataSet e imprime cada dato del array
     */

    public void imprimirArchivo() {
        for (Dato dato: this.DataSet) {
            dato.imprimirDato();
        }
        System.out.println();
    }

    /**
     * Método para determinar el primer dato numerico
     * Recibe como parámetro un entero que hace referencia a la columna
     * se recorre el arreglo dsMatriz
     * Si lo que está en la posición i en la columna es una instancia de Double
     * entonces primerN = i y se detiene (Es el primer numérico)
     */

    public int primerNumerico(int columna) {
        int primerN=0;
        for (int i = 0; i < this.dsMatrix.size(); i++) {
            if (this.dsMatrix.get(i).get(columna) instanceof Double) {
                primerN = i;
                break;
            }
        }
        return primerN;
    }

    //ESTADISTICAS
    /**
     * Contar cuantos valores numéricos hay en una columna
     * Se recorre el arreglo dsMatrix y si lo que está en la posición i en la columna es una instancia de Double
     * Entonces se aumenta 1 a cuenta, y se cuentan cuantos valores numéricos hay en la columna
     */

    public void Contar(int columna) {
        int cuenta = 0;
        for (int i = 0; i < this.dsMatrix.size(); i++) {
            if(this.dsMatrix.get(i).get(columna) instanceof Double) {
                cuenta++;
            }
        }
        System.out.println("\nHay " + cuenta + " valores numericos en la columna " + (columna+1) + ".\n");
    }

    /**
     * Método para obtener el mínimo
     * Se inicializa en el primer valor numérico de la columna
     * Se recorre el arreglo dsMatrix
     * Si lo que está en la posición i en la columna es una instancia de Double
     * Y lo que está en la posición i en la columna es una instancia de Double es menor que minimo en el arreglo
     * Entonces minimo es i
     */

    public void getMinimo(int columna) {
        int minimo = this.primerNumerico(columna);
        for(int i=minimo; i < this.dsMatrix.size(); i++) {
            if(this.dsMatrix.get(i).get(columna) instanceof Double && (Double) this.dsMatrix.get(i).get(columna) < (Double) this.dsMatrix.get(minimo).get(columna)) {
                minimo = i;
            }
        }
        System.out.println("\nEl valor mínimo de la columna " + (columna+1) + " es " + this.dsMatrix.get(minimo).get(columna) + ".\n");
    }

    /**
     * Método para obtener el máximo
     * Se inicializa en el primer valor numérico de la columna
     * Se recorre el arreglo dsMatrix
     * Si lo que está en la posición i en la columna es una instancia de Double
     * Y lo que está en la posición i en la columna es una instancia de Double es mayor que maximo en el arreglo
     * Entonces maximo es i
     */
    public void getMaximo(int columna) {
        int maximo = this.primerNumerico(columna);
        for(int i=maximo; i < this.dsMatrix.size(); i++) {
            if(this.dsMatrix.get(i).get(columna) instanceof Double && (Double) this.dsMatrix.get(i).get(columna) > (Double) this.dsMatrix.get(maximo).get(columna)) {
                maximo = i;
            }
        }
        System.out.println("\nEl valor máximo de la columna " + (columna+1) + " es " + this.dsMatrix.get(maximo).get(columna) + ".\n");
    }

    /**
     * Método para obtener el promedio
     * Se recorre el arreglo dsMatrix
     * Si lo que está en la posición i en la columna es una instancia de Double
     * Entonces la suma es la suma de todos los valores de i en la columna y el total se incrementa en 1
     * El promedio es la suma sobre el total
     */
    public void getPromedio(int columna) {
        Double suma = 0.0;
        int total = 0;
        for (int i = 0; i < this.dsMatrix.size(); i++) {
            if(this.dsMatrix.get(i).get(columna) instanceof Double) {
                suma += (Double) this.dsMatrix.get(i).get(columna);
                total++;
            }
        }
        System.out.println("\nEl promedio de la columna " + (columna+1) + " es " + suma/total + ".\n");
    }

    /**
     * Método para encontrar la moda, es decir, el valor que más se repite
     *
     * Se crea una matriz que tiene cada valor y el número de ocurrencias de cada valor de la columna que dan como parametro
     * Depués compara los valores de esa columna
     *
     * Recibe la columna de la que tiene que hallar la moda
     * Se crea una arraylist llamado valores de arreglos con Doubles
     * Se crea un un booleano para verificar si los valores que encuentra en la columna de la matriz del data set ya están en el arrayList de valores
     * Se recorre la matriz dsMatrix y si el elemento de la fila i de la columna recibida es una instancia de un doble
     * Entonces recorre el arrayList de arreglos con Doubles y verifica si el elemento ya está en ese array
     * Si cumple la condición yaIncluido es true y se sale del ciclo
     * Si está incluido, aumenta uno en la segunda columna de valores en la fila donde estaría ese valor que ya está incluido
     * Si no está incluido añade un array de Doubles a valores, que tiene el valor en la columna 0 y 1 en la columna 1
     * yaIncluido es false para hacer la verificación del siguiente valor
     */

    public void getModa (int columna) {
        ArrayList<Double []> valores = new ArrayList<>();
        boolean yaIncluido = false;
        for (int i = 0; i < this.dsMatrix.size(); i++) {
            if(this.dsMatrix.get(i).get(columna) instanceof Double) {
                int j;
                for (j=0; j < valores.size(); j++) {
                    if (this.dsMatrix.get(i).get(columna).equals(valores.get(j)[0])) {
                        yaIncluido = true;
                        break;
                    }
                }
                if (yaIncluido) {
                    valores.get(j)[1]++;
                } else {
                    valores.add(new Double[]{(Double) this.dsMatrix.get(i).get(columna), 1.0});
                }
            }
            yaIncluido = false;
        }

        /**
         * Compara las ocurrencias que están en la segunda columna y coge como moda el primer valor de la fila que tenga más repeticiones
         */

        int moda=0;
        for (int i = 0; i < valores.size(); i++) {
            if (valores.get(i)[1] > valores.get(moda)[1]) {
                moda = i;
            }
        }
        System.out.println("\nLa moda de la columna " + (columna+1) + " es " + valores.get(moda)[0] + ", con " + valores.get(moda)[1].intValue() + " ocurrencias.\n");
    }

    //FILTRO

    /**
     * Recibe un entero en la columna, y un valor tipo doble
     * El string es Mayor que
     * Se crea un nuevo ArrayList tipo Dato llamado newDS
     * A newDS se le agrega lo que esté en la posición 0 de this.DataSet
     * Se recorre dsMatrix
     * Si lo que está en la posición i en la columna es una instancia de Double
     * Y lo que está en la posición i en la columna es una instancia de Double es mayor que valor en el arreglo
     * Entonces se le agrega al arreglo lo que esté en la posición i
     */

    public MarcoDeDatos getMayorQue(int columna, Double valor) {
        String metodo = "MayorQue";
        ArrayList<Dato> newDS = new ArrayList<>();
        newDS.add(this.DataSet.get(0));
        for (int i = 0; i < this.dsMatrix.size(); i++) {
            if (this.dsMatrix.get(i).get(columna) instanceof Double && (Double) this.dsMatrix.get(i).get(columna)>valor) {
                newDS.add(this.DataSet.get(i));
            }
        }
        return nuevoArchivo(newDS, columna, valor, metodo);
    }

    /**
     * Recibe un entero en la columna, y un valor tipo doble
     * El string es MayorOIgualQue
     * Se crea un nuevo ArrayList tipo Dato llamado newDS
     * A newDS se le agrega lo que esté en la posición 0 de this.DataSet
     * Se recorre dsMatrix
     * Si lo que está en la posición i en la columna es una instancia de Double
     * Y lo que está en la posición i en la columna es una instancia de Double es mayor o igual que valor en el arreglo
     * Entonces se le agrega al arreglo lo que esté en la posición i
     */

    public MarcoDeDatos getMayorOIgualQue(int columna, Double valor) {
        String metodo = "MayorOIgualQue";
        ArrayList<Dato> newDS = new ArrayList<>();
        newDS.add(this.DataSet.get(0));
        for (int i = 0; i < this.dsMatrix.size(); i++) {
            if (this.dsMatrix.get(i).get(columna) instanceof Double && (Double) this.dsMatrix.get(i).get(columna)>=valor) {
                newDS.add(this.DataSet.get(i));
            }
        }
        return nuevoArchivo(newDS, columna, valor, metodo);
    }

    /**
     * Recibe un entero en la columna, y un valor tipo doble
     * El string es MenorQue
     * Se crea un nuevo ArrayList tipo Dato llamado newDS
     * A newDS se le agrega lo que esté en la posición 0 de this.DataSet
     * Se recorre dsMatrix
     * Si lo que está en la posición i en la columna es una instancia de Double
     * Y lo que está en la posición i en la columna es una instancia de Double es menor que valor en el arreglo
     * Entonces se le agrega al arreglo lo que esté en la posición i
     */

    public MarcoDeDatos getMenorQue(int columna, Double valor) {
        String metodo = "MenorQue";
        ArrayList<Dato> newDS = new ArrayList<>();
        newDS.add(this.DataSet.get(0));
        for (int i = 0; i < this.dsMatrix.size(); i++) {
            if (this.dsMatrix.get(i).get(columna) instanceof Double && (Double) this.dsMatrix.get(i).get(columna)<valor) {
                newDS.add(this.DataSet.get(i));
            }
        }
        return nuevoArchivo(newDS, columna, valor, metodo);
    }

    /**
     * Recibe un entero en la columna, y un valor tipo doble
     * El string es MenorOIgualQue
     * Se crea un nuevo ArrayList tipo Dato llamado newDS
     * A newDS se le agrega lo que esté en la posición 0 de this.DataSet
     * Se recorre dsMatrix
     * Si lo que está en la posición i en la columna es una instancia de Double
     * Y lo que está en la posición i en la columna es una instancia de Double es menor o igual que valor en el arreglo
     * Entonces se le agrega al arreglo lo que esté en la posición i
     */

    public MarcoDeDatos getMenorOIgualQue(int columna, Double valor) {
        String metodo = "MenorOIgualQue";
        ArrayList<Dato> newDS = new ArrayList<>();
        newDS.add(this.DataSet.get(0));
        for (int i = 0; i < this.dsMatrix.size(); i++) {
            if (this.dsMatrix.get(i).get(columna) instanceof Double && (Double) this.dsMatrix.get(i).get(columna)<=valor) {
                newDS.add(this.DataSet.get(i));
            }
        }
        return nuevoArchivo(newDS, columna, valor, metodo);
    }

    /**
     * Recibe un entero en la columna, y un valor tipo doble
     * El string es IgualA
     * Se crea un nuevo ArrayList tipo Dato llamado newDS
     * A newDS se le agrega lo que esté en la posición 0 de this.DataSet
     * Se recorre dsMatrix
     * Si lo que está en la posición i en la columna es una instancia de Double
     * Y lo que está en la posición i en la columna es una instancia de Double es igual que valor en el arreglo
     * Entonces se le agrega al arreglo lo que esté en la posición i
     */

    public MarcoDeDatos getIgualA(int columna, Double valor) {
        String metodo = "IgualA";
        ArrayList<Dato> newDS = new ArrayList<>();
        newDS.add(this.DataSet.get(0));
        for (int i = 0; i < this.dsMatrix.size(); i++) {
            if (this.dsMatrix.get(i).get(columna) instanceof Double && this.dsMatrix.get(i).get(columna)==valor) {
                newDS.add(this.DataSet.get(i));
            }
        }
        return nuevoArchivo(newDS, columna, valor, metodo);
    }

    /**
     * Recibe un entero en la columna, y un valor tipo doble
     * El string es DiferenteDe
     * Se crea un nuevo ArrayList tipo Dato llamado newDS
     * A newDS se le agrega lo que esté en la posición 0 de this.DataSet
     * Se recorre dsMatrix
     * Si lo que está en la posición i en la columna es una instancia de Double
     * Y lo que está en la posición i en la columna es una instancia de Double es diferente que valor en el arreglo
     * Entonces se le agrega al arreglo lo que esté en la posición i
     */

    public MarcoDeDatos getDiferenteDe(int columna, Double valor) {
        String metodo = "DiferenteDe";
        ArrayList<Dato> newDS = new ArrayList<>();
        newDS.add(this.DataSet.get(0));
        for (int i = 0; i < this.dsMatrix.size(); i++) {
            if (this.dsMatrix.get(i).get(columna) instanceof Double && this.dsMatrix.get(i).get(columna)!=valor) {
                newDS.add(this.DataSet.get(i));
            }
        }
        return nuevoArchivo(newDS, columna, valor, metodo);
    }

    /**
     * Todos los metodos de filtro retornaban a nuevoArchivo el array creado, la columna, el valor y el método
     * Así creamos un nuevo marco de datos con los datos originales que cumplían las condiciones planteadas
     * Creamos un nuevo ArrayList de tipo ArrayList<Object> llamado newDSMatrix
     * Recorremos el array newDS
     * Y añadimos a newDSMatrix lo que esté en la posición i de los métodos creados en la clase dato
     */

    public MarcoDeDatos nuevoArchivo(ArrayList<Dato> newDS, int columna, Double valor, String metodo) {
        ArrayList<ArrayList<Object>> newDSMatrix = new ArrayList<>();
        for (int i = 0; i < newDS.size(); i++) {
            newDSMatrix.add(new ArrayList<>());
            newDSMatrix.get(i).add(newDS.get(i).getStation());
            newDSMatrix.get(i).add(newDS.get(i).getName());
            newDSMatrix.get(i).add(newDS.get(i).getDate());
            newDSMatrix.get(i).add(newDS.get(i).getPrcp());
            newDSMatrix.get(i).add(newDS.get(i).getTavg());
            newDSMatrix.get(i).add(newDS.get(i).getTmax());
            newDSMatrix.get(i).add(newDS.get(i).getTmin());
        }

        /**
         * La clase FileWriter se utiliza para escribir datos en forma de caracteres en el archivo
         * El bloque try será el código sobre el que se intentará capturar un error si se produce catch hará su respectivo tratamiento
         *
         * Trata de crear un nuevo archivo csv, primero crea el nombre del archivo y lo inicializa como null
         * Dentro del try lo inicializa como el nombre del archivo desde el que lo está creando
         * Se quita el .csv por lo que se hace un substring de la cadena
         * A la nueva cadena le añade el nombre de la columna, el método y el valor que le habría pasado a ese método para crear el nuevo marco de datos,
         * y se le añade la extensión .csv
         * Se inicializa MyWriter dado el nombre del archivo
         * Se recorre la matriz con los for donde se empieza a añadir cada elemento entre comillas después de las comillas pone una coma
         * y luego de añadir todos los elementos de una fila da un salto de línea
         * Todo esto para crear un archivo .csv
         *
         * El método .write()  es para escribir texto para el achivo que creamos
         * El método .close() cuando este lista la escritura del archivo, esta se debe cerrar
         */

        FileWriter myWriter;
        String name=null;
        try {
            name = this.getNombreArchivo().substring(0, this.getNombreArchivo().length()-4) + newDSMatrix.get(0).get(columna)+metodo+valor+".csv";
            myWriter = new FileWriter(name);
            for (int i = 0; i < newDSMatrix.size(); i++) {
                for (int j = 0; j < newDSMatrix.get(i).size(); j++) {
                    myWriter.write("\"" + newDSMatrix.get(i).get(j) +"\",");
                }
                myWriter.write("\n");
            }
            myWriter.close();

            /**
             * Si atrapa una excepción
             * printStackTrace() imprime la línea exacta en la que el método generó la excepción
             */

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new MarcoDeDatos(name);
    }

}