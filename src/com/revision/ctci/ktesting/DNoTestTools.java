package com.revision.ctci.ktesting;

public class DNoTestTools {
    /* Load test a web page without tools */
    /* #1: create many threads to be simulating the user and then try calling the application for the requests
     * #2: also we could create threads of various behaviours each (for example one can request a simple page
     * reload while other asks for a database request and an another one asking ajax request) so that not same
     * service is asked again by all but to ask different kind of service like a typical user would
     * In both the above method create a load of huge proportion log the behaviour or create a REPORT of
     * time taken for the service, RECORDing any failures, timeout scenario, application crashing/getting
     * into deadlock because of resource huge demands, etc.,
     * #3: perform huge database request load test */
}