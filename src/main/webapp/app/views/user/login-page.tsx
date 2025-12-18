import React, {useState} from 'react';
import {useNavigate} from "react-router";
import axios from 'axios';

type FormState = {
    email: string;
    password: string;
    remember: boolean;
};

export default function LoginPage() {
    const navigate = useNavigate();
    const [form, setForm] = useState<FormState>({email: '', password: '', remember: false});
    const [showPassword, setShowPassword] = useState(false);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState<string | null>(null);

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const {name, value, type, checked} = e.target;
        setForm(prev => ({...prev, [name]: type === 'checkbox' ? checked : value}));
    };

    const validate = (): string | null => {
        if (!form.email.trim()) return 'Email is required';
        if (!form.password) return 'Password is required';
        if (form.password.length < 6) return 'Password must be at least 6 characters';
        return null;
    };

    const handleSubmit = async (e: React.FormEvent) => {
        const email = form.email
        const password = form.password
        const API_URL = process.env.API_PATH;

        if (!API_URL) throw new Error('API_PATH environment variable is not set');

        e.preventDefault();
        setError(null);
        const validation = validate();

        if (validation) {
            setError(validation);
            return;
        }

        setLoading(true);

        try {
            const response = await axios.post(`${API_URL}/login`, {
                email,
                password,
            });

            if (response.status != 200) {
                throw new Error('Login failed. Please try again.');
            }
            localStorage.setItem('token', response.data.token);
            window.dispatchEvent(new Event('storage'));
            navigate('/');
        } catch (err) {
            console.error(err);
            setError('Login failed. Please try again.');
        } finally {
            setLoading(false);
        }
    };

    return (
        <div className="min-h-screen flex items-center justify-center bg-gray-50 px-4">
            <div className="max-w-md w-full bg-white shadow-lg rounded-lg p-8">
                <div className="mb-6 text-center">
                    <img src="/images/logo.png" alt="App logo" className="mx-auto w-12 h-12"/>
                    <h1 className="mt-4 text-2xl font-semibold text-gray-800">Sign in to your account</h1>
                    <p className="mt-1 text-sm text-gray-500">Enter your credentials to continue</p>
                </div>

                {error && (
                    <div role="alert" aria-live="assertive"
                         className="mb-4 text-sm text-red-700 bg-red-50 border border-red-100 p-3 rounded">
                        {error}
                    </div>
                )}

                <form onSubmit={handleSubmit} className="space-y-4" noValidate>
                    <div>
                        <label htmlFor="email" className="block text-sm font-medium text-gray-700">
                            Email
                        </label>
                        <input
                            id="email"
                            name="email"
                            type="email"
                            autoComplete="email"
                            value={form.email}
                            onChange={handleChange}
                            required
                            className="mt-1 block w-full px-4 py-2 border border-gray-200 rounded-md shadow-sm focus:outline-none focus:ring-2 focus:ring-indigo-300"
                        />
                    </div>

                    <div>
                        <label htmlFor="password" className="block text-sm font-medium text-gray-700">
                            Password
                        </label>
                        <div className="relative mt-1">
                            <input
                                id="password"
                                name="password"
                                type={showPassword ? 'text' : 'password'}
                                autoComplete="current-password"
                                value={form.password}
                                onChange={handleChange}
                                required
                                className="block w-full px-4 py-2 border border-gray-200 rounded-md shadow-sm focus:outline-none focus:ring-2 focus:ring-indigo-300"
                            />
                            <button
                                type="button"
                                onClick={() => setShowPassword(s => !s)}
                                className="absolute right-2 top-1/2 -translate-y-1/2 text-sm text-gray-500 px-2 py-1 rounded hover:bg-gray-100 focus:outline-none"
                                aria-label={showPassword ? 'Hide password' : 'Show password'}
                            >
                                {showPassword ? 'Hide' : 'Show'}
                            </button>
                        </div>
                    </div>

                    <div className="flex items-center justify-between">
                        <label className="inline-flex items-center text-sm text-gray-700">
                            <input
                                type="checkbox"
                                name="remember"
                                checked={form.remember}
                                onChange={handleChange}
                                className="h-4 w-4 text-indigo-600 border-gray-300 rounded focus:ring-indigo-300"
                            />
                            <span className="ml-2">Remember me</span>
                        </label>
                        <a href="/forgot-password" className="text-sm text-indigo-600 hover:underline">
                            Forgot password?
                        </a>
                    </div>

                    <div>
                        <button
                            type="submit"
                            disabled={loading}
                            className="w-full flex justify-center items-center px-4 py-2 bg-indigo-600 text-white rounded-md shadow-sm hover:bg-indigo-700 disabled:opacity-60"
                        >
                            {loading ? 'Signing in...' : 'Sign in'}
                        </button>
                    </div>
                </form>

                <p className="mt-6 text-center text-sm text-gray-500">
                    Don’t have an account? <a href="/register" className="text-indigo-600 hover:underline">Create
                    one</a>
                </p>
            </div>
        </div>
    );
}