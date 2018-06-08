# Zopa Core

There is a need for a rate calculation system allowing prospective borrowers to obtain a quote from a pool of lenders for 36 month loans.

This system takes the form of a command-line application, it is written in Java 8 code, to run the application please refer to the [Run](#run) section.

### Requirements

* Maven 3.5.x
* Java 8

### Build

To build the solution run the following maven command

```
mvn clean install
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

I faced the following challange. Choosing the lowest is an easy task if all lenders in the pool are lending an amount equal or higher that the amount requested.
Since that's not always the case the system has to take money from different borrowers and ensure that lenders are paid to the specifies rate.
Hence in order to pay everyone the system will sort all lenders by rate and start borrowing from each lender until the requested amount is fully allocated to a loan.

Once the system defines the rate to be used then a quote can be generated using monthly compounding interest.

### Trade-offs

The compound interest formula isn't correct.
I assumed that a generic formula would serve in the mean time but if I had the chance I would work close to an actuarial or financial adviser to derive the correct formula.

## Contact

Raul Rodriguez

raulra08@gmail.com