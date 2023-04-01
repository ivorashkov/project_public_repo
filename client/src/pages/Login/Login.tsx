import { Form } from '../../components';
import { loginForm } from '../../staticData';
import { login } from '../../services';
import { FormEvent, useContext } from 'react';
import { useNavigate } from 'react-router-dom';
import { setLocalUserToken } from '../../hooks';
import { AuthContext } from '../../context';

export const Login = () => {
  const {loginFn} = useContext(AuthContext);
  const navigate = useNavigate();
  
  const submitHandlerReg = async (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();

    const formData = new FormData(e.currentTarget);  

    const formFields= {
      email: formData.get('email'), 
      password: formData.get('password'), 
    }

    login(formFields)
      .then(res => {
        setLocalUserToken(res.token)
        loginFn();
        navigate('/');
      });
  }
  
  return (
    <section>
      <Form title={loginForm.title} fields={loginForm.fields} buttonText={loginForm.buttonText} submitHandler={submitHandlerReg} />
    </section>
  );
};
