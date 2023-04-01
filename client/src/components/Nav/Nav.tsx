import {AuthContext} from '../../context';
import {NavLink} from 'react-router-dom';
import {useContext, useState} from 'react';

export const Nav = () => {
  const { userData } = useContext(AuthContext);

  return (
      <nav>
        <ul>
          {
            userData?.status ? 
            <>
                <li><NavLink to='/'>Home</NavLink></li>
                <li><NavLink to='/dashboard'>Dashboard</NavLink></li>
                {
                  userData?.status == 'inactive'
                    ? ''
                    : <li><NavLink to='/offer/create'>Create an offer</NavLink></li>
                }
          
                <li><NavLink to='/logout'>Logout</NavLink></li>
            </> : 
            <>
              <li><NavLink to='/'>Home</NavLink></li>
              <li><NavLink to='/register'>Register</NavLink></li>
              <li><NavLink to='/login'>Login</NavLink></li>
            </>
         }
        </ul>
      </nav>
  );
};
