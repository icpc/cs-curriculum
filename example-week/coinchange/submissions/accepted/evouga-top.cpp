#include <iostream>
#include <map>

const int maxn = 100001;
int64_t memo[maxn];
const int64_t p = 1000000007;

int64_t coinchange(int64_t n)
{
    if(n == 0)
        return 1LL;
    else if(n < 0)
        return 0;

    if(memo[n] != -1)
        return memo[n];        
        
    int64_t coins[] = {25,10,5,1};
    int64_t answer = 0;
    for(auto c : coins)
    {
        answer = (answer + coinchange(n-c)) % p;
    }
    memo[n] = answer;
    return answer;
}

int main()
{
    for(int i=0; i<maxn; i++)
        memo[i] = -1;
    int64_t n;
    std::cin >> n;
    std::cout << coinchange(n) << std::endl;
}
