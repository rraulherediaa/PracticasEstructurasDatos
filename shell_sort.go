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

func main() {
	reader := bufio.NewReader(os.Stdin)
	var numeros []int

	fmt.Println(strings.Repeat("=", 40))
	fmt.Println("   ORDENAMIENTO SHELL SORT")
	fmt.Println(strings.Repeat("=", 40))
	fmt.Println("Ingresa exactamente 10 números:")

	for i := 1; i <= 10; i++ {
		for {
			fmt.Printf("Número %d/10: ", i)
			entrada, _ := reader.ReadString('\n')
			entrada = strings.TrimSpace(entrada)

			numero, err := strconv.Atoi(entrada)
			if err != nil {
				fmt.Println("  Error: Ingresa un número válido.")
				continue
			}

			numeros = append(numeros, numero)
			break
		}
	}

	fmt.Printf("\nLista original: %v\n", numeros)

	// Crear copia para ordenar
	copia := make([]int, len(numeros))
	copy(copia, numeros)
	ordenados := shellSort(copia)

	fmt.Printf("Lista ordenada: %v\n", ordenados)
	fmt.Println(strings.Repeat("=", 40))
}
