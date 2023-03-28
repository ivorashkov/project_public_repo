import {AuthContext} from '../../context';
import {NavLink} from 'react-router-dom';
import {useContext} from 'react';

export const Nav = () => {
  const { userData, token, login, logout } = useContext(AuthContext);

  console.log('render nav')
  console.log(userData)
  console.log(token)

  const authNav = (
    <>
      <li><NavLink to='/'>Home</NavLink></li>
      <li><NavLink to='/dashboard'>Dashboard</NavLink></li>
      {
        userData?.status == 'inactive'
          ? ''
          : <li><NavLink to='/offer/create'>Create an offer</NavLink></li>
      }

      <li><NavLink to='/logout'>Logout</NavLink></li>
  </>
  );

  const unauthNav = (
    <>
      <li><NavLink to='/'>Home</NavLink></li>
      <li><NavLink to='/register'>Register</NavLink></li>
      <li><NavLink to='/login'>Login</NavLink></li>
    </>
  )

  return (
      <nav>
        <ul>
          {userData ? authNav : unauthNav}
        </ul>
      </nav>
  );
};
