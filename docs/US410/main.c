#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "asm.h"



int main(int argc, char *argv[]){
	
	char exterior[20], meio[20], interior[20];
	int id_container;
	float ext_tam, meio_tam, int_tam, dim_x, dim_y, dim_z, cap_ext, cap_meio, cap_int; 
	short pos_x, pos_y, pos_z,temp_container; 
	char refri;
	FILE *file;
	
	file = fopen("containers_info.txt", "r");
	if(file == NULL){
		perror("fopen()");
		return 1;
	}
	
	
	t_container_info *ptr = NULL;
	t_container_info *aux = NULL;
	ptr = (t_container_info *) malloc(sizeof(t_container_info));
	aux = ptr;
	int size = 1;
	int cont=0;
	while(!feof(file)){	
		fscanf(file, "%d %hd %hd %hd %f %f %f %hhd %s %s %s %f %f %f %f %f %f %hd", &id_container, &pos_x, &pos_y, &pos_z, &dim_x, &dim_y, &dim_z, &refri, exterior, meio, interior, &ext_tam, &meio_tam, &int_tam, &cap_ext, &cap_meio, &cap_int, &temp_container);

		cont++;
	}	
	
	printf("%d\n", cont);										//inicio while


	t_container_info array[cont];
	
	file = fopen("containers_info.txt", "r");
	if(file == NULL){
		perror("fopen()");
		return 1;
	}
	
	
	while(!feof(file)){												//inicio while
		
		ptr = (t_container_info*) realloc(aux, size*sizeof(t_container_info));
		
		fscanf(file, "%d %hd %hd %hd %f %f %f %hhd %s %s %s %f %f %f %f %f %f %hd", &id_container, &pos_x, &pos_y, &pos_z, &dim_x, &dim_y, &dim_z, &refri, exterior, meio, interior, &ext_tam, &meio_tam, &int_tam, &cap_ext, &cap_meio, &cap_int, &temp_container);
		ptr -> id_container = id_container;
		ptr -> pos_x = pos_x;
		ptr -> pos_y = pos_y;
		ptr -> pos_z = pos_z;
		ptr -> dim_x = dim_x;
		ptr -> dim_y = dim_y;
		ptr -> dim_z = dim_z;
		ptr -> refri = refri;
		strcpy(ptr -> exterior, exterior);
		strcpy(ptr -> meio, meio);
		strcpy(ptr -> interior, interior);
		ptr -> ext_tam = ext_tam;
		ptr -> meio_tam = meio_tam;
		ptr -> int_tam = int_tam;
		ptr -> cap_ext = cap_ext;
		ptr -> cap_meio = cap_meio;
		ptr -> cap_int = cap_int;
		ptr -> temp_container = temp_container;
		
		printf(" ------------------------------------------------\n");
		printf("| Container ID:                        %d |\n", ptr -> id_container);
		printf("| Posição X:                           %hd         |\n", ptr -> pos_x);
		printf("| Posição Y:                           %hd         |\n", ptr -> pos_y);
		printf("| Posição Z:                           %hd         |\n", ptr -> pos_z);
		printf("| Dimensão X(comprimento):             %.2f      |\n", ptr -> dim_x);
		printf("| Dimensão Y(largura):                 %.2f     |\n", ptr -> dim_y);
		printf("| Dimensão Z(altura):                  %.2f      |\n", ptr -> dim_z);
		printf("| Refrigerado(0->sim || 1->nao):       %hhd         |\n", ptr -> refri);
		printf("| Tipo de material da camada exterior: %s      |\n", ptr -> exterior);
		printf("| Tipo de material da camada do meio:  %s      |\n", ptr -> meio);
		printf("| Tipo de material da camada interior: %s      |\n", ptr -> interior);
		printf("| Espessura da camada exterior:        %.2f      |\n", ptr -> ext_tam);
		printf("| Espessura da camada do meio:         %.2f      |\n", ptr -> meio_tam);
		printf("| Espessura da camada interior:        %.2f      |\n", ptr -> int_tam);
		printf("| Capacidade exterior:                 %.2f      |\n", cap_ext = cap_ext);
		printf("| Capacidade do meio:                  %.2f      |\n", cap_meio = cap_meio);
		printf("| Capacidade interior:                 %.2f     |\n", cap_int = cap_int);
		printf("| Temperatura do Container:            %hd        |\n", ptr -> temp_container);
		printf(" ------------------------------------------------\n");
		
		array[size-1]=*ptr;

		
		size++;
		ptr += size;
		aux = ptr - size;
	}																			//fim while
	
	fclose(file);
	
	printf("%ld\n", sizeof(t_container_info));

	printf("%d\n", cont);
	printf("%d\n", array[0].id_container);
	
	short x=0, y=0, z=3;
	
	char teste= isrefri(x,y,z, cont, array);
	
	printf("%d\n", teste);
	
	float tempExt= atoi(argv[1]);
	char *outFileName = "US410.csv";
	FILE *outFile = fopen(outFileName, "a");
	
	if(teste==1){
		float teste2= calcEnergy(array, tempExt);
		printf("%f\n", teste2);
		fprintf(outFile, "The container in position x: %d, y: %d, z: %d, needs %f J to keep the required temperature\n",x,y,z,teste2);
	}
	else{
		fprintf(outFile, "The container in position x: %d, y: %d, z: %d, is not refrigerated \n",x,y,z);
	}
	
	
	
	
	
	fclose(outFile);

	
	
	
	void free(void *ptr);
	return 0;

}
