#include <stdlib.h>
#include <stdio.h>

static void stringcopy(/*@out@*/ char *str1, char *str2)
/*@requires maxRead(str2) >= 0 /\ maxSet(str1) <= 16 @*/;
int main(int argc, char **argv)

/*@requires maxRead(argv) >= 1 /\ maxRead(argv[1]) >= 0 @*/;
static void stringcopy(/*@out@*/ char *str1, char *str2){
    short length = 0;
    while(*str2 != ’\0’ && length != 16){
        *str1++ = *str2++;
        length++;
    }
    *str1 = ’\0’;
}

int main(int argc, char **argv) {
    char *buffer;
    if (argc != 2)
    return 0;
    buffer = (char *) malloc(17 * sizeof(char));
    if (buffer == NULL)
    return 0;
    stringcopy(buffer, argv[1]);
    printf("%s\n", buffer); /*flawfinder: ignore*/
    free(buffer);
    return 1;
}