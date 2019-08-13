package com.revision.ctci.ktesting;

public class BRandomCrashes {
    /* Important Points:
     * #1: Random Crashes but never in the same place.
     * #2: Single Thread Application
     * #3: Basic C Library */

    /* Possible Reasons:
     * Since this a C program related i can only assume the reasons :(
     * #1: what kind of error message if thrown (crash report)?
     * #2: null variable pointed/out of bounds would be forcing the error at the same place (out of the question)
     * #3: pointer/variable related - like above the error will be in same location each  time (out of the question)
     * #4: necessary variable is initialized only after it is run for the first time: calculation or the function (long shot)
     * #5: what happens when we try with different inputs? (are we first inputting anything or just starting the application)
     * #6: memory allocation - the variables are not removed and it gives unexpected behaviour
     * #7: isolate the blocks of code and try executing the same test multiple times in each block separately. since
     *     even though the problem seems to occur all over the places the issue looks like because of one specific reason
     * #8: look into the code for any null/pointer/exception handling/arithmetic irregularities
     * #9: resource allocation: is the application trying to use some other resource which is being used across other
     *     operating system based application which definitely needs to run even though our application is an single thread
     *     it still needs a certain operating system. try different OS scenarios or if possible run in an isolated environment
     * */
}