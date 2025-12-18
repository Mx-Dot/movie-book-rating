import React from 'react';
import './home.css';

export default function Home() {
    const [isLoggedIn] = React.useState(localStorage.getItem('token'));

    return (
        <>
            <div>
                {isLoggedIn ? <p>Welcome back!</p> : <p>Please log in to view your reviews.</p>}
            </div>
        </>
    );
}
