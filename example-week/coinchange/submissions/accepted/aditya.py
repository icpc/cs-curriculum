x = int(input())
MOD = int(1e9 + 7)

coins = [1, 5, 10, 25]

dp = [0 for _ in range(x + 1)]
dp[0] = 1

for i in range(1, x + 1):
    for c in coins:
        if i - c >= 0:
            dp[i] = (dp[i] + dp[i - c]) % MOD
            
print(dp[x])
