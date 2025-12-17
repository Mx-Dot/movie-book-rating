import React, {useEffect, useRef} from 'react';
import {Link} from 'react-router';
import {useTranslation} from 'react-i18next';


export default function Header() {
    const {t} = useTranslation();
    const headerRef = useRef<HTMLElement | null>(null);

    useEffect(() => {
        const currentHeader = headerRef.current;
        if (!currentHeader) return;

        const handleClick = (event: MouseEvent) => {
            const target = event.target as HTMLElement | null;
            if (!target) return;

            const clickedDropdown = target.closest('.js-dropdown');
            const dropdowns = currentHeader.querySelectorAll<HTMLElement>('.js-dropdown');

            dropdowns.forEach((dropdown) => {
                if (clickedDropdown !== dropdown && dropdown.getAttribute('data-dropdown-keepopen') !== 'true') {
                    dropdown.setAttribute('aria-expanded', 'false');
                    dropdown.nextElementSibling?.classList.add('hidden');
                }
            });

            if (clickedDropdown) {
                const isOpen = clickedDropdown.getAttribute('aria-expanded') === 'true';
                clickedDropdown.setAttribute('aria-expanded', String(!isOpen));
                clickedDropdown.nextElementSibling?.classList.toggle('hidden');
            }
        };

        currentHeader.addEventListener('click', handleClick);
        return () => currentHeader.removeEventListener('click', handleClick);
    }, []);

    return (
        <header ref={headerRef} className="bg-gray-50">
            <div className="container mx-auto px-4 md:px-6">
                <nav className="flex flex-wrap items-center justify-between py-2">
                    <Link to="/" className="flex py-1.5 mr-4">
                        <img src="/images/logo.png" alt={t('app.title')} width="30" height="30"
                             className="inline-block"/>
                        <span className="text-xl pl-3">{t('app.title')}</span>
                    </Link>
                    <button type="button" className="js-dropdown md:hidden border rounded cursor-pointer"
                            data-dropdown-keepopen="true"
                            aria-label={t('navigation.toggle')} aria-controls="navbarToggle" aria-expanded="false">
                        <div className="space-y-1.5 my-2.5 mx-4">
                            <div className="w-6 h-0.5 bg-gray-500"></div>
                            <div className="w-6 h-0.5 bg-gray-500"></div>
                            <div className="w-6 h-0.5 bg-gray-500"></div>
                        </div>
                    </button>
                    <div
                        className="hidden md:block grow md:grow-0 justify-end basis-full md:basis-auto pt-3 md:pt-1 pb-1"
                        id="navbarToggle">
                        <ul className="flex space-x-5">
                            <li>
                                <Link
                                    to="/"
                                    className="block text-gray-500 px-3 py-2 rounded-md hover:bg-gray-100 hover:text-gray-700 transition-colors duration-150 focus:outline-none focus:ring-2 focus:ring-indigo-300">
                                    {t('navigation.home')}
                                </Link>
                            </li>

                            <li className="relative">
                                <button
                                    type="button"
                                    className="js-dropdown block text-gray-500 px-3 py-2 rounded-md hover:bg-gray-100 hover:text-gray-700 transition-colors duration-150 focus:outline-none focus:ring-2 focus:ring-indigo-300"
                                    aria-controls="moviesMenu"
                                    aria-expanded="false">
                                    {t('movie_ratings.title')}
                                </button>
                                <div
                                    id="moviesMenu"
                                    className="hidden absolute left-0 mt-2 w-48 bg-white border rounded shadow-md z-20"
                                    role="menu"
                                    aria-labelledby="moviesMenu">
                                    <ul className="py-1">
                                        <li>
                                            <Link
                                                to="/movies"
                                                className="block px-4 py-2 text-gray-600 hover:bg-gray-100"
                                                role="menuitem">
                                                {t('movies.all', 'All movies')}
                                            </Link>
                                        </li>
                                        <li>
                                            <Link
                                                to="/movies/popular"
                                                className="block px-4 py-2 text-gray-600 hover:bg-gray-100"
                                                role="menuitem">
                                                {t('Popular')}
                                            </Link>
                                        </li>
                                        <li>
                                            <Link
                                                to="/movies/top-rated"
                                                className="block px-4 py-2 text-gray-600 hover:bg-gray-100"
                                                role="menuitem">
                                                {t('Top rated')}
                                            </Link>
                                        </li>
                                    </ul>
                                </div>
                            </li>

                            <li className="relative">
                                <button
                                    type="button"
                                    className="js-dropdown block text-gray-500 px-3 py-2 rounded-md hover:bg-gray-100 hover:text-gray-700 transition-colors duration-150 focus:outline-none focus:ring-2 focus:ring-indigo-300"
                                    aria-controls="tvMenu"
                                    aria-expanded="false">
                                    {t('tv_shows_ratings.title')}
                                </button>
                                <div
                                    id="tvMenu"
                                    className="hidden absolute left-0 mt-2 w-48 bg-white border rounded shadow-md z-20"
                                    role="menu"
                                    aria-labelledby="tvMenu">
                                    <ul className="py-1">
                                        <li>
                                            <Link
                                                to="/tv"
                                                className="block px-4 py-2 text-gray-600 hover:bg-gray-100"
                                                role="menuitem">
                                                {t('tv.all', 'All shows')}
                                            </Link>
                                        </li>
                                        <li>
                                            <Link
                                                to="/tv/popular"
                                                className="block px-4 py-2 text-gray-600 hover:bg-gray-100"
                                                role="menuitem">
                                                {t('tv.popular', 'Popular')}
                                            </Link>
                                        </li>
                                        <li>
                                            <Link
                                                to="/tv/top-rated"
                                                className="block px-4 py-2 text-gray-600 hover:bg-gray-100"
                                                role="menuitem">
                                                {t('tv.top_rated', 'Top rated')}
                                            </Link>
                                        </li>
                                    </ul>
                                </div>
                            </li>

                            <li>
                                <Link
                                    to="/login"
                                    className="block text-gray-500 px-3 py-2 rounded-md hover:bg-gray-100 hover:text-gray-700 transition-colors duration-150 focus:outline-none focus:ring-2 focus:ring-indigo-300">
                                    {t('login.title')}
                                </Link>
                            </li>
                        </ul>
                    </div>
                </nav>
            </div>
        </header>
    );
}
