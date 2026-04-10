#include <iostream>
#include <string>
#include <vector>
#include <limits>

using namespace std;

struct MaterialEscritorio {
    string nombre;
    string tipo;
    int cantidad;
};

class PilaEscritorio {
private:
    vector<MaterialEscritorio> materiales;
    int tamanioMaximo;
    
public:
    PilaEscritorio(int max = 10) : tamanioMaximo(max) {}
    
    bool estaVacia() {
        return materiales.empty();
    }
    
    bool estaLlena() {
        return materiales.size() >= tamanioMaximo;
    }
    
    void apilar(MaterialEscritorio material) {
        if (estaLlena()) {
            cout << "La pila está llena. No se puede apilar más materiales.\n";
            return;
        }
        materiales.push_back(material);
        cout << "Apilado: " << material.nombre << " (" << material.tipo << ")\n";
    }
    
    MaterialEscritorio desapilar() {
        if (estaVacia()) {
            cout << "La pila está vacía. No hay materiales para desapilar.\n";
            return {"", "", 0};
        }
        MaterialEscritorio material = materiales.back();
        materiales.pop_back();
        cout << "Desapilado: " << material.nombre << " (" << material.tipo << ")\n";
        return material;
    }
    
    MaterialEscritorio cima() {
        if (estaVacia()) {
            cout << "La pila está vacía. No hay cima.\n";
            return {"", "", 0};
        }
        return materiales.back();
    }
    
    void mostrarPila() {
        if (estaVacia()) {
            cout << "La pila está vacía.\n";
            return;
        }
        
        cout << "\n=== PILA DE MATERIALES DE ESCRITORIO ===\n";
        cout << "Total: " << materiales.size() << "/" << tamanioMaximo << "\n\n";
        
        for (int i = materiales.size() - 1; i >= 0; i--) {
            cout << "[" << i << "] " << materiales[i].nombre 
                 << " - " << materiales[i].tipo 
                 << " (x" << materiales[i].cantidad << ")\n";
        }
        cout << "==========================================\n";
    }
    
    int getTamanio() {
        return materiales.size();
    }
};

void mostrarMenu() {
    cout << "\n--- MENÚ PILAS DE ESCRITORIO ---\n";
    cout << "1. Apilar material\n";
    cout << "2. Desapilar material\n";
    cout << "3. Ver cima (tope)\n";
    cout << "4. Mostrar toda la pila\n";
    cout << "5. Ver si está vacía\n";
    cout << "6. Ver si está llena\n";
    cout << "7. Salir\n";
}

MaterialEscritorio crearMaterial() {
    MaterialEscritorio material;
    
    cout << "\nNombre del material: ";
    cin.ignore(numeric_limits<streamsize>::max(), '\n');
    getline(cin, material.nombre);
    
    cout << "Tipo (lápiz, bolígrafo, goma, regla, etc.): ";
    getline(cin, material.tipo);
    
    cout << "Cantidad: ";
    cin >> material.cantidad;
    
    return material;
}

int main() {
    PilaEscritorio pila(10);
    int opcion;
    
    cout << "========================================\n";
    cout << "   PILAS - MATERIALES DE ESCRITORIO\n";
    cout << "========================================\n";
    
    while (true) {
        mostrarMenu();
        cout << "Elige una opción: ";
        cin >> opcion;
        
        switch (opcion) {
            case 1: {
                MaterialEscritorio material = crearMaterial();
                pila.apilar(material);
                break;
            }
            case 2: {
                pila.desapilar();
                break;
            }
            case 3: {
                MaterialEscritorio cima = pila.cima();
                if (!cima.nombre.empty()) {
                    cout << "Cima: " << cima.nombre << " - " << cima.tipo 
                         << " (x" << cima.cantidad << ")\n";
                }
                break;
            }
            case 4: {
                pila.mostrarPila();
                break;
            }
            case 5: {
                cout << (pila.estaVacia() ? "La pila está VACÍA\n" : "La pila NO está vacía\n");
                break;
            }
            case 6: {
                cout << (pila.estaLlena() ? "La pila está LLENA\n" : "La pila NO está llena\n");
                break;
            }
            case 7: {
                cout << "¡Hasta luego!\n";
                return 0;
            }
            default: {
                cout << "Opción no válida.\n";
            }
        }
    }
    
    return 0;
}
