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

#### Design

The App class defines the main method from where the quotes a queried. This class takes the input validates it and also returns the ouput that the system generates.

I have packaged the core functionality into 'core.quotes' as this would make this part of the system less coupled into what I am sure it could easily become a larger system if developed further.

*QuoteIllustrator class*

The QuoteIllustrator class abstracts the functionality to provide the final illustration.
This way other formats could be easily incorporated: i.e print illustration to PDF file.

Since I wanted to sort the list of lenders I decided to do it in this class.
This way the DataReader class follows no other logic other than parsing the data and the LoanCalculator follows no other logic than calculating loans with the data submitted.

*DataReader class*

As this class is not supposed to save any state so I decided to define it's readData method static.

*LoanCalculator class*

The monthly compound formula is defined here, as well as, computing the final loan by considering if the requested amount is available from the lenders pool and can be borrowed.

#### Challenges:

- Choosing the lowest rate is an easy task if at least 1 lender in the pool is lending an amount equal or higher than the requested amount.
But when that's not the case I assumed that the system has to borrow from different lenders and at the same time ensure that lenders are paid the rate agreed.

In order to pay every lender the agreed rate the system will sort all lenders by rate and start borrowing from each lender until the requested amount is fully covered.
Best case scenario is when a lender has enough money to borrow the money in full and this one offers the lowest rate, otherwise as the system takes money from other lenders the final rate will be higher.

Although not requested I thought that it would be good to provide the period of time customers will be repaying their loan.
I didn't add this feature but in this case, following an agile way of working, I would raise it and speak to the product owner for to consider if this would add value.

### Trade-offs

I didn't find a formula that would match the numbers in the example given.
I assumed that a generic formula would work in the mean time but if I had the chance I would work close to an actuarial or financial adviser to derive the correct formula.

The way I allocate the final rate to quote a loan doesn't feel optimal, best option would be to find an appropriate way to calculate a proportion to assign the best rate possible.

## Contact

Raul Rodriguez

raulra08@gmail.com