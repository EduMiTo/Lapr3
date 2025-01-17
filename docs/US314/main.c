#include <stdio.h>
#include "asm.h"

int capx;
int capy;
int capz;

int main(){
	FILE *file;
	int id;
	int x;
	int y;
	int z;
	int length;
	int width;
	int height=6;
	
	int cLength=3;
	int cWidth=1;
	int cHeiht=2;
	
	remove("US314.txt");
	
	
	file=fopen("containers.txt","r");
	if(file==NULL){
		perror("fopen()");
		return 1;
	}
	
	fscanf(file, "%d %d", &length, &width);
	
	capx=length/cLength;
	capy=width/cWidth;
	capz=height/cHeiht;
	
	int shipPos[length/cLength][width/cWidth][height/cHeiht];
	
	for(int i=0;i<capx;i++){
		for(int j=0;j<capy;j++){
			for(int k=0;k<capz;k++){
				shipPos[i][j][k]=0;
			}
		}
	}
		
	while(!feof(file)){
		fscanf(file, "%d %d %d %d", &id, &x, &y, &z);
		shipPos[x][y][z]=id;
	}
	
	fclose(file);
	
	for(int i=0;i<capz;i++){
		printf("z=%d\n",i);
		for(int j=0;j<capx;j++){
			for(int k=0;k<capy;k++){
				printf("%d ", shipPos[j][k][i]);
			}
			printf("\n");
		}
	}
	
	int maxNumber= capx*capy*capz;

	long total=emptyoccupied(shipPos, maxNumber);
	
	printf("%ld\n",total);
	
	
	char *outFileName = "US314.txt";
	FILE *outFile = fopen(outFileName, "a");
	
	int *pointer = (int*) &total;
	
	fprintf(outFile, "Empty locations: %d\n",*pointer);
	pointer++;
	fprintf(outFile, "Occupied locations: %d\n",*pointer);
	fprintf(outFile, "\n");
	fclose(outFile);
	
	return 0;
}



