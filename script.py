#!/usr/bin python3

def main():
	soma = 0
	valor = int(input())
	while not valor == 0:
		soma = soma + valor
		valor = int(input())
	print(soma)

if __name__ == "__main__":
	main()
