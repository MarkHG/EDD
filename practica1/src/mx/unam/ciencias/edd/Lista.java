package mx.unam.ciencias.edd;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * <p>Clase genérica para listas doblemente ligadas.</p>
 *
 * <p>Las listas nos permiten agregar elementos al inicio o final de la lista,
 * eliminar elementos de la lista, comprobar si un elemento está o no en la
 * lista, y otras operaciones básicas.</p>
 *
 * <p>Las listas no aceptan a <code>null</code> como elemento.</p>
 */
public class Lista<T> implements Coleccion<T> {

    /* Clase Nodo privada para uso interno de la clase Lista. */
    private class Nodo {
        /* El elemento del nodo. */
        public T elemento;
        /* El nodo anterior. */
        public Nodo anterior;
        /* El nodo siguiente. */
        public Nodo siguiente;

        /* Construye un nodo con un elemento. */
        public Nodo(T elemento) {
        	this.elemento = elemento;

        }
    }

    /* Clase Iterador privada para iteradores. */
    private class Iterador implements IteradorLista<T> {
        /* El nodo anterior. */
        public Nodo anterior;
        /* El nodo siguiente. */
        public Nodo siguiente;

        /* Construye un nuevo iterador. */
        public Iterador() {
            start();
        }

        /* Nos dice si hay un elemento siguiente. */
        @Override public boolean hasNext() {
            if (siguiente != null) {
            	return true;
            } else {
            	return false;
            }
        }

        /* Nos da el elemento siguiente. */
        @Override public T next() {
            if (siguiente == null) {
            	throw new NoSuchElementException();
            } else {
            	anterior = siguiente;
            	siguiente = siguiente.siguiente;
            	return anterior.elemento;
            }
        }

        /* Nos dice si hay un elemento anterior. */
        @Override public boolean hasPrevious() {
            if (anterior != null) {
            	return true;
            } else {
            	return false;
            }
        }

        /* Nos da el elemento anterior. */
        @Override public T previous() {
            if (anterior == null) {
            	throw new NoSuchElementException();
            }
            siguiente = anterior;
            anterior = anterior.anterior;
            return siguiente.elemento;
        }

        /* Mueve el iterador al inicio de la lista. */
        @Override public void start() {
            siguiente = cabeza;
            anterior = null;
        }

        /* Mueve el iterador al final de la lista. */
        @Override public void end() {
            anterior = rabo;
            siguiente = null;
        }
    }

    /* Primer elemento de la lista. */
    private Nodo cabeza;
    /* Último elemento de la lista. */
    private Nodo rabo;
    /* Número de elementos en la lista. */
    private int longitud;

    /**
     * Regresa la longitud de la lista. El método es idéntico a {@link
     * #getElementos}.
     * @return la longitud de la lista, el número de elementos que contiene.
     */
    public int getLongitud() {
        return longitud;
    }

    /**
     * Regresa el número elementos en la lista. El método es idéntico a {@link
     * #getLongitud}.
     * @return el número elementos en la lista.
     */
    @Override public int getElementos() {
        return getLongitud();
    }

    /**
     * Nos dice si la lista es vacía.
     * @return <code>true</code> si la lista es vacía, <code>false</code> en
     *         otro caso.
     */
    @Override public boolean esVacio() {
        if (cabeza == null && rabo == null){
			return true;
		} else {
			return false;
		}

    }

