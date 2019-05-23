/* Riccardo Chiaretti, 1661390 */

class Bag {

  /*@non_null @*/int[] contents;
  int n; 
  //@ invariant 0 <= n;
  //@ invariant n <= contents.length; 

  Bag(/*@non_null @*/int[] input) {
    n = input.length;
    contents = new int[n];
    arraycopy(input, 0, contents, 0, n);
  }

  Bag() {
    n = 0;
    contents = new int[0];
  }

/* the next method should remove only the first occurrence of elt */
  void deleteFirst(int elt) {
    /*@loop_invariant i >= 0; @*/
    //@ loop_invariant i <= contents.length; 
    for (int i = 0; i < n; i++) {  
      if (contents[i] == elt ) {
         n--;
         contents[i] = contents[n];
         return;
      }
    }
  }

/* the next method should remove from the array *all* the occurrences of elt */
  void deleteAll(int elt) {
    //@ loop_invariant i <= contents.length+1; 
    for (int i = 0; i < n; i++) {   
      if (contents[i] == elt ) {
         n--;
         contents[i] = contents[n];
      }
    }
  }

  /*@ ensures \result >= 0;@*/
  int getCount(int elt) {
    int count = 0;
    /*@loop_invariant i >= 0; @*/
    //@ loop_invariant i <= contents.length; 
    //@ loop_invariant count >= 0;
    for (int i = 0; i < n; i++) 
      if (contents[i] == elt) count++; 
    return count;
  }

  /* Warning: you may have a hard time checking the method "add" below.
     ESC/Java2 may warn about a very subtle bug not easy to spot. 
   */

  void add(int elt) {
    if (n == contents.length) {
       int[] new_contents = new int[2*n+1];
       //@assert n < new_contents.length;
       arraycopy(contents, 0, new_contents, 0, n);
       contents = new_contents;
    }
    contents[n]=elt;
    n++;
  }

  void add(/*@non_null @*/Bag b) {
    int[] new_contents = new int[n + b.n];
    arraycopy(contents, 0, new_contents, 0, n);
    arraycopy(b.contents, 0, new_contents, n, b.n);
    contents = new_contents; 
    //@assert n == contents.length;
  }

  void add(/*@non_null @*/int[] a) {
    this.add(new Bag(a));
  }

  Bag(/*@non_null @*/Bag b) {
    //contents = new int[0]; //this is missing in the original code!
    this.add(b);    
  }

  //@requires srcOff >= 0;
  //@requires destOff >= 0;
  //@requires length >= 0;
  //@requires srcOff + length <= src.length;
  //@requires destOff + length <= dest.length;
  private static void arraycopy(/*@non_null @*/int[] src,
                                int   srcOff,
                                /*@non_null @*/int[] dest,
                                int   destOff,
                                int   length) {
    /*@ loop_invariant i<= length;@*/
    for(int i=0; i < length; i++) { 
       dest[destOff+i] = src[srcOff+i];
    }
  }
  
}
