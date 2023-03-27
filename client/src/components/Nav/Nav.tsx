import type {NavItems} from '../../types/';
import {NavItem} from './NavItem';
import {AuthContext} from '../../context';
import {NavLink} from 'react-router-dom';
import {useContext, useEffect} from 'react';

export const Nav = ({links}: NavItems) => {
  const data = useContext(AuthContext);

  console.log(`data.userData`, data.userData)

  const authNav = <>
    <li><NavLink to='/'>Home</NavLink></li>
    <li><NavLink to='/dashboard'>Dashboard</NavLink></li>
    {
      data?.userData?.status == 'inactive'
        ? ''
        : <li><NavLink to='/offer/create'>Create an offer</NavLink></li>
    }

    <li><NavLink to='/logout'>Logout</NavLink></li>
  </>

  const unauthNav = <>
    <li><NavLink to='/'>Home</NavLink></li>
    <li><NavLink to='/register'>Register</NavLink></li>
    <li><NavLink to='/login'>Login</NavLink></li>
  </>

  return (
      <nav>
        <ul>
          {data.userData ? authNav : unauthNav}
        </ul>
      </nav>
  );
};
