import React from 'react';
import {createBrowserRouter, RouterProvider} from 'react-router';
import App from "./app";
import Home from './home/home';
import Error from './error/error';
import LoginPage from "./views/user/login-page";
import RegisterPage from "./views/user/register-page";
import ForgotCredentialsPage from "./views/user/forgot-credentials-page";
import Tv from "./views/tv-shows/tv";
import Movies from "./views/movies/movies";
import AccountPage from "./views/user/account-page";
import ProtectedRoute from "./common/ProtectedRoute";

/**
 * Sets up and configures the application routes using a browser router.
 *
 * This method defines the routing structure for the application, including
 * paths for various pages like the home page, error page, login page,
 * registration page, and several categorized routes for movies and TV content.
 *
 * @return {JSX.Element} The router provider component with the application routes.
 */
export default function AppRoutes() {
    const router = createBrowserRouter([
        {
            element: <App/>,
            children: [
                {path: '', element: <Home/>},
                {path: 'error', element: <Error/>},
                {path: '*', element: <Error/>},
                {path: 'login', element: <LoginPage/>},
                {path: 'register', element: <RegisterPage/>},
                {path: 'account', element: <ProtectedRoute><AccountPage/></ProtectedRoute>},
                {path: 'forgot-password', element: <ForgotCredentialsPage/>},
                {path: 'movies', element: <Movies/>},
                {path: 'movies/popular', element: <Movies/>},
                {path: 'movies/top-rated', element: <Movies/>},
                {path: 'tv', element: <Tv/>},
                {path: 'tv/popular', element: <Tv/>},
                {path: 'tv/top-rated', element: <Tv/>},
            ]
        }
    ]);

    return (
        <RouterProvider router={router}/>
    );
}
