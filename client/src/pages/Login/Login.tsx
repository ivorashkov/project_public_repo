import { Form } from '../../components';
import { loginForm } from '../../staticData';
import { login } from '../../services';
import { FormEvent } from 'react';
import { useNavigate } from 'react-router-dom';
import { setLocalUserToken } from '../../hooks';

export const Login = () => {
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
        console.log('login ', res);

        setLocalUserToken(res.token)
        navigate('/');
      });
  }
  
  return (
    <section>
      <Form title={loginForm.title} fields={loginForm.fields} buttonText={loginForm.buttonText} submitHandler={submitHandlerReg} />
    </section>
  );
};
