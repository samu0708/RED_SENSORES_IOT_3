/* ============================================================
   PLATAFORMA DE MONITOREO AMBIENTAL URBANO
   GeneradorDatos - SEMANA 3

   Genera lecturas sinteticas en memoria para poder experimentar
   con tamanos (1.000 / 100.000 / 1.000.000) que no tendria
   sentido guardar en un CSV real.

   Los timestamps se generan en orden cronologico ascendente
   segun la posicion del arreglo: eso es lo que hace valida la
   precondicion de la busqueda binaria por timestamp. El PM2.5,
   en cambio, se genera al azar y por lo tanto NO queda ordenado.
   ============================================================ */

import java.util.Random;

public class GeneradorDatos {

    private static final int NUM_ESTACIONES = 9;
    private static final long SEMILLA = 20262L;

    /**
     * Genera n lecturas. El timestamp aumenta con la posicion
     * del arreglo (posicion 0 = timestamp mas antiguo).
     */
    public static LecturaSensor[] generar(int n) {

        Random azar = new Random(SEMILLA);
        LecturaSensor[] datos = new LecturaSensor[n];

        for (int i = 0; i < n; i++) {

            String id = String.format(
                    "EST-%03d", (i % NUM_ESTACIONES) + 1);

            String timestamp = String.format("%010d", i);

            double temperatura = 11 + azar.nextDouble() * 18;
            double humedad = 55 + azar.nextDouble() * 35;
            double pm25 = 5 + azar.nextDouble() * 55;

            datos[i] = new LecturaSensor(
                    id,
                    timestamp,
                    redondear(temperatura),
                    redondear(humedad),
                    redondear(pm25));
        }

        return datos;
    }

    private static double redondear(double valor) {
        return Math.round(valor * 10.0) / 10.0;
    }

    /**
     * Devuelve el timestamp exacto que ocupa esa posicion en
     * un arreglo generado por generar(n). Util para probar el
     * peor caso (buscar el ultimo elemento).
     */
    public static String timestampEnPosicion(int posicion) {
        return String.format("%010d", posicion);
    }

    /**
     * Devuelve un timestamp que nunca existe en los arreglos
     * generados por esta clase.
     */
    public static String timestampInexistente() {
        return "9999999999";
    }
}