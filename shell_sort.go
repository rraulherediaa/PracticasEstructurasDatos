package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

func shellSort(arr []int) []int {
	n := len(arr)
	gap := n / 2

	for gap > 0 {
		for i := gap; i < n; i++ {
			temp := arr[i]
			j := i
			for j >= gap && arr[j-gap] > temp {
				arr[j] = arr[j-gap]
				j -= gap
			}
			arr[j] = temp
		}
		gap /= 2
	}

	return arr
}

func ingresarNumeros() []int {
	var numeros []int
	reader := bufio.NewReader(os.Stdin)

	fmt.Println("Ingresa números (escribe 'fin' para terminar):")

	for {
		fmt.Print("> ")
		entrada, _ := reader.ReadString('\n')
		entrada = strings.TrimSpace(entrada)

		if strings.ToLower(entrada) == "fin" {
			break
		}

		numero, err := strconv.Atoi(entrada)
		if err != nil {
			fmt.Println("  Error: Ingresa un número válido o 'fin' para terminar.")
			continue
		}

		numeros = append(numeros, numero)
		fmt.Printf("  Agregado: %d\n", numero)
	}

	return numeros
}

func main() {
	fmt.Println(strings.Repeat("=", 40))
	fmt.Println("   ORDENAMIENTO SHELL SORT")
	fmt.Println(strings.Repeat("=", 40))

	numeros := ingresarNumeros()

	if len(numeros) == 0 {
		fmt.Println("\nNo se ingresaron números.")
		return
	}

	fmt.Printf("\nLista original: %v\n", numeros)
	fmt.Printf("Cantidad de elementos: %d\n", len(numeros))

	fmt.Print("\nPresiona Enter para ordenar...")
	bufio.NewReader(os.Stdin).ReadString('\n')

	// Crear copia para no modificar la original
	copia := make([]int, len(numeros))
	copy(copia, numeros)
	ordenados := shellSort(copia)

	fmt.Printf("\nLista ordenada: %v\n", ordenados)
	fmt.Println(strings.Repeat("=", 40))
}
