import type { Nav as NavInterface } from '../../types/';
import { NavLink } from 'react-router-dom';

export const NavItem = ({ path, text }: NavInterface) => {
  return (
    <li>
      <NavLink to={path}>{text}</NavLink>
    </li>
  );
};
