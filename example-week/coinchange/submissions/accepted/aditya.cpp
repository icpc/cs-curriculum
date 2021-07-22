#include <cmath>
#include <cstdio>
#include <vector>
#include <iostream>
#include <algorithm>
using namespace std;

const int MOD = 1e9 + 7;

int main() {
    int x; cin >> x;
    vector<long long> dp(x + 1);
    dp[0] = 1;
    vector<int> coins = {1, 5, 10, 25};
    
    for(int i = 1; i <= x; i++) {
        for(int& c : coins) {
            if(i - c >= 0) {
                dp[i] = (dp[i] + dp[i - c]) % MOD;
            }
        }
    }
    
    cout << dp[x] << endl;
    
    return 0;
}
