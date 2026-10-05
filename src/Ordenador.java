/* ============================================================
   PLATAFORMA DE MONITOREO AMBIENTAL URBANO
   Ordenador - VERSION SEMANA 4 (TODO 1 y TODO 2 resueltos)

   Todos los metodos estan instrumentados: cuentan comparaciones
   e intercambios. No quites los contadores.
   ============================================================ */

public class Ordenador {

    private static long comparaciones = 0;
    private static long intercambios = 0;

    public static long getComparaciones() { return comparaciones; }
    public static long getIntercambios()  { return intercambios; }
    public static void reiniciarContadores() { comparaciones = 0; intercambios = 0; }

    // =========================================================
    //  UTILIDADES
    // =========================================================

    private static void intercambiar(LecturaSensor[] datos, int i, int j) {
        LecturaSensor temporal = datos[i];
        datos[i] = datos[j];
        datos[j] = temporal;
        intercambios++;
    }

    /** Compara dos lecturas por su timestamp. */
    private static int comparar(LecturaSensor a, LecturaSensor b) {
        comparaciones++;
        return a.getTimestamp().compareTo(b.getTimestamp());
    }

    /** Compara dos lecturas por su valor de PM2.5. */
    private static int compararPorPm25(LecturaSensor a, LecturaSensor b) {
        comparaciones++;
        return Double.compare(a.getPm25(), b.getPm25());
    }

    // =========================================================
    //  2.7  ALGORITMOS SIMPLES
    // =========================================================

    /**
     * Ordenamiento burbuja.
     *
     * TODO 1 RESUELTO: se agrega una bandera que detecta que en una
     * pasada completa no hubo ningun intercambio. Si el arreglo ya
     * esta ordenado, se corta de inmediato en vez de seguir
     * recorriendolo. Con datos ya ordenados, el costo baja de
     * O(n^2) comparaciones a aproximadamente n-1.
     */
    public static void burbuja(LecturaSensor[] datos) {
        reiniciarContadores();
        int n = datos.length;
        for (int i = 0; i < n - 1; i++) {

            boolean huboIntercambio = false;

            for (int j = 0; j < n - 1 - i; j++) {
                if (comparar(datos[j], datos[j + 1]) > 0) {
                    intercambiar(datos, j, j + 1);
                    huboIntercambio = true;
                }
            }

            if (!huboIntercambio) {
                break;
            }
        }
    }

    /**
     * Ordenamiento por seleccion. Busca el menor y lo pone al inicio.
     * Este metodo esta correcto. Observa cuantos intercambios hace.
     */
    public static void seleccion(LecturaSensor[] datos) {
        reiniciarContadores();
        int n = datos.length;
        for (int i = 0; i < n - 1; i++) {
            int menor = i;
            for (int j = i + 1; j < n; j++) {
                if (comparar(datos[j], datos[menor]) < 0) {
                    menor = j;
                }
            }
            if (menor != i) {
                intercambiar(datos, i, menor);
            }
        }
    }

    /**
     * Ordenamiento por insercion. Inserta cada elemento en su lugar
     * dentro de la parte ya ordenada, como quien organiza cartas.
     * Este metodo esta correcto.
     */
    public static void insercion(LecturaSensor[] datos) {
        reiniciarContadores();
        for (int i = 1; i < datos.length; i++) {
            LecturaSensor actual = datos[i];
            int j = i - 1;
            while (j >= 0 && comparar(datos[j], actual) > 0) {
                datos[j + 1] = datos[j];
                intercambios++;
                j--;
            }
            datos[j + 1] = actual;
        }
    }

    // =========================================================
    //  2.8  ALGORITMOS AVANZADOS
    // =========================================================

    /**
     * MergeSort. Divide el arreglo por la mitad, ordena cada parte
     * y luego fusiona las dos partes ordenadas. Este metodo esta correcto.
     */
    public static void mergeSort(LecturaSensor[] datos) {
        reiniciarContadores();
        LecturaSensor[] auxiliar = new LecturaSensor[datos.length];
        mergeSortRecursivo(datos, auxiliar, 0, datos.length - 1);
    }

    private static void mergeSortRecursivo(LecturaSensor[] datos, LecturaSensor[] aux,
                                           int inicio, int fin) {
        if (inicio >= fin) return;
        int medio = inicio + (fin - inicio) / 2;
        mergeSortRecursivo(datos, aux, inicio, medio);
        mergeSortRecursivo(datos, aux, medio + 1, fin);
        fusionar(datos, aux, inicio, medio, fin);
    }

    private static void fusionar(LecturaSensor[] datos, LecturaSensor[] aux,
                                 int inicio, int medio, int fin) {
        for (int i = inicio; i <= fin; i++) aux[i] = datos[i];
        int izq = inicio, der = medio + 1;
        for (int k = inicio; k <= fin; k++) {
            if (izq > medio) {
                datos[k] = aux[der++];
            } else if (der > fin) {
                datos[k] = aux[izq++];
            } else if (comparar(aux[der], aux[izq]) < 0) {
                datos[k] = aux[der++];
            } else {
                datos[k] = aux[izq++];
            }
            intercambios++;
        }
    }

    /**
     * QuickSort con el PRIMER elemento como pivote.
     *
     * Se conserva TAL CUAL para poder seguir demostrando, en el
     * Experimento 4 - Caso B, por que falla con datos ya ordenados
     * (StackOverflowError). No se modifica porque sirve como evidencia
     * del problema que resuelve quickSortMedianaDeTres().
     */
    public static void quickSortPivotePrimero(LecturaSensor[] datos) {
        reiniciarContadores();
        quickRecursivo(datos, 0, datos.length - 1);
    }

