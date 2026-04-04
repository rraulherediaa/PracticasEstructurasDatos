class Nodo:
    def __init__(self, dato):
        self.dato = dato
        self.siguiente = None


class ListaEnlazada:
    def __init__(self):
        self.cabeza = None
        self.tamanio = 0
    
    def esta_vacia(self):
        return self.cabeza is None
    
    def agregar_inicio(self, dato):
        nuevo = Nodo(dato)
        nuevo.siguiente = self.cabeza
        self.cabeza = nuevo
        self.tamanio += 1
        print(f"'{dato}' agregado al inicio.")
    
    def agregar_final(self, dato):
        nuevo = Nodo(dato)
        if self.esta_vacia():
            self.cabeza = nuevo
        else:
            actual = self.cabeza
            while actual.siguiente:
                actual = actual.siguiente
            actual.siguiente = nuevo
        self.tamanio += 1
        print(f"'{dato}' agregado al final.")
    
    def agregar_posicion(self, dato, posicion):
        if posicion < 0 or posicion > self.tamanio:
            print("Posición fuera de rango.")
            return
        if posicion == 0:
            self.agregar_inicio(dato)
            return
        nuevo = Nodo(dato)
        actual = self.cabeza
        for _ in range(posicion - 1):
            actual = actual.siguiente
        nuevo.siguiente = actual.siguiente
        actual.siguiente = nuevo
        self.tamanio += 1
        print(f"'{dato}' agregado en posición {posicion}.")
    
    def eliminar_inicio(self):
        if self.esta_vacia():
            print("Lista vacía.")
            return
        dato = self.cabeza.dato
        self.cabeza = self.cabeza.siguiente
        self.tamanio -= 1
        print(f"'{dato}' eliminado del inicio.")
    
    def eliminar_final(self):
        if self.esta_vacia():
            print("Lista vacía.")
            return
        if self.cabeza.siguiente is None:
            dato = self.cabeza.dato
            self.cabeza = None
            self.tamanio -= 1
            print(f"'{dato}' eliminado del final.")
            return
        actual = self.cabeza
        while actual.siguiente.siguiente:
            actual = actual.siguiente
        dato = actual.siguiente.dato
        actual.siguiente = None
        self.tamanio -= 1
        print(f"'{dato}' eliminado del final.")
    
    def eliminar_posicion(self, posicion):
        if posicion < 0 or posicion >= self.tamanio:
            print("Posición fuera de rango.")
            return
        if posicion == 0:
            self.eliminar_inicio()
            return
        actual = self.cabeza
        for _ in range(posicion - 1):
            actual = actual.siguiente
        dato = actual.siguiente.dato
        actual.siguiente = actual.siguiente.siguiente
        self.tamanio -= 1
        print(f"'{dato}' eliminado de posición {posicion}.")
    
    def buscar(self, dato):
        actual = self.cabeza
        posicion = 0
        while actual:
            if actual.dato == dato:
                return posicion
            actual = actual.siguiente
            posicion += 1
        return -1
    
    def obtener(self, posicion):
        if posicion < 0 or posicion >= self.tamanio:
            return None
        actual = self.cabeza
        for _ in range(posicion):
            actual = actual.siguiente
        return actual.dato
    
    def mostrar(self):
        if self.esta_vacia():
            print("Lista vacía.")
            return
        print("\n=== Lista de Equipos ===")
        actual = self.cabeza
        posicion = 0
        while actual:
            print(f"[{posicion}] {actual.dato}")
            actual = actual.siguiente
            posicion += 1
        print(f"\nTotal: {self.tamanio} equipos")


# Inicializar lista con equipos de fútbol boliviano
lista_equipos = ListaEnlazada()
equipos_iniciales = [
    "Club Bolívar", "The Strongest", "Oriente Petrolero",
    "Club Wilstermann", "Always Ready"
]
for equipo in equipos_iniciales:
    lista_equipos.agregar_final(equipo)


while True:
    print("\n--- Menú Listas Enlazadas ---")
    print("1. Ver lista de equipos")
    print("2. Agregar al inicio")
    print("3. Agregar al final")
    print("4. Agregar en posición específica")
    print("5. Eliminar del inicio")
    print("6. Eliminar del final")
    print("7. Eliminar por posición")
    print("8. Buscar equipo")
    print("9. Obtener equipo por posición")
    print("10. Salir")
    
    opcion = input("Elige una opción: ")
    
    if opcion == "1":
        lista_equipos.mostrar()
        
    elif opcion == "2":
        equipo = input("Nombre del equipo: ").strip()
        if equipo:
            lista_equipos.agregar_inicio(equipo)
        else:
            print("Nombre inválido.")
            
    elif opcion == "3":
        equipo = input("Nombre del equipo: ").strip()
        if equipo:
            lista_equipos.agregar_final(equipo)
        else:
            print("Nombre inválido.")
            
    elif opcion == "4":
        equipo = input("Nombre del equipo: ").strip()
        try:
            pos = int(input("Posición: "))
            if equipo:
                lista_equipos.agregar_posicion(equipo, pos)
            else:
                print("Nombre inválido.")
        except ValueError:
            print("Posición debe ser un número.")
            
    elif opcion == "5":
        lista_equipos.eliminar_inicio()
        
    elif opcion == "6":
        lista_equipos.eliminar_final()
        
    elif opcion == "7":
        try:
            pos = int(input("Posición a eliminar: "))
            lista_equipos.eliminar_posicion(pos)
        except ValueError:
            print("Posición debe ser un número.")
            
    elif opcion == "8":
        equipo = input("Equipo a buscar: ").strip()
        pos = lista_equipos.buscar(equipo)
        if pos != -1:
            print(f"'{equipo}' encontrado en posición {pos}.")
        else:
            print(f"'{equipo}' no encontrado.")
            
    elif opcion == "9":
        try:
            pos = int(input("Posición: "))
            equipo = lista_equipos.obtener(pos)
            if equipo:
                print(f"Equipo en posición {pos}: {equipo}")
            else:
                print("Posición fuera de rango.")
        except ValueError:
            print("Posición debe ser un número.")
            
    elif opcion == "10":
        print("¡Hasta luego!")
        break
    else:
        print("Opción no válida.")
