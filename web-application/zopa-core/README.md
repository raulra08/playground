# Zopa Core

There is a need for a rate calculation system allowing prospective borrowers to obtain a quote from a pool of lenders for 36 month loans.

This system takes the form of a command-line application, it is written in Java 8 code in following TDD. To run the application please refer to the [Run](#run) section.

### Requirements

* Maven 3.5.x
* Java 8

### Build

To build the solution run the following maven command

```
mvn package
```

### Test

To run unit tests only run:

```
mvn test
```

### Run

To run the application in the form of a command line tool run the script bellow.

Remember to make sure maven is installed and that you have build the application.

```
./quote-loan.sh marketData.csv 1000
```

### The Solution

I faced the following challenge:

- Choosing the lowest rate is an easy task if all lenders in the pool are lending an amount equal or higher than the requested amount.
But what happens when the system has to take money from different lenders and at the same time ensure that lenders are paid the rate agreed.

In order to pay every lender in this scenario the system will sort all lenders by rate and start borrowing from each lender until the requested amount is fully covered amongs few or all lenders to a loan.
Best case scenario is when a lender has enough money to borrow the money in full and this one offers the lowest rate, otherwise as the system takes money from other lenders the final rate will be higher.


### Trade-offs

I didn't find a formula that would match the numbers in the given example.
I assumed that a generic formula would serve in the mean time but if I had the chance I would work close to an actuarial or financial adviser to derive the correct formula.

The way I allocate the final rate to quote a loan doesn't feel optimal, best option would be to find an appropriate way to calculate a proportion to assign the best rate possible.

## Contact

Raul Rodriguez

raulra08@gmail.com