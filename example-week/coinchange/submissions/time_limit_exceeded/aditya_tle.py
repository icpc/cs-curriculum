x = int(input())
MOD = int(1e9 + 7)

coins = [1, 5, 10, 25]

def recurse(left):
	if left == 0:
		return 1
	else:
		ans = 0
		for c in coins:
			if left - c >= 0:
				ans = (ans + recurse(left - c)) % MOD

		return ans

print(recurse(x))
