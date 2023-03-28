import { memo } from 'react';
import { Logo, Nav } from '../../components';

export const Header = memo(() => {
  console.log('render header')
  return (
    <header>
      <Logo />

      <Nav />
    </header>
  );
});
