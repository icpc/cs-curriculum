#include <iostream>
#include <map>

const int64_t p = 1000000007;

int64_t coinchange(int64_t n)
{
    if(n == 0)
        return 1LL;
    else if(n < 0)
        return 0;

    int64_t coins[] = {1,5,10,25};
    int64_t answer = 0;
    for(auto c : coins)
    {
        answer = (answer + coinchange(n-c)) % p;
    }
    return answer;
}

int main()
{
    int64_t n;
    std::cin >> n;
    std::cout << coinchange(n) << std::endl;
}
