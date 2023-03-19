import { Form } from '../../components';
import { registerForm } from '../../staticData';
import { FormEvent } from 'react';
import { RegisterForm as RFI } from '../../types';


export const Register = () => {
  const submitHandlerReg = async (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();

    const formData = new FormData(e.currentTarget);  

    const formFields = {
      firstName: formData.get('firstName'), 
      lastName: formData.get('lastName'), 
      email: formData.get('email'), 
      password: formData.get('password'), 
      passwordConfirm: formData.get('passwordConfirm'), 
      phoneNumber: formData.get('number'), 
    }

    fetch('http://localhost:8091/api/auth/signup', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(formFields)
    })
      .then((res) => console.log('ok : ', res))
      .catch((err) => console.error(err));
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
