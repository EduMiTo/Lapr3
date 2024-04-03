.section .data
	.global capx
	.global capy
	.global capz
	
.section .text
	.global emptyoccupied
	
emptyoccupied:
	
	#shippos em rdi
	#maxNumber em rsi
	
	movl $0, %edx
	movl $0, %r8d
	movq $0, %rcx
	
	movl %esi, %ecx
	
	myloop:
	
	#offset=z+capz*(y+capy*x)
	#6,3,3
	
	movl (%rdi), %r9d
	
	cmpl $0, %r9d
	jne counterOc
	jmp counterNOC
	
	cont:
	addq $4, %rdi
	
	
	
	
	loop myloop
	
	
	
	jmp end
	
	
counterOc:

	incl %edx
	
	cmpq $0, %rcx
	je end
	jmp cont

counterNOC:
	incl %r8d
	
	
	cmpq $0, %rcx
	je end
	jmp cont

end:

	movl $0, %eax
	
	movq %rdx, %rax
	shlq $32, %rax	
	addq %r8, %rax
	ret
	
	
