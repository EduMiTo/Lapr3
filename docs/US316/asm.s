.section .data
	.global capx
	.global capy
	.global capz
	
.section .text
	.global ocupiedSlots
	.global checkPos
	.global printer
	
ocupiedSlots:
	
	
	#arrayx em rdi
	#arrayy em rsi
	#arrayz em rdx
	#n em rcx
	#shippos em r8
	
	movl $0, %r12d
	
	myloop:
	
	#offset=z+capz*(y+capy*x)
	#6,3,3
	
	
	movl (%rdi), %eax
	movl (%rsi), %r10d
	movl (%rdx), %r11d
	

	pushq %rdi
	pushq %rsi
	pushq %rdx
	pushq %rcx
	pushq %r8
	pushq %r12

	
	movl %eax, %edi
	movl %r10d, %esi
	movl %r11d, %edx
	movq %r8, %rcx
	
	movl $0, %eax
	
	call checkPos
	
	popq %r12
	
	addl %eax, %r12d
	
	popq %r8
	popq %rcx
	popq %rdx
	popq %rsi
	popq %rdi
	
	
	addq $4, %rdi
	
	addq $4, %rsi
	
	addq $4, %rdx
	
	
	loop myloop
	
	jmp endf1
	

endf1:
	
	movl %r12d, %eax
	ret
	
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
	
	
