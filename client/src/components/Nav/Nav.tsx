import type { NavItems } from '../../types/';
import { NavItem } from './NavItem';

export const Nav = ({ links }: NavItems) => {
  return (
    <nav>
      <ul>
        {links.map((item) => (
          <NavItem path={item.path} text={item.text} />
        ))}
      </ul>
    </nav>
  );
};
