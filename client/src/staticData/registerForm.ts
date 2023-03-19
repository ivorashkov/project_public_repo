export const registerForm = {
  title: 'Register',
  fields: [
    {
      type: 'text',
      label: 'first name',
      name: 'firstName',
      testId: 'firstName'
    },
    {
      type: 'text',
      label: 'last name',
      name: 'lastName',
      testId: 'lastName'
    },
    {
      type: 'email',
      label: 'email',
      name: 'email',
      testId: 'email'
    },
    {
      type: 'password',
      label: 'password',
      name: 'password',
      testId: 'password'
    },
    {
      type: 'password',
      label: 'repeat Password',
      name: 'passwordConfirm',
      testId: 'passwordConfirm'
    },
    {
      type: 'text',
      label: 'pehone nubmer',
      name: 'number',
      testId: 'number'
    }
  ],
  buttonText: 'submit'
};
