.section .text

	.global isrefri
	
isrefri:
	

	#rcx esta o numero de strucks, ou seja o tamanho do ciclo

	loop:
		movl $0, %r9d
	
	
	cmpw 100(%r8), %di
	je certo1
	
	cont1:
	
	
	cmpw 102(%r8), %si
	je certo2
	
	cont2:
	
	
	
	cmpw 104(%r8), %dx
	je certo3
	
	cont3:
	
	cmpl $3, %r9d
	je encontrado
	
	addq $112, %r8
	
	
	loop loop
	
	jmp end
	
	
certo1:
	incl %r9d
	jmp cont1
	
certo2:
	incl %r9d
	jmp cont2
	
certo3:
	incl %r9d
	jmp cont3
	
encontrado:
	
		
	cmpb $0, 108(%r8)
	je	end
	
	movl $1, %eax
	ret
	
end:
	movl $0, %eax
	ret
