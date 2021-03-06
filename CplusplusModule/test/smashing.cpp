#include <iostream>

#include "gtest/gtest.h"
#include "../src/include/Savings.h"



void smashfault_sigaction(int signal, siginfo_t *si, void *arg)
{
    printf("Caught smashing at address %p\n", si->si_addr);
    exit(0);
}

void disableSmashingFault() {
    struct sigaction sa;
    memset(&sa, 0, sizeof(struct sigaction));
    sigemptyset(&sa.sa_mask);
    sa.sa_sigaction = smashfault_sigaction;
    sa.sa_flags   = SA_SIGINFO;
    sigaction(SIGABRT, &sa, NULL);
}


//STR02-CPP. Sanitize data passed to complex subsystems
TEST(null_termination_string_test_case, null_termination_string_test) {
    char a[16];
    char b[16];
    char c[32];
    strcpy(a, "0123456789abcdef");
    strcpy(b, "0123456789abcdef");
    strcpy(c, a);
    printf("a = %s\n", a);

}


TEST(stack_smashing_test_case, stack_smashing_test){
    disableSmashingFault();
    Savings* object = new Savings();
    char a[] = "StringA";
    printf("string-1 length = %d, String a = %s\n", object->stackSmashing(&a[0],"StringB"), a);

}


