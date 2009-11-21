#include <stdio.h>

long long fatorial[1000001];

int main() {
    int n, i, c;

    fatorial[0] = fatorial[1] = 1;
    for (i = 2; i < 1000001; i++) {
        long long fat = i * fatorial[i-1];
        while (fat % 10==0) { /*ignora os digitos finais = 0*/
            fat /= 10;
        }
        fat = fat % 10000000; /*guarda os digitos finais*/
        fatorial[i] = fat;
    }

    c = 1;
    while (scanf("%d",&n) != EOF) {
        printf("Instancia %d\n%d\n\n", c, fatorial[n]%10);
        c++;
    }
}
