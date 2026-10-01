/* ============================================================
   PLATAFORMA DE MONITOREO AMBIENTAL URBANO
   BuscadorLecturas - SEMANA 3

   Contiene los algoritmos de busqueda utilizados por el
   proyecto: busqueda lineal (no requiere orden) y busqueda
   binaria (requiere que los datos esten ordenados por el
   campo consultado).
   ============================================================ */

public class BuscadorLecturas {

    /**
     * Cantidad de comparaciones realizadas por la ultima busqueda.
     * Sirve como evidencia del costo algoritmico, independiente
     * del tiempo de ejecucion (que varia por maquina, SO, cache, etc).
     */
    private static int comparaciones = 0;

    public static int getComparaciones() {
        return comparaciones;
    }

    /**
     * Busqueda lineal por timestamp.
     * NO requiere que los datos esten ordenados.
     *
     * @return posicion de la lectura o -1 si no existe
     */
    public static int busquedaLinealPorTimestamp(
            LecturaSensor[] datos, String timestamp) {

        comparaciones = 0;

        for (int i = 0; i < datos.length; i++) {
            comparaciones++;
            if (datos[i].getTimestamp().equals(timestamp)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Busca la primera lectura de una estacion (busqueda lineal).
     *
     * IMPORTANTE: usamos .equals(), no ==, porque idSensor es String
     * y == compara referencias, no contenido.
     *
     * @return posicion de la primera coincidencia o -1
     */
    public static int buscarPorEstacion(
            LecturaSensor[] datos, String idSensor) {

        comparaciones = 0;

        for (int i = 0; i < datos.length; i++) {
            comparaciones++;
            if (datos[i].getIdSensor().equals(idSensor)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Busqueda binaria por timestamp.
     *
     * PRECONDICION: las lecturas deben estar ordenadas
     * ascendentemente por timestamp. GeneradorDatos garantiza
     * esta condicion.
     *
     * @return posicion de la lectura o -1 si no existe
     */
    public static int busquedaBinariaPorTimestamp(
            LecturaSensor[] datos, String timestamp) {

        comparaciones = 0;

        int inicio = 0;
        int fin = datos.length - 1;

        while (inicio <= fin) {

            int medio = (inicio + fin) / 2;
            comparaciones++;

            int comparacion =
                    datos[medio].getTimestamp().compareTo(timestamp);

            if (comparacion == 0) {
                return medio;
            }

            if (comparacion < 0) {
                inicio = medio + 1; // medio ya se comparo, no repetirlo
            } else {
                fin = medio - 1;
            }
        }

        return -1;
    }

    /**
     * Busqueda binaria por PM2.5.
     *
     * PRECONDICION: el arreglo debe estar ordenado ascendentemente
     * por PM2.5. GeneradorDatos NO garantiza esta condicion (el
     * valor se genera al azar), por lo que este metodo puede fallar
     * en encontrar valores que si existen. Ese es precisamente el
     * punto del Experimento 4: un algoritmo correcto aplicado sobre
     * datos que no cumplen su precondicion produce resultados
     * incorrectos.
     *
     * @return posicion de la lectura o -1 si no se encuentra
     */
    public static int busquedaBinariaPorPm25(
            LecturaSensor[] datos, double pm25) {

        comparaciones = 0;

        int inicio = 0;
        int fin = datos.length - 1;

        while (inicio <= fin) {

            int medio = (inicio + fin) / 2;
            comparaciones++;

            if (datos[medio].getPm25() == pm25) {
                return medio;
            }

            if (datos[medio].getPm25() < pm25) {
                inicio = medio + 1;
            } else {
                fin = medio - 1;
            }
        }

        return -1;
    }
}