#include <stdlib.h>
#include <unistd.h>
static int my_read(int fd, /*@partial@*/void *buffer, size_t size)
/*@requires maxSet(buffer) >= (size-1) @*/
{
    size_t bytes_read = 0;
    ssize_t bytes_buf;
    while (size > bytes_read){
        bytes_buf = read(fd, buffer, size - bytes_read);
        if (bytes_buf == -1)
            return 0;
        if (bytes_buf == 0)
            return 1;
        bytes_read += bytes_buf;
        buffer += bytes_buf;
    }
    return 0;
}

void func(int fd) {
    char *buf;
    size_t len = 0;
    size_t max_len = (size_t) (1<<64) - 1;
    int res_len = my_read(fd, (size_t *) &len, sizeof(len));
    if (res_len == 0 || len == max_len)
        return;
    buf = (char *) malloc(len + 1);
    if (buf == NULL)
        return;
    int res_buf = my_read(fd, (char *) buf, len);
    if (res_buf == 0){
        free(buf);
        return;
    }
    buf[len] = ’\0’;
    free(buf);
}