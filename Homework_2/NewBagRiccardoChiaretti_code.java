/* Riccardo Chiaretti, 1661390 */

final class Bag {

    private int[] contents;
    private int n;

    /*@ensures (contents != null  && n == contents.length) || (n == 0 && contents == null); @*/
    Bag(int[] input) {
        if (input != null){
            n = input.length;
            contents = new int[n];
            arraycopy(input, 0, contents, 0, n);
        }
    }

    /*@ensures n == 0 && contents.length == n; @*/
    Bag() {
        n = 0;
        contents = new int[0];
    }

    /*@ensures (contents == null ==> \result == null) || (contents != null ==> (\forall int j; j >= 0 && j < n; \result[j] == contents[j])); @*/
    int[] getContents(){
        if (contents != null){
            int[] retContents = new int[contents.length];
            if (n > 0 && n <= contents.length){
                for (int i = 0; i < n; i++)
                    retContents[i] = contents[i];
            }
            return retContents;
        }
        return null;
    }

    /*@ensures \result == n; @*/
    int getN(){
        return n;
    }

    /* the next method should remove only the first occurrence of elt */
    final void deleteFirst(int elt) {
        if (contents != null && n <= contents.length && n > 0){
            for (int i = 0; i < n; i++) {
                if (contents[i] == elt) {
                    int[] new_contents = new int[contents.length];
                    for (int j = 0; j < i; j++)
                        new_contents[j] = contents[j];
                    for (int j = 0; j < n-i-1; j++)
                        new_contents[j+i] = contents[j+i+1];
                    n--;
                    contents = new_contents;
                    /*@assert (\forall int j; j >= 0 && j < i; new_contents[j] != elt); @*/
                    /*@assert (i + 1 < \old(n)) ==> ((contents[i] == elt) <==> (\old(contents[i+1]) == elt)); @*/
                    return;
                }
            }
        }
    }

    /* the next method should remove from the array *all* the occurrences of elt */
    /*@ensures ((contents != null && n <= contents.length && n > 0) ==> (\forall int j; j >= 0 && j < n; contents[j] != elt)); @*/
    final void deleteAll(int elt) {
        int contents_n = n;
        if (contents != null && n <= contents.length && n > 0){
            for (int i = 0; i < n; i++) {  
                if (contents[i] == elt ) {
                    if (n == 1){
                        n--; //n = 0
                        contents = new int[contents.length];
                        return;
                    } else{
                        n--;
                        contents[i] = contents[n];
                        i--;
                    }
                }
            }
            if (contents_n != n) {
                int[] new_contents = new int[contents.length];
                arraycopy(new_contents, 0, contents, 0, n);
                contents = new_contents;
            }
        }
    }

    /*@ensures \result >= 0; @*/
    final int getCount(int elt) {
        int count = 0;
        if (contents != null && n <= contents.length && n > 0){
            for (int i = 0; i < n; i++) 
                if (contents[i] == elt) count++; 
        }
        return count;
    }

    /* Warning: you may have a hard time checking the method "add" below.
        ESC/Java2 may warn about a very subtle bug not easy to spot. 
    */
    /*@ensures (contents != null ==> ((n >= 0 && n <= contents.length) ==> (n == \old(n) + 1 && contents[\old(n)] == elt))); @*/
    final void add(int elt) {
        if (contents != null){
            if (n == contents.length) {
                int[] new_contents = new int[2*n+1]; 
                /*@ assert n < new_contents.length;@*/
                arraycopy(contents, 0, new_contents, 0, n);
                contents = new_contents;
            }
            if (n >= 0 && n <= contents.length){
                contents[n]=elt;
                n++;
            }
        }
    }

    final void add(Bag b) {
        if (b == null)
            return;
        int b_n = b.getN();
        int[] b_contents = b.getContents();
        if (b_contents == null || contents == null)
            return;
        if (n < 0 || b_n < 0 || n > contents.length || b_n > b_contents.length)
            return;
        int size = n + b_n;
        int[] new_contents = new int[n + b_n];
        for (int i = 0; i < n; i++)
            new_contents[i] = contents[i];
        for (int j = 0; j < b_n; j++)
            new_contents[j+n] = b_contents[j];
        contents = new_contents; 
        n = n + b_n;
        /*@assert (\forall int i; i >= 0 && i < \old(n); contents[i] == \old(contents[i])); @*/
        /*@assert (\forall int j; j >= 0 && j < b_n; contents[j+\old(n)] == b_contents[j]); @*/
        /*@assert n <= contents.length; @*/
    }

    final void add(int[] a) {
        if (a != null)
            this.add(new Bag(a));
    }

    Bag(Bag b) {
        if (b != null)
            this.add(b);    
    }
    
    //@ensures ((src != null && dest != null && srcOff >0 && destOff >0 && srcOff + length <= src.length && destOff + length <= dest.length) ==> (\forall int i; i>=0 && i<length; dest[destOff+i] == src[srcOff+i]));
    private static void arraycopy(int[] src, int srcOff, int[] dest, int destOff, int length) {
        if (src != null && dest != null && srcOff >0 && destOff >0 && srcOff + length <= src.length && destOff + length <= dest.length)
        for(int i = 0 ; i < length; i++) {
            dest[destOff+i] = src[srcOff+i];
        }
    }

}
