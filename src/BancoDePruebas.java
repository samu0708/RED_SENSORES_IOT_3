/* ============================================================
   PLATAFORMA DE MONITOREO AMBIENTAL URBANO
   BancoDePruebas - SEMANA 3

   Esta clase NO tiene main(). Contiene los experimentos de la
   Semana 3, invocados desde el unico main del proyecto en
   IngestaSensores.java. La aplicacion sigue teniendo un solo
   punto de entrada.
   ============================================================ */

public class BancoDePruebas {

    private static final int[] TAMANOS = {1_000, 100_000, 1_000_000};

    /**
     * Experimento 1: busqueda lineal en el peor caso (buscar el
     * ultimo elemento) para distintos tamanos de arreglo.
     */
    public static void experimentoUno() {

        System.out.println("=== EXPERIMENTO 1: BUSQUEDA LINEAL ===");
        System.out.printf(
                "%12s %16s %14s%n", "lecturas", "comparaciones", "tiempo (ms)");

        for (int n : TAMANOS) {

            LecturaSensor[] datos = GeneradorDatos.generar(n);
            String objetivo = GeneradorDatos.timestampEnPosicion(n - 1);

            long inicio = System.nanoTime();
            int posicion = BuscadorLecturas.busquedaLinealPorTimestamp(datos, objetivo);
            long fin = System.nanoTime();

            System.out.printf(
                    "%12d %16d %14.3f%n",
                    n,
                    BuscadorLecturas.getComparaciones(),
                    (fin - inicio) / 1_000_000.0);

            if (posicion < 0) {
                System.out.println("ADVERTENCIA: no encontro una lectura existente.");
            }
        }
        System.out.println();
    }

    /**
     * Experimento 2: compara comparaciones de busqueda lineal vs.
     * binaria buscando el mismo objetivo (el peor caso de la lineal).
     */
    public static void experimentoDos() {

        System.out.println("=== EXPERIMENTO 2: LINEAL vs BINARIA ===");
        System.out.printf(
                "%12s %14s %14s %12s%n", "lecturas", "lineal", "binaria", "relacion");

        for (int n : TAMANOS) {

            LecturaSensor[] datos = GeneradorDatos.generar(n);
            String objetivo = GeneradorDatos.timestampEnPosicion(n - 1);

            BuscadorLecturas.busquedaLinealPorTimestamp(datos, objetivo);
            int lineal = BuscadorLecturas.getComparaciones();

            BuscadorLecturas.busquedaBinariaPorTimestamp(datos, objetivo);
            int binaria = BuscadorLecturas.getComparaciones();

            System.out.printf(
                    "%12d %14d %14d %12.1f%n",
                    n, lineal, binaria, (double) lineal / binaria);
        }
        System.out.println();
    }

    /**
     * Experimento 3: buscar un timestamp que NO existe. Es el peor
     * caso real para ambos algoritmos (ninguno "acierta rapido").
     */
    public static void experimentoTres() {

        System.out.println("=== EXPERIMENTO 3: DATO INEXISTENTE ===");

        LecturaSensor[] datos = GeneradorDatos.generar(100_000);
        String objetivo = GeneradorDatos.timestampInexistente();

        BuscadorLecturas.busquedaLinealPorTimestamp(datos, objetivo);
        int lineal = BuscadorLecturas.getComparaciones();

        BuscadorLecturas.busquedaBinariaPorTimestamp(datos, objetivo);
        int binaria = BuscadorLecturas.getComparaciones();

        System.out.println("Lineal  -> comparaciones: " + lineal);
        System.out.println("Binaria -> comparaciones: " + binaria);
        System.out.println();
    }

    /**
     * Experimento 4: demuestra que la busqueda binaria por PM2.5
     * puede fallar en encontrar valores que si existen, porque el
     * arreglo no esta ordenado por ese campo (precondicion violada).
     */
    public static void experimentoCuatro() {

        System.out.println("=== EXPERIMENTO 4: BINARIA POR PM2.5 ===");

        LecturaSensor[] datos = GeneradorDatos.generar(10_000);

        int aciertosLineal = 0;
        int aciertosBinaria = 0;

        for (int i = 0; i < 20; i++) {

            double valor = datos[i * 137].getPm25();

            int posLineal = -1;
            for (int j = 0; j < datos.length; j++) {
                if (datos[j].getPm25() == valor) {
                    posLineal = j;
                    break;
                }
            }

            int posBinaria = BuscadorLecturas.busquedaBinariaPorPm25(datos, valor);

            if (posLineal >= 0) aciertosLineal++;
            if (posBinaria >= 0) aciertosBinaria++;
        }

        System.out.println("Valores buscados que SI existen: 20");
        System.out.println("Encontrados por busqueda lineal:  " + aciertosLineal);
        System.out.println("Encontrados por busqueda binaria: " + aciertosBinaria);
        System.out.println();
    }
}