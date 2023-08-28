#!/usr/bin python3

def main() -> None:
	times = int(input())
	output = fib(times)
	print(output)

def fib(times: int) -> int:
	last = 1
	past = 1
	result = 0
	if times == 1 or times == 2:
		result = 1
	while times > 2:
		result = last + past
		past = last
		last = result
		times = times - 1
	return result

if __name__ == "__main__":
	main()
