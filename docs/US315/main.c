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
	
	
	remove("US315.txt");
	
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
	
	
	
	
	

	
	int posx;
	int posy;
	int posz;
	
	
		do{
			printf("x: ");
			scanf("%d", &posx);
			if(posx>capx){
				printf("Out of ship range\n");
			}
		}while(posx>capx);
		
		printf("\n");
		
		do{
			printf("y: ");
			scanf("%d", &posy);
			if(posy>capy){
				printf("Out of ship range\n");
			}
		}while(posy>capy);
		
		printf("\n");
		do{
			printf("z: ");
			scanf("%d", &posz);
			if(posz>capz){
				printf("Out of ship range\n");				
			}
		}while(posz>capz);
		printf("\n");

		
	

	int total=checkPos(posx,posy,posz,shipPos);
	
	printf("Number: %d\n", total);
	
	char *outFileName = "US315.txt";
	FILE *outFile = fopen(outFileName, "a");
	fprintf(outFile, "Number: %d\n",total);
	fprintf(outFile, "\n");
	fclose(outFile);
	
	return 0;
}


void printer(int x, int y, int z)
{
	char *outFileName = "US315.txt";
	FILE *outFile = fopen(outFileName, "a");
	
	fprintf(outFile, "There is a container in position %d, %d, %d\n", x,y,z);
	fprintf(outFile, "\n");
	
	fclose(outFile);
	printf("There is a container in position %d, %d, %d\n", x,y,z);
	
}
