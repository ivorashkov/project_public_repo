import { memo } from 'react';
import { Logo, Nav } from '../../components';
import { navItems, navItemsAuthenticated } from '../../staticData';

export const Header = memo(() => {
  return (
    <header>
      <Logo />

      <Nav links={navItems} />
    </header>
  );
});