    private static void quickRecursivo(LecturaSensor[] datos, int inicio, int fin) {
        if (inicio >= fin) return;
        int posicionPivote = particionar(datos, inicio, fin);
        quickRecursivo(datos, inicio, posicionPivote - 1);
        quickRecursivo(datos, posicionPivote + 1, fin);
    }

    private static int particionar(LecturaSensor[] datos, int inicio, int fin) {
        LecturaSensor pivote = datos[inicio];
        int limite = inicio;
        for (int i = inicio + 1; i <= fin; i++) {
            if (comparar(datos[i], pivote) < 0) {
                limite++;
                intercambiar(datos, limite, i);
            }
        }
        intercambiar(datos, inicio, limite);
        return limite;
    }

    /**
     * QuickSort con pivote por MEDIANA DE TRES.
     *
     * TODO 2 RESUELTO. En vez de tomar siempre el primer elemento,
     * se comparan el primero, el del medio y el ultimo elemento del
     * segmento, y se usa como pivote el valor intermedio de los tres.
     * Esto evita el peor caso (particiones de tamano n-1) cuando los
     * datos ya llegan ordenados, que es justamente como llegan las
     * lecturas reales de la red (ordenadas por timestamp).
     */
    public static void quickSortMedianaDeTres(LecturaSensor[] datos) {
        reiniciarContadores();
        quickRecursivoMediana(datos, 0, datos.length - 1);
    }

    private static void quickRecursivoMediana(LecturaSensor[] datos, int inicio, int fin) {
        if (inicio >= fin) return;
        int posicionPivote = particionarMedianaDeTres(datos, inicio, fin);
        quickRecursivoMediana(datos, inicio, posicionPivote - 1);
        quickRecursivoMediana(datos, posicionPivote + 1, fin);
    }

    /**
     * Elige la mediana entre el primero, el medio y el ultimo elemento
     * del segmento, la coloca en la posicion "inicio" (para reutilizar
     * exactamente la misma logica de particion que ya funcionaba) y
     * particiona igual que particionar().
     */
    private static int particionarMedianaDeTres(LecturaSensor[] datos, int inicio, int fin) {

        int medio = inicio + (fin - inicio) / 2;

        // Ordena los tres candidatos (inicio, medio, fin) entre si.
        if (comparar(datos[medio], datos[inicio]) < 0) {
            intercambiar(datos, inicio, medio);
        }
        if (comparar(datos[fin], datos[inicio]) < 0) {
            intercambiar(datos, inicio, fin);
        }
        if (comparar(datos[fin], datos[medio]) < 0) {
            intercambiar(datos, medio, fin);
        }

        // Ahora datos[medio] es la mediana de los tres.
        // La movemos a "inicio" para reutilizar particionar() sin cambios.
        intercambiar(datos, inicio, medio);

        return particionar(datos, inicio, fin);
    }

    /**
     * HeapSort. Ordena usando una estructura llamada monticulo.
     * Este metodo esta correcto y funciona como caja negra por ahora.
     */
    public static void heapSort(LecturaSensor[] datos) {
        reiniciarContadores();
        int n = datos.length;
        for (int i = n / 2 - 1; i >= 0; i--) hundir(datos, n, i);
        for (int i = n - 1; i > 0; i--) {
            intercambiar(datos, 0, i);
            hundir(datos, i, 0);
        }
    }

    private static void hundir(LecturaSensor[] datos, int tamano, int raiz) {
        int mayor = raiz;
        int izq = 2 * raiz + 1;
        int der = 2 * raiz + 2;
        if (izq < tamano && comparar(datos[izq], datos[mayor]) > 0) mayor = izq;
        if (der < tamano && comparar(datos[der], datos[mayor]) > 0) mayor = der;
        if (mayor != raiz) {
            intercambiar(datos, raiz, mayor);
            hundir(datos, tamano, mayor);
        }
    }

    // =========================================================
    //  ORDENAR POR OTRO CRITERIO
    // =========================================================

    /**
     * Ordena las lecturas por concentracion de PM2.5, de menor a mayor.
     * Sirve para construir el ranking de estaciones mas contaminadas.
     *
     * TODO 3: este metodo hace exactamente lo que promete y no tiene
     * ningun defecto de codigo. Lo que hay que analizar (Experimento 5)
     * es su EFECTO COLATERAL: al reordenar por PM2.5, el arreglo deja
     * de estar ordenado por timestamp, y la busqueda binaria por
     * timestamp pierde su precondicion aunque este metodo este
     * perfectamente implementado.
     */
    public static void ordenarPorPm25(LecturaSensor[] datos) {
        reiniciarContadores();
        for (int i = 1; i < datos.length; i++) {
            LecturaSensor actual = datos[i];
            int j = i - 1;
            while (j >= 0 && compararPorPm25(datos[j], actual) > 0) {
                datos[j + 1] = datos[j];
                intercambios++;
                j--;
            }
            datos[j + 1] = actual;
        }
    }

    /** Verifica si un arreglo esta ordenado ascendentemente por timestamp. */
    public static boolean estaOrdenadoPorTimestamp(LecturaSensor[] datos) {
        for (int i = 1; i < datos.length; i++) {
            if (datos[i - 1].getTimestamp().compareTo(datos[i].getTimestamp()) > 0) {
                return false;
            }
        }
        return true;
    }
}