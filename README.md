# Galton-Board-Sim
This repository contains files for the Data structures final project - Galton Board

A Galton board is able to produce an approximate normal distribution because the pegs on the back of the board only allow the balls to go either right or left. This represents a binomial distribution.

We run an algorithm that initializes a value equal to half of the length of the array and randomly adds either +1 or -1 to that value 25 times. The resulting output represents the column that the ball landed in. We used an array to store the heights of each column. To draw our Galton board we used the default graphics library in java.

Upon running Main.java, a prompt will appear "Enter # of balls: ", simply enter the number of balls you want to simulate on the Galton board.
  
