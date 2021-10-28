<h2>Editorial: Maximum Subarrays</h2>
**Link**:[https://open.kattis.com/problems/maximumsubarrays/](https://open.kattis.com/problems/maximumsubarrays/)

One method to envision dynamic programming algorithms is to think about making choices about a problem as you move
through the data, left to right, keeping track of the current state, necessary information you need to know as you
proceed through making future choices. In this problem, imagine forming the k subarrays as you are moving left to
right. After processing some values (let's say the first m values), the items that matter as as follows:

(1) How many values have already been processed. (m)<br>
(2) How many completed subarrays have already been formed. (done)<br>
(3) Is there a current subarray that is "open" that can be extended? (open)<br>

<pre>
Consider the array: 3, -5, 2, 4, -1, 7, -3, -4, 1, 2
                              ^
</pre>                              
Imagine the situation with m = 3, done = 1, and open = True. In this case, f(3, 1, True) corresponds to the best score 
we can achieve, moving forward, starting at index 3, knowing that the second subarray has been opened. In this case, we 
have three possible options:

(1) Add 4 to the current running subarray - if we do this our score is 4 + f(4, 1, True).
(2) Skip 4 and complete the second subarray - if we do this our score is f(4, 2, False).
(3) Stop the previous subarray and start a new one - if we do this our score is f(4, 2, True)

Of these three options, whichever returns the highest score will be what f(3, 1, True) returns.

If we didn't have a streak running, then only two of these options (#2 and #3) would be valid, since there would be
no current subarray to extend.

In running the recursion, we treat the input value k as a global variable that can be referenced in the recursive function.
When we have completed k subarrays, we must return 0, since moving forward from that point, no more can be added to our score.

Finally, our best answer is the value of f(0, 0, False), since this represents the best score moving forward, after
processing 0 values, having 0 completed subarrays, and no subarray running.

To get the recursive solution to run fast enough, memoization must be used to store answers to each unique recursive call.
The maximum number of unique recursive calls that could happen is 5000 x 5000 x 2 = 50 million, since both n and k can
range upto 5000. Since the transition function runs in O(1) time (just looking at three simple options at most), this solution 
takes O(nk) time, which will pass in the time limit allocated.




