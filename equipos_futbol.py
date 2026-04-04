equipos = [
    "Club Bolívar", "The Strongest", "Oriente Petrolero",
    "Club Wilstermann", "Always Ready", "Nacional Potosí",
    "Blooming", "Royal Pari", "Guabirá", "Aurora",
    "Universitario de Sucre", "Independiente Petrolero",
    "Libertad Gran Mamoré", "San Antonio Bulo Bulo"
]

while True:
    print("\n--- Menú ---")
    print("1. Ver todos los equipos")
    print("2. Buscar equipo")
    print("3. Agregar equipo")
    print("4. Eliminar equipo")
    print("5. Salir")
    
    opcion = input("Elige una opción: ")
    
    if opcion == "1":
        print("\n=== Equipos de Primera División ===")
        for i, e in enumerate(equipos, 1):
            print(f"{i}. {e}")
        print(f"\nTotal: {len(equipos)} equipos")
            
    elif opcion == "2":
        nombre = input("Nombre a buscar: ").lower()
        encontrados = [e for e in equipos if nombre in e.lower()]
        if encontrados:
            print("Resultados:", encontrados)
        else:
            print("No se encontraron equipos.")
            
    elif opcion == "3":
        nuevo = input("Nombre del nuevo equipo: ").strip()
        if nuevo and nuevo not in equipos:
            equipos.append(nuevo)
            print(f"'{nuevo}' agregado.")
        elif nuevo in equipos:
            print("El equipo ya existe.")
        else:
            print("Nombre inválido.")
            
    elif opcion == "4":
        for i, e in enumerate(equipos, 1):
            print(f"{i}. {e}")
        try:
            num = int(input("Número del equipo a eliminar: "))
            if 1 <= num <= len(equipos):
                eliminado = equipos.pop(num - 1)
                print(f"'{eliminado}' eliminado.")
            else:
                print("Número fuera de rango.")
        except ValueError:
            print("Ingresa un número válido.")
            
    elif opcion == "5":
        print("¡Hasta luego!")
        break
    else:
        print("Opción no válida.")
