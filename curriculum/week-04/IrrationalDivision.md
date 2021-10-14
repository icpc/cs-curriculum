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

Thus, what we can do is try out all possible cuts, and of all possible cuts, take the best outcome of the game (maximum score).

What remains to be solved is, given a particular cut, what score will be achieved?

One subproblem that is helpful to solve is the following: given the dimensions of a piece of chocolate and the color of the top left corner, how many more Dark pieces of chocolate does it have than White pieces? This subproblem is left as an exercise for the reader. From this point on, we'll refer to score(r, c, topleft) as being the score for eating a region of chocolate of size r rows by c columns with the topleft corner color specified by topleft.

First, let's look at the situation where it's your turn:

You make a vertical cut, eating chocolate of size r by i. Your score for eating this piece is score(r, i, topleft). But, after you eat this piece, it is your sister's turn and she will score f(r, c-i, otherturn, newtopleft), where otherturn represents that your sister is moving next, and newtopleft represents the color of the resulting chocolate after your cut is made. If i is even, then newtopleft is the same as topleft, otherwise it's the opposite. Thus, the score for eating i columns of chocolate is

score(r, i, topleft) - f(r, c-i, otherturn, newtopleft)

because your score is the negative of the score your sister achieves.

A similar calculation can be made if it's your sister's turn. In this case, one nice thing is that the top left of the rectangle remains the same, since she cuts from the bottom.

In terms of implementation, it's probably easiest to code this with recursive code with a memoization table. A bit of care must be taken with the memoization table to not confuse a subproblem that has never been solved with one that has been solved but has a negative answer.

It turns out that some careful analysis can lead to a case work solution that looks at the parity of the number of rows and columns, as well as whether there are more rows and columns. (There are four separate cases that can be distinguished with some if statements.) But, for learning purposes, this problem serves as a nice example of dynamic programming.
