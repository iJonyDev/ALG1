# Recursividad
## Indice:
-  [Introduccion](#intro)
-  []()
-  []()
-  []()

## Introduccion
- La recursividad es una tecnica de programacion que aparece de forma natural en programacion, tanto en la definicion de tipos de datos como en el diseñode algoritmos.
- Consiste en que el algoritmo de llama a si mismos con un problema de menor tamaño y asi sucesivamente hasta que la solucion es inmediata.
- Algoritmos mas simples y compactos que los iterativos pero su ejecucion es mas lenta y consumen mas recursos.
- **Ambito de aplicacion**
  * General
  * Problemas que pueden resolverse de forma directa a partir de ciertos tamaños del problema planteado.
- **Razones de uso**
  * Problemas complejos con estructuras iterativas.
  * Soluciones elegantes.
  * Soluciones simples.
- **Condicion necesaria**: Asignacion dinamica de memoria.
### Caracteristicas basicas
- **Autoinvocacion** (caso general): el proceso se llama a si mismo
- **Caso directo** (caso base): el problema puede resolverse para casos de menor tamaño
- **Combinacion**: los resultados de la llamada precedente son utilizados para obtener una solucion, posiblemente combinados con otros datos.
### Ejemplo: calculo del factorial

![alt text](<Screenshot 2024-07-09 at 22.35.46.png>)
```java
// Algoritmo iterativo
int factorial(int n){
  int i, r = 1; {
  for (i = 0; i < n; i + +);
    r = r · (n − i);
  }
  return r;
}
```

## Verificacion de procedidmientos recursivos
## Escritura de programas recursivos
## Funcionamiento de la recursividad
