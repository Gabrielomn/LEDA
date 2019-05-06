package adt.linkedList.set;

import adt.linkedList.SingleLinkedListImpl;
import adt.linkedList.SingleLinkedListNode;
import static org.junit.jupiter.api.Assertions.*;

public class SetLinkedListImpl<T> extends SingleLinkedListImpl<T> implements SetLinkedList<T> {

   @Override
   public void removeDuplicates() {
      T[] k = this.toArray();
      SingleLinkedListNode aux = this.getHead();
      int tam = k.length - 1;

      for (int i = tam; i > 0; i--) {
         for (int j = i - 1; j > 0; j--) {
            if (k[i].equals(k[j])) {
               this.remove(k[j]);
               break;
            }
         }

      }

   }

   @Override
   public SetLinkedList<T> union(SetLinkedList<T> otherSet) {

      SetLinkedList k = new SetLinkedListImpl();
      SingleLinkedListNode aux = this.getHead();
      while (!aux.isNIL()) {
         k.insert(aux);
         aux = aux.getNext();
      }

      aux = ((SingleLinkedListImpl) otherSet).getHead();

      while (!aux.isNIL()) {
         if (((SingleLinkedListImpl) k).search(aux) == null) {
            k.insert(aux);
            aux = aux.getNext();
         }
      }

      return k;
   }

   @Override
   public SetLinkedList<T> intersection(SetLinkedList<T> otherSet) {
      SetLinkedList k = new SetLinkedListImpl();
      SingleLinkedListNode aux = this.getHead();
      while (!aux.isNIL()) {
         if (((SingleLinkedListImpl) otherSet).search(aux) != null) {
            k.insert(aux);
         }
         aux = aux.getNext();
      }

      return k;
   }

   @Override
   public void concatenate(SetLinkedList<T> otherSet) {
      SingleLinkedListNode aux = ((SingleLinkedListImpl) otherSet).getHead();

      while (!aux.isNIL()) {
         if (this.search((T) aux.getData()) == null) {
            this.insert((T) aux.getData());
         }
         aux = aux.getNext();
      }

   }

}
