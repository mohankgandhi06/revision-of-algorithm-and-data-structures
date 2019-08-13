package com.revision.ctci.ktesting;

public class FTestAnATM {
    /* Testing an ATM machine */
    /* Modules involved:
     * Developer insight - (all the way we need session, database interaction, thread, atomic commit features)
     * #1: Login
     * #2: Choose Mode [Deposit | Withdrawal | Change PIN | Cancel | Inquiry]
     * #3: For each of the above mode working
     * Simulated Test Case
     * For certain scenario we have to simulate the behaviour which might not be possible normally but could happen
     * for example a person cannot withdraw or deposit money at two different ATM if they can have only one card mapped
     * to one account. but we need to simulate like using an old card and new one scenario, cloned card scenario
     * Manual Test Case
     * Login -   Normal case input
     *           Give an incorrect pin | empty pin and enter
     *           After giving the proper password click cancel and check if logged out
     *           more number of illegal password tries
     * Deposit - Normal case input
     *           Deposit an amount less than accepted denomination
     *           Give one amount and deposit a different real money - Check if counting is proper (multiple times)
     *           Deposit using multiple locations (Not in actual location but try simulating it with thread)
     * Withdrawal - Parallel withdrawal
     *              Insufficient/Illegal Amount
     *              Clicking cancel/enter/other keys while transaction is happening so that potentially it might break
     *              Leave the money without taking
     *              Power Cut or Damage to the machine and some failure scenario
     *
     * Transaction: It occurs and updates in database. so simulate a scenario were the database is loaded to the brim
     *             and person additional transaction and check if behaviour is expected
     * For all the transaction check whether even if one step fails if everything fails
     * One person depositing and other person withdrawing during the process (affecting the same account)
     * Illegal input - try inputting some values when amount should be entered */
}