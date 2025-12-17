package com.mxdot.movie_book_rating.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * Serve Reacts index.html for all requests that are not relevant for the backend.
 */
@Controller
public class ReactForwardController {

    /**
     * Forwards all unmatched requests to the root path of the application.
     * This method is typically used to serve the React application's index.html file
     * for paths not directly handled by the backend.
     *
     * @return the forward directive to the root path of the application.
     */
    @GetMapping("{path:^(?!api|public|css|js|images)[^.]*}/**")
    public String handleForward() {
        return "forward:/";
    }

}
