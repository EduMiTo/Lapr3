#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "asm.h"

float calcEnergy(t_container_info container, float externalTemp){
	
	float requiredTemp = container.temp_container;


    float area = (container.dim_x * container.dim_z * 4) + (container.dim_y * container.dim_z * 2);

    float tamExt = container.ext_tam, fCapacity = container.cap_ext;
    float tamMeio = container.meio_tam, mCapacity = container.cap_meio;
    float tamInt = container.int_tam, iCapacity = container.cap_int;

    float totalResistivity = 0;

    totalResistivity += (tamExt/(fCapacity * area));
    totalResistivity += (tamMeio/(mCapacity * area));
    totalResistivity += (tamInt/(iCapacity * area));

    float requiredEnergy = (((externalTemp - requiredTemp)/totalResistivity) * 3600);

    return requiredEnergy;
}
