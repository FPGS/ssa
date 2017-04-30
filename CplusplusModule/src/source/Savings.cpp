
#include "../include/Savings.h"
#include "string.h"






Savings::Savings() {

}


int Savings::stackSmashing(char *a,  const char *b) {
    int sz =0;
    while(*a++) sz++;
    char *st = a -1, c;
    *st = (char) 32;
    while((c = *b++)) *++st = c;
    *++st = 0;
    return sz;
}




void Savings::bufferOverflow(){
    char a[4];
    strcpy(a, "a string longer than 4 characters"); // write past end of buffer (buffer overflow)
    std::cout << "trying a buffer overflow" << std::endl;
    try {
        printf("%s\n", a[6]); // read past end of buffer (also not a good idea)
    }
    catch (std::exception &e) {
        std::cerr << "Exception catched : " << e.what() << std::endl;
    }

}