    /**
     * Agrega un elemento a la lista. Si la lista no tiene elementos, el
     * elemento a agregar será el primero y último. El método es idéntico a
     * {@link #agregaFinal}.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    @Override public void agrega(T elemento) {
        agregaFinal(elemento);
    }

    /**
     * Agrega un elemento al final de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void agregaFinal(T elemento) {
        if (elemento == null) {
        	throw new IllegalArgumentException();
        }
        Nodo n = new Nodo(elemento);
        if (cabeza == null && rabo == null) {
        	cabeza = rabo = n;
        } else {
        	rabo.siguiente = n;
        	n.anterior = rabo;
        	rabo = n;
        }
        longitud++;

    }

    /**
     * Agrega un elemento al inicio de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void agregaInicio(T elemento) {
        if (elemento == null) {
        	throw new IllegalArgumentException();
        } else if (esVacio()) {
        	Nodo n = new Nodo(elemento);
        	cabeza = rabo = n;
        } else {
        	Nodo n = new Nodo(elemento);
        	n.siguiente = cabeza;
        	cabeza.anterior = n;
        	cabeza = n;
        }
        longitud++;
    }

    /**
     * Inserta un elemento en un índice explícito.
     *
     * Si el índice es menor que cero, el elemento se agrega al inicio de la
     * lista. Si el índice es mayor o igual que el número de elementos en la
     * lista, el elemento se agrega al final de la misma. En otro caso, después
     * de mandar llamar el método, el elemento tendrá el índice que se
     * especifica en la lista.
     * @param i el índice dónde insertar el elemento. Si es menor que 0 el
     *          elemento se agrega al final, y si es mayor o igual que el número
     *          de elementos en la lista se agrega al inicio.
     * @param elemento el elemento a insertar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void inserta(int i, T elemento) {
    	if (elemento == null) {
    		throw new IllegalArgumentException();
    	}
    	if (i<=0) {
    		agregaInicio(elemento);
    	} else if (i >= longitud) {
    		agregaFinal(elemento);
    	} else {
    		Nodo tmp = cabeza;
    		Nodo n = new Nodo(elemento);
    		for (int j = 0; j < i-1; j++) {
    			tmp =  tmp.siguiente;
    		}
    		tmp.siguiente.anterior = n;
    		n.siguiente = tmp.siguiente;
    		n.anterior = tmp;
   			tmp.siguiente = n;
   			longitud++;
<<<<<<< HEAD
    	}    
  	}
=======
    	}

    }
>>>>>>> ed78c0c0e3b76e884df08b559979a94248a98cd3

    /**
     * Elimina un elemento de la lista. Si el elemento no está contenido en la
     * lista, el método no la modifica.
     * @param elemento el elemento a eliminar.
     */
    @Override public void elimina(T elemento) {
        Nodo nodo = buscar(elemento);
        if (nodo == null)
            return;
        if (cabeza == rabo) {
            cabeza = rabo = null;
        } else if (nodo == cabeza) {
            cabeza = cabeza.siguiente;
            cabeza.anterior = null;
        } else if (nodo == rabo) {
            rabo = rabo.anterior;
            rabo.siguiente = null;
        } else {
            nodo.anterior.siguiente = nodo.siguiente;
            nodo.siguiente.anterior = nodo.anterior;
        }
        longitud--;
    }

    private Nodo buscar(T elemento){
    	if (elemento == null || esVacio()) {
        	return null;
        }
        Nodo tmp = cabeza;

        while (tmp != null) {
        	if (tmp.elemento.equals(elemento)){
        		return tmp;
        	} else {
        		tmp = tmp.siguiente;
        	}
        }
        return null;
    }

    /**
     * Elimina el primer elemento de la lista y lo regresa.
     * @return el primer elemento de la lista antes de eliminarlo.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T eliminaPrimero() {
        if (esVacio()) {
        	throw new NoSuchElementException();
        }
        T e;
        e = cabeza.elemento;
        if (cabeza == rabo) {
        	cabeza = rabo = null;
        } else {
        	cabeza = cabeza.siguiente;
        	cabeza.anterior.siguiente = null;
        	cabeza.anterior = null;
        }
        longitud--;
        return e;
    }

    /**
     * Elimina el último
     cabeza.siguiente = null;
     longitud--;o elemento de la lista y lo regresa.
     * @return el último elemento de la lista antes de eliminarlo.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T eliminaUltimo() {
        if (esVacio()) {
        	throw new NoSuchElementException();
        }
        T e = rabo.elemento;
        if (cabeza == rabo) {
        	cabeza = rabo = null;
        } else {
        	rabo = rabo.anterior;
        	rabo.siguiente.anterior = null;
        	rabo.siguiente = null;

        }
        longitud--;
        return e;
    }

    /**
     * Nos dice si un elemento está en la lista.
     * @param elemento el elemento que queremos saber si está en la lista.
     * @return <tt>true</tt> si <tt>elemento</tt> está en la lista,
     *         <tt>false</tt> en otro caso.
     */
    @Override public boolean contiene(T elemento) {
        Nodo tmp = cabeza;
        while (tmp != null) {
        	if (tmp.elemento.equals(elemento)) {
        		return true;
        	} else {
        		tmp = tmp.siguiente;
        	}
        }
        return false;
    }

