#include <stdio.h>

int main() {
	float A, G, RA, RG;
	scanf("%f %f %f %f", &A, &G, &RA, &RG);
	double a = (A / RA); // preco do litro de alcool pra cada km
	double g = (G / RG); // preco do litro de gasolina pra cada km
	puts(g <= a ? "G" : "A");
	return 0;
}
