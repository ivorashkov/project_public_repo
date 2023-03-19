import { Form } from '../../components';
import { registerForm } from '../../staticData';
import { FormEvent } from 'react';
import { fetchAPI } from '../../services/fetchAPI';

export const Register = () => {
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

    fetchAPI('api/auth/signup', 'POST', formFields, '/login');
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
