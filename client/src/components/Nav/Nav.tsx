import { Link } from 'react-router-dom';

export const Nav = () => {
    return (
        <nav className="nav">
            <ul>
                <li>
                    <Link to='/'>Home</Link>
                </li>

                <li>
                    <Link to='/register'>Register</Link>
                </li>

                <li>
                    <Link to='/login'>Login</Link>
                </li>

                <li>
                    <Link to='/offer/create'>Create an offer</Link>
                </li>
            </ul>
        </nav>
    );
}