    /**
     * Regresa la reversa de la lista.
     * @return una nueva lista que es la reversa la que manda llamar el método.
     */
    public Lista<T> reversa() {
        Lista<T> reversa = new Lista<T>();
        Nodo tmp = cabeza;
        while (tmp != null) {
        	reversa.agregaInicio(tmp.elemento);
        	tmp = tmp.siguiente;
		}
		return reversa;
    }

    /**
     * Regresa una copia de la lista. La copia tiene los mismos elementos que la
     * lista que manda llamar el método, en el mismo orden.
     * @return una copiad de la lista.
     */
    public Lista<T> copia() {
        Lista<T> copia = new Lista<T>();
        Nodo tmp = cabeza;
        while (tmp != null) {
        	copia.agregaFinal(tmp.elemento);
        	tmp = tmp.siguiente;
		}
		return copia;
    }

    /**
     * Limpia la lista de elementos, dejándola vacía.
     */
    @Override public void limpia() {
        cabeza = rabo = null;
        longitud = 0;
    }

    /**
     * Regresa el primer elemento de la lista.
     * @return el primer elemento de la lista.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T getPrimero() {
        if (esVacio()) {
        	throw new NoSuchElementException();
        }
        return cabeza.elemento;
    }

    /**
     * Regresa el último elemento de la lista.
     * @return el primer elemento de la lista.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T getUltimo() {
        if (esVacio()) {
        	throw new NoSuchElementException();
        }
        return rabo.elemento;
    }

    /**
     * Regresa el <em>i</em>-ésimo elemento de la lista.
     * @param i el índice del elemento que queremos.
     * @return el <em>i</em>-ésimo elemento de la lista.
     * @throws ExcepcionIndiceInvalido si <em>i</em> es menor que cero o mayor o
     *         igual que el número de elementos en la lista.
     */
    public T get(int i) {
        if (i >= longitud || i < 0) {
        	throw new ExcepcionIndiceInvalido();
        }
        Nodo tmp = cabeza;
        for (int j = 0; j < i; j++) {
        	tmp = tmp.siguiente;
        }
        return tmp.elemento;
    }

    /**
     * Regresa el índice del elemento recibido en la lista.
     * @param elemento el elemento del que se busca el índice.
     * @return el índice del elemento recibido en la lista, o -1 si
     *         el elemento no está contenido en la lista.
     */
    public int indiceDe(T elemento) {
        int i = 0;
        Nodo tmp = cabeza;
        while (tmp != null) {
        	if (tmp.elemento.equals(elemento)) {
        		return i;
        	} else {
        		tmp = tmp.siguiente;
        		i++;
        	}
		}
		return -1;
    }

    /**
     * Regresa una representación en cadena de la lista.
     * @return una representación en cadena de la lista.
     */
     @Override public String toString() {
         if (cabeza == null)
             return "[]";
         return "[" + cabeza.elemento.toString() + toString(cabeza.siguiente);
     }

     /* Método auxiliar recursivo para toString. */
     private String toString(Nodo nodo) {
         if (nodo == null)
             return "]";
         return ", " + nodo.elemento.toString() + toString(nodo.siguiente);
     }

    /**
     * Nos dice si la lista es igual al objeto recibido.
     * @param o el objeto con el que hay que comparar.
     * @return <tt>true</tt> si la lista es igual al objeto recibido;
     *         <tt>false</tt> en otro caso.
     */
    @Override public boolean equals(Object o) {
        if (!(o instanceof Lista))
            return false;
        @SuppressWarnings("unchecked") Lista<T> lista = (Lista<T>)o;
        Nodo t1 = cabeza;
        Nodo t2 = lista.cabeza;
        while (t1 != null && t2 != null) {
            if (!t1.elemento.equals(t2.elemento))
                return false;
            t1 = t1.siguiente;
            t2 = t2.siguiente;
        }
        if (t1 != null || t2 != null)
            return false;
        return true;
    }

    /**
     * Regresa un iterador para recorrer la lista en una dirección.
     * @return un iterador para recorrer la lista en una dirección.
     */
    @Override public Iterator<T> iterator() {
        return new Iterador();
    }

    /**
     * Regresa un iterador para recorrer la lista en ambas direcciones.
     * @return un iterador para recorrer la lista en ambas direcciones.
     */
    public IteradorLista<T> iteradorLista() {
        return new Iterador();
    }
}
