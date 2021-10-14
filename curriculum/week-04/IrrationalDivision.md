<h2>Editorial: Irrational Division</h2>
**Link**:[https://open.kattis.com/problems/irrationaldivision/](https://open.kattis.com/problems/irrationaldivision/)

In thinking about this problem, one must look at what pieces of information affect the state of the game. The obvious things that affect the game are

1. Number of rows
2. Number of columns

There are two other items that matter that may be less obvious to some:

3. Whose turn it is
4. Whether the top left corner of the rectangle remaining is Dark or White chocolate.

In developing a dynamic programming solution, we first want to come up with a recursively defined function, where we can find the answer by building off of answers to "smaller" subproblems of the same nature.

Noting that we have 4 relevant items to computing the answer, we define the following function:

f(r, c, turn, topleft) = score in a game where there are r rows and c columns, the person moving next is turn (0=you,1=sister), and topleft (0=dark,1=white) represents the color of the chocolate at the top left corner.

If it's your turn, then you must make a vertical cut, eating a region of chocolate with the size r by i, where i is the number of columns you choose to eat.
If it's your sister's turn, then she makes a horizontal cut, eating a region of chocolate with the size i by c, where i is the number of rows she wants to eat.
