# Galton-Board-Sim
This repository contains files for the Data structures final project - Galton Board

A Galton board is able to produce an approximate normal distribution because the pegs on the back of the board only allow the balls to go either right or left. This represents a binomial distribution.

We then run an algorithm that initializes a value equal to half of the length of the array and randomly adds either +1 or -1 to that value 25 times. The resulting output represents the column that the ball landed in.

In order to correctly simulate the pegs on the Galton board, the number of random generations should be equal to the number of columns available for the balls to land in.

We used an array to store the heights of each column and we draw the columns in reverse order. This is because the JFrame class’s pixels start in the top left corner and increase as you move down.

To draw our Galton board we used the default graphics library in java.
  
