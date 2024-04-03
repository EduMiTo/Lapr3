#ifndef ASM_H
#define ASM_H
typedef struct{
	char exterior[20], meio[20], interior[20];
	int id_container;
	float ext_tam, meio_tam, int_tam, dim_x, dim_y, dim_z, cap_ext, cap_meio, cap_int; 
	short pos_x, pos_y, pos_z,temp_container; 
	char refri;
} t_container_info;

float calcEnergy(t_container_info s, float externalTemp);
#endif
