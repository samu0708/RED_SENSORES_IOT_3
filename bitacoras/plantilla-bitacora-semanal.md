# Bitacora individual - Semana [XX]

> Copia este archivo y renombralo como `s[XX]-[tu-nombre].md`.
> Completa todas las secciones con tus propias palabras. Esta bitacora es
> individual, aunque el codigo pueda haberse construido en equipo.

## 1. Datos de la actividad

- **Estudiante:** Samuel Medina
- **Equipo:** Samuel
- **Semana:** 3
- **Tema principal:** encontrar un dato
- **Pregunta de la semana:** ¿Cómo encontramos una lectura específica cuando el repositorio pasa de cientos a cientos de miles o millones de registros?
## 2. Prediccion antes de ejecutar

Antes de abrir o ejecutar el programa, responde:

1. **Que creo que va a ocurrir?**
   no se va a ejecutar  correctamente

2. **Que parte del programa o del algoritmo puede fallar?**
   en el main hay metodos llamados que aun no tenemos en el codigo

3. **Como comprobare mi prediccion?**
   ejecutare el main

## 3. Evidencia del laboratorio

### Resultado observado

el codigo no se ejecuto y por las pruebas de esta semana

### Diferencia entre la prediccion y el resultado

fue como lo probe

### Error o comportamiento inesperado

- **Que ocurrio?** el sistema no ejecuto el main
- **Por que ocurrio?** las pruebas de la semana 3
- **Como lo corregimos o que falta corregir?** darle una funcion dentro del codigo a estas pruebas

## 4. Explicacion en lenguaje llano

Explica el concepto principal como se lo explicarias a una persona de doce
anos. Usa entre tres y cinco lineas y evita palabras tecnicas que no expliques.

> como buscarias una aguja en un pajar pues lo mejor es retirar la mayor cantidad de paja mientras que la escaneamos eso es lo que buscamos hacer cuando hablamos de encontrar un dato entre millones.

### Ejemplo o analogia

un ejemplo puede ser buscar la gomita que  te gusta entre miles pues es realmente dificil de no ser que tuvieras una forma de ir retirando dea grandes cantidades escaneando una por una.
## 5. El vacio que encontre

Al intentar explicar el tema, identifica el punto que aun no comprendes bien.

- **Mi duda concreta es:** como funciona el buscador
- **Lo que ya puedo explicar es:** lo que hace el buscador
- **Para resolver la duda consulte:** clase
- **Ahora lo entiendo asi:** el buscador es capaz de ir comparando el indice de los arreglos con un bucle for aunque hay metodos mas efectivos

## 6. Trazado de la solucion

Escoge una ejecucion, recorrido o caso representativo y trazalo paso a paso.
Incluye los valores importantes despues de cada paso.

| Paso | Estado de los datos o estructura                                              | Decision o resultado            |
|---|-------------------------------------------------------------------------------|---------------------------------|
| 1 | el sistema necesita buscar los datos con una funcion que nose ha implementado | el sistema  no pudo  leerlo     |
| 2 | vamos a crear las funciones                                                   | cambiamos los archivos          
| 3 | volvemos a añadir los metodos en el main                                      | el sistema reconoce las pruebas |
| 4 | ya podemos ejecutar main con las nuevas funciones de busqueda                 | se ejecutan las pruebas         |

**Completa o agrega filas si es necesario.** Si trabajaste con una estructura,
dibuja su estado en cada paso o inserta aqui una imagen legible.

## 7. Decision de diseño

Relaciona lo aprendido con la Plataforma de Monitoreo Ambiental Urbano.

- **Problema que debiamos resolver:** la posibilidad de buscar datos
- **Estructura, algoritmo o estrategia elegida:** creamos las nuevas clases y buscador lecturas 
- **Alternativa descartada:** eliminar los accesos en el main
- **Por que elegimos la primera:** ya obtenemos las  nuevas funciones
- **Que evidencia respalda la decision:** el resultado que nos da el main
## 8. Aporte al proyecto

- **Archivo(s) o modulo(s) trabajado(s):** BuscadorLecturas/GeneradorDatos/BancoDePruebas/
- **Cambio realizado:** Creamos las distintaas funciones de busqueda, simulamos datos  con generador datos y realizamos pruebas a nuestras funciones con Banco de Pruebas.
- **Como se conecta con la capa anterior:** Añade una nueva funcion a esa capa que nos permite buscar ademas de la posibilidad de hacer pruebas.
- **Que queda pendiente para la siguiente semana:** Buscar nuevas formas de encontrar datos y almacenar estas busquedas

## 9. Commits realizados

Registra los commits que muestran tu aporte individual.

| Commit                                                     | Mensaje | Que demuestra |
|------------------------------------------------------------|---|---|
| creacion de BuscadorLecturas,GeneradorDatos,Bancodepruebas |Nuevas funciones de busqueda en el codigo|lo trabajado en la guia

## 10. Reexplicacion final

Despues del taller, vuelve a responder la pregunta de la semana en cinco lineas
o menos. Esta respuesta debe ser mas precisa que la de la seccion 4 y debe
incluir la razon de tu decision tecnica.

> ¿Cómo encontramos una lectura específica cuando el repositorio pasa de cientos a cientos de miles o millones de registros?
> utilizamos una funcion capaz de comparar los indices de los arreglos hasta encontrar el valor igual

## 11. Reflexion individual

Responde con honestidad:

1. **Lo que ahora puedo hacer y antes no podia:**
   generar datos random
2. **El error o supuesto que mas me enseno:**
   como sirve el buscador
3. **La pregunta que llevaria a la proxima clase:**
   como  funciona bien generador datos
4. **Que parte del trabajo fue realmente mia:**
   Todo

## Lista de verificacion antes de entregar

- [ x] Escribi la prediccion antes de consultar el resultado.
- [ x] Inclui evidencia concreta del laboratorio.
- [ x] Explique un concepto sin depender de jerga.
- [ x] Registre un vacio, una duda o un error real.
- [ x] Trace al menos un caso paso a paso.
- [ x] Justifique una decision del proyecto y una alternativa descartada.
- [ x] Registre mis commits y mi aporte individual.
- [ x] Deje claro que queda pendiente.
- [ x] Renombre el archivo con el formato `sXX-nombre.md`.
