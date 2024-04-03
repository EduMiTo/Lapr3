.section .data
	.global capx
	.global capy
	.global capz
	
.section .text
	.global checkPos
	.global printer
	
checkPos:
	
	
	#posx em edi
	#posy em esi
	#posz em edx
	#shippos em rcx
	
	movl $0, %r12d
	
	#offset=z+capz*(y+capy*x)
	#6,3,3
	
	movl %edi, %eax
	
	imull capy(%rip), %eax
	
	addl %esi, %eax
	
	imull capz(%rip), %eax
	
	addl %edx, %eax
	
	
	imul $4, %eax
	
	movslq %eax, %rax
	
	addq %rax, %rcx
	
	movl (%rcx), %r9d
	
	subq %rax, %rcx
	
	
	cmpl $0, %r9d
	jne print
	
	
	
	jmp end
	
	

print:

	
	
	
	incl %r12d
	call printer
	


end:
	
	movl %r12d, %eax
	ret
	
	
