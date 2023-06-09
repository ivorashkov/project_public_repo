import { Form } from '../../components';
import { registerForm } from '../../staticData';
import { FormEvent, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { login, register } from '../../services';

export const Register = () => {
  const navigate = useNavigate();

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

    register(formFields)
      .then(res => {
        navigate('/login');
      });
  }

  return (
    <section>
      <Form
        title={registerForm.title}
        fields={registerForm.fields}
        buttonText={registerForm.buttonText}
        submitHandler={submitHandlerReg}
      />
    </section>
  );
};
