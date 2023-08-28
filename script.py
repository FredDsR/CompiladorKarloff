#!/usr/bin python3

def main():
	times = int(input())
	output = fib(times)
	print(output)

def fib(times: int) -> int:
	result = 0
	past = 1
	while times > 0:
		result = result + past
		past = result
		times = times - 1
	return result

if __name__ == "__main__":
	main()
