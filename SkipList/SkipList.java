import java.util.Random;

/**
 * SkipList - Implementación en Java
 * Estructura de datos probabilística para búsqueda, inserción y eliminación en O(log n)
 */
public class SkipList<T extends Comparable<T>> {
    private SkipListNode<T> head;
    private int maxLevel;
    private double probability;
    private Random random;
    private int currentLevel;

    public SkipList(int maxLevel, double probability) {
        this.maxLevel = maxLevel;
        this.probability = probability;
        this.random = new Random();
        this.currentLevel = 0;
        this.head = new SkipListNode<>(null, maxLevel);
    }

    /**
     * Genera un nivel aleatorio para un nuevo nodo
     */
    private int randomLevel() {
        int level = 0;
        while (level < maxLevel - 1 && random.nextDouble() < probability) {
            level++;
        }
        return level;
    }

    /**
     * Busca un elemento en la SkipList
     */
    public boolean search(T key) {
        SkipListNode<T> current = head;
        
        for (int i = currentLevel; i >= 0; i--) {
            int safetyCounter = 0;
            while (current.next[i] != null && current.next[i].value.compareTo(key) < 0) {
                current = current.next[i];
                safetyCounter++;
                if (safetyCounter > 10000) {
                    System.err.println("Error: Posible bucle infinito en search");
                    return false;
                }
            }
        }
        
        current = current.next[0];
        return current != null && current.value.compareTo(key) == 0;
    }

    /**
     * Inserta un elemento en la SkipList
     */
    @SuppressWarnings("unchecked")
    public void insert(T key) {
        SkipListNode<T>[] update = new SkipListNode[maxLevel];
        SkipListNode<T> current = head;
        
        for (int i = currentLevel; i >= 0; i--) {
            int safetyCounter = 0;
            while (current.next[i] != null && current.next[i].value.compareTo(key) < 0) {
                current = current.next[i];
                safetyCounter++;
                if (safetyCounter > 10000) {
                    System.err.println("Error: Posible bucle infinito en insert");
                    return;
                }
            }
            update[i] = current;
        }
        
        current = current.next[0];
        
        if (current == null || current.value.compareTo(key) != 0) {
            int newNodeLevel = randomLevel();
            
            if (newNodeLevel > currentLevel) {
                for (int i = currentLevel + 1; i <= newNodeLevel; i++) {
                    update[i] = head;
                }
                currentLevel = newNodeLevel;
            }
            
            SkipListNode<T> newNode = new SkipListNode<>(key, newNodeLevel);
            
            for (int i = 0; i <= newNodeLevel; i++) {
                newNode.next[i] = update[i].next[i];
                update[i].next[i] = newNode;
            }
        }
    }

    /**
     * Elimina un elemento de la SkipList
     */
    @SuppressWarnings("unchecked")
    public void delete(T key) {
        SkipListNode<T>[] update = new SkipListNode[maxLevel];
        SkipListNode<T> current = head;
        
        for (int i = currentLevel; i >= 0; i--) {
            int safetyCounter = 0;
            while (current.next[i] != null && current.next[i].value.compareTo(key) < 0) {
                current = current.next[i];
                safetyCounter++;
                if (safetyCounter > 10000) {
                    System.err.println("Error: Posible bucle infinito en delete");
                    return;
                }
            }
            update[i] = current;
        }
        
        current = current.next[0];
        
        if (current != null && current.value.compareTo(key) == 0) {
            for (int i = 0; i <= currentLevel; i++) {
                if (update[i].next[i] != current) {
                    break;
                }
                update[i].next[i] = current.next[i];
            }
            
            while (currentLevel > 0 && head.next[currentLevel] == null) {
                currentLevel--;
            }
        }
    }

    /**
     * Imprime la SkipList nivel por nivel
     */
    public void print() {
        System.out.println("\nSkip List Structure:");
        for (int i = currentLevel; i >= 0; i--) {
            SkipListNode<T> node = head.next[i];
            System.out.print("Level " + i + ": Head -> ");
            while (node != null) {
                System.out.print(node.value + " -> ");
                node = node.next[i];
            }
            System.out.println("null");
        }
    }

    /**
     * Nodo de la SkipList
     */
    private static class SkipListNode<T> {
        T value;
        SkipListNode<T>[] next;

        @SuppressWarnings("unchecked")
        public SkipListNode(T value, int level) {
            this.value = value;
            this.next = new SkipListNode[level + 1];
        }
    }
}
