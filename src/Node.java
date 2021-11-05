public class Node<T>{

    T element;
    Node next;

    /**
     * Constructor para crear un nodo.
     * @param element - objeto de tipo genérico.
     */
    public Node(T element) {
        this.element = element;
    }

    /**
     * Método para obtener el elemento del nodo.
     * @return T - objeto de tipo genérico que representa el nodo.
     */
    public T getElement() {
        return element;
    }

    /**
     * Método que regresa el nodo siguiente respecto a uno.
     * @return Node - nodo siguiente.
     */
    public Node getNext() {
        return next;
    }

     /**
      * Método para asignarle a un nodo un nodo siguiente.
      * @param next - objeto de tipo nodo.
      */
    public void setNext(Node next) {
        this.next = next;
    }
}
