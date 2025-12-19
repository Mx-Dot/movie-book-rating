import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router';

export default function AccountPage() {
    const navigate = useNavigate();
    const [user, setUser] = useState({ email: '', username: '', memberSince: '' });

    useEffect(() => {
        const token = localStorage.getItem('token');
        if (!token) {
            navigate('/login', { replace: true });
            return;
        }

        /**
         * @TODO: Replace with actual user data from the backend.
         */
        setUser({
            email: 'user@example.com',
            username: 'MovieFan2024',
            memberSince: 'January 2024'
        });

    }, [navigate]);

    return (
        <div className="min-h-screen bg-gray-50 py-8 px-4">
            <div className="max-w-4xl mx-auto">
                <div className="bg-white shadow-lg rounded-lg overflow-hidden">
                    {/* Header Section */}
                    <div className="bg-gradient-to-r from-indigo-600 to-purple-600 px-6 py-8">
                        <div className="flex items-center space-x-4">
                            <div className="w-20 h-20 bg-white rounded-full flex items-center justify-center text-3xl font-bold text-indigo-600">
                                {user.username.charAt(0).toUpperCase()}
                            </div>
                            <div className="text-white">
                                <h1 className="text-3xl font-bold">{user.username}</h1>
                                <p className="text-indigo-100 mt-1">Member since {user.memberSince}</p>
                            </div>
                        </div>
                    </div>

                    {/* Account Details */}
                    <div className="p-6 space-y-6">
                        <div>
                            <h2 className="text-xl font-semibold text-gray-800 mb-4">Account Information</h2>
                            <div className="space-y-4">
                                <div className="flex items-center justify-between py-3 border-b border-gray-200">
                                    <span className="text-gray-600 font-medium">Email</span>
                                    <span className="text-gray-800">{user.email}</span>
                                </div>
                                <div className="flex items-center justify-between py-3 border-b border-gray-200">
                                    <span className="text-gray-600 font-medium">Username</span>
                                    <span className="text-gray-800">{user.username}</span>
                                </div>
                            </div>
                        </div>

                        {/* Stats Section */}
                        <div>
                            <h2 className="text-xl font-semibold text-gray-800 mb-4">Your Activity</h2>
                            <div className="grid grid-cols-1 md:grid-cols-3 gap-4">
                                <div className="bg-indigo-50 rounded-lg p-4 text-center">
                                    <p className="text-3xl font-bold text-indigo-600">0</p>
                                    <p className="text-gray-600 mt-1">Movie Reviews</p>
                                </div>
                                <div className="bg-purple-50 rounded-lg p-4 text-center">
                                    <p className="text-3xl font-bold text-purple-600">0</p>
                                    <p className="text-gray-600 mt-1">TV Show Reviews</p>
                                </div>
                                <div className="bg-pink-50 rounded-lg p-4 text-center">
                                    <p className="text-3xl font-bold text-pink-600">0</p>
                                    <p className="text-gray-600 mt-1">Total Ratings</p>
                                </div>
                            </div>
                        </div>

                        {/* Action Buttons */}
                        <div className="pt-4 space-y-3">
                            <button className="w-full bg-indigo-600 text-white py-3 rounded-lg hover:bg-indigo-700 transition-colors duration-150 font-medium">
                                Edit Profile
                            </button>
                            <button className="w-full bg-gray-200 text-gray-700 py-3 rounded-lg hover:bg-gray-300 transition-colors duration-150 font-medium">
                                Change Password
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}