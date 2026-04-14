/**
 * Clase que representa una Pila (Stack) implementada con POO en C# .NET.
 * Sigue el principio LIFO (Last In, First Out).
 * 
 * @author Raul Heredia
 * @version 1.0
 */
using System;

public class Pila<T>
{
    private Nodo<T> cima;
    private int tamanio;

    /**
     * Clase interna Nodo para representar cada elemento de la pila.
     * Utiliza genéricos para aceptar cualquier tipo de dato.
     */
    private class Nodo<U>
    {
        public U dato;
        public Nodo<U> siguiente;

        public Nodo(U dato)
        {
            this.dato = dato;
            this.siguiente = null;
        }
    }

    /**
     * Constructor de la clase Pila.
     * Inicializa la pila vacía.
     */
    public Pila()
    {
        this.cima = null;
        this.tamanio = 0;
    }

    /**
     * Agrega un elemento a la cima de la pila (push).
     * @param dato Elemento a agregar
     */
    public void Push(T dato)
    {
        Nodo<T> nuevoNodo = new Nodo<T>(dato);
        nuevoNodo.siguiente = cima;
        cima = nuevoNodo;
        tamanio++;
        Console.WriteLine($"Elemento apilado: {dato}");
    }

    /**
     * Elimina y retorna el elemento de la cima de la pila (pop).
     * @return Elemento de la cima, o default si la pila está vacía
     */
    public T Pop()
    {
        if (EstaVacia())
        {
            Console.WriteLine("La pila está vacía. No se puede desapilar.");
            return default(T);
        }
        T dato = cima.dato;
        cima = cima.siguiente;
        tamanio--;
        Console.WriteLine($"Elemento desapilado: {dato}");
        return dato;
    }

    /**
     * Retorna el elemento de la cima sin eliminarlo (peek).
     * @return Elemento de la cima, o default si la pila está vacía
     */
    public T Peek()
    {
        if (EstaVacia())
        {
            Console.WriteLine("La pila está vacía. No hay cima.");
            return default(T);
        }
        return cima.dato;
    }

    /**
     * Verifica si la pila está vacía.
     * @return true si está vacía, false en caso contrario
     */
    public bool EstaVacia()
    {
        return cima == null;
    }

    /**
     * Retorna el número de elementos en la pila.
     * @return Tamaño de la pila
     */
    public int GetTamanio()
    {
        return tamanio;
    }

    /**
     * Muestra todos los elementos de la pila.
     */
    public void MostrarPila()
    {
        if (EstaVacia())
        {
            Console.WriteLine("La pila está vacía.");
            return;
        }

        Console.WriteLine("=== PILA ACTUAL ===");
        Console.WriteLine($"Tamaño: {tamanio}");
        Console.WriteLine("CIMA ->");
        
        Nodo<T> actual = cima;
        while (actual != null)
        {
            Console.WriteLine($"  [{actual.dato}]");
            actual = actual.siguiente;
        }
        
        Console.WriteLine("<- FONDO");
        Console.WriteLine("==================\n");
    }

    /**
     * Limpia la pila, eliminando todos los elementos.
     */
    public void Limpiar()
    {
        cima = null;
        tamanio = 0;
        Console.WriteLine("Pila limpiada.");
    }
}
