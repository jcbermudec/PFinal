/**
 * java.util.* importa todas las clases en el java.util package
 * java.io.* importa todas las clases en el java.io. package
 */

import java.util.*;
import java.io.*;

/**
 * Esta clase contiene los menú y la creación de objetos para la interacción con el usuario y el llamado de los métodos
 */

public class Main {
    /**
     * while(true) es un while infinito
     * System.getProperty() ->  las propiedades del sistema se inicializan para contener información sobre el entorno de ejecución
     * carpeta -> user.dir -> Directorio de trabajo del usuario
     *
     * Un nuevo array tipo File llamado listaDeArchivos que es igual a un array de archivos de carpeta
     *
     * Se recorre con un for-each a ListaDeArchivos
     * Determina si el elemento del array es un archivo del directorio y si ese archivo termina con .csv
     *
     * Para crear un nuevo marco de datos del archivo se instancia archivo1 que es del tipo MarcoDeDatos
     * imprime el archivo1
     *
     * Se despliega un menu para la interacción con el usuario donde se dan las opciones del sistema
     * Si es 0 vuelva a la lista de archivos
     * Si es diferente de 1 y diferente de 2 cierra el ciclo
     *
     * Se pide la columna y se le resta 1 porque empezamos a contar desde el 0
     * Se determina el menú del tipo de operaciones que se pueden realizar
     * Dependiendo el número ingresado se llama al método y se le envía como parámetro la columna
     *
     * Se despliega un nuevo menú con más opciones para el usuario
     * El usuario ingresa un número por teclado dependiendo que función quiere
     * Se crea un nuveo objeto llamado MarcoDeDatos que inicialmente es null
     * Dependiendo el número ingresado se realiza una operación
     * El archivo 2 se crea desde el archivo 1 y se llama el respectivo método
     * A este método se le envía la columna y el valor ingresados por el usuario
     */

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        /**
         * while(true) es un while infinito
         * System.getProperty() ->  las propiedades del sistema se inicializan para contener información sobre el entorno de ejecución
         * carpeta -> user.dir -> Directorio de trabajo del usuario
         *
         * Un nuevo array tipo File llamado listaDeArchivos que es igual a un array de archivos de carpeta
         *
         * Se recorre con un for-each a ListaDeArchivos
         * Determina si el elemento del array es un archivo del directorio y si ese archivo termina con .csv
         */
        ciclo:
        while (true) {
            File carpeta = new File(System.getProperty("user.dir"));
            File[] listaDeArchivos = carpeta.listFiles();
            System.out.println("Archivos:");

            for (File archivo : listaDeArchivos) {
                if (archivo.isFile() && archivo.getName().endsWith(".csv")) {
                    System.out.println("    " + archivo.getName());
                }
            }

            /**
             * Para crear un nuevo marco de datos del archivo se instancia archivo1 que es del tipo MarcoDeDatos
             * imprime el archivo1
             */

            System.out.println();
            System.out.print("Crear un marco de datos del archivo: ");
            String archivo = sc.next();
            System.out.println();
            MarcoDeDatos archivo1 = new MarcoDeDatos(archivo);
            archivo1.imprimirArchivo();

            /**
             * Se despliega un menu para la interacción con el usuario donde se dan las opciones del sistema
             * Si es 0 vuelva a la lista de archivos
             * Si es diferente de 1 y diferente de 2 cierra el ciclo
             */
            anidado:
            while (true) {
                System.out.println("El archivo sobre el que se está operando es " + archivo1.getNombreArchivo() + ".\n");
                System.out.println("Tipos de Operación:");
                System.out.println("    0. Volver a lista de archivos");
                System.out.println("    1. Estadísticas");
                System.out.println("    2. Filtro");
                System.out.print("\nEscribe el número del tipo de operación que deseas realizar o cualquier otro número para detener el programa: ");
                int tipoOperacion = sc.nextInt();

                if (tipoOperacion==0) {
                    break;
                } else if (tipoOperacion != 1 && tipoOperacion != 2) {
                    break ciclo;
                }

                /**
                 * Se pide la columna y se le resta 1 porque empezamos a contar desde el 0
                 */
                System.out.print("\nEscribe el número de la columna sobre la cual deseas realizar la operación (las operaciones solo pueden ser realizadas sobre columnas numéricas): ");
                int columna = sc.nextInt()-1;

                if (columna<3 || columna>6) {
                    System.out.println("\nColumna no numérica o inexistente.\n");
                    break;
                }

                int operacion;

                /**
                 * Se determina el menú del tipo de operaciones que se pueden realizar
                 */

                switch (tipoOperacion) {
                    case 1:
                        System.out.println("\nOperaciones:");
                        System.out.println("    1. Contar");
                        System.out.println("    2. Minimo");
                        System.out.println("    3. Máximo");
                        System.out.println("    4. Promedio");
                        System.out.println("    5. Moda");
                        System.out.print("\nEscribe el número de la operación que deseas realizar: ");
                        operacion = sc.nextInt();

                        /**
                         * Dependiendo el número ingresado se llama al método y se le envía como parámetro la columna
                         */

                        switch (operacion) {
                            case 1:
                                archivo1.Contar(columna);
                                break;
                            case 2:
                                archivo1.getMinimo(columna);
                                break;
                            case 3:
                                archivo1.getMaximo(columna);
                                break;
                            case 4:
                                archivo1.getPromedio(columna);
                                break;
                            case 5:
                                archivo1.getModa(columna);
                                break;
                            default:
                                System.out.println("\nOperación no válida");
                                break anidado;
                        }
                        break;

                    case 2:
                        /**
                         * Se despliega un nuevo menú con más opciones para el usuario
                         * El usuario ingresa un número por teclado dependiendo que función quiere
                         */
                        System.out.println("\nOperaciones:");
                        System.out.println("    1. Menor que");
                        System.out.println("    2. Mayor que");
                        System.out.println("    3. Menor o igual que");
                        System.out.println("    4. Mayor o igual que");
                        System.out.println("    5. Igual a");
                        System.out.println("    6. Diferente de");
                        System.out.print("\nEscribe el número de la operación que deseas realizar: ");
                        operacion = sc.nextInt();
                        System.out.print("\nEscribe el valor con el cual deseas realizar la operación: ");
                        double valor = sc.nextDouble();
                        System.out.println();

                        /**
                         * Se crea un nuveo objeto llamado MarcoDeDatos que inicialmente es null
                         */

                        MarcoDeDatos archivo2 = null;

                        /**
                         * Dependiendo el número ingresado se realiza una operación
                         * El archivo 2 se crea desde el archivo 1 y se llama el respectivo método
                         * A este método se le envía la columna y el valor ingresados por el usuario
                         */

                        switch (operacion) {
                            case 1:
                                archivo2 = archivo1.getMenorQue(columna, valor);
                                break;
                            case 2:
                                archivo2 = archivo1.getMayorQue(columna, valor);
                                break;
                            case 3:
                                archivo2 = archivo1.getMenorOIgualQue(columna, valor);
                                break;
                            case 4:
                                archivo2 = archivo1.getMayorOIgualQue(columna, valor);
                                break;
                            case 5:
                                archivo2 = archivo1.getIgualA(columna, valor);
                                break;
                            case 6:
                                archivo2 = archivo1.getDiferenteDe(columna, valor);
                                break;
                            default:
                                System.out.println("\nOperación no válida");
                                break anidado;
                        }
                        archivo2.imprimirArchivo();
                        break;
                }
            }
        }
    }
}