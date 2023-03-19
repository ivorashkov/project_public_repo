import { Form } from '../../components';
import { registerForm } from '../../staticData';
import { FormEvent, useState } from 'react';
import { fetchAPI } from '../../services/fetchAPI';
import { useNavigate } from 'react-router-dom';

export const Register = () => {
  const navigate = useNavigate();
  const [errorClass, setErrorClass] = useState('');

  const submitHandlerReg = async (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();

    const formData = new FormData(e.currentTarget);  

    const formFields= {
      firstName: formData.get('firstName'), 
      lastName: formData.get('lastName'), 
      email: formData.get('email'), 
      password: formData.get('password'), 
      passwordConfirm: formData.get('passwordConfirm'), 
      phoneNumber: formData.get('number'), 
    }

    try {
      await fetchAPI('api/auth/signup', 'POST', formFields, '/login');  

      navigate('/login')
    } catch (error) {
      setErrorClass('error')
    }
  }

  return (
    <section className={errorClass}>
      <Form
        title={registerForm.title}
        fields={registerForm.fields}
        buttonText={registerForm.buttonText}
        submitHandler={submitHandlerReg}
      />
    </section>
  );
};
