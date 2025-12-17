import React from 'react';
import { createBrowserRouter, RouterProvider } from 'react-router';
import App from "./app";
import Home from './home/home';
import Error from './error/error';
import Tv from "./views/tv";


export default function AppRoutes() {
  const router = createBrowserRouter([
    {
      element: <App />,
      children: [
        { path: '', element: <Home /> },
        { path: 'error', element: <Error /> },
        { path: '*', element: <Error /> },
        { path: 'movies', element: <Home />},
        { path: 'movies/popular', element: <Home /> },
        { path: 'movies/top-rated', element: <Home /> },
        { path: 'tv', element: <Tv />},
        { path: 'tv/popular', element: <Home />},
        { path: 'tv/top-rated', element: <Home />},
      ]
    }
  ]);

  return (
    <RouterProvider router={router} />
  );
}
