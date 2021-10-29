// Arup Guha
// 9/18/2015
// Examples for Brute Force Lecture for Programming Team
// http://www.cs.ucf.edu/~dmarino/progcontests/devteam-15/bruteforce.c

#include <stdio.h>

#define ODOMETER 0
#define PERMS 0
#define COMBOS 0
#define DERANGEMENTS 1
#define UPWARDS 0

// Recursive functions.
void printOdometer(int odometer[], int k, int n);
void printPerms(int perm[], int used[], int k, int n);
void printCombos(int subset[], int k, int n);
void printDerangements(int perm[], int used[], int k, int n);
void printUpWards(char word[], int skip, int k, int n);

// Prints our results.
void print(int array[], int n);
void printSubsets(int subset[], int n);

// Wrapper functions that run the recursive functions.
void runOdometer();
void runPerms();
void runCombos();
void runDerangements();
void runUpWards();

int main() {

    // Run what you want...
    if (ODOMETER) runOdometer();
    if (PERMS) runPerms();
    if (COMBOS) runCombos();
    if (DERANGEMENTS) runDerangements();
    if (UPWARDS) runUpWards();

    return 0;
}

// Runs a 4 digit odometer.
void runOdometer() {
    int meter[4];
    printOdometer(meter, 0, 4);
    printf("\n");
}

// Prints all possible seetings of odometer with n digits with the first k fixed.
void printOdometer(int odometer[], int k, int n) {

    // Base case.
    if (k == n) print(odometer, n);

    // Fill in each possible digit in slot k and recurse.
    else {
        int i;
        for (i=0; i<10; i++) {
            odometer[k] = i;
            printOdometer(odometer, k+1, n);
        }
    }
}

// Prints array[0..n-1].
void print(int array[], int n) {
    int i;
    for (i=0; i<n; i++)
        printf("%d ", array[i]);
    printf("\n");
}

// Prints out all permutations of 0,1,2,3.
void runPerms() {
    int perm[4];
    int i, used[4];
    for (i=0; i<4; i++) used[i] = 0;
    printPerms(perm, used, 0, 4);
    printf("\n");
}

// Prints all permutations of 0,1,...,n-1 where the first k items of perm are fixed.
void printPerms(int perm[], int used[], int k, int n) {

    // Base case.
    if (k == n) print(perm, n);

    // Recursive case - fill in slot k.
    else {
        int i;

        // Only fill slot k with items that have yet to be used. If i hasn't been used,
        // put it in slot k and recursively print all permutations with these k+1 starting items.
        for (i=0; i<n; i++) {
            if (!used[i]) {
                used[i] = 1;
                perm[k] = i;
                printPerms(perm, used, k+1, n);
                used[i] = 0;
            }
        }
    }
}

// Prints all combinations of 0,1,2,3,4.
void runCombos() {
    int i, items[5];
    for (i=0; i<5; i++) items[i] = 0;
    printCombos(items, 0, 5);
    printf("\n");
}

void printCombos(int subset[], int k, int n) {

    // Base case, subset filled in.
    if (k == n) printSubsets(subset, n);

    // Recursive case - fill slot k.
    else {

        // First do subset without item k.
        printCombos(subset, k+1, n);

        // Now do the subset with item k. Must return subset to original setting!!!
        subset[k] = 1;
        printCombos(subset, k+1, n);
        subset[k] = 0;
    }
}

// Prints out the subset of 0,1,2..,n-1 represented by subset. subset[i] is 1 iff i is in the subset.
void printSubsets(int subset[], int n) {
    int i;
    for (i=0; i<n; i++)
        if (subset[i])
            printf("%d ", i);
    printf("\n");
}

// Prints out all derangements of 0,1,2,3,4.
void runDerangements() {
    int perm[4];
    int i, used[4];
    for (i=0; i<4; i++) used[i] = 0;
    printDerangements(perm, used, 0, 4);
    printf("\n");
}

// Prints out all derangements in perm with the first k digits fixed.
void printDerangements(int perm[], int used[], int k, int n) {

   // Base case.
    if (k == n) print(perm, n);

    // Recursive case - fill in slot k.
    else {
        int i;

        // Same as permutation, but we don't allow slot k to be filled with k.
        for (i=0; i<n; i++) {
            if (!used[i] && i != k) {
                used[i] = 1;
                perm[k] = i;
                printDerangements(perm, used, k+1, n);
                used[i] = 0;
            }
        }
    }
}

// Prints all 5 length upwards with skip 2.
void runUpWards() {
    char word[6];
    word[5] = '\0';
    printUpWards(word, 2, 0, 5);
    printf("\n");
}

// Prints all skip-level upwords of length n.
void printUpWards(char word[], int skip, int k, int n) {

    // Base case - word is filled in.
    if (k == n) printf("%s\n", word);

    // Try each possible item in slot k.
    else {

        // If we haven't filled anything yet, we start with 'a', otherwise go to the last character and
        // count forward skip+1 places.
        char start = k == 0 ? 'a' : word[k-1] + skip + 1;

        char next;
        for (next=start; next<='z'; next++) {
            word[k] = next;
            printUpWards(word, skip, k+1, n);
        }
    }
}
