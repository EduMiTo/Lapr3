main: main.o refri.o calcEnergy.o
	gcc main.o refri.o calcEnergy.o -o main
main.o: main.c asm.h
	gcc -Wall -g -c main.c -o main.o

calcEnergy.o: calcEnergy.c
	gcc -Wall -g -c calcEnergy.c -o calcEnergy.o


refri.o: refri.s
	gcc -Wall -g -c refri.s -o refri.o
run: main
	gdb -tui main 20
clean:
	rm *.o main
	
nrun: main 
	./main 20
