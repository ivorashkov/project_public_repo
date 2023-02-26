import { Shell } from "../Shell";
import { Nav } from "../Nav";
import './Header.scss';

export const Header = () => {
    return (
        <header className="header">
            <Shell>
                <Nav />
            </Shell>
        </header>
    );
}