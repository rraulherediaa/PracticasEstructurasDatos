/**
 * Clase principal para demostrar el uso de la Pila implementada con POO en C# .NET.
 * Incluye diferentes casos de uso y ejemplos con entrada de datos por consola.
 * 
 * @author Raul Heredia
 * @version 1.0
 */
using System;

class Program
{
    static void Main(string[] args)
    {
        Console.WriteLine("==========================================");
        Console.WriteLine("   EXAMEN PRIMER PARCIAL - PILAS EN C#");
        Console.WriteLine("   Implementación con POO");
        Console.WriteLine("==========================================\n");

        // CASO 1: Pila de enteros
        Console.WriteLine("--- CASO 1: PILA DE ENTEROS ---");
        Pila<int> pilaEnteros = new Pila<int>();
        Console.Write("Ingrese cantidad de enteros a apilar: ");
        int cantidadEnteros = 0;
        try
        {
            cantidadEnteros = int.Parse(Console.ReadLine());
        }
        catch
        {
            Console.WriteLine("Error: Debe ingresar un número válido.");
        }
        
        for (int i = 0; i < cantidadEnteros; i++)
        {
            Console.Write($"Ingrese entero #{i + 1}: ");
            int num = 0;
            try
            {
                num = int.Parse(Console.ReadLine());
            }
            catch
            {
                Console.WriteLine("Error: Debe ingresar un número válido.");
            }
            pilaEnteros.Push(num);
        }
        pilaEnteros.MostrarPila();
        Console.Write("¿Desea desapilar un elemento? (s/n): ");
        string opcion = Console.ReadLine().ToLower();
        if (opcion == "s")
        {
            pilaEnteros.Pop();
            pilaEnteros.MostrarPila();
            Console.WriteLine($"Cima actual: {pilaEnteros.Peek()}");
        }
        Console.WriteLine();

        // CASO 2: Pila de strings
        Console.WriteLine("--- CASO 2: PILA DE STRINGS ---");
        Pila<string> pilaStrings = new Pila<string>();
        Console.Write("Ingrese cantidad de strings a apilar: ");
        int cantidadStrings = 0;
        try
        {
            cantidadStrings = int.Parse(Console.ReadLine());
        }
        catch
        {
            Console.WriteLine("Error: Debe ingresar un número válido.");
        }
        
        for (int i = 0; i < cantidadStrings; i++)
        {
            Console.Write($"Ingrese string #{i + 1}: ");
            string texto = Console.ReadLine();
            pilaStrings.Push(texto);
        }
        pilaStrings.MostrarPila();
        Console.Write("¿Cuántos elementos desea desapilar? ");
        int cantidadDesapilar = 0;
        try
        {
            cantidadDesapilar = int.Parse(Console.ReadLine());
        }
        catch
        {
            Console.WriteLine("Error: Debe ingresar un número válido.");
        }
        for (int i = 0; i < cantidadDesapilar; i++)
        {
            Console.WriteLine($"Desapilando: {pilaStrings.Pop()}");
        }
        pilaStrings.MostrarPila();
        Console.WriteLine();

        // CASO 3: Pila de objetos personalizados
        Console.WriteLine("--- CASO 3: PILA DE OBJETOS PERSONA ---");
        Pila<Persona> pilaPersonas = new Pila<Persona>();
        Console.Write("Ingrese cantidad de personas a apilar: ");
        int cantidadPersonas = 0;
        try
        {
            cantidadPersonas = int.Parse(Console.ReadLine());
        }
        catch
        {
            Console.WriteLine("Error: Debe ingresar un número válido.");
        }
        
        for (int i = 0; i < cantidadPersonas; i++)
        {
            Console.Write($"Ingrese nombre #{i + 1}: ");
            string nombre = Console.ReadLine();
            Console.Write($"Ingrese edad #{i + 1}: ");
            int edad = 0;
            try
            {
                edad = int.Parse(Console.ReadLine());
            }
            catch
            {
                Console.WriteLine("Error: Debe ingresar un número válido.");
            }
            pilaPersonas.Push(new Persona(nombre, edad));
        }
        pilaPersonas.MostrarPila();
        Console.WriteLine($"Cima: {pilaPersonas.Peek()}");
        pilaPersonas.Pop();
        pilaPersonas.MostrarPila();
        Console.WriteLine();

        // CASO 4: Pila vacía - manejo de errores
        Console.WriteLine("--- CASO 4: PILA VACÍA - MANEJO DE ERRORES ---");
        Pila<double> pilaVacia = new Pila<double>();
        Console.WriteLine($"¿Está vacía? {pilaVacia.EstaVacia()}");
        Console.WriteLine("Intentando desapilar de pila vacía...");
        pilaVacia.Pop();
        Console.WriteLine("Intentando ver cima de pila vacía...");
        pilaVacia.Peek();
        Console.WriteLine();

        // CASO 5: Pila con muchos elementos
        Console.WriteLine("--- CASO 5: PILA CON MUCHOS ELEMENTOS ---");
        Pila<int> pilaGrande = new Pila<int>();
        Console.Write("Ingrese cantidad de elementos a apilar: ");
        int cantidadGrande = 0;
        try
        {
            cantidadGrande = int.Parse(Console.ReadLine());
        }
        catch
        {
            Console.WriteLine("Error: Debe ingresar un número válido.");
        }
        
        for (int i = 1; i <= cantidadGrande; i++)
        {
            pilaGrande.Push(i * 10);
        }
        pilaGrande.MostrarPila();
        Console.WriteLine($"Tamaño: {pilaGrande.GetTamanio()}");

        Console.Write("¿Cuántos elementos desea desapilar? ");
        int cantidadDesapilarGrande = 0;
        try
        {
            cantidadDesapilarGrande = int.Parse(Console.ReadLine());
        }
        catch
        {
            Console.WriteLine("Error: Debe ingresar un número válido.");
        }
        Console.WriteLine($"Desapilando {cantidadDesapilarGrande} elementos:");
        for (int i = 0; i < cantidadDesapilarGrande; i++)
        {
            pilaGrande.Pop();
        }
        pilaGrande.MostrarPila();
        Console.WriteLine();

        // CASO 6: Limpiar pila
        Console.WriteLine("--- CASO 6: LIMPIAR PILA ---");
        Pila<string> pilaLimpiar = new Pila<string>();
        Console.Write("Ingrese cantidad de elementos a apilar: ");
        int cantidadLimpiar = 0;
        try
        {
            cantidadLimpiar = int.Parse(Console.ReadLine());
        }
        catch
        {
            Console.WriteLine("Error: Debe ingresar un número válido.");
        }
        
        for (int i = 0; i < cantidadLimpiar; i++)
        {
            Console.Write($"Ingrese elemento #{i + 1}: ");
            string elemento = Console.ReadLine();
            pilaLimpiar.Push(elemento);
        }
        Console.WriteLine("Antes de limpiar:");
        pilaLimpiar.MostrarPila();
        Console.WriteLine("Limpiando pila...");
        pilaLimpiar.Limpiar();
        Console.WriteLine("Después de limpiar:");
        pilaLimpiar.MostrarPila();
        Console.WriteLine();

        Console.WriteLine("==========================================");
        Console.WriteLine("   FIN DE LA DEMOSTRACIÓN");
        Console.WriteLine("==========================================");
    }
}

/**
 * Clase Persona para demostrar pilas de objetos personalizados.
 */
class Persona
{
    private string nombre;
    private int edad;

    public Persona(string nombre, int edad)
    {
        this.nombre = nombre;
        this.edad = edad;
    }

    public override string ToString()
    {
        return $"{nombre} ({edad} años)";
    }
}
