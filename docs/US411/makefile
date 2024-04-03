main: main.o calcEnergy.o
	gcc main.o calcEnergy.o -o main
main.o: main.c asm.h
	gcc -Wall -g -c main.c -o main.o

calcEnergy.o: calcEnergy.c
	gcc -Wall -g -c calcEnergy.c -o calcEnergy.o

run: main
	gdb -tui main 10 5000 20
clean:
	rm *.o main 
	
nrun: main
	./main 10 5000 20
